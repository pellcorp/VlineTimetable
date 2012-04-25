package com.pellcorp.android.vline.offline;

import java.util.ArrayList;
import java.util.List;

public class TimetableService {
	private final List<StationTime> times = new ArrayList<StationTime>();

	public TimetableService() {
	}
	
	public void addTime(StationTime time) {
		times.add(time);
	}

	public List<StationTime> getTimes() {
		return times;
	}
}
