package com.demo.jobportal.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.jobportal.dao.DigitalResumeDao;
import com.demo.jobportal.dao.UserDao;
import com.demo.jobportal.dao.entity.DigitalResumeEntity;
import com.demo.jobportal.dao.entity.UsersEntity;
import com.demo.jobportal.pojos.DigitalResumePojo;

@Service
public class DigitalResumeService {

    @Autowired
    private DigitalResumeDao digitalResumeDao;

    @Autowired
    private UserDao usersDao;

    public DigitalResumeEntity createOrUpdateResume(DigitalResumePojo resumeRequest) {
        UsersEntity user = usersDao.findById(resumeRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        DigitalResumeEntity resume = digitalResumeDao.findByUserUserId(resumeRequest.getUserId())
                .orElse(new DigitalResumeEntity());

        resume.setUser(user);
        resume.setCareerObjective(resumeRequest.getCareerObjective());
        resume.setEducation(resumeRequest.getEducation());
        resume.setWorkExperience(resumeRequest.getWorkExperience());
        resume.setCourses(resumeRequest.getCourses());
        resume.setExtracurricular(resumeRequest.getExtracurricular());
        resume.setSkills(resumeRequest.getSkills());
        resume.setCertifications(resumeRequest.getCertifications());
        resume.setProjects(resumeRequest.getProjects());

        return digitalResumeDao.save(resume);
    }

    public DigitalResumeEntity getResumeByUser(Long userId) {
        return digitalResumeDao.findByUserUserId(userId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));
    }

    public void deleteResume(Long userId) {
        DigitalResumeEntity resume = digitalResumeDao.findByUserUserId(userId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));
        digitalResumeDao.delete(resume);
    }
}

