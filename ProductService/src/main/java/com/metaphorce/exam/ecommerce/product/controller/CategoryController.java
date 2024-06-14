package com.metaphorce.exam.ecommerce.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metaphorce.exam.ecommerce.product.dto.CategoryDTO;
import com.metaphorce.exam.ecommerce.product.mapper.CategoryMapper;
import com.metaphorce.exam.ecommerce.product.model.impl.Category;
import com.metaphorce.exam.ecommerce.product.service.impl.CategoryService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/category")
@RestController
@Slf4j
public class CategoryController {
	@Autowired
	private CategoryService service;
	
	/**
	 * Implements the HTTP Post to create a Category
	 * @param Category Data to insert
	 * @return New Category create
	 * @author Juan Chavez
	 * @since Jun/11/2024 
	 */
	@PostMapping
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO CategoryRequest) {
		log.info("Category to create: " + CategoryRequest);
		ResponseEntity<CategoryDTO> response = null;
		Category newCategory = this.service.create(CategoryMapper.mapper.toEntity(CategoryRequest));

		if(newCategory != null) {
			response = ResponseEntity.ok(CategoryMapper.mapper.toDTO(newCategory));
			log.info("Category created: " + newCategory);
		} else {
			response = ResponseEntity.badRequest().build();
			log.info("Error to create Category: " + CategoryRequest);
		}

		return response;
	}

	/**
	 * Implements the HTTP Get to find all Categorys
	 * @return List of Categorys
	 * @author Juan Chavez
	 * @since Jun/11/2024 
	 */
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getAllCategories() {
		log.info("Get All Categorys ...");
		ResponseEntity<List<CategoryDTO>> response = null;
		List<Category> listCategorys = this.service.getAll();

		if(listCategorys != null) {
			log.info("Categorys[" + listCategorys.size() + "]");
			response = ResponseEntity.ok(listCategorys.stream().map(CategoryMapper.mapper::toDTO).toList());
		} else {
			response = ResponseEntity.notFound().build();
			log.info("Error to get all Categorys");
		}

		return response;
	}

	/**
	 * Implements HTTP Get to find a Category by id
	 * @param id Category Id
	 * @return Category data
	 * @return New Category create
	 * @author Juan Chavez
	 * @since Jun/11/2024
	 */
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer id) {
		log.info("Get Category[" + id + "]");
		return service.get(id).map((Category Category) -> {
			log.info("Category found: " + Category);
			return ResponseEntity.ok(CategoryMapper.mapper.toDTO(Category));
		}).orElseGet(() -> {
			log.warn("Category not found: " + id);
			return ResponseEntity.notFound().build();
		});
	}

	/**
	 * Implements HTTP Put for update a Category
	 * @param id Category Id
	 * @param Category Category data to update
	 * @return Category modified
	 * @return New Category create
	 * @author Juan Chavez
	 * @since Jun/11/2024
	 */
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryDTO Category) {
		log.info("Category[" + id + "] to update: " + Category);
		ResponseEntity<CategoryDTO> response = null;
		Category updateCategoryData = this.service.update(id, CategoryMapper.mapper.toEntity(Category));

		if(updateCategoryData != null) {
			response = ResponseEntity.ok(CategoryMapper.mapper.toDTO(updateCategoryData));
			log.info("Category[" + id + "] updated");
		} else {
			response = ResponseEntity.badRequest().build();
			log.info("Category[" + id + "] not updated");
		}

		return response;
	}

	/**
	 * Implements HTTP Delete for delete a Category
	 * @param id Category Id
	 * @return Response
	 * @return New Category create
	 * @author Juan Chavez
	 * @since Jun/11/2024
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
		log.info("Category[" + id + "] to delete");
		this.service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
