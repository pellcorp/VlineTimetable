package com.pellcorp.android.vline.offline;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

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
	
	public TimetableRequest(final Line line, final Direction direction, final Period period) {
		this.line = line;
		this.direction = direction;
		this.period = period;
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;

    	TimetableRequest other = (TimetableRequest) obj;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(line, other.line);
        builder.append(direction, other.direction);
        builder.append(period, other.period);
        return builder.isEquals();
	}

	@Override
	public int hashCode() {
	    HashCodeBuilder builder = new HashCodeBuilder();
	    builder.append(line);
	    builder.append(direction);
	    builder.append(period);
	    return builder.toHashCode();
	}
}
