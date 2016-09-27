package com.arora.account.service;

import com.arora.account.model.User;
import com.arora.account.repository.RoleRepository;
import com.arora.account.repository.UserRepository;
import com.arora.player.model.Player;
import com.arora.player.repository.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PlayerRepository playerRepository;    
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);

        Player player = new Player();
        player.setUserid(user.getId());
        playerRepository.save(player);
    }
    
    
    @Override
    public void updateProfile(User user) {
    	
    	User user2 = userRepository.findByUsername(user.getUsername());
    	
    	user2.setUseremail(user.getUseremail());
    	user2.setBio(user.getBio());
    	user2.setContact(user.getContact());
    	user2.setLocation(user.getLocation());
    	user2.setName(user.getName());
    	user2.setPicture(user.getPicture());
    	user2.setCity(user.getCity());
    	//user2.setUsertype(user.getUsertype());
    	
    	userRepository.saveAndFlush(user2);
           
    }    

    @Override
    public void updatePassword(User user, Principal principal) {
    	
    	User user2 = userRepository.findByUsername(principal.getName());
    	
    	user2.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));    	
    	userRepository.saveAndFlush(user2);
           
    } 
    
    @Override
    public void deleteProfile(Principal principal) {
    	
    	User user = userRepository.findByUsername(principal.getName());	
    	userRepository.delete(user);
           
    }     
    
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    @Override
    public User findByUseremail(String useremail) {
        return userRepository.findByUseremail(useremail);
    }
}
