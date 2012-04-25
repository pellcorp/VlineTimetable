package com.pellcorp.android.vline.offline;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TimetableParser {
	private static final Pattern STATION_URL_PATTERN = Pattern.compile("http://ptv.vic.gov.au/stop/view/([0-9]+)");
	private static final Pattern TIME_PATTERN = Pattern.compile("([0-9]+):([0-9]+) ([am|pm]+)");
	
	public TimetableParser() {
	}
	
	public List<TimetableService> parseTimetable(Document doc) throws Exception {
		List<StationTimes> stationTimes = new ArrayList<StationTimes>();
		
		// this will be the div
    	Element tableElement = doc.getElementById("ttAccessibleBody").getElementsByTag("table").get(0);
    	Elements tableRows = tableElement.select("tr");
    	
    	for (int i=4; i<tableRows.size(); i++) {
    		Element row = tableRows.get(i);
    		Elements cells = row.select("td");
    		Element stationHref = cells.get(0).getElementsByTag("a").get(0);
    		String href = stationHref.attr("href");
    		
    		String stationName = removeBracketSuffix(stationHref.text());
    		int stationId = -1;
    		Matcher matcher = STATION_URL_PATTERN.matcher(href);
    		if (matcher.find()) {
    			stationId = toNumber(matcher.group(1));
    		}
    		Station station = new Station(stationId, stationName);
    		StationTimes times = new StationTimes(station);
    		stationTimes.add(times);
    		
    		for (int j=1; j<cells.size(); j++) {
    			String cell = cells.get(j).text();
   				matcher = TIME_PATTERN.matcher(cell);
   				if (matcher.find()) {
   					int hours = toNumber(matcher.group(1));
   					int minutes = toNumber(matcher.group(2));
   					String amPm = matcher.group(3);
   					if("pm".equals(amPm)) {
   						hours += 12;
   					}
   					times.addTime(new Time(hours, minutes));
   				} else {
   					times.addTime(Time.EMPTY);
   				}
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
	
	private String removeBracketSuffix(String stationName) {
		int indexOf = stationName.indexOf("(");
		if (indexOf != -1) {
			return stationName.substring(0, indexOf).trim();
		} else {
			return stationName;
		}
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
