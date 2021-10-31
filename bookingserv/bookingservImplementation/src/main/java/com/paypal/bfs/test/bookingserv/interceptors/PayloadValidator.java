package com.paypal.bfs.test.bookingserv.interceptors;

import java.io.InputStream;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.util.DateAttributeConverter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PayloadValidator {

	ObjectMapper MAPPER = new ObjectMapper();

	public Set<ValidationMessage> validateBookingPaylod(Booking booking) throws JsonMappingException {
		log.debug("validating incoiming payload");
		InputStream schemaAsStream = PayloadValidator.class.getClassLoader().getResourceAsStream("schema.json");
		JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7).getSchema(schemaAsStream);
		ObjectNode jsonNode = (ObjectNode) MAPPER.convertValue(booking, JsonNode.class);
		if (booking.getCheckIn() != null) {
			jsonNode.put("check_in", DateAttributeConverter.getFormattedStringDate(booking.getCheckIn()));
		}
		if (booking.getCheckOut() != null) {
			jsonNode.put("check_out", DateAttributeConverter.getFormattedStringDate(booking.getCheckIn()));
		}

		Set<ValidationMessage> errors = schema.validate(jsonNode);
		return errors;
	}

}
