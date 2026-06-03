package org.fmnf.findmynextfieldapi.utils;

import org.fmnf.findmynextfieldapi.models.dto.UserDTO;
import org.fmnf.findmynextfieldapi.models.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDTO mapUserToDTO(User user) {
        return new UserDTO(user.getName(), user.getRole().name());
    }

}
