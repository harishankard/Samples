package com.paypal.bfs.test.bookingserv.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class BookingServiceTest {

	@Autowired
	private BookingService bookingService;

	ObjectMapper MAPPER = new ObjectMapper();

	@Test
	public void testCreateBookingService() throws JsonProcessingException {
		Booking apiBooking = new Booking();
		apiBooking.setId(122312);
		apiBooking.setFirstName("hari");
		apiBooking.setLastName("shankar");
		apiBooking.setCheckIn(new Date(System.currentTimeMillis()));
		apiBooking.setDataOfBirth("1912-03-12");
		apiBooking.setDeposit(5121.12);
		apiBooking.setTotalPrice(6000.50);
		apiBooking.setCheckOut(new Date(System.currentTimeMillis()));
		Address apiAddress = new Address();
		apiAddress.setCity("Tiruppur");
		apiAddress.setLine1("30 i J G NAGAT");
		apiAddress.setLine2("FIRST STREET");
		apiAddress.setState("TAMILNADU");
		apiAddress.setZipCode("641602");
		apiBooking.setAddress(apiAddress);

		Booking savedBooking = bookingService.saveorUpdate(apiBooking);
		String bookingApiStr = MAPPER.writeValueAsString(apiBooking);
		String savedBookingStr = MAPPER.writeValueAsString(savedBooking);

		assertEquals(bookingApiStr, savedBookingStr);
	}

	@Test
	public void testgetAllBookingService() throws JsonProcessingException {
		Booking apiBookin1 = new Booking();
		apiBookin1.setId(122312);
		apiBookin1.setFirstName("hari");
		apiBookin1.setCheckIn(new Date(System.currentTimeMillis()));
		apiBookin1.setDataOfBirth("1912-03-12");
		apiBookin1.setDeposit(5121.12);
		apiBookin1.setTotalPrice(6000.50);
		apiBookin1.setCheckOut(new Date(System.currentTimeMillis()));
		Address apiAddress1 = new Address();
		apiAddress1.setCity("Tiruppur");
		apiAddress1.setLine1("30 i J G NAGAT");
		apiAddress1.setLine2("FIRST STREET");
		apiAddress1.setState("TAMILNADU");
		apiAddress1.setZipCode("641602");
		apiBookin1.setAddress(apiAddress1);

		Booking apiBooking2 = new Booking();
		apiBooking2.setId(122421);
		apiBooking2.setFirstName("shankar");
		apiBooking2.setCheckIn(new Date(System.currentTimeMillis()));
		apiBooking2.setDataOfBirth("1912-04-12");
		apiBooking2.setDeposit(1212.12);
		apiBooking2.setTotalPrice(3121.50);
		apiBooking2.setCheckOut(new Date(System.currentTimeMillis()));
		Address apiAddress2 = new Address();
		apiAddress2.setCity("Tiruppur");
		apiAddress2.setLine1("49 C J G NAGAT");
		apiAddress2.setLine2("SECOND STREET");
		apiAddress2.setState("TAMILNADU");
		apiAddress2.setZipCode("641602");
		apiBooking2.setAddress(apiAddress2);

		List<Booking> allBookings = Arrays.asList(apiBookin1, apiBooking2);
		bookingService.saveorUpdate(apiBookin1);
		bookingService.saveorUpdate(apiBooking2);
		List<Booking> savedBookings = bookingService.getAllBooking();

		String bookingsStr = MAPPER.writeValueAsString(allBookings);
		String savedBookingsStr = MAPPER.writeValueAsString(savedBookings);

		assertEquals(bookingsStr, savedBookingsStr);
	}

	@Test
	public void testgetAllBookingByIDService() throws JsonProcessingException {
		Booking apiBookin1 = new Booking();
		apiBookin1.setId(122312);
		apiBookin1.setFirstName("hari");
		apiBookin1.setCheckIn(new Date(System.currentTimeMillis()));
		apiBookin1.setDataOfBirth("1912-03-12");
		apiBookin1.setDeposit(5121.12);
		apiBookin1.setTotalPrice(6000.50);
		apiBookin1.setCheckOut(new Date(System.currentTimeMillis()));
		Address apiAddress1 = new Address();
		apiAddress1.setCity("Tiruppur");
		apiAddress1.setLine1("30 i J G NAGAT");
		apiAddress1.setLine2("FIRST STREET");
		apiAddress1.setState("TAMILNADU");
		apiAddress1.setZipCode("641602");
		apiBookin1.setAddress(apiAddress1);

		Booking apiBooking2 = new Booking();
		apiBooking2.setId(122421);
		apiBooking2.setFirstName("shankar");
		apiBooking2.setCheckIn(new Date(System.currentTimeMillis()));
		apiBooking2.setDataOfBirth("1912-04-12");
		apiBooking2.setDeposit(1212.12);
		apiBooking2.setTotalPrice(3121.50);
		apiBooking2.setCheckOut(new Date(System.currentTimeMillis()));
		Address apiAddress2 = new Address();
		apiAddress2.setCity("Tiruppur");
		apiAddress2.setLine1("49 C J G NAGAT");
		apiAddress2.setLine2("SECOND STREET");
		apiAddress2.setState("TAMILNADU");
		apiAddress2.setZipCode("641602");
		apiBooking2.setAddress(apiAddress2);

		bookingService.saveorUpdate(apiBookin1);
		bookingService.saveorUpdate(apiBooking2);
		Booking savedBooking = bookingService.getBookingById(122421);

		String bookingStr = MAPPER.writeValueAsString(apiBooking2);
		String savedBookingStr = MAPPER.writeValueAsString(savedBooking);

		assertEquals(bookingStr, savedBookingStr);
	}

	@Test
	public void testDeleteBooking() throws JsonProcessingException {
		Booking apiBookin1 = new Booking();
		apiBookin1.setId(122312);
		apiBookin1.setFirstName("hari");
		apiBookin1.setCheckIn(new Date(System.currentTimeMillis()));
		apiBookin1.setDataOfBirth("1912-03-12");
		apiBookin1.setDeposit(5121.12);
		apiBookin1.setTotalPrice(6000.50);
		apiBookin1.setCheckOut(new Date(System.currentTimeMillis()));
		Address apiAddress1 = new Address();
		apiAddress1.setCity("Tiruppur");
		apiAddress1.setLine1("30 i J G NAGAT");
		apiAddress1.setLine2("FIRST STREET");
		apiAddress1.setState("TAMILNADU");
		apiAddress1.setZipCode("641602");
		apiBookin1.setAddress(apiAddress1);

		Booking apiBooking2 = new Booking();
		apiBooking2.setId(122421);
		apiBooking2.setFirstName("shankar");
		apiBooking2.setCheckIn(new Date(System.currentTimeMillis()));
		apiBooking2.setDataOfBirth("1912-04-12");
		apiBooking2.setDeposit(1212.12);
		apiBooking2.setTotalPrice(3121.50);
		apiBooking2.setCheckOut(new Date(System.currentTimeMillis()));
		Address apiAddress2 = new Address();
		apiAddress2.setCity("Tiruppur");
		apiAddress2.setLine1("49 C J G NAGAT");
		apiAddress2.setLine2("SECOND STREET");
		apiAddress2.setState("TAMILNADU");
		apiAddress2.setZipCode("641602");
		apiBooking2.setAddress(apiAddress2);

		List<Booking> allBookings = Arrays.asList(apiBookin1);
		bookingService.saveorUpdate(apiBookin1);
		bookingService.saveorUpdate(apiBooking2);
		bookingService.deleteById(122421);
		List<Booking> savedBookings = bookingService.getAllBooking();

		String bookingsStr = MAPPER.writeValueAsString(allBookings);
		String savedBookingsStr = MAPPER.writeValueAsString(savedBookings);

		assertEquals(bookingsStr, savedBookingsStr);
	}

}
