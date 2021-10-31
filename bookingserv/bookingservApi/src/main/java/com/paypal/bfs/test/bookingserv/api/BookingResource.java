package com.paypal.bfs.test.bookingserv.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.paypal.bfs.test.bookingserv.api.model.Booking;

public interface BookingResource {
	/**
	 * Create {@link Booking} resource
	 *
	 * @param booking the booking object
	 * @return the created booking
	 * @throws JsonMappingException
	 */
	@RequestMapping(value = "/v1/bfs/booking", method = RequestMethod.POST)
	ResponseEntity<Booking> create(@RequestBody Booking booking) throws JsonMappingException;

	/**
	 * Get all Bookings {@link Booking} resource.
	 *
	 * @return the List of bookings.
	 */
	@RequestMapping(value = "/v1/bfs/bookings", method = RequestMethod.GET)
	ResponseEntity<List<Booking>> getAllBooking();

	/**
	 * Get a booking {@link Booking} resource
	 *
	 * @param id - Id of the booking object
	 * @return the matching booking
	 */
	@RequestMapping(value = "/v1/bfs/booking/{id}", method = RequestMethod.GET)
	ResponseEntity<Booking> getBookingById(@PathVariable Integer id);

	/**
	 * Get a booking {@link Booking} resource
	 *
	 * @param id - Id of the booking object
	 * @return the matching booking
	 */
	@RequestMapping(value = "/v1/bfs/booking/{id}", method = RequestMethod.DELETE)
	void deleteBooking(@PathVariable Integer id);
}
