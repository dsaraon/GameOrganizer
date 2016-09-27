package com.arora.event.web;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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



@Controller
public class EventController {
	
    @Autowired
	private EventService eventService;

    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
	private PlayerRepository playerRepository;    
    
    @Autowired
    private UserRepository userRepository;      
	
    @Autowired
    private EventValidator eventValidator;
    
    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String events(String error, Model model, Principal principal, @RequestParam(value = "date", required=false) String date, @RequestParam(value = "time", required=false) String time) {
            	
    	if (error != null) {
            model.addAttribute("error", "You cannot join to the game because you already have game in same time.");
    	}
    	
    	User user = userRepository.findByUsername(principal.getName());
    	Player player = playerRepository.findByUserid(user.getId());   
    	
    	model.addAttribute("eventForm", eventService.getEvents(date, time));
    	model.addAttribute("playerInfo", player);
    	model.addAttribute("userType", user.getUsertype());
    	
        return "events";
    }    
    
    @RequestMapping(value = "/create-event", method = RequestMethod.GET)
    public String registration(Model model) {
        
    	model.addAttribute("eventForm", new Event());
        return "create-event";
    }
	
    @RequestMapping(value = "/create-event", method = RequestMethod.POST)
    public String registration(@ModelAttribute("eventForm") Event eventForm, BindingResult bindingResult, Model model, Principal principal) {
        
    	eventValidator.validate(eventForm, bindingResult);

        if (bindingResult.hasErrors()) {
        	return "create-event";
        }
    	
    	eventService.save(eventForm, principal);
        return "redirect:/events";
    }
    
    @RequestMapping(value = "/update-event", method = RequestMethod.GET)
    public String updateEvent(Model model, @RequestParam("id") String id, Principal principal) {
    	
    	Event event = eventRepository.findById(Long.parseLong(id));
    	if (!principal.getName().equals(event.getOwner())) {
    		return "redirect:/events";
    	}
    	
    	model.addAttribute("eventForm", eventRepository.findById(Long.parseLong(id)));
    	
        return "update-event";
    }     
    
    
    @RequestMapping(value = "/update-event", method = RequestMethod.POST)
    public String editProfile(Principal principal, @ModelAttribute("eventForm") Event eventForm, BindingResult bindingResult, Model model) {
        
    	eventValidator.validate(eventForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "update-event";
	    }
    	
		System.out.println("get city");
		System.out.println(eventForm.getCity());
		
    	eventService.updateEvent(eventForm);

        return "redirect:/events";
    }     
    
    
    
    
    
    @RequestMapping(value = "/delete-event", method = RequestMethod.GET)
    public String deleteEvent(@RequestParam("id") String id, Principal principal) {
        
    	Event event = eventRepository.findById(Long.parseLong(id));
    	if (!principal.getName().equals(event.getOwner())) {
    		return "redirect:/events";
    	}
    	
    	eventService.deleteEvent(Long.parseLong(id));
        return "redirect:/events";
    } 
    
}
