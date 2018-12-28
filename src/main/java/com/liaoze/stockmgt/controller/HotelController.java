package com.liaoze.stockmgt.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.liaoze.stockmgt.impl.HotelServiceImpl;
import com.liaoze.stockmgt.model.Hotel;


@Controller
public class HotelController {
	@Resource
	private HotelServiceImpl hotelServiceImpl;
	
//	@Autowired
//	Hotel hotel;
//	
	@RequestMapping(value="/searchHotel")
	@ResponseBody
	public Map<String, Object> getHotelByID(HttpServletRequest request) {
		int HotelID = Integer.parseInt(request.getParameter("HotelID"));
		Map<String, Object> map = new HashMap<>();
		Hotel hotel = new Hotel();
		hotel.setHotelID(HotelID);
		Hotel hotel1 = hotelServiceImpl.searchHotel(hotel);
		map.put("code", 1);
		return map;
	}
	
	
	@RequestMapping(value="/hotel")
	public String hotel() {
		
		return "/hotel/hotel";
	}
}
