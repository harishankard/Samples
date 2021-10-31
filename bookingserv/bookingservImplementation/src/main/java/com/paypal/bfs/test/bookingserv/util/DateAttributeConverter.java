package com.paypal.bfs.test.bookingserv.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAttributeConverter {

	private final static String DATE_FORMATS = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	private final static SimpleDateFormat FORMATTER = new SimpleDateFormat(DATE_FORMATS);

	public static String getFormattedStringDate(Date inputDate) {
		if (inputDate != null) {
			String dateString = FORMATTER.format(inputDate);
			return dateString;
		}
		return null;
	}

}
