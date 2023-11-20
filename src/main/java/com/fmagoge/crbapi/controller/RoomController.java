package com.fmagoge.crbapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fmagoge.crbapi.dto.BookingDTO;
import com.fmagoge.crbapi.dto.RoomDTO;
import com.fmagoge.crbapi.exception.BookingValidityException;
import com.fmagoge.crbapi.exception.RoomNotFoundException;
import com.fmagoge.crbapi.model.Room;
import com.fmagoge.crbapi.service.RoomService;

@Controller
@RequestMapping("/")
public class RoomController {
	
	private String message = "";

	@Autowired
	private RoomService roomService;

	@GetMapping("/")
	public String showConferenceRooms(@RequestParam(value = "message", required = false) String message, Model model) {
		List<RoomDTO> roomsDtos = roomService.getAllRooms();
		model.addAttribute("list", roomsDtos);
		return "conference_rooms";
	}

	@GetMapping("/room/view_room_bookings")
	public String viewAConferenceRoom(@RequestParam(value = "message", required = false) String message,RedirectAttributes attributes , Model model,
			@RequestParam Long id) {
		String page = null;
		try {
			Room room = roomService.getRoomById(id);
			model.addAttribute("room", room);
			page = "conference_room_bookings";
		} catch (RoomNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", message += e.getMessage());
			page = "/";
		}
		return page;
	}

	@GetMapping("/room/makingbooking")
	public String makingBooking(Model model, RedirectAttributes attributes, @RequestParam Long id) {
		String page = null;
		try {
			Room room = roomService.getRoomById(id);
			model.addAttribute("room", room);
			page = "make_booking";
		} catch (RoomNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", message +=  e.getMessage());
			page = "redirect:/";
		}
		return page;
	}
	
	@PostMapping("/room/view_room_bookings_made")
	public String saveBooking(Model model,@ModelAttribute BookingDTO bookingDTO,
			 RedirectAttributes attributes) {
		
		String page = null;
		try {
			roomService.saveBooking(bookingDTO);
			page = "redirect:/";
		} catch (BookingValidityException e) {
			e.printStackTrace();
			attributes.addAttribute("message", message +=  e.getMessage());
			page = "redirect:/";
		}
		return page;
	}

}
