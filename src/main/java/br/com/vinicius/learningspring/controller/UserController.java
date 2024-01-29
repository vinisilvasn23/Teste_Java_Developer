package br.com.vinicius.learningspring.controller;

import br.com.vinicius.learningspring.dto.CreateDepositDto;
import br.com.vinicius.learningspring.dto.UserDto;
import br.com.vinicius.learningspring.model.User;
import br.com.vinicius.learningspring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody final UserDto userdata) {
        final User createdUser = userService.createUser(userdata);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> readUsers() {
        final List<User> allUsers = userService.readUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> retrieveUser(@PathVariable final String id) {
        final User user = userService.retrieveUser(Long.parseLong(id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> partialUpdateUser(
            @PathVariable long id,
            @RequestBody UserDto updatedUserDto
    ) {
        User updated = userService.partialUpdateUser(updatedUserDto, id);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable final String id) {
        userService.deleteUser(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<User> createDeposit(@Valid @RequestBody final CreateDepositDto depositData,
            @PathVariable final String id) {
        final User depositedUser = userService.createDeposit(depositData, Long.parseLong(id));
        return new ResponseEntity<>(depositedUser, HttpStatus.CREATED);
    }
}