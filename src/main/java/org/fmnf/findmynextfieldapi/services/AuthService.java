package org.fmnf.findmynextfieldapi.services;

import org.fmnf.findmynextfieldapi.models.dto.LoginDetailsDTO;
import org.fmnf.findmynextfieldapi.models.dto.RegisterDetailsDTO;
import org.fmnf.findmynextfieldapi.models.inputs.LoginFormDetails;
import org.fmnf.findmynextfieldapi.models.inputs.RegisterFormDetails;
import org.fmnf.findmynextfieldapi.models.user.Roles;
import org.fmnf.findmynextfieldapi.models.user.User;
import org.fmnf.findmynextfieldapi.repositories.UserRepository;
import org.fmnf.findmynextfieldapi.utils.InputValidator;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public RegisterDetailsDTO register(RegisterFormDetails registerInfos) {
        if (!InputValidator.validateRegisterFormDetails(registerInfos)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid register informations");
        }

        String email = registerInfos.getEmail().trim().toLowerCase();
        String username = registerInfos.getUsername().trim();

        if (userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already used");
        }

        if (userRepository.existsByName(username)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already used");
        }

        User user = new User(
                null,
                username,
                email,
                passwordEncoder.encode(registerInfos.getPassword()),
                Roles.USER
        );

        User savedUser = userRepository.save(user);

        return new RegisterDetailsDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole(),
                jwtService.generateToken(savedUser)
        );
    }

    public LoginDetailsDTO login(LoginFormDetails loginInfos) {
        if (!InputValidator.validateLoginFormDetails(loginInfos)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid login informations");
        }

        String identificationName = loginInfos.getIdentificationName().trim();

        User user = userRepository.findByEmail(identificationName.toLowerCase())
                .or(() -> userRepository.findByName(identificationName))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        if (user.getPassword() == null || !passwordEncoder.matches(loginInfos.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        return new LoginDetailsDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                jwtService.generateToken(user)
        );
    }
}
