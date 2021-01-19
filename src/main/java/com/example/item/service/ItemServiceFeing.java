package com.example.item.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.item.cliente.IProductoClienteRest;
import com.example.item.iservice.IItemService;
import com.example.item.models.Item;
import com.example.commons.models.Producto;

@Service("itemServiceFeign")
public class ItemServiceFeing implements IItemService {

	@Autowired
	private IProductoClienteRest clienteFeign;

	@Override
	public List<Item> findAll() {

		return clienteFeign.findAll().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findaById(Long id, Integer cantidad) {

		return new Item(clienteFeign.detalle(id), cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		return clienteFeign.crear(producto);
	}

	@Override
	public Producto update(Producto producto, Long id) {
		return clienteFeign.update(producto, id);
	}

	@Override
	public void delete(Long id) {
		clienteFeign.eliminar(id);
	}

}
