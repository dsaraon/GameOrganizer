package com.arora.account.validator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.arora.account.model.User;
import com.arora.account.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "useremail", "NotEmpty");
        if (!isValidEmailAddress(user.getUseremail())) {
        	errors.rejectValue("useremail", "Diff.userForm.emailValid");
        }
                
        if (userService.findByUseremail(user.getUseremail()) != null) {
            errors.rejectValue("useremail", "Duplicate.userForm.useremail");
        }        
        
              
    }

    public void validateUpdateProfile(Object o, Errors errors, User userOld) {
        
    	User user = (User) o;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "useremail", "NotEmpty");
        if (!isValidEmailAddress(user.getUseremail())) {
        	errors.rejectValue("useremail", "Diff.userForm.emailValid");
        }
                
        
        if (!(userOld.getUseremail().equals(user.getUseremail()))) {
            if (userService.findByUseremail(user.getUseremail()) != null) {
                errors.rejectValue("useremail", "Duplicate.userForm.useremail");
            } 
        }
         
    }
    
    public void validatePassword(Object o, Errors errors) {
        
    	User user = (User) o;
    	
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
         
    }    
    
    
    public static boolean isValidEmailAddress(String email) {
    	   boolean result = true;
    	   try {
    	      InternetAddress emailAddr = new InternetAddress(email);
    	      emailAddr.validate();
    	   } catch (AddressException ex) {
    	      result = false;
    	   }
    	   return result;
    }
    
    
    
}
