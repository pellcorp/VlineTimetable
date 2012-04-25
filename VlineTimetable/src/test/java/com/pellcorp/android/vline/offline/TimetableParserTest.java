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
		
		assertEquals(new Time(5, 42), services.get(0).getTimes().get(0).getTime());
		assertEquals(Time.EMPTY, services.get(0).getTimes().get(1).getTime());
		assertEquals(new Time(5, 51), services.get(0).getTimes().get(2).getTime());
		assertEquals(new Time(5, 58), services.get(0).getTimes().get(3).getTime());
		assertEquals(new Time(6, 13), services.get(0).getTimes().get(4).getTime());
		assertEquals(new Time(6, 21), services.get(0).getTimes().get(5).getTime());
		assertEquals(new Time(6, 27), services.get(0).getTimes().get(6).getTime());
		assertEquals(new Time(6, 31), services.get(0).getTimes().get(7).getTime());
		assertEquals(new Time(6, 33), services.get(0).getTimes().get(8).getTime());
		assertEquals(new Time(6, 37), services.get(0).getTimes().get(9).getTime());
		assertEquals(new Time(6, 41), services.get(0).getTimes().get(10).getTime());
		assertEquals(new Time(6, 51), services.get(0).getTimes().get(11).getTime());
		assertEquals(new Time(6, 54), services.get(0).getTimes().get(12).getTime());
		assertEquals(new Time(7, 0), services.get(0).getTimes().get(13).getTime());
		
		assertEquals(Time.EMPTY, services.get(1).getTimes().get(1).getTime());
	}
	
	@Test
	public void testToMelbourneWeekday() throws Exception {
		Document doc = ResourceUtils.loadResourceAsDocument("/ToMelbourneWeekday.html");
		List<TimetableService> services = parser.parseTimetable(doc);
		assertEquals(30, services.size());
	}
}
