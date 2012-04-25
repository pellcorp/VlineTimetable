package com.pellcorp.android.vline.offline;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class TimetableParserTest {
	TimetableParser parser = new TimetableParser();
	
	@Test
	public void testToGeelongWeekday() throws Exception {
		String dirtyHtml = ResourceUtils.loadResourceAsString("/ToGeelongWeekday.html");
		List<TimetableService> services = parser.parseTimetable(dirtyHtml);
		assertEquals(19, services.size());
	}
	
	@Test
	public void testToMelbourneWeekday() throws Exception {
		String dirtyHtml = ResourceUtils.loadResourceAsString("/ToMelbourneWeekday.html");
		List<TimetableService> services = parser.parseTimetable(dirtyHtml);
		assertEquals(30, services.size());
	}
}
