package org.fmnf.findmynextfieldapi.utils;

import org.fmnf.findmynextfieldapi.models.register.RegisterFormDetails;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class InputValidator {

    private static final String REGEX_EMAIL =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";


    public static boolean validateRegisterFormDetails(RegisterFormDetails registerFormDetails) {
        return registerFormDetails.getPassword().length() >= 10 || !verifyInputEmailValid(registerFormDetails.getEmail());
    }

    private static boolean verifyInputEmailValid(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }


}
