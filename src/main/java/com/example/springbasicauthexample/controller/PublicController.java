package com.example.springbasicauthexample.controller;

import com.example.springbasicauthexample.entity.Role;
import com.example.springbasicauthexample.entity.RoleType;
import com.example.springbasicauthexample.entity.User;
import com.example.springbasicauthexample.model.UserDto;
import com.example.springbasicauthexample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public")
@RequiredArgsConstructor
public class PublicController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<String> getPublic() {
        return ResponseEntity.ok("Called public method");
    }

    @PostMapping("/account")
    public ResponseEntity<UserDto> createUserAccount(@RequestBody UserDto userDto, @RequestParam RoleType roleType) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createAccount(userDto, roleType));
    }

    private UserDto createAccount(UserDto userDto, RoleType roleType) {
        final var user = new User();
        user.setPassword(userDto.getPassword());
        user.setUserName(userDto.getUserName());

        User createdUser = userService.createNewAccount(user, Role.from(roleType));

        return UserDto.builder()
                .userName(createdUser.getUserName())
                .password(createdUser.getPassword())
                .build();
    }

}
