package org.fmnf.findmynextfieldapi.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
public class AuthController {

    /*
        Login
        Register
        Forgotten password ?
     */

    @PostMapping
    public RegisterDetailsDTO register(@RequestBody RegisterFormDetails registerInfos) {

    }
  //  https://medium.com/@sallu-salman/implementing-sign-in-with-google-in-spring-boot-application-5f05a34905a8

}
