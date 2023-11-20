package com.fmagoge.crbapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fmagoge.crbapi.model.Booking;


public interface BookingRepository extends JpaRepository<Booking, Long> {

}
