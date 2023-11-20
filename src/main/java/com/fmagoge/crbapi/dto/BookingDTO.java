package com.fmagoge.crbapi.dto;

public class BookingDTO {

	private long id;
	private String subject;
	private String startTime;
	private String endTime;
	private int numberOfAttendees;

	public BookingDTO(long id, String subject, String startTime, String endTime, int numberOfAttendees) {
		super();
		this.id = id;
		this.subject = subject;
		this.startTime = startTime;
		this.endTime = endTime;
		this.numberOfAttendees = numberOfAttendees;
	}

	public BookingDTO() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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
}
