package com.training.springbootbuyitem.service;

import com.training.springbootbuyitem.entity.model.Item;
import com.training.springbootbuyitem.entity.response.UpdateItemResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IItemService extends ICrudService<Item> {

    void restock(Long id, Integer quantity);

    void dispatch(Long id, Integer quantity);

    void block(Long id, Integer quantity);

    List<Item> updateList(List<Long> itemList, Item newItemInfo);
}