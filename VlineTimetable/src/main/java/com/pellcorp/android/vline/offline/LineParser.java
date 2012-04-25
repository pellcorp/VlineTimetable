package com.pellcorp.android.vline.offline;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LineParser {
	private static final Pattern LINE_URL_PATTERN = Pattern.compile("&line=([\\w]+)&");
	
	public LineParser() {
	}
	
	public List<Line> parseRoutes(String routeSelectorHtml) {
		List<Line> routeList = new ArrayList<Line>();
		
		Document doc = HtmlUtils.clean(routeSelectorHtml);
		
		// this is a select
		Element select = doc.getElementById("MainLineID");
		Elements options = select.getElementsByTag("option");
		for (Element option : options) {
			String routeName = option.text();
			String lineId = option.attr("value");
			routeList.add(new Line(lineId, routeName));
		}
		
		return routeList;
	}
	
	public String parseLineIdFromMetaRefresh(String html) {
		Document doc = HtmlUtils.clean(html);
		Elements elements = doc.select("meta[http-equiv=refresh]");
		if (elements.size() > 0) {
			Element element = elements.get(0);
			String content = element.attr("content");
			Matcher matcher = LINE_URL_PATTERN.matcher(content);
			if (matcher.find()) {
				return matcher.group(1);
			}
		}
		return null;
	}
}
