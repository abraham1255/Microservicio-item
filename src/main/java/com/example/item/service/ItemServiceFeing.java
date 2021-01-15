package com.example.item.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.item.cliente.IProductoClienteRest;
import com.example.item.iservice.IItemService;
import com.example.item.models.Item;
import com.example.item.models.Producto;

@Service("itemServiceFeing")
public class ItemServiceFeing implements IItemService {

	@Autowired
	private IProductoClienteRest clienteFeing;

	@Override
	public List<Item> findAll() {

		return clienteFeing.findAll().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findaById(Long id, Integer cantidad) {

		return new Item(clienteFeing.detalle(id), cantidad);
	}

}
