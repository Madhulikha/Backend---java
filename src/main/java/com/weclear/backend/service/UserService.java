package com.weclear.backend.service;

import com.weclear.backend.exceptions.UserAlreadyExistsException;
import com.weclear.backend.exceptions.InvalidCredentialsException;
import com.weclear.backend.model.Role;
import com.weclear.backend.model.User;
import com.weclear.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public User signup(User user) {
        if (userRepository.findByPhno(user.getPhno()).isPresent()) {
            throw new UserAlreadyExistsException("Username already exists!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER); // Default to USER role
        return userRepository.save(user);
    }

    public User login(String phno, String password) {
        Optional<User> user = userRepository.findByPhno(phno);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user.get();
        } else {
            throw new InvalidCredentialsException("Invalid username or password!");
        }
    }

    public boolean checkUserExistence(String phno) {
        return userRepository.findByPhno(phno).isPresent();
    }

    public User updateUser(Long id, User updatedUser){

        User existingUser = userRepository.findById(id).get();
        
        existingUser.setPhno(updatedUser.getPhno());
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        
        return userRepository.save(existingUser);


    }
}
