package com.demo.jobportal.service;

import com.demo.jobportal.dao.ApplicantProfileDao;
import com.demo.jobportal.dao.RecruiterProfileDao;
import com.demo.jobportal.dao.UserDao;
import com.demo.jobportal.dao.entity.ApplicantProfileEntity;
import com.demo.jobportal.dao.entity.RecruiterProfileEntity;
import com.demo.jobportal.dao.entity.UsersEntity;
import com.demo.jobportal.dao.entity.UsersEntity.ApprovalStatus;
import com.demo.jobportal.pojos.UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserDao userDAO;

    @Autowired 
    private JwtService jwtService ; 


    @Autowired
    private ApplicantProfileDao applicantProfileDao ; 


    @Autowired
    private RecruiterProfileDao recruiterProfileDao ; 

    // ✅ Register user
    public UsersEntity createUser(UserPojo userPojo) {
        if (userDAO.existsByEmail(userPojo.getEmail()))
            throw new RuntimeException("Email already exists");
        if (userDAO.existsByPhoneNumber(userPojo.getPhoneNumber()))
            throw new RuntimeException("Phone number already exists");

        UsersEntity user = new UsersEntity();
        user.setFirstName(userPojo.getFirstName());
        user.setLastName(userPojo.getLastName());
        user.setEmail(userPojo.getEmail());
        user.setPhoneNumber(userPojo.getPhoneNumber());
        user.setPassword(userPojo.getPassword()); // Encode in real app
        user.setRole(userPojo.getRole());

        if (userPojo.getRole() == UsersEntity.Role.RECRUITER) {
            user.setApprovalStatus(ApprovalStatus.WAITING);
        } else {
            user.setApprovalStatus(ApprovalStatus.APPROVED);
        }

        return userDAO.save(user);
    }

    // ✅ Login user
 public Map<String, Object> login(String email, String password) {
    UsersEntity user = userDAO.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!password.equals(user.getPassword())) {
        throw new RuntimeException("Invalid credentials");
    }

    Map<String, Object> response = new HashMap<>();

    // Recruiter approval handling
    if (user.getRole() == UsersEntity.Role.RECRUITER) {
        switch (user.getApprovalStatus()) {
            case WAITING -> {
                response.put("message", "Recruiter awaiting admin approval");
                response.put("status", "PENDING_APPROVAL");
                response.put("userId", user.getUserId());
                response.put("role", user.getRole().toString());
                return response;
            }
            case REJECTED -> {
                response.put("message", "Your recruiter registration was rejected");
                response.put("status", "REJECTED");
                response.put("userId", user.getUserId());
                response.put("role", user.getRole().toString());
                return response;
            }
            case APPROVED -> {
                // continue
            }
        }
    }

    // ✅ Generate JWT token
    String token = jwtService.generateToken(user.getEmail(), user.getRole().toString());

    response.put("message", "Login successful");
    response.put("status", "SUCCESS");
    response.put("token", token);
    response.put("userId", user.getUserId());
    response.put("role", user.getRole().toString());

    // ✅ Add applicantId or recruiterId dynamically
    if (user.getRole() == UsersEntity.Role.APPLICANT) {
        ApplicantProfileEntity applicant = applicantProfileDao.findByUser(user);
        if (applicant != null) {
            response.put("applicantId", applicant.getApplicantId());
        }
    } else if (user.getRole() == UsersEntity.Role.RECRUITER) {
        RecruiterProfileEntity recruiter = recruiterProfileDao.findByUser(user);
        if (recruiter != null) {
            response.put("recruiterId", recruiter.getRecruiterId());
        }
    }

    return response;
}




    // ✅ Get all users
    public List<UsersEntity> getAllUsers() {
        return userDAO.findAll();
    }

    // ✅ Update user
    public UsersEntity updateUser(Long id, UserPojo userPojo) {
        UsersEntity user = userDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(userPojo.getFirstName());
        user.setLastName(userPojo.getLastName());
        user.setPhoneNumber(userPojo.getPhoneNumber());
        user.setPassword(userPojo.getPassword()); // Encode in real app

        return userDAO.save(user);
    }

    // ✅ Delete user
    public String deleteUser(Long id) {
        UsersEntity user = userDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userDAO.delete(user);
        return "User deleted successfully";
    }
}
