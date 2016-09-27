package com.arora.location.model;


import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "locations")
public class Location {
    
	private Long id;
	private String owner;
	private String name;
	private String coordinates;
	private String text_location;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	public String getText_location() {
		return text_location;
	}
	public void setText_location(String text_location) {
		this.text_location = text_location;
	}
    
}
