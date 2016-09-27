package com.arora.location.validator;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.arora.event.model.Event;
import com.arora.location.model.Location;


@Component
public class LocationValidator implements Validator {


	@Override
	public boolean supports(Class<?> aClass) {
		return Event.class.equals(aClass);
	}

	@Override
    public void validate(Object o, Errors errors) {
        
		Location event = (Location) o;
		
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "coordinates", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text_location", "NotEmpty");

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
