package com.arora.event.validator;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.arora.event.model.Event;


@Component
public class EventValidator implements Validator {


	@Override
	public boolean supports(Class<?> aClass) {
		return Event.class.equals(aClass);
	}

	@Override
    public void validate(Object o, Errors errors) {
        
		Event event = (Event) o;
		
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "time", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fee_person", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "num_players", "NotEmpty");
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
