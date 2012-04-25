package com.pellcorp.android.vline.offline;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Line {
	private final String lineId;
	private final String name;
	
	public Line(final String lineId, final String name) {
		this.lineId = lineId;
		this.name = name;
	}

	public String getLineId() {
		return lineId;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return lineId + ": " + name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;

		Line other = (Line) obj;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(lineId, other.lineId);
        return builder.isEquals();
	}

	@Override
	public int hashCode() {
	    HashCodeBuilder builder = new HashCodeBuilder();
	    builder.append(lineId);
	    return builder.toHashCode();
	}
}
