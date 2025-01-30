package com.weclear.backend.controller;


import com.weclear.backend.model.User;
import com.weclear.backend.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.weclear.backend.exceptions.UserAlreadyExistsException;

@RestController
@RequestMapping("/api1/auth")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user, HttpSession session) {
        
        try{
            User newUser = userService.signup(user);
            session.setAttribute("user", newUser);  // Save user in HTTP session
            return ResponseEntity.ok(newUser);
        }
        catch(UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request, @RequestBody User user, HttpSession session) {
    try {
        
        User loggedInUser = userService.login(user.getPhno(), user.getPassword());
        return ResponseEntity.ok(loggedInUser);

    } catch (Exception e) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("WWW-Authenticate", "Basic realm=\"Access to the site\"");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .headers(headers)
                             .body("Login failed: " + e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/check-user")
    public ResponseEntity<Boolean> checkUserExistence(@RequestBody Map<String, String> request) {

        try{
            String phno = request.get("phno");
            boolean userExists = userService.checkUserExistence(phno);
            return ResponseEntity.ok(userExists);
        }
        catch(Exception e){

            System.out.println("Error while checking user existence: "+ e);
            // Return an internal server error status with a meaningful message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
      
    @PutMapping("/updateUser/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User user) {
        System.out.println("Updated User: " + user); // Log incoming user data
    
        return userService.updateUser(userId, user);
    }

}