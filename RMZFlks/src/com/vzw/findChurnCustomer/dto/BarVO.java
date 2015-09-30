package com.vzw.findChurnCustomer.dto;

public class BarVO {
	
	boolean show;
	double barWidth;
	int order;
	boolean horizontal;
	boolean fill;
	String fillColor;
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
	}
	public double getBarWidth() {
		return barWidth;
	}
	public void setBarWidth(double barWidth) {
		this.barWidth = barWidth;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public boolean isHorizontal() {
		return horizontal;
	}
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	public boolean isFill() {
		return fill;
	}
	public void setFill(boolean fill) {
		this.fill = fill;
	}
	public String getFillColor() {
		return fillColor;
	}
	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}

}
