package com.korit.demo.service;

import com.korit.demo.entity.User;
import com.korit.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private User findUserByUsername1(String username) {
        List<User> foundUsers = userRepository.findAll();
        for(int i = 0; i < foundUsers.size(); i++) {
            User user = foundUsers.get(i);
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private User findUserByUsername2(String username) {
        List<User> foundUsers = userRepository.findAll();
        for(User user : foundUsers) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private User findUserByUsername3(String username) {
        List<User> foundUsers = userRepository.findAll();
        AtomicReference<User> foundUser = new AtomicReference<>();
        foundUsers.forEach(user -> {
            if(user.getUsername().equals(username)) {
                    foundUser.set(user);
            };
        });
        return foundUser.get();
    }

    private User findUserByUsername4(String username) {
        return userRepository.findAll().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .get();
    }

    private User findUserByUsername5(String username) {
        List<User> foundUsers = userRepository.findAll();
        Iterator<User> iterator = foundUsers.iterator();
        while(iterator.hasNext()) {
            User user = iterator.next();
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User register(User user) {
        User foundUser = findUserByUsername1(user.getUsername());
        return userRepository.save(user);
    }

    public List<User> findUsersAll() {
        return userRepository.findAll();
    }
}
