package com.training.springbootbuyitem.controller;

import com.training.springbootbuyitem.entity.model.Item;
import com.training.springbootbuyitem.entity.request.CreateItemRequestDto;
import com.training.springbootbuyitem.entity.request.DispatchItemRequestDto;
import com.training.springbootbuyitem.entity.request.RestockItemRequestDto;
import com.training.springbootbuyitem.entity.response.CreateItemResponseDto;
import com.training.springbootbuyitem.entity.response.GetItemResponseDto;
import com.training.springbootbuyitem.entity.response.UpdateItemResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IBuyController {

    @PostMapping
    ResponseEntity<CreateItemResponseDto> createItem(@RequestBody CreateItemRequestDto request);

    @GetMapping("/item/{id}")
    ResponseEntity<GetItemResponseDto> getItem(@PathVariable("id") Long id);

    @PatchMapping("/item/{id}")
    ResponseEntity<UpdateItemResponseDto> updateItem(@PathVariable("id") Long id, @RequestBody Item item);

    @PatchMapping("/list/ids/update")
    ResponseEntity<List<UpdateItemResponseDto>> updateItems(@RequestParam List<Long> idList, @RequestBody Item item);

    @DeleteMapping("/item/{id}")
    ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") Long id);

    @GetMapping
    ResponseEntity<List<GetItemResponseDto>> listItems();

    @GetMapping("/list/ids")
    ResponseEntity<List<GetItemResponseDto>> getItems(@RequestParam List<Long> idList);

    @PostMapping("/item/{id}/dispatch")
    ResponseEntity<HttpStatus> dispatchItem(@PathVariable("id") Long id, @RequestBody DispatchItemRequestDto request);

    @PostMapping("/item/{id}/block")
    ResponseEntity<HttpStatus> blockItem(@PathVariable("id") Long id, @RequestBody DispatchItemRequestDto request);

    @PostMapping("/item/{id}/{user}/block")
    ResponseEntity<HttpStatus> blockItemForUser(@PathVariable("id") Long id, @PathVariable("user") Long userId,
                                                @RequestBody DispatchItemRequestDto request);

    @PostMapping("/item/{id}/restock")
    ResponseEntity<HttpStatus> restockItem(@PathVariable("id") Long id, @RequestBody RestockItemRequestDto request);

}
