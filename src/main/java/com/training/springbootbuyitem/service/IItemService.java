package com.training.springbootbuyitem.service;

import com.training.springbootbuyitem.entity.model.Item;

import java.util.List;

public interface IItemService extends ICrudService<Item> {

    void restock(Long id, Integer quantity);

    void dispatch(Long id, Integer quantity);

    void block(Long id, Integer quantity);

    List<Item> updateList(List<Item> itemList);

    List<Item> updateListById(List<Long> idList);
}
