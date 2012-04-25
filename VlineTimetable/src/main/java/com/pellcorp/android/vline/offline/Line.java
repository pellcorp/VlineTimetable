package com.pellcorp.android.vline.offline;

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
}
