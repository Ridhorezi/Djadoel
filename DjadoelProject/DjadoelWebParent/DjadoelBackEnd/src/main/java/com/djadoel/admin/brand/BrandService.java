package com.djadoel.admin.brand;

import java.util.List;

import com.djadoel.common.entity.Brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BrandService {

	@Autowired
	private BrandRepository brandRepo;

	public List<Brand> listAll() {
		return brandRepo.findAll();
	}
}
