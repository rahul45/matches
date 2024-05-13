package com.footballmatches.matches.services.impl;

import com.footballmatches.matches.entity.User;
import com.footballmatches.matches.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(String userName) {
        List<User> list =users.stream().filter(u->u.getUsername().equals(userName)).collect(Collectors.toList());
        return list.get(0);
    }
    List<User> users =new ArrayList<>();
    public UserServiceImpl(){
        users = Stream.of(
                new User(101,"rahul","admin","pass","admin")
                ,new User(102,"rahul1","admin1","pass1","user")
        ).collect(Collectors.toList());
    }
    @Override
    public List<User> getUsers() {
       return users;
    }
}
