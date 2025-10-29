package com.demo.jobportal.pojos;

import com.demo.jobportal.dao.entity.UsersEntity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPojo {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private Role role;
}
