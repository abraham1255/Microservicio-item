package com.example.item.iservice;

import java.util.List;



import com.example.item.models.Item;
import com.example.commons.models.Producto;

public interface IItemService {

	public List<Item> findAll();

	public Item findaById(Long id, Integer cantidad);

	public Producto save(Producto producto);

	public Producto update(Producto producto, Long id);

	public void delete(Long id);

}
