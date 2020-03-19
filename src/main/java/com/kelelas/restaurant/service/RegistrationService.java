package com.kelelas.restaurant.service;

import com.kelelas.restaurant.entity.User;
import com.kelelas.restaurant.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegistrationService {
    private final UserRepository userRepository;
    @Autowired
    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean saveNewUser (User user){
        try {
            userRepository.save(user);
            return true;
        } catch (Exception ex) {
            log.info("{Почтовый адрес уже существует}");
            return false;
        }
    }
}
