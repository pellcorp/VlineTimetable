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
		assertEquals(19, services.size());
	}
	
	@Test
	public void testToMelbourneWeekday() throws Exception {
		Document doc = ResourceUtils.loadResourceAsDocument("/ToMelbourneWeekday.html");
		List<TimetableService> services = parser.parseTimetable(doc);
		assertEquals(30, services.size());
	}
}
