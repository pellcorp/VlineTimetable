package com.pellcorp.android.vline.offline;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.jsoup.nodes.Document;
import org.junit.Test;

public class LineParserTest {
	private LineParser parser = new LineParser();
	
	@Test
	public void testLoadLines() throws Exception {
		Document doc = ResourceUtils.loadResourceAsDocument("/RouteSelector.html");
		List<Line> lineList = parser.parseRoutes(doc);
		assertEquals(55, lineList.size());
	}
	
	@Test
	public void testParseLineIdFromMetaRefresh() throws Exception {
		Document doc = ResourceUtils.loadResourceAsDocument("/MetaRefresh.html");
		String lineId = parser.parseLineIdFromMetaRefresh(doc);
		assertEquals("01V23", lineId);
	}
	
	@Test
	public void testInvalidParseLineIdFromMetaRefresh() throws Exception {
		Document doc = ResourceUtils.loadResourceAsDocument("/InvalidMetaRefresh.html");
		String lineId = parser.parseLineIdFromMetaRefresh(doc);
		assertNull(lineId);
	}
	
	@Test
	public void testNoParseLineIdFromMetaRefresh() throws Exception {
		Document doc = ResourceUtils.loadResourceAsDocument("/NoMetaRefresh.html");
		String lineId = parser.parseLineIdFromMetaRefresh(doc);
		assertNull(lineId);
	}
	
	@Test
	public void testParseLineIdFromFromRouteView() throws Exception {
		Document doc = ResourceUtils.loadResourceAsDocument("/RouteView.html");
		String lineId = parser.parseLineIdFromFromRouteView(doc);
		assertEquals("4055", lineId);
	}
}
