package com.fmagoge.crbapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
//@Table(name = "bookings")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "booking_name")
	private String name;

	@Column(name = "start_time")
	private String startTime;

	@Column(name = "end_time")
	private String endTime;

	@Column(name = "attendees_number")
	private int numberOfAttendees;

	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
	
	public Booking(long id, String name, String startTime, String endTime, int numberOfAttendees) {
		super();
		this.id = id;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.numberOfAttendees = numberOfAttendees;
		//this.room = room;
	}

	public Booking() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getNumberOfAttendees() {
		return numberOfAttendees;
	}

	public void setNumberOfAttendees(int numberOfAttendees) {
		this.numberOfAttendees = numberOfAttendees;
	}

	public void setRoom(Room room) {
		this.room = room;
		
	}

	public Room getRoom() {
		return room;
	}
//
//	public void setRoom(Room room) {
//		this.room = room;
//	}
	
}
