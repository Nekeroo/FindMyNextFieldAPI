package org.fmnf.findmynextfieldapi.models.inputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterFormDetails {

    private String email;
    private String password;
    private String username;

}
