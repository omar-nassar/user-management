package com.nassar.usermanagement.service;

import com.nassar.usermanagement.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    List<User> listOfUsers = new ArrayList<>();

    public UserService() {
        User user1 = new User(1, "Ahmad");
        User user2 = new User(2, "Adel");

        listOfUsers.add(user1);
        listOfUsers.add(user2);
    }

    public User findUser(int id) {
        return listOfUsers.stream()
                            .filter(u -> u.getId() == id)
                            .findFirst()
                            .get();
    }

    public void createNewUser(User newUser) {
        listOfUsers.add(newUser);
    }

    public void deleteUser(int id) {
        listOfUsers.remove(id);
    }

    public void updateUser(User user) {
        User foundUser = listOfUsers.stream()
            .filter(u -> u.getId() == user.getId())
            .findFirst()
            .get();

        foundUser.setName(user.getName());
    }

    public List<User> listAllUsers() {
        return listOfUsers;
    }
}