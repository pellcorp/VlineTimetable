package com.pellcorp.android.vline.offline;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import com.pellcorp.android.vline.offline.TimetableRequest.Direction;
import com.pellcorp.android.vline.offline.TimetableRequest.Period;

public class TimetableCrawlerTest {
	private TimetableHtmlProvider provider;
	private CrawlerCrawler crawler;
	
	@Before
	public void setUp() throws Exception {
		provider = mock(TimetableHtmlProvider.class);
		crawler = new CrawlerCrawler(provider);
		
		
	}
	
	@Test
	public void test() throws Exception {
		Line mainLine = new Line("1745", "Geelong - Melbourne");
		Line line = new Line("01V23", mainLine.getName());

		// this creates a mock where the route selector has a single line - that being geelong
		// so we can test without having to load too much data.
		buildGeelongMock(line);

		List<Timetable> timeTables = crawler.getTimetables();
		assertNotNull(timeTables);
		assertEquals(1, timeTables.size());
		Timetable timeTable = timeTables.get(0);
		
		List<TimetableService> weeklyToGeelongServices = timeTable.getServices(Direction.OUTGOING, Period.WEEKDAY);
		assertEquals(32, weeklyToGeelongServices.size());
		
		List<TimetableService> saturdayToGeelongServices = timeTable.getServices(Direction.OUTGOING, Period.SATURDAY);
		assertEquals(20, saturdayToGeelongServices.size());
		
		List<TimetableService> sundayToGeelongServices = timeTable.getServices(Direction.OUTGOING, Period.SUNDAY);
		assertEquals(15, sundayToGeelongServices.size());
		
		List<TimetableService> weeklyToMelbourneServices = timeTable.getServices(Direction.INCOMING, Period.WEEKDAY);
		assertEquals(30, weeklyToMelbourneServices.size());
		
		List<TimetableService> saturdayToMelbourneServices = timeTable.getServices(Direction.INCOMING, Period.SATURDAY);
		assertEquals(19, saturdayToMelbourneServices.size());
		
		List<TimetableService> sundayToMelbourneServices = timeTable.getServices(Direction.INCOMING, Period.SUNDAY);
		assertEquals(15, sundayToMelbourneServices.size());
	}

	private void buildGeelongMock(Line line) throws IOException {
		Document routeSelector = ResourceUtils.loadResourceAsDocument("/1745/RouteSelector.html");
		when(provider.getRouteSelector()).thenReturn(routeSelector);
		
		Document routeView = ResourceUtils.loadResourceAsDocument("/1745/RouteView.html");
		when(provider.getRoute("1745")).thenReturn(routeView);
		
		Document routeMetaRefresh = ResourceUtils.loadResourceAsDocument("/1745/MetaRefresh.html");
		TimetableLineRequest request = new TimetableLineRequest("1745", "4046");
		when(provider.getTimetable(request)).thenReturn(routeMetaRefresh);

		Document weekdayIncoming = ResourceUtils.loadResourceAsDocument("/1745/Weekday_Incoming.html");
		Document saturdayIncoming = ResourceUtils.loadResourceAsDocument("/1745/Saturday_Incoming.html");
		Document sundayIncoming = ResourceUtils.loadResourceAsDocument("/1745/Sunday_Incoming.html");
		Document weekdayOutgoing = ResourceUtils.loadResourceAsDocument("/1745/Weekday_Outgoing.html");
		Document saturdayOutgoing = ResourceUtils.loadResourceAsDocument("/1745/Saturday_Outgoing.html");
		Document sundayOutgoing = ResourceUtils.loadResourceAsDocument("/1745/Sunday_Outgoing.html");
		
		TimetableRequest ttWeekdayIncoming = new TimetableRequest(line, Direction.INCOMING, Period.WEEKDAY);
		when(provider.getTimetable(ttWeekdayIncoming)).thenReturn(weekdayIncoming);
		
		TimetableRequest ttSaturdayIncoming = new TimetableRequest(line, Direction.INCOMING, Period.SATURDAY);
		when(provider.getTimetable(ttSaturdayIncoming)).thenReturn(saturdayIncoming);
		
		TimetableRequest ttSundayIncoming = new TimetableRequest(line, Direction.INCOMING, Period.SUNDAY);
		when(provider.getTimetable(ttSundayIncoming)).thenReturn(sundayIncoming);
		
		TimetableRequest ttWeekdayOutgoing = new TimetableRequest(line, Direction.OUTGOING, Period.WEEKDAY);
		when(provider.getTimetable(ttWeekdayOutgoing)).thenReturn(weekdayOutgoing);
		
		TimetableRequest ttSaturdayOutgoing = new TimetableRequest(line, Direction.OUTGOING, Period.SATURDAY);
		when(provider.getTimetable(ttSaturdayOutgoing)).thenReturn(saturdayOutgoing);
		
		TimetableRequest ttSundayOutgoing = new TimetableRequest(line, Direction.OUTGOING, Period.SUNDAY);
		when(provider.getTimetable(ttSundayOutgoing)).thenReturn(sundayOutgoing);
	}
}
