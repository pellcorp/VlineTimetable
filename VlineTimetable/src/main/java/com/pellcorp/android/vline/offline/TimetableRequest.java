package com.pellcorp.android.vline.offline;

public class TimetableRequest {
	public enum Direction {
		INCOMING, OUTGOING
	}
	
	public enum Period {
		WEEKDAY, SATURDAY, SUNDAY
	}
	
	private final Line line;
	private final Direction direction;
	private final Period period;
	
	public TimetableRequest(final Line line, final Period period, final Direction direction) {
		this.line = line;
		this.period = period;
		this.direction = direction;
	}

	public Line getLine() {
		return line;
	}

	public Direction getDirection() {
		return direction;
	}

	public Period getPeriod() {
		return period;
	}
}
