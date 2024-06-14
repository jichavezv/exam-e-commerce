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

import com.metaphorce.exam.ecommerce.product.dto.InventoryDTO;
import com.metaphorce.exam.ecommerce.product.mapper.InventoryMapper;
import com.metaphorce.exam.ecommerce.product.model.impl.Inventory;
import com.metaphorce.exam.ecommerce.product.service.impl.InventoryService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class InventoryControllerTest {
	@Autowired
	private InventoryController controller;
	
	@Autowired
	private InventoryService service;
	
	private Inventory test;
	
	@BeforeEach
	public void setUp() {
		test = service.create(Inventory.builder()
				.quantity(new Random().nextInt(1000))
				.build());
	}
	
	@Test
	public void createInventoryTest() {
		InventoryDTO data = InventoryDTO.builder()
				.quantity(new Random().nextInt(1000))
				.build();
		
		log.info("Data Test --> " + data);
		
		ResponseEntity<InventoryDTO> response = controller.createInventory(data);
		InventoryDTO newData = response.getBody();
		
		log.info("Create --> " + newData);
		
		assertNotNull(newData);
		assertEquals(data.getQuantity(), newData.getQuantity());
	}
	
	@Test
	public void getInventoryTest() {
		ResponseEntity<InventoryDTO> response = controller.getInventory(test.getId());
		InventoryDTO read = response.getBody();
		
		log.info("Read --> " + read);
		
		assertNotNull(read);
		assertEquals(read.getQuantity(), test.getQuantity());
	}
	
	@Test
	public void listInventories() {
		ResponseEntity<List<InventoryDTO>> response = controller.getAllInventories();
		List<InventoryDTO> data = response.getBody();
		
		log.info("Read --> " + data);
		
		assertNotNull(data);
	}
	
	@Test
	public void updateInventory() {
		test.setQuantity(new Random().nextInt(1000));
		
		ResponseEntity<InventoryDTO> response = controller.updateInventory(test.getId(), InventoryMapper.mapper.toDTO(test));
		InventoryDTO update = response.getBody();
		
		log.info("Update --> " + update);
		
		assertNotNull(update);
		assertEquals(test.getQuantity(), update.getQuantity());
	}
	
	@Test
	public void deleteInventory() {
		log.info("Delete --> " + test);
		
		controller.deleteInventory(test.getId());
		
		Optional<Inventory> delete = service.get(test.getId());
		
		assertFalse(delete.isPresent());
	}
}
