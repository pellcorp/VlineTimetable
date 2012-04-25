package com.pellcorp.android.vline.offline;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.jsoup.nodes.Document;
import org.junit.Test;

public class TimetableParserTest {
	TimetableParser parser = new TimetableParser();
	
	@Test
	public void testToGeelongWeekday() throws Exception {
		Document doc = ResourceUtils.loadResourceAsDocument("/ToGeelongWeekday.html");
		List<TimetableService> services = parser.parseTimetable(doc);
		assertEquals(32, services.size());
		
		List<StationTime> firstServiceTimes = services.get(0).getTimes(); 
		assertEquals(new Time(5, 42), firstServiceTimes.get(0).getTime());
		assertEquals(Time.EMPTY, firstServiceTimes.get(1).getTime());
		assertEquals(new Time(5, 51), firstServiceTimes.get(2).getTime());
		assertEquals(new Time(5, 58), firstServiceTimes.get(3).getTime());
		assertEquals(new Time(6, 13), firstServiceTimes.get(4).getTime());
		assertEquals(new Time(6, 21), firstServiceTimes.get(5).getTime());
		assertEquals(new Time(6, 27), firstServiceTimes.get(6).getTime());
		assertEquals(new Time(6, 31), firstServiceTimes.get(7).getTime());
		assertEquals(new Time(6, 33), firstServiceTimes.get(8).getTime());
		assertEquals(new Time(6, 37), firstServiceTimes.get(9).getTime());
		assertEquals(new Time(6, 41), firstServiceTimes.get(10).getTime());
		assertEquals(new Time(6, 51), firstServiceTimes.get(11).getTime());
		assertEquals(new Time(6, 54), firstServiceTimes.get(12).getTime());
		assertEquals(new Time(7, 0), firstServiceTimes.get(13).getTime());
		
		assertEquals(Time.EMPTY, services.get(1).getTimes().get(1).getTime());
		
		// now lets test pm
		List<StationTime> secondPmTimes = services.get(8).getTimes(); 
		assertEquals(new Time(13, 0), secondPmTimes.get(0).getTime());
		assertEquals(new Time(13, 3), secondPmTimes.get(1).getTime());
		assertEquals(new Time(13, 7) , secondPmTimes.get(2).getTime());
		assertEquals(Time.EMPTY, secondPmTimes.get(3).getTime());
		assertEquals(Time.EMPTY, secondPmTimes.get(4).getTime());
		assertEquals(Time.EMPTY, secondPmTimes.get(5).getTime());
		assertEquals(Time.EMPTY, secondPmTimes.get(6).getTime());
		assertEquals(Time.EMPTY, secondPmTimes.get(7).getTime());
		assertEquals(Time.EMPTY, secondPmTimes.get(8).getTime());
		assertEquals(Time.EMPTY, secondPmTimes.get(9).getTime());
		assertEquals(new Time(13, 51), secondPmTimes.get(10).getTime());
		assertEquals(new Time(13, 56), secondPmTimes.get(11).getTime());
		assertEquals(new Time(14, 0), secondPmTimes.get(12).getTime());
		assertEquals(new Time(14, 5), secondPmTimes.get(13).getTime());
	}
	
	@Test
	public void testToMelbourneWeekday() throws Exception {
		Document doc = ResourceUtils.loadResourceAsDocument("/ToMelbourneWeekday.html");
		List<TimetableService> services = parser.parseTimetable(doc);
		assertEquals(30, services.size());
	}
}
