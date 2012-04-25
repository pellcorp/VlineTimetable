package com.pellcorp.android.vline.offline;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class TimetableLineRequest {
	private final String mainLineId;
	private final String lineId;
	
	/**
	 * The initial route request has a different lineId to what we eventually
	 * want, so that why its not domain objects here.
	 * 
	 * @param mainLineId
	 * @param lineId
	 */
	public TimetableLineRequest(final String mainLineId, final String lineId) {
		this.mainLineId = mainLineId;
		this.lineId = lineId;
	}

	public String getMainLineId() {
		return mainLineId;
	}

	public String getLineId() {
		return lineId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;

		TimetableLineRequest other = (TimetableLineRequest) obj;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(mainLineId, other.mainLineId);
        builder.append(lineId, other.lineId);
        return builder.isEquals();
	}

	@Override
	public int hashCode() {
	    HashCodeBuilder builder = new HashCodeBuilder();
	    builder.append(mainLineId);
	    builder.append(lineId);
	    return builder.toHashCode();
	}
}
