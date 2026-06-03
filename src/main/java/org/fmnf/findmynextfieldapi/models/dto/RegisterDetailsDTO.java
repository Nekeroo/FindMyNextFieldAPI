package org.fmnf.findmynextfieldapi.models.dto;

import org.fmnf.findmynextfieldapi.models.user.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterDetailsDTO {

    private Long id;
    private String username;
    private String email;
    private Roles role;
    private String token;
}
