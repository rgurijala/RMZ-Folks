package com.vzw.findChurnCustomer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.vzw.findChurnCustomer.dto.ChurnDto;
import com.vzw.findChurnCustomer.dto.CustomerDto;
import com.vzw.findChurnCustomer.dto.OffersDto;

public class CustomerDao {
	public static final String dbUrl = "jdbc:oracle:thin:@113.128.163.225:1521:XE";
	public static final String dbUserName = "rmzfolks";
	public static final String dbPwd = "rmzfolks";
	
	public List getOffers()
	{
		OffersDto dto = new OffersDto();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List offersList = null;
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			con = DriverManager.getConnection(dbUrl, dbUserName, dbPwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT OFFER_TYPE, OFFER_VALUE FROM USER_OFFERS");
			offersList = new ArrayList();
			if(rs.next())
			{
				dto.setOfferType(rs.getString(1));
				dto.setOfferValue(rs.getString(2));
				offersList.add(dto);
			}
			rs.close();
			stmt.close();
			con.close();
			return offersList;
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		return offersList;
		
	}
	
	public List getcustomersData(String accountNum)
	{
		CustomerDto dto = new CustomerDto();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List customerList = null;
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			con = DriverManager.getConnection(dbUrl, dbUserName, dbPwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT MDN, ACCOUNT_NUMBER, NAME, "
					+ "MOST_USED_FEATURE, DATA_USED, ALLOWANCE, IS_CHURN_CUSTOMER, INSERTED_DATE"
					+ " FROM USER_DATA WHERE ACCOUNT_NUMBER = '"+accountNum+"'");
			customerList = new ArrayList();
			
			if(rs.next())
			{
				System.out.println("inside");
				dto.setIsChurnCustomer(rs.getString(7));
				if(dto.getIsChurnCustomer().equalsIgnoreCase("Y"))
				{
					dto.setMdn(rs.getString(1));
					dto.setAccountNumber(rs.getString(2));
					dto.setName(rs.getString(3));
					dto.setMostUsedFeature(rs.getString(4));
					dto.setDataUsed(rs.getString(5));
					dto.setAllowance(rs.getString(6));
					dto.setInsertedDate(rs.getDate(8));
					customerList.add(dto);
				}
				
			}
			
			System.out.println(customerList.size());
			rs.close();
			stmt.close();
			con.close();
			return customerList;
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		return customerList;
		
	}

	public List getcustomersDataByMDN(String mdn)
	{
		CustomerDto dto = new CustomerDto();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List customerList = null;
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			con = DriverManager.getConnection(dbUrl, dbUserName, dbPwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT MDN, ACCOUNT_NUMBER, NAME, "
					+ "MOST_USED_FEATURE, DATA_USED, ALLOWANCE, IS_CHURN_CUSTOMER FROM USER_DATA where MDN = "+mdn);
			customerList = new ArrayList();
			if(rs.next())
			{
					dto.setMdn(rs.getString(1));
					dto.setAccountNumber(rs.getString(2));
					dto.setName(rs.getString(3));
					dto.setMostUsedFeature(rs.getString(4));
					dto.setDataUsed(rs.getString(5));
					dto.setAllowance(rs.getString(6));
					dto.setIsChurnCustomer(rs.getString(7));
					customerList.add(dto);
			}
			rs.close();
			stmt.close();
			con.close();
			return customerList;
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		return customerList;
		
	}
	
	public List getchurnDataByAcctNum(String accntNum)
	{
		ChurnDto dto = new ChurnDto();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List churnList = null;
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			con = DriverManager.getConnection(dbUrl, dbUserName, dbPwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT SEARCH_STRING, INSERTED_DATE FROM USER_SEARCH_HISTORY WHERE ACCOUNT_NUMBER = "+accntNum);
			churnList = new ArrayList();
			if(rs.next())
			{
					dto.setSearchString(rs.getString(1));
					dto.setInsertedDate(rs.getString(2));
					churnList.add(dto);
			}
			rs.close();
			stmt.close();
			con.close();
			return churnList;
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		return churnList;
		
	}
}
