package com.arora.location.repository;


import java.util.List;
import com.arora.location.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

	Location findById(Long id);
	List<Location> findByOwner(String owner);
	
}