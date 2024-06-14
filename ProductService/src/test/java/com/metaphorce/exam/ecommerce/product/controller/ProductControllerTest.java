package com.metaphorce.exam.ecommerce.product.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.metaphorce.exam.ecommerce.product.dto.ProductDTO;
import com.metaphorce.exam.ecommerce.product.mapper.ProductMapper;
import com.metaphorce.exam.ecommerce.product.model.impl.Category;
import com.metaphorce.exam.ecommerce.product.model.impl.Discount;
import com.metaphorce.exam.ecommerce.product.model.impl.Inventory;
import com.metaphorce.exam.ecommerce.product.model.impl.Product;
import com.metaphorce.exam.ecommerce.product.service.impl.CategoryService;
import com.metaphorce.exam.ecommerce.product.service.impl.DiscountService;
import com.metaphorce.exam.ecommerce.product.service.impl.InventoryService;
import com.metaphorce.exam.ecommerce.product.service.impl.ProductService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ProductControllerTest {
	@Autowired
	private ProductController controller;
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private DiscountService discountService;
	
	private Product test;
	
	@BeforeEach
	public void setUp() {
		Category catTest = categoryService.create(Category.builder()
				.name("Test")
				.description("Description Test")
				.build());
		
		Inventory invTest = inventoryService.create(Inventory.builder()
				.quantity(new Random().nextInt(1000))
				.build());
		
		Discount disTest = discountService.create(Discount.builder()
				.name("Test")
				.description("Description Test")
				.active(true)
				.discountPercent(new Random().nextDouble(100.0))
				.build());
		
		test = service.create(Product.builder()
				.name("Test")
				.description("Description Test")
				.SKU("SKU Test")
				.price(new Random().nextDouble(1000.0))
				.category(catTest)
				.discount(disTest)
				.inventory(invTest)
				.build());
	}
	
	@Test
	public void createProductTest() {
		ProductDTO data = ProductDTO.builder()
				.name("Test")
				.description("Description Test")
				.categoryId(test.getCategory().getId())
				.inventoryId(test.getInventory().getId())
				.discountId(test.getDiscount().getId())
				.build();
		
		log.info("Data Test --> " + data);
		
		ResponseEntity<ProductDTO> response = controller.createProduct(data);
		ProductDTO newData = response.getBody();
		
		log.info("Create --> " + newData);
		
		assertNotNull(newData);
		assertEquals(data.getName(), newData.getName());
	}
	
	@Test
	public void getProductTest() {
		ResponseEntity<ProductDTO> response = controller.getProduct(test.getId());
		ProductDTO read = response.getBody();
		
		log.info("Read --> " + read);
		
		assertNotNull(read);
		assertEquals(read.getDescription(), test.getDescription());
	}
	
	@Test
	public void listProducts() {
		ResponseEntity<List<ProductDTO>> response = controller.getAllProducts();
		List<ProductDTO> data = response.getBody();
		
		log.info("Read --> " + data);
		
		assertNotNull(data);
	}
	
	@Test
	public void updateProduct() {
		test.setName("Name modified");
		test.setDescription("Desc modified");
		
		ResponseEntity<ProductDTO> response = controller.updateProduct(test.getId(), ProductMapper.mapper.toDTO(test));
		ProductDTO update = response.getBody();
		
		log.info("Update --> " + update);
		
		assertNotNull(update);
		assertEquals(test.getName(), update.getName());
	}
	
	@Test
	public void deleteProduct() {
		log.info("Delete --> " + test);
		
		controller.deleteProduct(test.getId());
		
		Optional<Product> delete = service.get(test.getId());
		
		assertFalse(delete.isPresent());
	}
}
