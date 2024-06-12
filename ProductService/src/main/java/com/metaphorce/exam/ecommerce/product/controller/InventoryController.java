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

import com.metaphorce.exam.ecommerce.product.dto.InventoryDTO;
import com.metaphorce.exam.ecommerce.product.mapper.InventoryMapper;
import com.metaphorce.exam.ecommerce.product.model.impl.Inventory;
import com.metaphorce.exam.ecommerce.product.service.impl.InventoryService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/inventory")
@RestController
@Slf4j
public class InventoryController {
	@Autowired
	private InventoryService service;
	
	/**
	 * Implements the HTTP Post to create a Inventory
	 * @param Inventory Data to insert
	 * @return New Inventory create
	 * @author Juan Chavez
	 * @since Jun/11/2024 
	 */
	@PostMapping
	public ResponseEntity<InventoryDTO> createInventory(@Valid @RequestBody InventoryDTO InventoryRequest) {
		log.info("Inventory to create: " + InventoryRequest);
		ResponseEntity<InventoryDTO> response = null;
		Inventory newInventory = this.service.create(InventoryMapper.mapper.toEntity(InventoryRequest));

		if(newInventory != null) {
			response = ResponseEntity.ok(InventoryMapper.mapper.toDTO(newInventory));
			log.info("Inventory created: " + newInventory);
		} else {
			response = ResponseEntity.badRequest().build();
			log.info("Error to create Inventory: " + InventoryRequest);
		}

		return response;
	}

	/**
	 * Implements the HTTP Get to find all Inventorys
	 * @return List of Inventorys
	 * @author Juan Chavez
	 * @since Jun/11/2024 
	 */
	@GetMapping
	public ResponseEntity<List<InventoryDTO>> getAllInventorys() {
		log.info("Get All Inventorys ...");
		ResponseEntity<List<InventoryDTO>> response = null;
		List<Inventory> listInventorys = this.service.getAll();

		if(listInventorys != null) {
			log.info("Inventorys[" + listInventorys.size() + "]");
			response = ResponseEntity.ok(listInventorys.stream().map(InventoryMapper.mapper::toDTO).toList());
		} else {
			response = ResponseEntity.notFound().build();
			log.info("Error to get all Inventorys");
		}

		return response;
	}

	/**
	 * Implements HTTP Get to find a Inventory by id
	 * @param id Inventory Id
	 * @return Inventory data
	 * @return New Inventory create
	 * @author Juan Chavez
	 * @since Jun/11/2024
	 */
	@GetMapping("/{id}")
	public ResponseEntity<InventoryDTO> getInventory(@PathVariable Integer id) {
		log.info("Get Inventory[" + id + "]");
		return service.get(id).map((Inventory Inventory) -> {
			log.info("Inventory found: " + Inventory);
			return ResponseEntity.ok(InventoryMapper.mapper.toDTO(Inventory));
		}).orElseGet(() -> {
			log.warn("Inventory not found: " + id);
			return ResponseEntity.notFound().build();
		});
	}

	/**
	 * Implements HTTP Put for update a Inventory
	 * @param id Inventory Id
	 * @param Inventory Inventory data to update
	 * @return Inventory modified
	 * @return New Inventory create
	 * @author Juan Chavez
	 * @since Jun/11/2024
	 */
	@PutMapping("/{id}")
	public ResponseEntity<InventoryDTO> updateInventory(@PathVariable Integer id, @Valid @RequestBody InventoryDTO Inventory) {
		log.info("Inventory[" + id + "] to update: " + Inventory);
		ResponseEntity<InventoryDTO> response = null;
		Inventory updateInventoryData = this.service.update(id, InventoryMapper.mapper.toEntity(Inventory));

		if(updateInventoryData != null) {
			response = ResponseEntity.ok(InventoryMapper.mapper.toDTO(updateInventoryData));
			log.info("Inventory[" + id + "] updated");
		} else {
			response = ResponseEntity.badRequest().build();
			log.info("Inventory[" + id + "] not updated");
		}

		return response;
	}

	/**
	 * Implements HTTP Delete for delete a Inventory
	 * @param id Inventory Id
	 * @return Response
	 * @return New Inventory create
	 * @author Juan Chavez
	 * @since Jun/11/2024
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteInventory(@PathVariable Integer id) {
		log.info("Inventory[" + id + "] to delete");
		this.service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
