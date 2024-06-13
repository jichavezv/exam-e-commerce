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

import com.metaphorce.exam.ecommerce.product.model.impl.Inventory;
import com.metaphorce.exam.ecommerce.product.service.impl.InventoryService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class InventoryServiceTest {
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
		Inventory data = Inventory.builder()
				.quantity(new Random().nextInt(1000))
				.build();
		
		log.info("Data Test --> " + data);
		
		Inventory newData = service.create(data);
		
		log.info("Create --> " + newData);
		
		assertNotNull(newData);
		assertEquals(data.getQuantity(), newData.getQuantity());
	}
	
	@Test
	public void getInventoryTest() {
		Inventory read = service.get(test.getId()).get();
		log.info("Read --> " + read);
		
		assertNotNull(read);
		assertEquals(read.getQuantity(), test.getQuantity());
	}
	
	@Test
	public void listInventories() {
		List<Inventory> data = service.getAll();
		log.info("Read --> " + data);
		
		assertNotNull(data);
	}
	
	@Test
	public void updateInventory() {
		test.setQuantity(new Random().nextInt(1000));
		
		Inventory update = service.update(test.getId(), test);
		log.info("Update --> " + update);
		
		assertNotNull(update);
		assertEquals(test.getQuantity(), update.getQuantity());
	}
	
	@Test
	public void deleteInventory() {
		log.info("Delete --> " + test);
		
		service.delete(test.getId());
		
		Optional<Inventory> delete = service.get(test.getId());
		
		assertFalse(delete.isPresent());
	}
}
