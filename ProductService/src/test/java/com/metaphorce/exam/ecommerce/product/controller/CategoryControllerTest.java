package com.metaphorce.exam.ecommerce.product.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.metaphorce.exam.ecommerce.product.dto.CategoryDTO;
import com.metaphorce.exam.ecommerce.product.mapper.CategoryMapper;
import com.metaphorce.exam.ecommerce.product.model.impl.Category;
import com.metaphorce.exam.ecommerce.product.service.impl.CategoryService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class CategoryControllerTest {
	@Autowired
	CategoryController controller;
	
	@Autowired
	private CategoryService service;
	
	private Category test;
	
	@BeforeEach
	public void setUp() {
		test = service.create(Category.builder()
				.name("Test")
				.description("Description Test")
				.build());
	}
	
	@Test
	public void createCategoryTest() {
		CategoryDTO data = CategoryDTO.builder()
				.name("Test")
				.description("Description Test")
				.build();
		
		log.info("Data Test --> " + data);
		
		ResponseEntity<CategoryDTO> response = controller.createCategory(data);
		CategoryDTO newData = response.getBody();
		
		log.info("Create --> " + newData);
		
		assertNotNull(newData);
		assertNotNull(newData.getId());
		assertEquals(data.getName(), newData.getName());
	}
	
	@Test
	public void getCategoryTest() {
		ResponseEntity<CategoryDTO> response = controller.getCategory(test.getId());
		CategoryDTO read = response.getBody();
		
		log.info("Read --> " + read);
		
		assertNotNull(read);
		assertEquals(read.getDescription(), test.getDescription());
	}
	
	@Test
	public void listCategories() {
		ResponseEntity<List<CategoryDTO>> response = controller.getAllCategories();
		List<CategoryDTO> data = response.getBody();
		
		log.info("Read --> " + data);
		
		assertNotNull(data);
	}
	
	@Test
	public void updateCategory() {
		test.setName("Name modified");
		test.setDescription("Desc modified");
		
		ResponseEntity<CategoryDTO> response = controller.updateCategory(test.getId(), CategoryMapper.mapper.toDTO(test));
		CategoryDTO update = response.getBody();
		
		log.info("Update --> " + update);
		
		assertNotNull(update);
		assertEquals(test.getName(), update.getName());
	}
	
	@Test
	public void deleteCategory() {
		log.info("Delete --> " + test);
		
		controller.deleteCategory(test.getId());
		
		Optional<Category> delete = service.get(test.getId());
		
		assertFalse(delete.isPresent());
	}
}
