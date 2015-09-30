package com.vzw.findChurnCustomer.dto;

import java.sql.Date;



public class CustomerDto {
	
	private String mdn;
	private String name;
	private String accountNumber;
	private String mostUsedFeature;
	private String dataUsed;
	private String allowance;
	private String isChurnCustomer;
	private Date insertedDate;
	
	
	
	public String getMdn() {
		return mdn;
	}
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getMostUsedFeature() {
		return mostUsedFeature;
	}
	public void setMostUsedFeature(String mostUsedFeature) {
		this.mostUsedFeature = mostUsedFeature;
	}
	public String getDataUsed() {
		return dataUsed;
	}
	public void setDataUsed(String dataUsed) {
		this.dataUsed = dataUsed;
	}
	
	public String getIsChurnCustomer() {
		return isChurnCustomer;
	}
	public void setIsChurnCustomer(String isChurnCustomer) {
		this.isChurnCustomer = isChurnCustomer;
	}

	public Date getInsertedDate() {
		return insertedDate;
	}

	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}

	public String getAllowance() {
		return allowance;
	}

	public void setAllowance(String allowance) {
		this.allowance = allowance;
	}

}
