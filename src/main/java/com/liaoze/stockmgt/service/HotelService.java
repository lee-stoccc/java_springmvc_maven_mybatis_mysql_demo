package com.liaoze.stockmgt.service;

import org.springframework.stereotype.Service;

import com.liaoze.stockmgt.model.Hotel;

@Service
public interface HotelService {
	public Hotel searchHotel(Hotel hotel);

}
