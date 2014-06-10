package com.pellcorp.android.vline.offline;

import java.util.Date;
import java.util.List;

import android.app.IntentService;
import android.content.Intent;

import com.pellcorp.ptv.http.PtvClient;
import com.pellcorp.ptv.timetable.TimetableBuilder;
import com.pellcorp.ptv.timetable.TimetableRequest;

public class VlineTimetableService extends IntentService {
	public static final String TIMETABLE = "com.pellcorp.android.vline.TIMETABLE";
	
	private final TimetableRequest LARA_TO_MELBOURNE = new TimetableRequest(1745, 1534, 1);
	private final TimetableRequest MELBOURNE_TO_LARA = new TimetableRequest(1745, 1534, 21);
	
	public VlineTimetableService() {
		super("VlineTimetableService");
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		TimeTableResult results = null;
		
		try {
			PtvClient client = new PtvClient("96bf335e-bfaa-11e3-8bed-0263a9d0b8a0", 1000128);
			TimetableBuilder builder = new TimetableBuilder(client);
			List<Date> toMelbourne = builder.createTimetable(LARA_TO_MELBOURNE, 10);
			List<Date> fromMelbourne = builder.createTimetable(MELBOURNE_TO_LARA, 10);
			
			results = new TimeTableResult(toMelbourne, fromMelbourne);
		} catch (Exception e) {
			results = new TimeTableResult(e.getMessage());
		}
		
		Intent broadcastIntent = new Intent();
		broadcastIntent.setAction(VlineTimetableReceiver.ACTION_TIMETABLE);
		broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
		broadcastIntent.putExtra(TIMETABLE, results);
		sendBroadcast(broadcastIntent);
	}
}
