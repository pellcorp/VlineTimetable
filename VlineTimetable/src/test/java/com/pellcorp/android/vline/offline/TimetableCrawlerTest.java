package com.pellcorp.android.vline.offline;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
		
		Document routeSelector = ResourceUtils.loadResourceAsDocument("/RouteSelector.html");
		when(provider.getRouteSelector()).thenReturn(routeSelector);
	}
	
	@Test
	public void test() throws Exception {
		Document routeView = ResourceUtils.loadResourceAsDocument("/1745/RouteView.html");
		when(provider.getRoute("1745")).thenReturn(routeView);
		
		Document routeMetaRefresh = ResourceUtils.loadResourceAsDocument("/1745/MetaRefresh.html");
		TimetableLineRequest request = new TimetableLineRequest("1745", "4046");
		when(provider.getTimetable(request)).thenReturn(routeMetaRefresh);
		
		// 1745
		Line mainLine = new Line("1745", "Geelong - Melbourne");
		Line line = new Line("01V23", mainLine.getName());
		
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
		
		Timetable timeTable = crawler.getTimetable(mainLine);

	}
}
