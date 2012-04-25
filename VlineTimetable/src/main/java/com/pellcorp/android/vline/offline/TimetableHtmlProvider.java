package com.pellcorp.android.vline.offline;

import java.io.IOException;

import org.jsoup.nodes.Document;

public interface TimetableHtmlProvider {
	// this will actually not provide a timetable, instead it will provide a
	// meta http-equiv refresh tag we have to parse.
	Document getTimetable(TimetableLineRequest request) throws IOException;
	Document getTimetable(TimetableRequest request) throws IOException;
}
