package com.arora.event.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.arora.event.model.Event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

	
	Event findById(Long id);
	List<Event> findByDate(String date);
	List<Event> findByTime(String time);
	List<Event> findByDateAndTime(String date, String time);
	List<Event> findByOwner(String owner);
	
	
	List<Event> findByDateandtimeGreaterThan(Date date);
}