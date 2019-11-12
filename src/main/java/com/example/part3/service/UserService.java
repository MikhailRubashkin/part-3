package com.example.part3.service;

import com.example.part3.domain.Role;
import com.example.part3.domain.User;
import com.example.part3.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class UserService implements UserDetailsService {

    private static Logger log = Logger.getLogger("UserService");

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }



    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public boolean addUser( User user) {
        log.info("Saving user=" + user);
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Encoded password: " + passwordEncoder);
        userRepo.save(user);

        return true;
    }

}
