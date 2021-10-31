package com.paypal.bfs.test.bookingserv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paypal.bfs.test.bookingserv.mapper.ModelAPIMapper;
import com.paypal.bfs.test.bookingserv.model.Address;
import com.paypal.bfs.test.bookingserv.model.Booking;
import com.paypal.bfs.test.bookingserv.repository.AddressRepository;
import com.paypal.bfs.test.bookingserv.repository.BookingRepository;

@Service
public class BookingService {

	@Autowired
	ModelAPIMapper mapper;

	@Autowired
	BookingRepository bookingRepository;
	@Autowired
	AddressRepository addressRepository;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<com.paypal.bfs.test.bookingserv.api.model.Booking> getAllBooking() {
		List<com.paypal.bfs.test.bookingserv.api.model.Booking> apiBookings = new ArrayList<com.paypal.bfs.test.bookingserv.api.model.Booking>();
		bookingRepository.findAll().forEach(modelBooking -> apiBookings.add(mapper.mapModeltoApi(modelBooking)));
		return apiBookings;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)

	public com.paypal.bfs.test.bookingserv.api.model.Booking getBookingById(int id) {
		Optional<Booking> modelBooking = bookingRepository.findById(id);

		if (modelBooking.isPresent()) {
			com.paypal.bfs.test.bookingserv.api.model.Booking apiBooking = mapper.mapModeltoApi(modelBooking.get());
			return apiBooking;
		}
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteById(int id) {
		Optional<Booking> modelBooking = bookingRepository.findById(id);

		if (modelBooking.isPresent()) {
			bookingRepository.delete(modelBooking.get());
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public com.paypal.bfs.test.bookingserv.api.model.Booking saveorUpdate(
			com.paypal.bfs.test.bookingserv.api.model.Booking apiBooking) {
		Booking modelBooking = mapper.mapApitoModel(apiBooking);
		checkforExistingAdddress(modelBooking);
		Booking savedBooking = bookingRepository.save(modelBooking);
		return mapper.mapModeltoApi(savedBooking);
	}

	private void checkforExistingAdddress(Booking booking) {
		if (booking.getAddress() != null) {
			final Address addr = booking.getAddress();
			Optional<Address> existingAddr = addressRepository.findByLine1AndCityAndStateAndZipCode(addr.getLine1(),
					addr.getCity(), addr.getState(), addr.getZipCode());
			if (existingAddr.isPresent()) {
				existingAddr.get().setLine2(addr.getLine2());
				booking.setAddress(existingAddr.get());
			} else {
				addr.setId(UUID.randomUUID().toString());
			}
		}
	}

	public void setBookingRepository(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	public void setAddressRepository(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

}
