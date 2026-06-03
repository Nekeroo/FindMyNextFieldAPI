package org.fmnf.findmynextfieldapi.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.fmnf.findmynextfieldapi.models.dto.LoginDetailsDTO;
import org.fmnf.findmynextfieldapi.models.inputs.GoogleLoginFormDetails;
import org.fmnf.findmynextfieldapi.models.user.Roles;
import org.fmnf.findmynextfieldapi.models.user.User;
import org.fmnf.findmynextfieldapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Locale;

@Service
public class GoogleAuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final GoogleIdTokenVerifier verifier;
    private final String googleClientId;

    public GoogleAuthService(
            UserRepository userRepository,
            JwtService jwtService,
            @Value("${google.client-id:}") String googleClientId
    ) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.googleClientId = googleClientId;
        this.verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance())
                .setAudience(List.of(googleClientId))
                .build();
    }

    public LoginDetailsDTO login(GoogleLoginFormDetails loginInfos) {
        if (googleClientId == null || googleClientId.isBlank()) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Google authentication is not configured");
        }

        if (loginInfos == null || loginInfos.getIdToken() == null || loginInfos.getIdToken().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Google id token is required");
        }

        GoogleIdToken.Payload payload = verifyToken(loginInfos.getIdToken().trim());
        if (!Boolean.TRUE.equals(payload.getEmailVerified())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Google email is not verified");
        }

        String email = payload.getEmail().toLowerCase(Locale.ROOT);
        String name = payload.get("name") instanceof String googleName && !googleName.isBlank()
                ? googleName
                : email.substring(0, email.indexOf("@"));

        User user = userRepository.findByEmail(email)
                .orElseGet(() -> userRepository.save(new User(
                        null,
                        generateAvailableUsername(name),
                        email,
                        null,
                        Roles.USER
                )));

        return new LoginDetailsDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                jwtService.generateToken(user)
        );
    }

    private GoogleIdToken.Payload verifyToken(String idToken) {
        try {
            GoogleIdToken verifiedToken = verifier.verify(idToken);
            if (verifiedToken == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Google id token");
            }

            return verifiedToken.getPayload();
        } catch (GeneralSecurityException | IOException exception) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unable to verify Google id token", exception);
        }
    }

    private String generateAvailableUsername(String name) {
        String baseUsername = name.trim()
                .replaceAll("[^a-zA-Z0-9_-]", "_")
                .replaceAll("_+", "_");

        if (baseUsername.isBlank()) {
            baseUsername = "google_user";
        }

        if (baseUsername.length() > 45) {
            baseUsername = baseUsername.substring(0, 45);
        }

        String username = baseUsername;
        int suffix = 1;
        while (userRepository.existsByName(username)) {
            username = baseUsername + "_" + suffix;
            suffix++;
        }

        return username;
    }
}
