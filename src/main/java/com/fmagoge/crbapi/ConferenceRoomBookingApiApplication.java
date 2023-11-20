package com.fmagoge.crbapi;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fmagoge.crbapi.model.Booking;
import com.fmagoge.crbapi.model.Room;
import com.fmagoge.crbapi.repository.BookingRepository;
import com.fmagoge.crbapi.repository.RoomRepository;
import com.fmagoge.crbapi.utils.Constants;

@SpringBootApplication
public class ConferenceRoomBookingApiApplication implements CommandLineRunner {

    @Bean
    ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(ConferenceRoomBookingApiApplication.class, args);
	}

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Override
	public void run(String... args) throws Exception {
		setupData();
	}

	
	public void setupData() {
		Room room1 = new Room();
		room1.setName(Constants.ROOM_1_NAME);
		room1.setCapacity(Constants.ROOM_4_CAPACITY);
		room1.setAvailablity(true);
		
		Booking booking1 = new Booking();
		booking1.setName("First Meeting");
		booking1.setStartTime("10:00");
		booking1.setEndTime("11:00");
		booking1.setNumberOfAttendees(2);
	
		List<Booking> bookings = new ArrayList<Booking>();
		bookings.add(booking1);
		room1.setBookings(bookings);
		room1.addBooking(booking1);
		roomRepository.save(room1);
		
		Room room2 = new Room();
		room2.setName(Constants.ROOM_2_NAME);
		room2.setCapacity(Constants.ROOM_2_CAPACITY);
		room2.setAvailablity(true);
		roomRepository.save(room2);
		
		Room room3 = new Room();
		room3.setName(Constants.ROOM_3_NAME);
		room3.setCapacity(Constants.ROOM_3_CAPACITY);
		room3.setAvailablity(true);
		roomRepository.save(room3);
		
		Room room4 = new Room();
		room4.setName(Constants.ROOM_4_NAME);
		room4.setCapacity(Constants.ROOM_4_CAPACITY);
		room4.setAvailablity(true);
		roomRepository.save(room4);
	}
}
