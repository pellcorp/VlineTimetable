package com.pellcorp.android.vline.offline;

import java.io.IOException;

public interface TimetableHtmlProvider {
	String getTimetable(TimetableRequest request) throws IOException;
}
