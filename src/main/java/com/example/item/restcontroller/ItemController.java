package com.example.item.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.item.iservice.IItemService;
import com.example.item.models.Item;
import com.example.item.service.ItemService;

@RestController
public class ItemController {
	
@Autowired
@Qualifier("itemServiceFeing")
private IItemService itemService;

	@GetMapping("/listar")
	public List<Item> listar() {
		return itemService.findAll();
	}
	@GetMapping("/detalle/{id}/cantidad/{cantidad}")
	public Item detalle (@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findaById(id, cantidad);
	}

}
