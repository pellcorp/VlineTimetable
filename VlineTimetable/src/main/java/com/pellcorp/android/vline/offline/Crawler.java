package com.pellcorp.android.vline.offline;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;

public class Crawler {
	public Crawler() {
	}
	
	public List<Timetable> getTimetables() throws Exception {
		TimetableHtmlProvider provider = new TimetableHtmlProvider();
		LineParser lineParser = new LineParser();
		
		List<Timetable> timetables = new ArrayList<Timetable>();
		
		Document routeSelector = provider.getRouteSelector();
		List<Line> lineList = lineParser.parseRoutes(routeSelector);
		for (Line mainLine : lineList) {
			Document routeView = provider.getRoute(mainLine.getLineId());
			String lineId = lineParser.parseLineIdFromFromRouteView(routeView);
			
			TimetableLineRequest request = new TimetableLineRequest(
					mainLine.getLineId(), lineId);
					
			Document firstRoutePage = provider.getTimetable(request);
			
			String realLineId = lineParser.parseLineIdFromMetaRefresh(firstRoutePage);
			
			
		}
		
		return timetables;
	}
}
