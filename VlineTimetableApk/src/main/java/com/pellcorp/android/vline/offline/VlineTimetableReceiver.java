package com.pellcorp.android.vline.offline;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class VlineTimetableReceiver extends BroadcastReceiver {
	public static final String ACTION_TIMETABLE =
		      "com.pellcorp.android.vline.action.ACTION_TIMETABLE";
	
	private final Receiver receiver;
	
	public VlineTimetableReceiver(final Receiver receiver) {
		super();
		
		this.receiver = receiver;
	}
	
	public interface Receiver {
		void onReceive(TimeTableResult results);
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		TimeTableResult results = (TimeTableResult) intent.getSerializableExtra(
						VlineTimetableService.TIMETABLE);
		receiver.onReceive(results);
	}
}
