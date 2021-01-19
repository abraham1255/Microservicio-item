package com.example.item.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.item.iservice.IItemService;
import com.example.item.models.Item;
import com.example.item.models.Producto;

@Service("serviceRestTemplate")
public class ItemService implements IItemService{
	
	@Autowired 
	private RestTemplate registrarRestemplate;

	@Override
	public List<Item> findAll() {
		List<Producto> productos = Arrays.asList(
				registrarRestemplate.getForObject("http://servicio-productos/listar", Producto[].class)); 
		return productos.stream().map(p-> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Item findaById(Long id, Integer cantidad) {
		Map<String,String> pathVariables = new HashMap<String,String>();
		pathVariables.put("id", id.toString());
		Producto producto = registrarRestemplate.getForObject("http://servicio-productos/detalle/{id}",Producto.class, pathVariables); 
		return new Item(producto,cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
		
		ResponseEntity<Producto> response = registrarRestemplate.exchange("http://servicio-productos/crear", HttpMethod.POST, body, Producto.class);
		Producto productoResponse = response.getBody();
		System.out.println("getBody:: " + productoResponse.toString());
		return productoResponse;
	}

	@Override
	public Producto update(Producto producto, Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		
		HttpEntity<Producto> body= new HttpEntity<Producto>(producto);
		ResponseEntity<Producto> response = registrarRestemplate.exchange("http://servicio-productos/editar/{id}", 
				HttpMethod.PUT, body, Producto.class, pathVariables);
		
		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		registrarRestemplate.delete("http://servicio-productos/eliminar/{id}", pathVariables);
		
	}

}
