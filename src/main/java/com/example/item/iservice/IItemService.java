package com.example.item.iservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.item.models.Item;

public interface IItemService {
	
	
	public List<Item> findAll();
	public Item  findaById(Long id, Integer cantidad);

}
