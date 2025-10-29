package com.demo.jobportal.controller;

import com.demo.jobportal.dao.entity.UsersEntity;
import com.demo.jobportal.pojos.LoginRequest;
import com.demo.jobportal.pojos.LoginResponse;
import com.demo.jobportal.pojos.UserPojo;
import com.demo.jobportal.service.JwtService;
import com.demo.jobportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService ; 

    @GetMapping("/")
    public List<UsersEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public UsersEntity register(@RequestBody UserPojo userPojo) {
        return userService.createUser(userPojo);
    }

@PostMapping("/login")
public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginData) {
    try {
        String email = loginData.get("email");
        String password = loginData.get("password");

        Map<String, Object> result = userService.login(email, password);
        return ResponseEntity.ok(result); // ✅ send back token, role, userId, etc.

    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", e.getMessage()));
    }
}







    @PutMapping("/{id}")
    public UsersEntity updateUser(@PathVariable Long id, @RequestBody UserPojo userPojo) {
        return userService.updateUser(id, userPojo);
    }


    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }


    
}
