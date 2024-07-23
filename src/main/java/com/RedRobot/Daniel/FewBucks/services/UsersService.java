package com.RedRobot.Daniel.FewBucks.services;

import com.RedRobot.Daniel.FewBucks.entities.Users;
import com.RedRobot.Daniel.FewBucks.repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /* Setting the role as a default to a USER, unless specified differently in the JSON body.*/
    public Users registerUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null || user.getRole().isEmpty()){
            user.setRole("USER");
        } else if (!user.getRole().equals("ADMIN")){
            throw new IllegalArgumentException("Invalid role");
        }
        return usersRepo.save(user);
    }

    public List<Users> getUsers(){
        return usersRepo.findAll();
    }
    public Optional<Users> findUser(String search){
        return usersRepo.findByUserName(search);
    }
}
