package com.pellcorp.android.vline.offline;

import java.util.ArrayList;
import java.util.List;

public class Timetable {
	private final TimetableRequest request;
	
	private final List<TimetableService> services = new ArrayList<TimetableService>();
	
	public Timetable(TimetableRequest request) {
		this.request = request;
	}
	
	public TimetableRequest getTimetableRequest() {
		return request;
	}

	public void addService(TimetableService service) {
		services.add(service);
	}

	public List<TimetableService> getServices() {
		return services;
	}
}
