package com.succes.ecommerce.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.succes.ecommerce.dto.ProductDto;
import com.succes.ecommerce.Repository.CategoryRepo;
import com.succes.ecommerce.controller.RequestPojo.ApiResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.succes.ecommerce.model.Category;
import com.succes.ecommerce.model.Products;
import com.succes.ecommerce.service.ProductService.ProductServices;

@RestController
@RequestMapping("/api")
public class ProductController {
	@Autowired
	ProductServices ProductServices;

	@Autowired
	private CategoryRepo categoryRepo;

	@RequestMapping("/product/getAll")
	public List<Products> getAllPRoducts(){
		return ProductServices.getAllProducts();
	}
	@RequestMapping("/product/getAllCategory")
	public List<Category> getAllCategory(){
		return ProductServices.getAllCategory();
	}
	@RequestMapping("/product/getProductsByCategory/{categoryId}")
	public List<Products> getProductsByCategory(@RequestBody HashMap<String,String> request){
		String category_id = request.get("cat_id");
		return ProductServices.getProductsByCategory(category_id);
	}
	@GetMapping("/product/{productId}")
	public Optional<Products> findProduct(@PathVariable Long productId) {


		return ProductServices.findProduct(productId);

	}
	@GetMapping("/product/category/{categoryId}")
	public Optional<Category> findCategory(@PathVariable Long categoryId) {


		return ProductServices.findCategory(categoryId);

	}


	@GetMapping( value = "/product/getimage/{img_name}",produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImageWithMediaType(@PathVariable("img_name") String img_name) throws IOException {
		InputStream in = getClass().getResourceAsStream("/images/"+img_name);
		return IOUtils.toByteArray(in);
	}
	@PostMapping("/product/category")
	public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
		ProductServices.createCategory(category);
		return ResponseEntity.ok(new ApiResponse("Category successfully", ""));
	}
	@PostMapping("/product/create")
	public ResponseEntity<ApiResponse> createProduct(@RequestBody Products product) {
		Optional<Category> optionalCategory = categoryRepo.findById(product.getCategory_id().getId());
		if (!optionalCategory.isPresent()) {
			return ResponseEntity.badRequest().body(new ApiResponse("category does not exists", ""));
		}
		ProductServices.createProduct(product, optionalCategory.get());
		return ResponseEntity.ok(new ApiResponse(" Product Created successfully", ""));
	}
	@PutMapping("/product/update/{productId}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Long productId, @RequestBody Products product) throws Exception {
		Optional<Category> optionalCategory = categoryRepo.findById(product.getCategory_id().getId());
		if (!optionalCategory.isPresent()) {
			return ResponseEntity.badRequest().body(new ApiResponse("category does not exists", ""));
		}
		ProductServices.updateProduct(product, productId);
		return ResponseEntity.ok(new ApiResponse(" Product Updated successfully", ""));
	}
	@PutMapping("/product/updateCat/{categoryId}")
	public Category updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody Category newcategory ) throws Exception {

		return categoryRepo.findById(categoryId)
				.map(category -> {
					category.setName(newcategory.getName());
					return categoryRepo.save(category);
				})
				.orElseGet(() -> {
					newcategory.setId(categoryId);
					return categoryRepo.save(newcategory);
				});

	}
	@DeleteMapping("/product/deleteProd/{productId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("productId") Long productId ){





		ProductServices.deleteCartItem(productId);

		return ResponseEntity.ok(new ApiResponse(" Product Deleted successfully", ""));

	}
	@DeleteMapping("/product/deleteCat/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategoryItem(@PathVariable("categoryId") Long categoryId ){





		ProductServices.deleteCategoryItem(categoryId);

		return ResponseEntity.ok(new ApiResponse(" Category Deleted successfully", ""));

	}


}
