package com.pellcorp.android.vline.offline;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.jsoup.nodes.Document;
import org.junit.Test;

import com.pellcorp.android.vline.offline.TimetableRequest.Direction;
import com.pellcorp.android.vline.offline.TimetableRequest.Period;

public class TimetableHtmlProviderImplTest {
	TimetableHtmlProviderImpl provider = new TimetableHtmlProviderImpl();
	
	@Test
	public void testLoadGeelongWeeklyTimetable() throws Exception {
		Line line = new Line("01V23", "Geelong - Melbourne");
		TimetableRequest request = new TimetableRequest(line, Direction.INCOMING, Period.WEEKDAY);
		Document response = provider.getTimetable(request);
		
		TimetableParser parser = new TimetableParser();
		List<TimetableService> services = parser.parseTimetable(response);
		assertEquals(30, services.size());
	}
	
	@Test
	public void testGetRouteGeelong() throws Exception {
		//http://tt.ptv.vic.gov.au/tt/XSLT_REQUEST?itdLPxx_lineMain=1745&itdLPxx_lineID=4046&itdLPxx_output=html
		TimetableLineRequest request = new TimetableLineRequest("1745", "4046");
		Document response = provider.getTimetable(request);
		System.out.println(response);
	}
}
