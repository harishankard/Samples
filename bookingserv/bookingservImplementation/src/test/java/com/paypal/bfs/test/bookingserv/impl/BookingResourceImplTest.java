package com.paypal.bfs.test.bookingserv.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.ValidationMessage;
import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.interceptors.PayloadValidator;
import com.paypal.bfs.test.bookingserv.service.BookingService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BookingResourceImpl.class)
@TestPropertySource(locations = "classpath:application.properties")
public class BookingResourceImplTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private BookingService bookingService;

	@MockBean
	PayloadValidator payloadValidator;

	private PayloadValidator validator = new PayloadValidator();

	@Test
	public void testCreateBooking() throws Exception {
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

		given(bookingService.saveorUpdate(Mockito.any())).willReturn(apiBooking);

		mvc.perform(post("/v1/bfs/booking").contentType(MediaType.APPLICATION_JSON).content(toJson(apiBooking)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.first_name", is("hari")));

		verify(bookingService, VerificationModeFactory.times(1)).saveorUpdate(Mockito.any());
		reset(bookingService);

	}

	@Test
	public void testCreateBookingwithValidationError() throws Exception {
		Booking apiBooking = new Booking();
		apiBooking.setId(122312);
		apiBooking.setFirstName("hari");
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

		Set<ValidationMessage> erros = validator.validateBookingPaylod(apiBooking);
		given(bookingService.saveorUpdate(Mockito.any())).willReturn(apiBooking);

		given(payloadValidator.validateBookingPaylod(Mockito.any())).willReturn(erros);

		mvc.perform(post("/v1/bfs/booking").contentType(MediaType.APPLICATION_JSON).content(toJson(apiBooking)))
				.andExpect(status().isBadRequest());

		verify(payloadValidator, VerificationModeFactory.times(1)).validateBookingPaylod(Mockito.any());
		reset(bookingService);
		reset(payloadValidator);

	}

	@Test
	public void testGetBooking() throws Exception {
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
		apiBooking2.setId(122312);
		apiBooking2.setFirstName("hari");
		apiBooking2.setCheckIn(new Date(System.currentTimeMillis()));
		apiBooking2.setDataOfBirth("1912-03-12");
		apiBooking2.setDeposit(5121.12);
		apiBooking2.setTotalPrice(6000.50);
		apiBooking2.setCheckOut(new Date(System.currentTimeMillis()));
		Address apiAddress2 = new Address();
		apiAddress2.setCity("Tiruppur");
		apiAddress2.setLine1("30 i J G NAGAT");
		apiAddress2.setLine2("FIRST STREET");
		apiAddress2.setState("TAMILNADU");
		apiAddress2.setZipCode("641602");
		apiBooking2.setAddress(apiAddress2);

		List<Booking> allBookings = Arrays.asList(apiBookin1, apiBooking2);
		given(bookingService.getAllBooking()).willReturn(allBookings);

		mvc.perform(get("/v1/bfs/bookings").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].first_name", is(apiBookin1.getFirstName())))
				.andExpect(jsonPath("$[1].first_name", is(apiBooking2.getFirstName())));
		verify(bookingService, VerificationModeFactory.times(1)).getAllBooking();
		reset(bookingService);
	}

	@Test
	public void testGetBookingByID() throws Exception {
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

		given(bookingService.getBookingById(apiBookin1.getId())).willReturn(apiBookin1);

		mvc.perform(get("/v1/bfs/booking/122312").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.first_name", is(apiBookin1.getFirstName())));
		verify(bookingService, VerificationModeFactory.times(1)).getBookingById(apiBookin1.getId());
		reset(bookingService);
	}

	@Test
	public void testDeleteBookingByID() throws Exception {
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

		mvc.perform(delete("/v1/bfs/booking/122312").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
		reset(bookingService);
	}

	static byte[] toJson(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

}
