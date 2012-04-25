package com.pellcorp.android.vline.offline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TimetableParser {
	private static final Pattern STATION_URL_PATTERN = Pattern.compile("http://ptv.vic.gov.au/stop/view/([0-9]+)");
	private static final Pattern TIME_PATTERN = Pattern.compile("([0-9]+):([0-9]+)");
	
	public TimetableParser() {
	}
	
	public List<TimetableService> parseTimetable(Document doc) throws IOException {
		List<StationTimes> stationTimes = new ArrayList<StationTimes>();
		
		// get(0) gets the div[class=ttHeader] which is really stupid!
		Elements amPmElements = doc.getElementById("ttHeader")
				.select("div[class=ttHeader]").get(5).getElementsByTag("div");
		
    	Elements stopColumns = doc.select("div[class=ma_stop]");
    	
    	for (int i=0; i<stopColumns.size(); i++) {
    		Element stopColumn = stopColumns.get(i);
    		
    		Element stationHref = stopColumn.getElementsByTag("a").get(0);
    		String href = stationHref.attr("href");
    		
    		String stationName = cleanupStationName(stationHref.text());
    		int stationId = -1;
    		Matcher matcher = STATION_URL_PATTERN.matcher(href);
    		if (matcher.find()) {
    			stationId = toNumber(matcher.group(1));
    		}
    		Station station = new Station(stationId, stationName);
    		
    		StationTimes times = new StationTimes(station);
    		stationTimes.add(times);
    		
    		Element row = doc.getElementById("ttBR_row_" + (i+1));
    		Elements cells = row.getElementsByTag("div");
    		
    		// we are offsetting by one because stupid jsoup includes the parent in
    		// getElementsByTag if its the same tag type.
    		for (int j=1; j<cells.size(); j++) {
    			String amPm = amPmElements.get(j).text();
    			String cell = cells.get(j).text();
    			
    			Time time = Time.EMPTY;
   				matcher = TIME_PATTERN.matcher(cell);
   				if (matcher.find()) {
   					int hours = toNumber(matcher.group(1));
   					int minutes = toNumber(matcher.group(2));
   					
   					if("pm".equals(amPm)) {
   						hours += 12;
   					}
   					time = new Time(hours, minutes);
   				}
   				times.addTime(time);
    		}
    	}
    	return parseStationTimes(stationTimes);
	}
	
	private List<TimetableService> parseStationTimes(List<StationTimes> stationTimes) {
		List<TimetableService> timetableService = 
				new ArrayList<TimetableService>(stationTimes.get(0).getTimes().size());
		 
		for(int i=0; i<stationTimes.get(0).getTimes().size(); i++) {
			TimetableService service = new TimetableService();
			for(StationTimes stationTime: stationTimes) {
				Station station = stationTime.getStation();
				Time time = stationTime.getTimes().get(i);
				service.addTime(new StationTime(station, time));
			}
			timetableService.add(service);
		}
		return timetableService;
	}
	
	private String cleanupStationName(String stationName) {
		String cleanedStationName = stationName.trim();
		
		int indexOf = cleanedStationName.indexOf("(");
		if (indexOf != -1) {
			cleanedStationName = cleanedStationName.substring(0, indexOf).trim();
		}
		
		if (cleanedStationName.endsWith("Station")) {
			cleanedStationName = cleanedStationName
					.substring(0, cleanedStationName.length() - "Station".length()).trim();
		}
		
		return cleanedStationName;
	}
	
	private int toNumber(String numberValue) {
		try {
			return Integer.valueOf(numberValue);
		} catch(NumberFormatException e) {
			return -1;
		}
	}
}

class StationTimes {
	private final Station station;
	private final List<Time> times = new ArrayList<Time>();
	
	public StationTimes(final Station station) {
		this.station = station;
	}

	public Station getStation() {
		return station;
	}

	public List<Time> getTimes() {
		return times;
	}

	public void addTime(Time time) {
		this.times.add(time);
	}
}
