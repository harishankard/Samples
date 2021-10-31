package com.paypal.bfs.test.bookingserv.impl;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.networknt.schema.ValidationMessage;
import com.paypal.bfs.test.bookingserv.api.BookingResource;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.customexceptions.ValidationException;
import com.paypal.bfs.test.bookingserv.interceptors.PayloadValidator;
import com.paypal.bfs.test.bookingserv.service.BookingService;

@RestController
public class BookingResourceImpl implements BookingResource {

	@Autowired
	BookingService bookingService;
	@Autowired
	PayloadValidator validator;

	@Override
	public ResponseEntity<Booking> create(Booking booking) throws JsonMappingException {

		Set<ValidationMessage> errors = validator.validateBookingPaylod(booking);
		StringBuilder errorsCombined = new StringBuilder();
		for (ValidationMessage error : errors) {
			String errorStr = error.toString();
			if (errorStr.contains("data_of_birth")) {
				errorsCombined.append(errorStr).append(" Format should be in YYYY-MM-DD.").append("\n");
			} else {
				errorsCombined.append(errorStr).append("\n");
			}

		}

		if (errors.size() > 0)
			throw new ValidationException("Incoorect json ", errorsCombined.toString());
		Booking savedBooking = bookingService.saveorUpdate(booking);

		return ResponseEntity.status(201).body(savedBooking);
	}

	@Override
	public ResponseEntity<List<Booking>> getAllBooking() {
		List<Booking> savedBookings = bookingService.getAllBooking();
		return ResponseEntity.status(200).body(savedBookings);
	}

	@Override
	public ResponseEntity<Booking> getBookingById(Integer id) {
		Booking savedBooking = bookingService.getBookingById(id);
		return ResponseEntity.status(200).body(savedBooking);
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity rulesForCustomerNotFound(HttpServletRequest req, Exception e) {
		return ResponseEntity.status(400).body(((ValidationException) e).getErrorMessage());

	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBooking(Integer id) {
		bookingService.deleteById(id);
	}
}
