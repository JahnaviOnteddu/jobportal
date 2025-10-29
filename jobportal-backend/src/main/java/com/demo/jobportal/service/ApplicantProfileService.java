package com.demo.jobportal.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.jobportal.dao.ApplicantProfileDao;
import com.demo.jobportal.dao.UserDao;
import com.demo.jobportal.dao.entity.ApplicantProfileEntity;
import com.demo.jobportal.dao.entity.UsersEntity;
import com.demo.jobportal.pojos.ApplicantProfilePojo;

@Service
public class ApplicantProfileService {

    @Autowired
    private ApplicantProfileDao applicantProfileDao;

    @Autowired
    private UserDao userDAO;

    // CREATE
    public ApplicantProfileEntity createProfile(ApplicantProfilePojo pojo) {
        UsersEntity user = userDAO.findById(pojo.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() != UsersEntity.Role.APPLICANT) {
        throw new RuntimeException("Only applicants can have a profile");
        }



        ApplicantProfileEntity profile = new ApplicantProfileEntity();
        profile.setApplicantId(pojo.getApplicantId());
        profile.setExperienceYears(pojo.getExperienceYears());
        profile.setEducation(pojo.getEducation());
        profile.setSkills(pojo.getSkills());
        profile.setAboutYou(pojo.getAboutYou());
        profile.setProjectUrl(pojo.getProjectUrl());
        profile.setUser(user);

        return applicantProfileDao.save(profile);
    }

    // READ all
    public List<ApplicantProfileEntity> getAllProfiles() {
        return applicantProfileDao.findAll();
    }

    // READ by ID
    public ApplicantProfileEntity getProfileById(Long applicantId) {
        return applicantProfileDao.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant profile not found"));
    }

    // UPDATE
    public ApplicantProfileEntity updateProfile(Long applicantId, ApplicantProfilePojo pojo) {
        ApplicantProfileEntity profile = applicantProfileDao.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        profile.setExperienceYears(pojo.getExperienceYears());
        profile.setEducation(pojo.getEducation());
        profile.setSkills(pojo.getSkills());
        profile.setAboutYou(pojo.getAboutYou());
        profile.setProjectUrl(pojo.getProjectUrl());

        return applicantProfileDao.save(profile);
    }

    // DELETE
    public void deleteProfile(Long applicantId) {
        applicantProfileDao.deleteById(applicantId);
    }

    
}
