package com.pellcorp.android.vline.offline;

import java.util.ArrayList;
import java.util.List;

public class Timetable {
	public enum Direction {
		INCOMING, OUTGOING
	}
	
	public enum Period {
		WEEKDAY, SATURDAY, SUNDAY
	}
	
	private final Line line;
	private final Direction direction;
	private final Period period;
	
	private final List<TimetableService> services = new ArrayList<TimetableService>();
	
	public Timetable(final Line line, final Direction direction, final Period period) {
		this.line = line;
		this.direction = direction;
		this.period = period;
	}
	
	public Period getPeriod() {
		return period;
	}

	public Line getLine() {
		return line;
	}

	public Direction getDirection() {
		return direction;
	}

	public void addService(TimetableService service) {
		services.add(service);
	}

	public List<TimetableService> getServices() {
		return services;
	}
}
