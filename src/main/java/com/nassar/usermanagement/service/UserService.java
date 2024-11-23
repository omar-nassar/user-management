package com.nassar.usermanagement.service;

import com.nassar.usermanagement.exception.BusinessException;
import com.nassar.usermanagement.model.User;
import com.nassar.usermanagement.model.UserRequest;
import com.nassar.usermanagement.model.UserResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    List<User> listOfUsers = new ArrayList<>();

    public UserService() {
        User user1 = new User(1, "Ahmad", List.of("java", "sql", "linux"));
        User user2 = new User(2, "Adel", List.of("git", "docker", "html"));

        listOfUsers.add(user1);
        listOfUsers.add(user2);
    }

    public User findUser(int id) {
        return listOfUsers.stream()
                            .filter(u -> u.getId() == id)
                            .findFirst()
                            .get();
    }

    public UserResponse createNewUser(UserRequest userRequest) {
        doValidations(userRequest);
        User user = new User((int) System.currentTimeMillis(),
            userRequest.getName(),
            userRequest.getSkills());

        listOfUsers.add(user);

        return new UserResponse(user.getId(), user.getName(), user.getSkills());
    }

    private void doValidations(UserRequest userRequest) {
        if(userRequest.getSkills().size() < 3) {
            throw new BusinessException("Minimum 3 skills needed");
        }
    }

    public void deleteUser(int id) {
        listOfUsers.remove(id);
    }

    public void updateUser(int id, UserRequest user) {
        User foundUser = listOfUsers.stream()
            .filter(u -> u.getId() == id)
            .findFirst()
            .get();

        foundUser.setName(user.getName());
    }

    public List<User> listAllUsers() {
        return listOfUsers;
    }

    public List<String> getSkills(int id) {
        return listOfUsers.stream()
                            .filter(u -> u.getId() == id)
                            .findFirst()
                            .get()
                            .getSkills();
    }
}