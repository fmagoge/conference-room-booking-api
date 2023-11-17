package com.fmagoge.crbapi.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rooms")
public class Room {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "room_name")
    private String name;
    
	@Column(name = "capacity")
    private int capacity;
    
	@Column(name = "availability")
    private boolean availablity;
    
	@Column(name = "start_time")
    private String startTime;
    
	@Column(name = "end_time")
    private String endTime;
	
	

	public Room(Long id, String name, int capacity, boolean availablity, String startTime, String endTime) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.availablity = availablity;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	
	public Room() {
		
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isAvailablity() {
		return availablity;
	}

	public void setAvailablity(boolean availablity) {
		this.availablity = availablity;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	@Override
	public String toString() {
		return "Room [id=" + id + ", name=" + name + ", capacity=" + capacity + ", availablity=" + availablity
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
}
