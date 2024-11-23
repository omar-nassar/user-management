package com.nassar.usermanagement.controller;

import com.nassar.usermanagement.model.ErrorResponse;
import com.nassar.usermanagement.model.User;
import com.nassar.usermanagement.model.UserRequest;
import com.nassar.usermanagement.model.UserResponse;
import com.nassar.usermanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping//(headers = {"api-key", "api-secret"})
    @Operation(
        summary = "To retrieve all users defined",
        description = "To retrieve all users defined"
    )
    public List<UserResponse> getUser() {
        return userService
                    .listAllUsers()
                    .stream()
                    .map(u -> new UserResponse(u.getId(), u.getName(), u.getSkills()))
                    .toList();
    }

    @GetMapping
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "User retrieved successfully",
                content = @Content(schema = @Schema(implementation = UserResponse.class))
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Invalid user id passed",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
        }
    )
    public UserResponse getUserById(@PathVariable("id") int userId) {
        User user = userService.findUser(userId);
        return new UserResponse(user.getId(), user.getName(), user.getSkills());
    }

    @PostMapping
    @Operation(
        summary = "To create a user based on the provided model"
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "User created successfully",
                content = @Content(schema = @Schema(implementation = UserResponse.class))
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = @Content(schema = @Schema(hidden = true))
            )
        }
    )
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createNewUser(@RequestBody @Valid UserRequest userRequest) {
        return userService.createNewUser(userRequest);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @PutMapping("{id}")
    public void updateUser(@PathVariable int id, @RequestBody UserRequest user) {
        userService.updateUser(id, user);
    }

    @GetMapping("{id}/skills")
    public List<String> getSkills(@PathVariable int id) {
        return userService.getSkills(id);
    }

    /***
     * POST ../user-management/users/{operation-type}
         user {id, name, skills}
             operation-type = 1 -> create
                receive {name, skills}
                validation {name, skills}
            operation-type = 2 -> delete
                receive {id}
                no validation on request body
            operation-type = 3 -> get users
                receive {id}
                no validation on request body
            operation-type = 4 -> update users
                receive {skills}
                validation {skills}
        userService class
        logic for all variables
            1 -> ....
            2 -> ...
            3 -> ....
     ***/
}