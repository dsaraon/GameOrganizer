package com.arora.account.service;

import java.security.Principal;

import com.arora.account.model.User;

public interface UserService {
    void save(User user);
    void updateProfile(User user);
    void updatePassword(User user, Principal principal);
    void deleteProfile(Principal principal);
    User findByUsername(String username);
    User findByUseremail(String useremail);
    
}
