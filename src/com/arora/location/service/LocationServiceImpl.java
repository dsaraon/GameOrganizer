package com.arora.location.service;

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
import com.arora.location.model.Location;
import com.arora.location.repository.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {
    
	@Autowired
    private LocationRepository locationRepository;

    @Override
    public void save(Location location, Principal principal) {
    	location.setOwner(principal.getName());
        locationRepository.save(location);
    }


	@Override
	public List<Location> getLocations() {
		
		List<Location> list = null;
		list = locationRepository.findAll();
		return list;
		
		
	}

	@Override
	public void deleteLocation(Long id) {
		Location location = locationRepository.findById(id);
		locationRepository.delete(location);
	}

	@Override
	public void updateLocation(Location location) {
		
		Location location2 = locationRepository.findById(location.getId());
		
		location2.setName(location.getName());
		location2.setOwner(location.getOwner());
		location2.setCoordinates(location.getCoordinates());
		location2.setText_location(location.getText_location());
    	
		locationRepository.saveAndFlush(location2);		
		
	}

}
