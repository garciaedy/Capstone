package com.capstone.notxapp.service;

import com.capstone.notxapp.dto.RegistrationDto;
import com.capstone.notxapp.entity.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);
}
