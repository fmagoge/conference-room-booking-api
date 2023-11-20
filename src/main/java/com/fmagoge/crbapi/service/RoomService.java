package com.fmagoge.crbapi.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmagoge.crbapi.dto.BookingDTO;
import com.fmagoge.crbapi.dto.RoomDTO;
import com.fmagoge.crbapi.exception.BookingValidityException;
import com.fmagoge.crbapi.exception.RoomNotFoundException;
import com.fmagoge.crbapi.model.Booking;
import com.fmagoge.crbapi.model.Room;
import com.fmagoge.crbapi.repository.RoomRepository;
import com.fmagoge.crbapi.utils.Constants;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private ModelMapper modelMapper;

	private List<Room> getRooms() {
		return roomRepository.findAll();
	}

	public List<RoomDTO> getAllRooms() {
		List<Room> list = getRooms();
		System.out.println(list);
		return roomRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
	}

	private RoomDTO convertEntityToDTO(Room room) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		RoomDTO roomDTO = new RoomDTO();
		roomDTO = modelMapper.map(room, RoomDTO.class);
		roomDTO.setAvailablityString(room.isAvailablity() ? "Available" : " Not Available");
		return roomDTO;
	}

	public Room getRoomById(Long id) {
		Optional<Room> room = roomRepository.findById(id);
		if (room.isPresent()) {
			return room.get();
		} else {
			throw new RoomNotFoundException("Room with Id: " + id + " not found");
		}
	}


	private boolean isRoomAvailable(Room room, Booking booking) {
		// Rule 3: Bookings happen on a first-come-first-serve basis.
		// Rule 4: Bookings should be optimal
		// Rule 5: Bookings cannot be done during maintenance time

		// Check for overlap with existing bookings
		for (Booking existingBooking : room.getBookings()) {
			LocalTime existingStartTime = LocalTime.parse(existingBooking.getStartTime());
			LocalTime existingEndTime = LocalTime.parse(existingBooking.getEndTime());

			LocalTime newBookingStartTime = LocalTime.parse(booking.getStartTime());
			LocalTime newBookingEndTime = LocalTime.parse(booking.getEndTime());

			if ((newBookingStartTime.isAfter(existingStartTime) && newBookingStartTime.isBefore(existingEndTime))
					|| (newBookingEndTime.isAfter(existingStartTime) && newBookingEndTime.isBefore(existingEndTime))) {
				validityException("Booking overlaps with an existing booking.");
				return false;
			}

			LocalTime maintenceStartTime1 = LocalTime.parse(Constants.FIRST_MAINTAINENCE_TIME.split(" ")[0]);
			LocalTime maintenceEndTime1 = LocalTime.parse(Constants.FIRST_MAINTAINENCE_TIME.split(" ")[1]);
			LocalTime maintenceStartTime2 = LocalTime.parse(Constants.SECOND_MAINTAINENCE_TIME.split(" ")[0]);
			LocalTime maintenceEndTime2 = LocalTime.parse(Constants.SECOND_MAINTAINENCE_TIME.split(" ")[1]);
			LocalTime maintenceStartTime3 = LocalTime.parse(Constants.THIRD_MAINTAINENCE_TIME.split(" ")[0]);
			LocalTime maintenceEndTime3 = LocalTime.parse(Constants.THIRD_MAINTAINENCE_TIME.split(" ")[1]);

			if ((newBookingStartTime.isAfter(maintenceStartTime1) && newBookingStartTime.isBefore(maintenceEndTime1))
					|| (newBookingEndTime.isAfter(maintenceStartTime2) && newBookingEndTime.isBefore(maintenceEndTime2))
					|| (newBookingEndTime.isAfter(maintenceStartTime3)
							&& newBookingEndTime.isBefore(maintenceEndTime3))) {
				validityException("Booking overlaps with a maintanence time.");
				return false;
			}
		}
		return true;
	}

	// Check if booking is valid based on the given rules
	public boolean isValidBooking(Booking booking) {

		// Rule 1: Bookings can be done only for the current date. This rule is satisfied automatically be doing the booking

		// Rule 2: Booking can be done only in intervals of 15 mins
		if (!isValidTimeInterval(booking.getStartTime(), booking.getEndTime())) {
			validityException("Booking can only be done in intervals of 15 mins.");
			return false;
		}

		// Rule 3: Bookings happen on a first-come-first-serve basis

		// Check for maintenance time overlap
		if (isMaintenanceOverlap(booking)) {
			validityException("Booking cannot be done during maintenance time.");
			return false;
		}

		// Rule 6: The number of people allowed for booking should be greater than 1 and
		// less than or equal to the maximum capacity
		// Check if the number of people is within the allowed range
		if (booking.getNumberOfAttendees() <= 1 || booking.getNumberOfAttendees() > getMaxCapacity(booking)) {

			validityException("Number of people allowed for booking should be greater "
					+ "than 1 and less than or equal to the maximum capacity.");
			return false;
		}
		return true;
	}

	// Check if the booking time is in intervals of 15 mins
	private boolean isValidTimeInterval(String startTime, String endTime) {
		LocalTime startTimLT = LocalTime.parse(startTime);
		LocalTime endTimeLT = LocalTime.parse(endTime);
		if (startTimLT.getMinute() % 15 != 0 || endTimeLT.getMinute() % 15 != 0) {
			validityException("Booking time must be in intervals of 15 minutes.");
			return false;
		}

		return true;
	}

	// Check if the booking overlaps with maintenance time
	private boolean isMaintenanceOverlap(Booking booking) {

		LocalTime maintenceStartTime1 = LocalTime.parse(Constants.FIRST_MAINTAINENCE_TIME.split(" ")[0]);
		LocalTime maintenceEndTime1 = LocalTime.parse(Constants.FIRST_MAINTAINENCE_TIME.split(" ")[1]);
		LocalTime maintenceStartTime2 = LocalTime.parse(Constants.SECOND_MAINTAINENCE_TIME.split(" ")[0]);
		LocalTime maintenceEndTime2 = LocalTime.parse(Constants.SECOND_MAINTAINENCE_TIME.split(" ")[1]);
		LocalTime maintenceStartTime3 = LocalTime.parse(Constants.THIRD_MAINTAINENCE_TIME.split(" ")[0]);
		LocalTime maintenceEndTime3 = LocalTime.parse(Constants.THIRD_MAINTAINENCE_TIME.split(" ")[1]);

		if ((LocalTime.parse(booking.getStartTime()).isAfter(maintenceStartTime1)
				&& LocalTime.parse(booking.getEndTime()).isBefore(maintenceEndTime1))
				|| (LocalTime.parse(booking.getStartTime()).isAfter(maintenceStartTime2)
						&& LocalTime.parse(booking.getEndTime()).isBefore(maintenceEndTime2))
				|| (LocalTime.parse(booking.getStartTime()).isAfter(maintenceStartTime3)
						&& LocalTime.parse(booking.getEndTime()).isBefore(maintenceEndTime3))) {
			validityException("Booking overlaps with a maintanence time.");
			return false;
		}
		return false;
	}

	// Get the maximum capacity of a room
	private int getMaxCapacity(Booking booking) {
		return booking.getRoom().getCapacity();
	}

	// Find the optimal room for booking based on the number of attendees
	public Optional<Room> findOptimalRoom(Set<Room> availableRooms, int numberOfAttendees) {
		// Filter available rooms based on the number of attendees and sort by capacity
		List<Room> optimalRooms = availableRooms.stream().filter(room -> room.getCapacity() >= numberOfAttendees)
				.sorted((r1, r2) -> Integer.compare(r1.getCapacity(), r2.getCapacity())).collect(Collectors.toList());

		// Return the first optimal room if available
		return optimalRooms.isEmpty() ? Optional.empty() : Optional.of(optimalRooms.get(0));
	}

	public void saveBooking(BookingDTO bookingDTO) {

		Room room = roomRepository.findById(bookingDTO.getId()).get();

		Booking booking = new Booking();
		booking.setName(bookingDTO.getSubject());
		booking.setStartTime(bookingDTO.getStartTime());
		booking.setEndTime(bookingDTO.getEndTime());
		booking.setNumberOfAttendees(bookingDTO.getNumberOfAttendees());

		room.addBooking(booking);

		if (isRoomAvailable(room, booking)) {
			if (isValidBooking(booking)) {
				roomRepository.save(room);
			}
		}
	}

	private void validityException(String message) {
		throw new BookingValidityException(message);
	}

}
