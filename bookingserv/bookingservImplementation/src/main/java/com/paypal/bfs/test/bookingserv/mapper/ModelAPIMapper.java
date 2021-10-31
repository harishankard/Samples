package com.paypal.bfs.test.bookingserv.mapper;

import org.springframework.stereotype.Service;

import com.paypal.bfs.test.bookingserv.model.Address;
import com.paypal.bfs.test.bookingserv.model.Booking;

@Service
public class ModelAPIMapper {

	public com.paypal.bfs.test.bookingserv.api.model.Booking mapModeltoApi(Booking modelBooking) {
		com.paypal.bfs.test.bookingserv.api.model.Booking apiBooking = new com.paypal.bfs.test.bookingserv.api.model.Booking();
		apiBooking.setId(modelBooking.getId());
		apiBooking.setFirstName(modelBooking.getFirstName());
		apiBooking.setLastName(modelBooking.getLastName());
		apiBooking.setCheckIn(modelBooking.getCheckIn());
		apiBooking.setCheckOut(modelBooking.getCheckOut());
		apiBooking.setDataOfBirth(modelBooking.getDataOfBirth());
		apiBooking.setDeposit(modelBooking.getDeposit());
		apiBooking.setTotalPrice(modelBooking.getTotalPrice());
		if (modelBooking.getAddress() != null) {
			apiBooking.setAddress(mapModeltoApi(modelBooking.getAddress()));
		}
		return apiBooking;
	}

	public Booking mapApitoModel(com.paypal.bfs.test.bookingserv.api.model.Booking apiBooking) {
		Booking modelBooking = new Booking();
		modelBooking.setId(apiBooking.getId());
		modelBooking.setFirstName(apiBooking.getFirstName());
		modelBooking.setLastName(apiBooking.getLastName());
		modelBooking.setCheckIn(apiBooking.getCheckIn());
		modelBooking.setCheckOut(apiBooking.getCheckOut());
		modelBooking.setDataOfBirth(apiBooking.getDataOfBirth());
		modelBooking.setDeposit(apiBooking.getDeposit());
		modelBooking.setTotalPrice(apiBooking.getTotalPrice());
		if (apiBooking.getAddress() != null) {
			modelBooking.setAddress(mapApitoModel(apiBooking.getAddress()));
		}

		return modelBooking;
	}

	public com.paypal.bfs.test.bookingserv.api.model.Address mapModeltoApi(Address modelAddress) {
		com.paypal.bfs.test.bookingserv.api.model.Address apiAddress = new com.paypal.bfs.test.bookingserv.api.model.Address();
		apiAddress.setCity(modelAddress.getCity());
		apiAddress.setLine1(modelAddress.getLine1());
		apiAddress.setLine2(modelAddress.getLine2());
		apiAddress.setState(modelAddress.getState());
		apiAddress.setZipCode(modelAddress.getZipCode());
		return apiAddress;
	}

	public Address mapApitoModel(com.paypal.bfs.test.bookingserv.api.model.Address apiAddress) {
		Address modelAddress = new Address();
		modelAddress.setCity(apiAddress.getCity());
		modelAddress.setLine1(apiAddress.getLine1());
		modelAddress.setLine2(apiAddress.getLine2());
		modelAddress.setState(apiAddress.getState());
		modelAddress.setZipCode(apiAddress.getZipCode());
		return modelAddress;
	}

}
