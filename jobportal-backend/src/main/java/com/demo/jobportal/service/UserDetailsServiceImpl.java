package com.demo.jobportal.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.jobportal.dao.UserDao;
import com.demo.jobportal.dao.entity.UsersEntity;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;

        

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UsersEntity user = userDao.findByEmail(email)
    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    // return new UserDetailsImpl(user.getEmail(), user.getPassword() , user.getRole());
    return new UserDetailsImpl(user.getEmail(), user.getPassword() , user.getRole());
    }
    
}
