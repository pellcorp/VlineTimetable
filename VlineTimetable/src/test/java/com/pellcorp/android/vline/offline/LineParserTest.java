package com.pellcorp.android.vline.offline;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

public class LineParserTest {
	private LineParser parser = new LineParser();
	
	@Test
	public void testLoadLines() throws Exception {
		String dirtyHtml = ResourceUtils.loadResourceAsString("/RouteSelector.html");
		List<Line> lineList = parser.parseRoutes(dirtyHtml);
		assertEquals(56, lineList.size());
	}
	
	@Test
	public void testParseLineIdFromMetaRefresh() throws Exception {
		String dirtyHtml = ResourceUtils.loadResourceAsString("/MetaRefresh.html");
		String lineId = parser.parseLineIdFromMetaRefresh(dirtyHtml);
		assertEquals("01V23", lineId);
	}
	
	@Test
	public void testInvalidParseLineIdFromMetaRefresh() throws Exception {
		String dirtyHtml = ResourceUtils.loadResourceAsString("/InvalidMetaRefresh.html");
		String lineId = parser.parseLineIdFromMetaRefresh(dirtyHtml);
		assertNull(lineId);
	}
	
	@Test
	public void testNoParseLineIdFromMetaRefresh() throws Exception {
		String dirtyHtml = ResourceUtils.loadResourceAsString("/NoMetaRefresh.html");
		String lineId = parser.parseLineIdFromMetaRefresh(dirtyHtml);
		assertNull(lineId);
	}
}
