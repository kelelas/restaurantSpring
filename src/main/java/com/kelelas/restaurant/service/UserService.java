package com.kelelas.restaurant.service;

import com.kelelas.restaurant.entity.User;
import com.kelelas.restaurant.exception.DBException;
import com.kelelas.restaurant.repository.UserRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user with email " + email + " not found!"));
    }

    public void save(User user){
        try {
            userRepository.save(user);
        }catch (Exception e){
            throw new DBException(e);
        }
    }
}
