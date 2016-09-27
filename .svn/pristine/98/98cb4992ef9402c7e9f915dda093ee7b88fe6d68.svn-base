package com.arora.event.service;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.arora.account.model.User;
import com.arora.event.model.Event;
import com.arora.event.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {
    
	@Autowired
    private EventRepository eventRepository;

    @Override
    public void save(Event event, Principal principal) {
    	
    	event.setOwner(principal.getName());
    	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    	
    	try {
			event.setDateandtime(sdf.parse(event.getDate() + " " + event.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

        eventRepository.save(event);
    }


    
    
    
	@Override
	public List<Event> getEvents(String date, String time) {
		
		List<Event> list = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		
		try {
			
			if (!StringUtils.isNotBlank(time)) {
				time = "00:00 AM";
			}
			
			if (!StringUtils.isNotBlank(date)) {
				date = "01/01/1970";
			}
			
			if (StringUtils.isNotBlank(date) && StringUtils.isNotBlank(time)) {
				System.out.println("select only date and time");
				list = eventRepository.findByDateandtimeGreaterThan(sdf.parse(date + " " + time));
			}
			
			if (!StringUtils.isNotBlank(time) && !StringUtils.isNotBlank(date)) {
				System.out.println("select all");
				list = eventRepository.findAll();			
			}
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return list;
		
		
	}

	@Override
	public void deleteEvent(Long id) {
		Event event = eventRepository.findById(id);
		eventRepository.delete(event);
	}

	@Override
	public void updateEvent(Event event) {
		
		Event event2 = eventRepository.findById(event.getId());
		
		event2.setDate(event.getDate());
		event2.setTime(event.getTime());
		event2.setLocation(event.getLocation());
		event2.setFee_person(event.getFee_person());
		event2.setDescription(event.getDescription());
		event2.setNum_players(event.getNum_players());
		event2.setType(event.getType());
		event2.setCity(event.getCity());
    	
		eventRepository.saveAndFlush(event2);		
		
	}

}
