package com.vzw.findChurnCustomer.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.vzw.findChurnCustomer.dao.CustomerDao;
import com.vzw.findChurnCustomer.dto.BarVO;
import com.vzw.findChurnCustomer.dto.CustomerDto;
import com.vzw.findChurnCustomer.dto.FlotChartVO;

public class churnCustomerService {
	
	public CustomerDao dao;
	
	public CustomerDao getDao() {
		return dao;
	}

	public void setDao(CustomerDao dao) {
		this.dao = dao;
	}

	public String getHomePageDetails(String accountNum)
	{
		List<CustomerDto> customerList = dao.getcustomersData(accountNum);
		double gaugeValue = (Double.parseDouble(customerList.get(0).getDataUsed())/Double.parseDouble(customerList.get(0).getAllowance()))*100;
		List<FlotChartVO> flotList = new ArrayList<FlotChartVO>();
		FlotChartVO vo = new FlotChartVO();
		BarVO bar = new BarVO();
		bar.setBarWidth(0.2);
		bar.setFill(true);
		bar.setFillColor("RGB(52,149,253)");
		bar.setHorizontal(false);
		bar.setOrder(1);
		bar.setShow(true);
		vo.setBars(bar);
		vo.setLabel("Usage Date");
		vo.setColor("RGB(52,149,253)");
		vo.setStack(false);
		List totalData = new ArrayList();
		for(CustomerDto custo : customerList)
		{
			List data = new ArrayList();
			data.add(custo.getInsertedDate().getTime());
			data.add(custo.getDataUsed());
			totalData.add(data);
		}
		vo.setData(totalData);
		flotList.add(vo);
		String response = convertToJSON(totalData);
		StringBuffer finalStr = new StringBuffer(response);
		finalStr.append("&").append(gaugeValue);
		return finalStr.toString();
		
	}
	
	public List getOffers()
	{
		List offersList = dao.getOffers();
		return offersList;
		
	}
	
	public String getcustomersDataByMDN(String mdn)
	{
		
		List<CustomerDto> customerList = dao.getcustomersDataByMDN(mdn);
		int gaugeValue = (Integer.parseInt(customerList.get(0).getDataUsed())/Integer.parseInt(customerList.get(0).getAllowance()))*100;
		List<FlotChartVO> flotList = new ArrayList<FlotChartVO>();
		FlotChartVO vo = new FlotChartVO();
		BarVO bar = new BarVO();
		bar.setBarWidth(180000);
		bar.setFill(true);
		bar.setFillColor("RGB(52,149,253)");
		bar.setHorizontal(false);
		bar.setOrder(1);
		bar.setShow(true);
		vo.setBars(bar);
		vo.setLabel("Usage Date");
		vo.setColor("RGB(52,149,253)");
		vo.setStack(false);
		List totalData = new ArrayList();
		for(CustomerDto custo : customerList)
		{
			List data = new ArrayList();
			data.add(custo.getInsertedDate().getTime());
			data.add(custo.getDataUsed());
			totalData.add(data);
		}
		vo.setData(totalData);
		flotList.add(vo);
		String response = convertToJSON(flotList);
		StringBuffer finalStr = new StringBuffer(response);
		finalStr.append("&").append(gaugeValue);
		return finalStr.toString();

		
	}
	
	public List getchurnDataByAcctNum(String AcctNum)
	{
		List churnList = dao.getchurnDataByAcctNum(AcctNum);
		return churnList;
		
	}
	
	public String convertToJSON(List list)
	{
		ObjectMapper obj = new ObjectMapper();
		StringBuilder response = new StringBuilder();
		response.append("[");
		try {
			response.append(obj.writeValueAsString(list));
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.append("]");
		return response.toString();
	}

}
