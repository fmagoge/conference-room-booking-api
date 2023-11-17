package com.fmagoge.crbapi;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fmagoge.crbapi.model.Room;
import com.fmagoge.crbapi.repository.RoomRepository;
import com.fmagoge.crbapi.service.RoomService;
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

	@Override
	public void run(String... args) throws Exception {
		setupData();
	}

	
	public void setupData() {
		Room room1 = new Room();
		room1.setName(Constants.ROOM_1_NAME);
		room1.setCapacity(Constants.ROOM_4_CAPACITY);
		room1.setAvailablity(true);
		room1.setStartTime("");
		room1.setEndTime("");
		roomRepository.save(room1);
		
		Room room2 = new Room();
		room2.setName(Constants.ROOM_2_NAME);
		room2.setCapacity(Constants.ROOM_2_CAPACITY);
		room2.setAvailablity(true);
		room2.setStartTime("");
		room2.setEndTime("");
		roomRepository.save(room2);
		
		Room room3 = new Room();
		room3.setName(Constants.ROOM_3_NAME);
		room3.setCapacity(Constants.ROOM_3_CAPACITY);
		room3.setAvailablity(true);
		room3.setStartTime("");
		room3.setEndTime("");
		roomRepository.save(room3);
		
		Room room4 = new Room();
		room4.setName(Constants.ROOM_4_NAME);
		room4.setCapacity(Constants.ROOM_4_CAPACITY);
		room4.setAvailablity(true);
		room4.setStartTime("");
		room4.setEndTime("");
		roomRepository.save(room4);
	}
}
