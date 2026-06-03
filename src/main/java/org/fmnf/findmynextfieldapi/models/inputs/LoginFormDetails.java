package org.fmnf.findmynextfieldapi.models.inputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginFormDetails {

    private String identificationName;
    private String password;

}
