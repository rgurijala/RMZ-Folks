package com.vzw.cutomer.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vzw.findChurnCustomer.dto.ChurnDto;
import com.vzw.findChurnCustomer.dto.OffersDto;
import com.vzw.findChurnCustomer.service.churnCustomerService;

@Controller
@RequestMapping("/*")
public class VZWCustomController {

	@Autowired
	churnCustomerService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showHome(ModelMap model) {
        System.out.println("Enter showHome......................");
        String accountNum = "bfatugpi"; // Change this to Babus accntnumber 
		String response = service.getHomePageDetails(accountNum);
		System.out.println(response);
		model.addAttribute("customerList", response);
		
		ModelAndView modelView = new ModelAndView("home");
		return modelView;
	}
	@RequestMapping(value="babu.htm", method = RequestMethod.GET)
	public ModelAndView HighData(ModelMap model) {
        System.out.println("Enter HighData......................");
        String accountNum = "bfatugpi"; // Change this to Babus accntnumber 
		String response = service.getHomePageDetails(accountNum);
		
		model.addAttribute("customerList", response);
		model.addAttribute("highlight", "babu");
		ModelAndView modelView = new ModelAndView("babu");
		return modelView;
	}
	@RequestMapping(value="soban.htm", method = RequestMethod.GET)
	public ModelAndView mediumData(ModelMap model) {
        System.out.println("Enter mediumData......................");
        String accountNum = "ng7627se"; // Change this to Sobans accntnumber 
		String response = service.getHomePageDetails(accountNum);
		
		model.addAttribute("customerList", response);
		model.addAttribute("highlight", "soban");
		ModelAndView modelView = new ModelAndView("soban");
		return modelView;
	}
	@RequestMapping(value="rama.htm", method = RequestMethod.GET)
	public ModelAndView lowData(ModelMap model) {
        System.out.println("Enter lowData......................");
        String accountNum = "bwzs8kk6";  
		String response = service.getHomePageDetails(accountNum);
		
		model.addAttribute("customerList", response);
		model.addAttribute("highlight", "rama");
		ModelAndView modelView = new ModelAndView("rama");
		return modelView;
	}
	
	/*@RequestMapping(value = "/displayHomePage")
	public ModelAndView showHome(ModelMap model) {

		String accountNum = "bwzs8kk6"; 
		String response = service.getHomePageDetails(accountNum);
		
		ModelAndView modelView = new ModelAndView("home");
		modelView.addObject("response", response);
		return modelView;
	}*/
	
	@RequestMapping(value = "/getChurnData")
	public @ResponseBody Map<String, Object> getChurnData(ModelMap model, @RequestParam String accountNumber) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<ChurnDto> response = service.getchurnDataByAcctNum(accountNumber) ;
		resultMap.put("churnList", response);
		return resultMap;
	}
	
	@RequestMapping(value = "/getOffers")
	public @ResponseBody Map<String, Object> getOffers(ModelMap model) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<OffersDto> response = service.getOffers() ;
		resultMap.put("offersList", response);
		return resultMap;
	}
	
	@RequestMapping(value = "/getcustomersDataByMDN")
	@ResponseBody public String getcustomersDataByMDN(ModelMap model, @RequestParam String mdn) {
		String response = service.getcustomersDataByMDN(mdn) ;
		return response;
	}

}
