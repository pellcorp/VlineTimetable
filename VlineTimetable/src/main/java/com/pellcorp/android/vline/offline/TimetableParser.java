package com.pellcorp.android.vline.offline;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public class TimetableParser {
	private static final Pattern STATION_URL_PATTERN = Pattern.compile("http://ptv.vic.gov.au/stop/view/([0-9]+)");
	private static final Pattern TIME_PATTERN = Pattern.compile("([0-9]+):([0-9]+) ([am|pm]+)");
	
	private static final Whitelist WHITELIST = new Whitelist();
	static {
		WHITELIST.addTags("table", "tr", "td", "div", "a");
		WHITELIST.addAttributes("div", "id");
		WHITELIST.addAttributes("a", "href");
	}
	
	public TimetableParser() {
	}
	
	public List<StationTimes> parseTimetable(String timeTableHtml) throws Exception {
		String html = Jsoup.clean(timeTableHtml, WHITELIST);
		
		Document doc = Jsoup.parse(html);
		
		List<StationTimes> stationList = new ArrayList<StationTimes>();
		
		// this will be the div
    	Element tableElement = doc.getElementById("ttAccessibleBody").getElementsByTag("table").get(0);
    	Elements tableRows = tableElement.select("tr");
    	
    	for (int i=4; i<tableRows.size(); i++) {
    		Element row = tableRows.get(i);
    		Elements cells = row.select("td");
    		Element stationHref = cells.get(0).getElementsByTag("a").get(0);
    		String href = stationHref.attr("href");
    		
    		String stationName = stationHref.text();
    		int stationId = -1;
    		Matcher matcher = STATION_URL_PATTERN.matcher(href);
    		if (matcher.find()) {
    			stationId = toNumber(matcher.group(1));
    		}
    		StationTimes times = new StationTimes(stationId, stationName);
    		stationList.add(times);
    		
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
   					times.addTime(null);
   				}
    		}
    	}
    	
    	return stationList;
	}
	
	private int toNumber(String numberValue) {
		try {
			return Integer.valueOf(numberValue);
		} catch(NumberFormatException e) {
			return -1;
		}
	}
}
