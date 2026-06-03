package org.fmnf.findmynextfieldapi.controllers;

import org.fmnf.findmynextfieldapi.models.dto.LoginDetailsDTO;
import org.fmnf.findmynextfieldapi.models.dto.RegisterDetailsDTO;
import org.fmnf.findmynextfieldapi.models.inputs.GoogleLoginFormDetails;
import org.fmnf.findmynextfieldapi.models.inputs.LoginFormDetails;
import org.fmnf.findmynextfieldapi.models.inputs.RegisterFormDetails;
import org.fmnf.findmynextfieldapi.services.AuthService;
import org.fmnf.findmynextfieldapi.services.GoogleAuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final GoogleAuthService googleAuthService;

    public AuthController(AuthService authService, GoogleAuthService googleAuthService) {
        this.authService = authService;
        this.googleAuthService = googleAuthService;
    }

    @PostMapping("/register")
    public RegisterDetailsDTO register(@RequestBody RegisterFormDetails registerInfos) {
        return authService.register(registerInfos);
    }

    @PostMapping("/login")
    public LoginDetailsDTO login(@RequestBody LoginFormDetails loginInfos) {
        return authService.login(loginInfos);
    }

    @PostMapping("/google")
    public LoginDetailsDTO loginWithGoogle(@RequestBody GoogleLoginFormDetails loginInfos) {
        return googleAuthService.login(loginInfos);
    }

}
