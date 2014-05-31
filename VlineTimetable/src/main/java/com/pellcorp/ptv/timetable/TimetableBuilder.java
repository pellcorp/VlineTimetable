package com.pellcorp.ptv.timetable;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.jsonpath.JsonPath;
import com.pellcorp.ptv.http.PtvClient;

public class TimetableBuilder {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final PtvClient client;

	public TimetableBuilder(final PtvClient client) {
		this.client = client;
	}

	public List<Date> createTimetable(TimetableRequest request, int limit)
			throws IOException {
		String url = MessageFormat
				.format("/v2/mode/3/line/{0,number,#}/stop/{1,number,#}/directionid/{2,number,#}/departures/all/limit/{3,number,#}",
						request.getLineId(), request.getDepartureId(),
						request.getDirectionId(), limit);

		String response = client.doGet(url);
		List<String> dateStrings = JsonPath.read(response, "$..time_timetable_utc");
		List<Date> entries = new ArrayList<Date>();
		for (String dateString : dateStrings) {
			Date date = parseDateTime((String) dateString);
			entries.add(date);
		}
		return entries;
	}

	private Date parseDateTime(String input) {
		// NOTE: SimpleDateFormat uses GMT[-+]hh:mm for the TZ which breaks
		// things a bit. Before we go on we have to repair this.
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");

		// this is zero time so we need to add that TZ indicator for
		if (input.endsWith("Z")) {
			input = input.substring(0, input.length() - 1) + "GMT-00:00";
		} else {
			int inset = 6;

			String s0 = input.substring(0, input.length() - inset);
			String s1 = input.substring(input.length() - inset, input.length());

			input = s0 + "GMT" + s1;
		}

		try {
			return df.parse(input);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
}
