package com.pellcorp.ptv.timetable;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.pellcorp.ptv.http.PtvClient;

public class TimetableBuilderTest extends Assert {
	private PtvClient client = new PtvClient("96bf335e-bfaa-11e3-8bed-0263a9d0b8a0", 1000128);
	private TimetableBuilder builder = new TimetableBuilder(client);
	
	@Test
	@Ignore
	public void testGetTimetable() throws Exception {
		TimetableRequest laraToMelbourne = new TimetableRequest(1745, 1534, 1);
		List<Date> laraDepartures = builder.createTimetable(laraToMelbourne, 25);
		for (Date entry : laraDepartures) {
			System.out.println(entry);
		}
		
		TimetableRequest melbourneToLara = new TimetableRequest(1745, 1534, 21);
		List<Date> melbourneDepartures = builder.createTimetable(melbourneToLara, 25);
		for (Date entry : melbourneDepartures) {
			System.out.println(entry);
		}
	}
}
