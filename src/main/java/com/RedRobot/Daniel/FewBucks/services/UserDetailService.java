package com.RedRobot.Daniel.FewBucks.services;

import com.RedRobot.Daniel.FewBucks.entities.Users;
import com.RedRobot.Daniel.FewBucks.repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;

    /*The purpose of this method is to load user-specific data from the database during the authentication process.*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*Fetches the user from the database, this returns an 'Optional<Users>', which may or may not contain a user*/
        Optional<Users> user = usersRepo.findByUserName(username);
        if (user.isPresent()) {
            /*Extracts 'User' object from Optional<Users> user*/
            Users userObject = user.get();
            return User.builder()
                    .username(userObject.getUserName())
                    .password(userObject.getPassword())
                    .roles(userObject.getRole())
                    .build();

        } else {
            throw new UsernameNotFoundException("Username not found");
        }
    }
}
