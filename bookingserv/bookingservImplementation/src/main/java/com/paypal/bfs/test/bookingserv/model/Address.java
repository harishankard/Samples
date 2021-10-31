
package com.paypal.bfs.test.bookingserv.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	@Id
	private String id;
	private String line1;
	private String line2;
	private String city;
	private String state;
	private String zipCode;
	@ManyToOne
	private Booking booking;
}
