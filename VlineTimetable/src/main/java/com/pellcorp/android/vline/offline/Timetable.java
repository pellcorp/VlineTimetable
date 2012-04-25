package com.pellcorp.android.vline.offline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pellcorp.android.vline.offline.TimetableRequest.Direction;
import com.pellcorp.android.vline.offline.TimetableRequest.Period;

/**
 * A timetable consists of WEEKLY, SATURDAY and SUNDAY for both directions 
 */
public class Timetable {
	private final Line line;
	
	private Map<Direction, Map<Period, List<TimetableService>>> servicesMap = 
			new HashMap<Direction, Map<Period, List<TimetableService>>>();
	
	
	public Timetable(final Line line) {
		for (Direction direction : Direction.values()) {
			Map<Period, List<TimetableService>> directionMap = new HashMap<Period, List<TimetableService>>();
			for (Period period : Period.values()) {
				directionMap.put(period, new ArrayList<TimetableService>());
			}
			servicesMap.put(direction, directionMap);
		}
		
		this.line = line;
	}
	
	public Line getLine() {
		return line;
	}

	public void addServices(final Direction direction, final Period period, final List<TimetableService> services) {
		getServices(direction, period).addAll(services);
	}

	public List<TimetableService> getServices(final Direction direction, Period period) {
		return servicesMap.get(direction).get(period);
	}
}
