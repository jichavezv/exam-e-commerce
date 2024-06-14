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

import com.metaphorce.exam.ecommerce.product.dto.DiscountDTO;
import com.metaphorce.exam.ecommerce.product.mapper.DiscountMapper;
import com.metaphorce.exam.ecommerce.product.model.impl.Discount;
import com.metaphorce.exam.ecommerce.product.service.impl.DiscountService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class DiscountControllerTest {
	@Autowired
	private DiscountController controller;
	
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
		DiscountDTO data = DiscountDTO.builder()
				.name("Test")
				.description("Description Test")
				.active(true)
				.discountPercent(new Random().nextDouble(100.0))
				.build();
		
		log.info("Data Test --> " + data);
		
		ResponseEntity<DiscountDTO> response = controller.createDiscount(data);
		DiscountDTO newData = response.getBody();
		
		log.info("Create --> " + newData);
		
		assertNotNull(newData);
		assertEquals(data.getName(), newData.getName());
	}
	
	@Test
	public void getDiscountTest() {
		ResponseEntity<DiscountDTO> response = controller.getDiscount(test.getId());
		DiscountDTO read = response.getBody();
		
		log.info("Read --> " + read);
		
		assertNotNull(read);
		assertEquals(read.getDescription(), test.getDescription());
	}
	
	@Test
	public void listDiscounts() {
		ResponseEntity<List<DiscountDTO>> response = controller.getAllDiscounts();
		List<DiscountDTO> data = response.getBody();
		
		log.info("Read --> " + data);
		
		assertNotNull(data);
	}
	
	@Test
	public void updateDiscount() {
		test.setName("Name modified");
		test.setDescription("Desc modified");
		test.setActive(false);
		test.setDiscountPercent(new Random().nextDouble(100.0));
		
		ResponseEntity<DiscountDTO> response = controller.updateDiscount(test.getId(), DiscountMapper.mapper.toDTO(test));
		DiscountDTO update = response.getBody();
		
		log.info("Update --> " + update);
		
		assertNotNull(update);
		assertEquals(test.getName(), update.getName());
	}
	
	@Test
	public void deleteDiscount() {
		log.info("Delete --> " + test);
		
		controller.deleteDiscount(test.getId());
		
		Optional<Discount> delete = service.get(test.getId());
		
		assertFalse(delete.isPresent());
	}
}
