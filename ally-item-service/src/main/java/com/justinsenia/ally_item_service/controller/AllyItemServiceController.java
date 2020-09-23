package com.justinsenia.ally_item_service.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.justinsenia.ally_item_service.model.Item;
import com.justinsenia.ally_item_service.repository.ItemRepository;
import com.justinsenia.ally_item_service.exception.ResourceNotFoundException;

@RestController
@RequestMapping(path="/api/v1")
public class AllyItemServiceController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping("/items")
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
	
	@GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(
    @PathVariable(value = "id") Long itemId) throws ResourceNotFoundException {
        Item item = itemRepository.findById(itemId)
        .orElseThrow(() -> new ResourceNotFoundException("Item not found on :: "+ itemId));
        return ResponseEntity.ok().body(item);
    }
	
	@PostMapping("/items")
    public Item createItem(@Valid @RequestBody Item item) {
		
		item.setCreatedBy(item.getItemName());
		item.setUpdatedBy(item.getItemName());
        return itemRepository.save(item);
    }
	
	@PutMapping("/item/{id}")
    public ResponseEntity<Item> updateItem(
    @PathVariable(value = "id") Long itemId,
    @Valid @RequestBody Item itemDetails) throws ResourceNotFoundException {
         Item item = itemRepository.findById(itemId)
          .orElseThrow(() -> new ResourceNotFoundException("Item not found on :: "+ itemId));
  
        item.setItemName(itemDetails.getItemName());
        item.setItemDescription(itemDetails.getItemDescription());
        item.setUpdatedAt(new Date());
        final Item updatedItem = itemRepository.save(item);
        return ResponseEntity.ok(updatedItem);
    }
	
	@DeleteMapping("/item/{id}")
	public Map<String, Boolean> deleteItem(
	@PathVariable(value = "id") Long itemId) throws Exception {
       Item item = itemRepository.findById(itemId)
          .orElseThrow(() -> new ResourceNotFoundException("Item not found on :: "+ itemId));

       itemRepository.delete(item);
       Map<String, Boolean> response = new HashMap<>();
       response.put("deleted", Boolean.TRUE);
       return response;
	}
	
}