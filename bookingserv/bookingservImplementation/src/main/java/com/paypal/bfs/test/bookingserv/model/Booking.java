
package com.paypal.bfs.test.bookingserv.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

	/**
	 * Booking id
	 * 
	 */
	@Id
	private Integer id;
	/**
	 * First name (Required)
	 * 
	 */
	private String firstName;
	/**
	 * Last name (Required)
	 * 
	 */
	private String lastName;
	/**
	 * Data of Birth
	 * 
	 */
	private String dataOfBirth;
	/**
	 * Check in Data time
	 * 
	 */
	private Date checkIn;
	/**
	 * Check out Data time
	 * 
	 */
	private Date checkOut;
	/**
	 * Total Price
	 * 
	 */
	private Double totalPrice;
	/**
	 * Deposit
	 * 
	 */
	private Double deposit;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "addressId", referencedColumnName = "id")
	private Address address;

}
