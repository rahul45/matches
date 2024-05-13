package com.footballmatches.matches.services;

import com.footballmatches.matches.entity.User;

import java.util.List;

public interface UserService {
        User getUser(String userName);
    List<User> getUsers();
}
