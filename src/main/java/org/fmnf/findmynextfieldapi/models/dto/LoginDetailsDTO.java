package org.fmnf.findmynextfieldapi.models.dto;

import org.fmnf.findmynextfieldapi.models.user.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginDetailsDTO {

    private Long id;
    private String username;
    private String email;
    private Roles role;
    private String token;
}
