package com.pellcorp.android.vline.offline;

public class TimetableLineRequest {
	private final int mainLineId;
	private final int lineId;
	private final String lineName;
	
	/**
	 * The initial route request has a different lineId to what we eventually
	 * want, so that why its not domain objects here.
	 * 
	 * @param mainLineId
	 * @param lineId
	 * @param lineName
	 */
	public TimetableLineRequest(final int mainLineId, 
			final int lineId, final String lineName) {
		this.mainLineId = mainLineId;
		this.lineId = lineId;
		this.lineName = lineName;
		
	}

	public int getMainLineId() {
		return mainLineId;
	}

	public int getLineId() {
		return lineId;
	}

	public String getLineName() {
		return lineName;
	}
}
