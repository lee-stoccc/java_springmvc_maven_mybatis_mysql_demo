package com.liaoze.stockmgt.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.liaoze.stockmgt.dao.HotelDao;
import com.liaoze.stockmgt.model.Hotel;
import com.liaoze.stockmgt.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService{
	@Autowired
	private HotelDao hotelDao;

	@Override
	public Hotel searchHotel(Hotel hotel) {
		return hotelDao.searchHotel(hotel);
	}

}
