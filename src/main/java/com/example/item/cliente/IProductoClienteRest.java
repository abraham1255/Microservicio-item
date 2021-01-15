package com.example.item.cliente;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.item.models.Producto;

@FeignClient(name ="servicio-productos")
public interface IProductoClienteRest {
	@GetMapping("/api/productos")
	public List<Producto> findAll();
	
	@GetMapping("/api/productos/{id}")
	public Producto detalle(@PathVariable Long id);
	

}
