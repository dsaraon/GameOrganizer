package com.arora.tournament.model;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tournaments")
public class Tournament {

    private Long id;
	private String date;
	private String time;
	private String location;
	private String city;
    private String num_teams;
    private String num_per_team;
    private String owner;
    private Date dateandtime;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNum_teams() {
		return num_teams;
	}

	public void setNum_teams(String num_teams) {
		this.num_teams = num_teams;
	}

	public String getNum_per_team() {
		return num_per_team;
	}

	public void setNum_per_team(String num_per_team) {
		this.num_per_team = num_per_team;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Date getDateandtime() {
		return dateandtime;
	}

	public void setDateandtime(Date dateandtime) {
		this.dateandtime = dateandtime;
	}	
   
}
