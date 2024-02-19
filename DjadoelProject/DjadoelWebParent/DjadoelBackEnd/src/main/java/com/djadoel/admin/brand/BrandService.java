package com.djadoel.admin.brand;

import java.util.List;

import com.djadoel.common.entity.Brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/

@Service
@Transactional
public class BrandService {

	public static final int BRANDS_PER_PAGE = 10;

	@Autowired
	private BrandRepository brandRepo;

	public List<Brand> listAll() {
		return (List<Brand>) brandRepo.findAll();
	}

	public Page<Brand> listByPage(int pageNum, String sortField, String sortDir, String keyword) {

		Sort sort = Sort.by(sortField);

		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

		Pageable pageable = PageRequest.of(pageNum - 1, BRANDS_PER_PAGE, sort);

		if (keyword != null) {
			return brandRepo.findAll(keyword, pageable);
		}

		return brandRepo.findAll(pageable);
	}

	public Brand insert(Brand brand) {
		return brandRepo.save(brand);
	}

	public Brand update(Brand brand) {
		return brandRepo.save(brand);
	}

	public Brand get(Integer id) throws BrandNotFoundException {
		try {
			return brandRepo.findById(id).get();
		} catch (Exception e) {
			throw new BrandNotFoundException("Could not find any brand with ID " + id);
		}
	}

	public void delete(Integer id) throws BrandNotFoundException {
		Long countById = brandRepo.countById(id);

		if (countById == null || countById == 0) {
			throw new BrandNotFoundException("Could not find any brand with ID " + id);
		}

		brandRepo.deleteById(id);
	}

	public String checkUniqueBrands(Integer id, String name) {

		boolean isCreatingNew = (id == null || id == 0);

		Brand brandByName = brandRepo.findByName(name);

		if (isCreatingNew) {

			if (brandByName != null)
				return "Duplicate";

		} else {

			if (brandByName != null && brandByName.getId() != id)
				return "Duplicate";
		}

		return "OK";
	}

}
