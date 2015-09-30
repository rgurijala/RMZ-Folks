package com.vzw.findChurnCustomer.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.vzw.findChurnCustomer.dto.CustomerDto;
import com.vzw.findChurnCustomer.dto.FlotChartVO;

public class DBtest {

	public static final String dbUrl = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String dbUserName = "rmzfolks";
	public static final String dbPwd = "rmzfolks";
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		/*DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		
		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPwd);
		
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT SYSDATE FROM DUAL");
		
		if(rs.next())
		{
			Date date = rs.getDate(1);
			System.out.println("current date is >>>>>>>>> "+date);
		}
		rs.close();
		stmt.close();
		con.close();*/
		
		List<CustomerDto> test = getcustomersData();
		String response = "";
		for(CustomerDto a : test)
		{
			response = convertObjToString(a);
			
		}
		
		
//		System.out.println("test >>>>>>>>>>>> "+test);

	}
	
	public static List getcustomersData()
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
					+ "MOST_USED_FEATURE, DATA_USED, SIGNAL_STRENGTH, IS_CHURN_CUSTOMER FROM USER_DATA");
			customerList = new ArrayList();
			if(rs.next())
			{
				dto.setIsChurnCustomer(rs.getString(7));
				/*if(dto.getIsChurnCustomer().equalsIgnoreCase("Y"))
				{*/
					dto.setMdn(rs.getString(1));
					dto.setAccountNumber(rs.getString(2));
					dto.setName(rs.getString(3));
					dto.setMostUsedFeature(rs.getString(4));
					dto.setDataUsed(rs.getString(5));
					dto.setAllowance(rs.getString(6));
					customerList.add(dto);
//				}
				
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
	
	public static String convertObjToString(Object obj)
	{
		
		
		return null;
		
	}

}
