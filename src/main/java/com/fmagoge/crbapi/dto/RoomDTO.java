package com.fmagoge.crbapi.dto;


public class RoomDTO {

	private long id;
    private String name;
    private int capacity;
    private boolean availablity;
    private String startTime;
    private String endTime;
    
	public RoomDTO(long id, String name, int capacity, boolean availablity, String startTime, String endTime) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.availablity = availablity;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public RoomDTO() {
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
		return "RoomDTO [id=" + id + ", name=" + name + ", capacity=" + capacity + ", availablity=" + availablity
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
}
