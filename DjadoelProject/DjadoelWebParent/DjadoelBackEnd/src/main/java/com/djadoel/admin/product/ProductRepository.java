package com.djadoel.admin.product;

import com.djadoel.common.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
}
