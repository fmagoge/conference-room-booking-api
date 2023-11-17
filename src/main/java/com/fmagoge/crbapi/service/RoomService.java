package com.fmagoge.crbapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmagoge.crbapi.dto.RoomDTO;
import com.fmagoge.crbapi.model.Room;
import com.fmagoge.crbapi.repository.RoomRepository;
import com.fmagoge.crbapi.utils.Constants;

@Service
public class RoomService {
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<RoomDTO> getAllRooms() {
		
		return roomRepository.findAll()
				.stream()
				.map(this::convertEntityToDTO)
				.collect(Collectors.toList());
	}
	
	
	private RoomDTO convertEntityToDTO(Room room) {
		RoomDTO roomDTO = new RoomDTO();
		roomDTO = modelMapper.map(room, RoomDTO.class);
		return roomDTO;
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
