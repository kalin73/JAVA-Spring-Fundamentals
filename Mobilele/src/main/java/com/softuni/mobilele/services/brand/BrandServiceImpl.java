package com.softuni.mobilele.services.brand;

import org.springframework.stereotype.Service;

import com.softuni.mobilele.repositories.BrandRepository;
import com.softuni.mobilele.services.init.DataBaseInitService;

@Service
public class BrandServiceImpl implements BrandService, DataBaseInitService {
	private final BrandRepository brandRepository;

	public BrandServiceImpl(BrandRepository brandRepository) {
		this.brandRepository = brandRepository;
	}

	@Override
	public void dbInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDbInit() {
		return this.brandRepository.count() > 0;
	}

}
