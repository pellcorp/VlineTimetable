package com.pellcorp.android.vline.offline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;

import com.pellcorp.android.vline.offline.TimetableRequest.Direction;
import com.pellcorp.android.vline.offline.TimetableRequest.Period;

public class CrawlerCrawler {
	private final TimetableHtmlProvider provider;
	private final LineParser lineParser = new LineParser();
	private final TimetableParser timeTableParser = new TimetableParser();
	
	public CrawlerCrawler(TimetableHtmlProvider provider) {
		this.provider = provider;
	}
	
	public List<Timetable> getTimetables() throws IOException {
		List<Timetable> timetables = new ArrayList<Timetable>();
		
		Document routeSelector = provider.getRouteSelector();
		List<Line> mainLines = lineParser.parseRoutes(routeSelector);
		for (Line mainLine : mainLines) {
			Timetable timetable = getTimetable(mainLine);
			timetables.add(timetable);
		}
		return timetables;
	}
	
	public Timetable getTimetable(Line mainLine) throws IOException {
		Document routeView = provider.getRoute(mainLine.getLineId());
		String lineId = lineParser.parseLineIdFromFromRouteView(routeView);
		
		TimetableLineRequest request = new TimetableLineRequest(
				mainLine.getLineId(), lineId);
				
		Document firstRoutePage = provider.getTimetable(request);
		
		String realLineId = lineParser.parseLineIdFromMetaRefresh(firstRoutePage);
		
		Line line = new Line(realLineId, mainLine.getName());
		Timetable timetable = new Timetable(line);
		
		for(Direction direction : Direction.values()) {
			for(Period period : Period.values()) {
				TimetableRequest ttRequest = new TimetableRequest(line, direction, period);
				Document timetableDoc = provider.getTimetable(ttRequest);
				List<TimetableService> services = timeTableParser.parseTimetable(timetableDoc);
				timetable.addServices(direction, period, services);
			}
		}
		return timetable;
	}
}
