package org.fmnf.findmynextfieldapi.utils;

import org.fmnf.findmynextfieldapi.models.inputs.LoginFormDetails;
import org.fmnf.findmynextfieldapi.models.inputs.RegisterFormDetails;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class InputValidator {

    private static final String REGEX_EMAIL =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";


    public static boolean validateRegisterFormDetails(RegisterFormDetails registerFormDetails) {
        return registerFormDetails != null
                && verifyInputNotBlank(registerFormDetails.getUsername())
                && verifyInputEmailValid(registerFormDetails.getEmail())
                && verifyPasswordValid(registerFormDetails.getPassword());
    }

    public static boolean validateLoginFormDetails(LoginFormDetails loginFormDetails) {
        return loginFormDetails != null
                && verifyInputNotBlank(loginFormDetails.getIdentificationName())
                && verifyInputNotBlank(loginFormDetails.getPassword());
    }

    private static boolean verifyInputEmailValid(String email) {
        return verifyInputNotBlank(email) && Pattern.matches(REGEX_EMAIL, email);
    }

    private static boolean verifyPasswordValid(String password) {
        return verifyInputNotBlank(password) && password.length() >= 10;
    }

    private static boolean verifyInputNotBlank(String input) {
        return input != null && !input.isBlank();
    }


}
