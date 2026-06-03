package org.fmnf.findmynextfieldapi.services;

import org.fmnf.findmynextfieldapi.models.dto.UserDTO;
import org.fmnf.findmynextfieldapi.models.dto.UserRegistrationCountDTO;
import org.fmnf.findmynextfieldapi.models.user.UserRegistrationCount;
import org.fmnf.findmynextfieldapi.models.user.User;
import org.fmnf.findmynextfieldapi.repositories.UserRepository;
import org.fmnf.findmynextfieldapi.utils.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository ur) {
        userRepository = ur;
    }

    public List<UserDTO> retrieveAllPlayers() {
        List<User> users = userRepository.findAll();

        return users.stream().map((user -> new UserDTO(user.getName(), user.getRole().name()))).toList();
    }

    public List<UserRegistrationCountDTO> retrievePlayersAndRankThemFromThereRegistrations() {
        return userRepository.findUsersWithRegistrationCount()
                .stream()
                .map(dto ->
                        new UserRegistrationCountDTO(UserMapper.mapUserToDTO(dto.user()), dto.registrationCount()))
                .toList();
    };

}
