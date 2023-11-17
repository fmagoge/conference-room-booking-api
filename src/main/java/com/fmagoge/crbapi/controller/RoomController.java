package com.fmagoge.crbapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fmagoge.crbapi.dto.RoomDTO;
import com.fmagoge.crbapi.service.RoomService;

@RestController
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@GetMapping("/rooms")
	public List<RoomDTO> getAllRooms(){
		return roomService.getAllRooms();
		
	}
	
	

}
