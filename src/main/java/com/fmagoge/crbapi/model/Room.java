package com.fmagoge.crbapi.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
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

	@OneToMany(mappedBy = "room", fetch = FetchType.EAGER,
			  cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Booking> bookings;
	
	public void addBooking(Booking booking) {
		if (booking != null) {
			if (bookings == null) {
				bookings = new ArrayList<>();
			}
			bookings.add(booking);
			booking.setRoom(this);
		}
	}

	

	public Room(Long id, String name, int capacity, boolean availablity, List<Booking> bookings) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.availablity = availablity;
		this.bookings = bookings;
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

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	
}
