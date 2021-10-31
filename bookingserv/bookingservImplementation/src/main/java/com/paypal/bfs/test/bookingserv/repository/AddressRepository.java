package com.paypal.bfs.test.bookingserv.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.paypal.bfs.test.bookingserv.model.Address;

public interface AddressRepository extends CrudRepository<Address, String> {

	public Optional<Address> findByLine1AndCityAndStateAndZipCode(String line2, String city, String state,
			String zipCode);

}
