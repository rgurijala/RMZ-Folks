package com.vzw.findChurnCustomer.dto;

import java.util.List;

public class FlotChartVO {
	
	String label;
	List<List> data;
	boolean stack;
	String color;
	BarVO bars;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<List> getData() {
		return data;
	}
	public void setData(List<List> data) {
		this.data = data;
	}
	public boolean isStack() {
		return stack;
	}
	public void setStack(boolean stack) {
		this.stack = stack;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public BarVO getBars() {
		return bars;
	}
	public void setBars(BarVO bars) {
		this.bars = bars;
	}

}
