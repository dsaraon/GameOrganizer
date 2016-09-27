package com.arora.location.web;


import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.arora.account.model.User;
import com.arora.account.repository.UserRepository;
import com.arora.account.service.UserService;
import com.arora.account.validator.UserValidator;
import com.arora.event.model.Event;
import com.arora.event.repository.EventRepository;
import com.arora.event.service.EventService;
import com.arora.event.validator.EventValidator;
import com.arora.player.model.Player;
import com.arora.player.repository.PlayerRepository;
import com.arora.player.validator.PlayerValidator;
import com.arora.tournament.service.TournamentService;
import com.arora.tournament.validator.TournamentValidator;
import com.arora.location.model.Location;
import com.arora.location.repository.LocationRepository;
import com.arora.location.service.LocationService;
import com.arora.location.validator.LocationValidator;


@Controller
public class LocationController {
	
    @Autowired
    private LocationRepository locationRepository;
    
    @Autowired
    private LocationValidator locationValidator;
    
    @Autowired
	private LocationService locationService;

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public String locations(String error, Model model, Principal principal) {
            

    	List<Location> locations = locationRepository.findByOwner(principal.getName());
    	
    	model.addAttribute("locationForm", locations);
    	
    	/*
    	if (error != null) {
            model.addAttribute("error", "You cannot join to the game because you already have game in same time.");
    	}
    	
    	User user = userRepository.findByUsername(principal.getName());
    	Player player = playerRepository.findByUserid(user.getId());   
    	
    	model.addAttribute("eventForm", eventService.getEvents(date, time));
    	model.addAttribute("playerInfo", player);
    	model.addAttribute("userType", user.getUsertype());
    	*/
    	
        return "locations";
    }    
    
    
    @RequestMapping(value = "/create-location", method = RequestMethod.GET)
    public String creatLocation(Model model) {
        
    	model.addAttribute("locationForm", new Location());
        return "create-location";
    }
	
    
    @RequestMapping(value = "/create-location", method = RequestMethod.POST)
    public String creatLocation(@ModelAttribute("locationForm") Location locationForm, BindingResult bindingResult, Model model, Principal principal) {
        
    	locationValidator.validate(locationForm, bindingResult);

        if (bindingResult.hasErrors()) {
        	return "create-location";
        }
    	
    	locationService.save(locationForm, principal);
        return "redirect:/locations";
    }
    
    
    @RequestMapping(value = "/update-location", method = RequestMethod.GET)
    public String updateEvent(Model model, @RequestParam("id") String id, Principal principal) {
    	
    	Location location = locationRepository.findById(Long.parseLong(id));
    	if (!principal.getName().equals(location.getOwner())) {
    		return "redirect:/locations";
    	}
    	
    	model.addAttribute("locationForm", locationRepository.findById(Long.parseLong(id)));
    	
        return "update-location";
    }     
    
    
    @RequestMapping(value = "/update-location", method = RequestMethod.POST)
    public String editProfile(Principal principal, @ModelAttribute("locationForm") Location locationForm, BindingResult bindingResult, Model model) {
        
    	locationValidator.validate(locationForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "update-location";
	    }
    	
		locationForm.setOwner(principal.getName());
		locationService.updateLocation(locationForm);

        return "redirect:/locations";
    }     
    
    
    
    
    
    @RequestMapping(value = "/delete-location", method = RequestMethod.GET)
    public String deleteLocation(@RequestParam("id") String id, Principal principal) {
        
    	Location location = locationRepository.findById(Long.parseLong(id));
    	if (!principal.getName().equals(location.getOwner())) {
    		return "redirect:/locations";
    	}
    	
    	locationService.deleteLocation(Long.parseLong(id));
        return "redirect:/locations";
    } 
 
    
    @RequestMapping(value = "/list/locations", produces = "application/json", method = RequestMethod.GET)
    public String countMember(Model model, Principal principal) {    
        	
    	String s = "";
    	boolean success = false;
    	
    	List<Location> locations = locationService.getLocations();
    	
    	for (int i = 0; i < locations.size(); i++) {
    		
    		Location loc = locations.get(i);
    		if (loc != null) {
    			//{"firstName":"John", "lastName":"Doe"}, 
    			if ((locations.size() - i) == 1) {
    				s = s + "{\"id\":\""+loc.getId()+"\",\"name\":\""+loc.getName()+"\",\"coordinates\":\""+loc.getCoordinates()+"\",\"text_location\":\""+loc.getText_location()+"\"}";
    			}
    			else {
    				s = s + "{\"id\":\""+loc.getId()+"\",\"name\":\""+loc.getName()+"\",\"coordinates\":\""+loc.getCoordinates()+"\",\"text_location\":\""+loc.getText_location()+"\"},";
    			}
    			
    			success = true;
    		}
    		
    	}
    	
    	model.addAttribute("success", success);
    	model.addAttribute("list", s);
    	
    	return "list";
    }    
    
    
}
