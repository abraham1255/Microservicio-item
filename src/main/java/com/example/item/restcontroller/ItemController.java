package com.example.item.restcontroller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.item.iservice.IItemService;
import com.example.item.models.Item;
import com.example.item.models.Producto;
import com.example.item.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ItemController {
	
@Autowired
@Qualifier("itemServiceFeing")
//@Qualifier("serviceRestTemplate")
private IItemService itemService;

	@GetMapping("/listar")
	public List<Item> listar() {
		return itemService.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/detalle/{id}/cantidad/{cantidad}")
	public Item detalle (@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findaById(id, cantidad);
	}
	
	public Item metodoAlternativo (Long id,  Integer cantidad) {
		Item item = new Item();
		Producto producto = new Producto();
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("camara sony");
		producto.setPrecio(10000.00);
		item.setProducto(producto);
		return item;
	}

}
