package com.succes.ecommerce.service.CategoryServices;

import com.succes.ecommerce.Repository.CategoryRepo;
import com.succes.ecommerce.Repository.ProductRepo;
import com.succes.ecommerce.model.Category;
import com.succes.ecommerce.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServices {


	@Autowired
	CategoryRepo cateRepo;


	public List<Category>getAllCategory(){
		return cateRepo.findAll();
	}
	public Optional<Category> findCategory(Long categoryId) {
		return cateRepo.findById(categoryId);
	}

	public void createCategory(Category category) {
		cateRepo.save(category);
	}


	public void deleteCategoryItem(Long categoryId) {

		cateRepo.deleteById(categoryId);
	}
}
