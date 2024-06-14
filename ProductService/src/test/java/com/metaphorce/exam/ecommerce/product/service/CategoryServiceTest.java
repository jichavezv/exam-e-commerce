package com.metaphorce.exam.ecommerce.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.metaphorce.exam.ecommerce.product.model.impl.Category;
import com.metaphorce.exam.ecommerce.product.service.impl.CategoryService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class CategoryServiceTest {
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
		Category data = Category.builder()
				.name("Test")
				.description("Description Test")
				.build();
		
		log.info("Data Test --> " + data);
		
		Category newData = service.create(data);
		
		log.info("Create --> " + newData);
		
		assertNotNull(newData);
		assertEquals(data.getName(), newData.getName());
	}
	
	@Test
	public void getCategoryTest() {
		Category read = service.get(test.getId()).get();
		log.info("Read --> " + read);
		
		assertNotNull(read);
		assertEquals(read.getDescription(), test.getDescription());
	}
	
	@Test
	public void listCategories() {
		List<Category> data = service.getAll();
		log.info("Read --> " + data);
		
		assertNotNull(data);
	}
	
	@Test
	public void updateCategory() {
		test.setName("Name modified");
		test.setDescription("Desc modified");
		
		Category update = service.update(test.getId(), test);
		log.info("Update --> " + update);
		
		assertNotNull(update);
		assertEquals(test.getName(), update.getName());
	}
	
	@Test
	public void deleteCategory() {
		log.info("Delete --> " + test);
		
		service.delete(test.getId());
		
		Optional<Category> delete = service.get(test.getId());
		
		assertFalse(delete.isPresent());
	}
}
