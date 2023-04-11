package com.softuni.mobilele.services.offer;

import org.springframework.stereotype.Service;

import com.softuni.mobilele.services.init.DataBaseInitService;

@Service
public class OfferServiceImpl implements OfferService, DataBaseInitService{

	@Override
	public void dbInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDbInit() {
		// TODO Auto-generated method stub
		return false;
	}

}
