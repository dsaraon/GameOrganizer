package com.arora.tournament.validator;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.arora.tournament.model.Tournament;


@Component
public class TournamentValidator implements Validator {


	@Override
	public boolean supports(Class<?> aClass) {
		return Tournament.class.equals(aClass);
	}

	@Override
    public void validate(Object o, Errors errors) {
        
		Tournament tournament = (Tournament) o;
		
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "time", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "num_teams", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "num_per_team", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location", "NotEmpty");
        

    }
	
	public String getRedirectPage(HttpServletRequest context) {
		
    	String referer = context.getHeader("Referer");
    	String pageName = referer.substring(referer.lastIndexOf("/")+1);
    	
    	String redirectPage = "welcome";
    	if (!pageName.isEmpty()) {
    		redirectPage = pageName;
    	}
    	
    	return redirectPage;
	}
	
}
