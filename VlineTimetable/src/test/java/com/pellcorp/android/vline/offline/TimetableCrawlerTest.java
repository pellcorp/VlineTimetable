package com.pellcorp.android.vline.offline;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

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
		// 1745
		Line mainLine = new Line("1745", "Geelong - Melbourne");
		Timetable timeTable = crawler.getTimetable(mainLine);
		
	}

}
