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

import com.metaphorce.exam.ecommerce.product.model.impl.Discount;
import com.metaphorce.exam.ecommerce.product.service.impl.DiscountService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class DiscountServiceTest {
	@Autowired
	private DiscountService service;
	
	private Discount test;
	
	@BeforeEach
	public void setUp() {
		test = service.create(Discount.builder()
				.name("Test")
				.description("Description Test")
				.active(true)
				.discountPercent(new Random().nextDouble(100.0))
				.build());
	}
	
	@Test
	public void createDiscountTest() {
		Discount data = Discount.builder()
				.name("Test")
				.description("Description Test")
				.active(true)
				.discountPercent(new Random().nextDouble(100.0))
				.build();
		
		log.info("Data Test --> " + data);
		
		Discount newData = service.create(data);
		
		log.info("Create --> " + newData);
		
		assertNotNull(newData);
		assertEquals(data.getName(), newData.getName());
	}
	
	@Test
	public void getDiscountTest() {
		Discount read = service.get(test.getId()).get();
		log.info("Read --> " + read);
		
		assertNotNull(read);
		assertEquals(read.getDescription(), test.getDescription());
	}
	
	@Test
	public void listDiscounts() {
		List<Discount> data = service.getAll();
		log.info("Read --> " + data);
		
		assertNotNull(data);
	}
	
	@Test
	public void updateDiscount() {
		test.setName("Name modified");
		test.setDescription("Desc modified");
		test.setActive(false);
		test.setDiscountPercent(new Random().nextDouble(100.0));
		
		Discount update = service.update(test.getId(), test);
		log.info("Update --> " + update);
		
		assertNotNull(update);
		assertEquals(test.getName(), update.getName());
	}
	
	@Test
	public void deleteDiscount() {
		log.info("Delete --> " + test);
		
		service.delete(test.getId());
		
		Optional<Discount> delete = service.get(test.getId());
		
		assertFalse(delete.isPresent());
	}
}
