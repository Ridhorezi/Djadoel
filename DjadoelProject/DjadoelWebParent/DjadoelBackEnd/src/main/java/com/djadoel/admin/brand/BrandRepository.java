package com.djadoel.admin.brand;

import com.djadoel.common.entity.Brand;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
	
}
