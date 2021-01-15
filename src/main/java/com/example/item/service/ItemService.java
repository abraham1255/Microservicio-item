package com.example.item.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.item.iservice.IItemService;
import com.example.item.models.Item;
import com.example.item.models.Producto;

@Service
public class ItemService implements IItemService{
	
	@Autowired 
	private RestTemplate registrarRestemplate;

	@Override
	public List<Item> findAll() {
		List<Producto> productos = Arrays.asList(
				registrarRestemplate.getForObject("http://localhost:8089/api/productos", Producto[].class)); 
		return productos.stream().map(p-> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Item findaById(Long id, Integer cantidad) {
		Map<String,String> pathVariables = new HashMap<String,String>();
		pathVariables.put("id", id.toString());
		Producto producto = registrarRestemplate.getForObject("http://localhost:8089/api/productos/{id}",Producto.class, pathVariables); 
		return new Item(producto,cantidad);
	}

}
