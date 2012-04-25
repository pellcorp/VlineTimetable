package com.pellcorp.android.vline;

import org.junit.Test;

import com.pellcorp.android.vline.offline.Line;
import com.pellcorp.android.vline.offline.TimetableHtmlProvider;
import com.pellcorp.android.vline.offline.TimetableHtmlProviderImpl;
import com.pellcorp.android.vline.offline.TimetableRequest;
import com.pellcorp.android.vline.offline.TimetableRequest.Direction;
import com.pellcorp.android.vline.offline.TimetableRequest.Period;

public class TimetableHtmlProviderImplTest {

	@Test
	public void testLoadGeelongWeeklyTimetable() throws Exception {
		Line line = new Line("01V23", "Geelong - Melbourne");
		TimetableRequest request = new TimetableRequest(line, Period.WEEKDAY, Direction.INCOMING);
		TimetableHtmlProvider provider = new TimetableHtmlProviderImpl();
		
		String html = provider.getTimetable(request);
		System.out.println(html);
	}

}
