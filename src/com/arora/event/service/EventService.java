package com.arora.event.service;

import java.security.Principal;
import java.util.List;

import com.arora.event.model.Event;

public interface EventService {
	void save(Event event, Principal principal);
	void updateEvent(Event event);
	List<Event> getEvents(String date, String time);
	void deleteEvent(Long id);
}
