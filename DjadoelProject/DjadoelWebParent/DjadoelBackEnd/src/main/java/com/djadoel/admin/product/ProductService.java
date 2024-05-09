package com.djadoel.admin.product;

import java.util.List;

import com.djadoel.common.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> listAll() {

		return (List<Product>) productRepository.findAll();
	}

	public Product get(Integer id) throws ProductNotFoundException {
		try {
			return productRepository.findById(id).get();
		} catch (Exception e) {
			throw new ProductNotFoundException("Could not find any brand with ID " + id);
		}
	}

}
