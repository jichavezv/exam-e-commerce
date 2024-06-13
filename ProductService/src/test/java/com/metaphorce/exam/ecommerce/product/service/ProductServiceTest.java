package com.metaphorce.exam.ecommerce.product.service;

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
public class ProductServiceTest {
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
		Product data = Product.builder()
				.name("Test")
				.description("Description Test")
				.build();
		
		log.info("Data Test --> " + data);
		
		Product newData = service.create(data);
		
		log.info("Create --> " + newData);
		
		assertNotNull(newData);
		assertEquals(data.getName(), newData.getName());
	}
	
	@Test
	public void getProductTest() {
		Product read = service.get(test.getId()).get();
		log.info("Read --> " + read);
		
		assertNotNull(read);
		assertEquals(read.getDescription(), test.getDescription());
	}
	
	@Test
	public void listProducts() {
		List<Product> data = service.getAll();
		log.info("Read --> " + data);
		
		assertNotNull(data);
	}
	
	@Test
	public void updateProduct() {
		test.setName("Name modified");
		test.setDescription("Desc modified");
		
		Product update = service.update(test.getId(), test);
		log.info("Update --> " + update);
		
		assertNotNull(update);
		assertEquals(test.getName(), update.getName());
	}
	
	@Test
	public void deleteProduct() {
		log.info("Delete --> " + test);
		
		service.delete(test.getId());
		
		Optional<Product> delete = service.get(test.getId());
		
		assertFalse(delete.isPresent());
	}
}
