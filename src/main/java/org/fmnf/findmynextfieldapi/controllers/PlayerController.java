package org.fmnf.findmynextfieldapi.controllers;

import org.fmnf.findmynextfieldapi.models.dto.UserDTO;
import org.fmnf.findmynextfieldapi.models.dto.UserRegistrationCountDTO;
import org.fmnf.findmynextfieldapi.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final UserService userService;

    public PlayerController(UserService us) {
        userService = us;
    }

    /*
        Retrieve all players
     */

    @GetMapping("/all")
    public List<UserDTO> retrieveAllPlayers() {
        return userService.retrieveAllPlayers();
    }

    @GetMapping("/top")
    public List<UserRegistrationCountDTO> rankPlayersFromRegistrations() {
        return userService.retrievePlayersAndRankThemFromThereRegistrations();
    }

}
