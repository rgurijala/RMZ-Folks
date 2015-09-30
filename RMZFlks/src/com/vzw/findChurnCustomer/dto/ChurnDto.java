package com.vzw.findChurnCustomer.dto;

public class ChurnDto {
	
	private String insertedDate;
	private String searchString;
	
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public String getInsertedDate() {
		return insertedDate;
	}
	public void setInsertedDate(String insertedDate) {
		this.insertedDate = insertedDate;
	}

}
