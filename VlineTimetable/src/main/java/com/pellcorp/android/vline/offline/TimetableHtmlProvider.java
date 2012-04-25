package com.pellcorp.android.vline.offline;

import java.io.IOException;

import org.jsoup.nodes.Document;

public interface TimetableHtmlProvider {
	Document getRoute(String mainLineId) throws IOException;
	Document getRouteSelector() throws IOException;
	Document getTimetable(TimetableLineRequest request) throws IOException;
	Document getTimetable(TimetableRequest request) throws IOException;
}
