package com.RedRobot.Daniel.FewBucks.services;

import com.RedRobot.Daniel.FewBucks.entities.Users;
import com.RedRobot.Daniel.FewBucks.repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    UsersRepo usersRepo;

    public List<Users> getUsers(){
        return usersRepo.findAll();
    }
}
