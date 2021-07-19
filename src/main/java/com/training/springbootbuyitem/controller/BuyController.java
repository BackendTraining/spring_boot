package com.training.springbootbuyitem.controller;

import com.training.springbootbuyitem.entity.model.Item;
import com.training.springbootbuyitem.entity.request.CreateItemRequestDto;
import com.training.springbootbuyitem.entity.request.DispatchItemRequestDto;
import com.training.springbootbuyitem.entity.request.RestockItemRequestDto;
import com.training.springbootbuyitem.entity.response.CreateItemResponseDto;
import com.training.springbootbuyitem.entity.response.GetItemResponseDto;
import com.training.springbootbuyitem.entity.response.UpdateItemResponseDto;
import com.training.springbootbuyitem.service.ItemService;
import com.training.springbootbuyitem.utils.annotation.ServiceOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("/items")
public class BuyController implements IBuyController {

    private final ItemService itemService;
    private final ModelMapper mapper;


    public BuyController(ItemService itemService, ModelMapper mapper) {
        this.itemService = itemService;
        this.mapper = mapper;
    }

    @Override
    @GetMapping
    @ServiceOperation("listItems")
    public ResponseEntity<List<GetItemResponseDto>> listItems() {
        List<GetItemResponseDto> responseDTO = itemService.list()
            .stream()
            .map(i -> mapper.map(i, GetItemResponseDto.class))
            .collect(Collectors.toList());

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    @ServiceOperation("getItem")
    public ResponseEntity<GetItemResponseDto> getItem(@PathVariable("id") Long id) {
        Item item = itemService.get(id);
        GetItemResponseDto responseDTO = mapper.map(item, GetItemResponseDto.class);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Override
    @GetMapping("/ids/")
    @ServiceOperation("getItems")
    public ResponseEntity<List<GetItemResponseDto>> getItems(@RequestParam List<Long> idList) {
        List<GetItemResponseDto> responsoDTO = itemService.get(idList)
            .stream()
            .map(i -> mapper.map(i, GetItemResponseDto.class))
            .collect(Collectors.toList());

        return new ResponseEntity<>(responsoDTO, HttpStatus.OK);
    }

    @Override
    @PostMapping
    @ServiceOperation("createItem")
    public ResponseEntity<CreateItemResponseDto> createItem(@RequestBody @Valid CreateItemRequestDto request) {
        Item itemFromRequest = mapper.map(request, Item.class);
        Item createdItem = itemService.save(itemFromRequest);
        CreateItemResponseDto responseDTO = mapper.map(createdItem, CreateItemResponseDto.class);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @Override
    @PatchMapping("/{id}")
    @ServiceOperation("updateItem")
    public ResponseEntity<UpdateItemResponseDto> updateItem(@PathVariable("id") Long id, @RequestBody Item item) {
        item.setItemUid(id);

        Item itemUpdated = itemService.update(item);
        UpdateItemResponseDto responseDTO = mapper.map(itemUpdated, UpdateItemResponseDto.class);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Override
    @PatchMapping
    @ServiceOperation("updateItems")
    public ResponseEntity<List<UpdateItemResponseDto>> updateItems(@RequestParam List<Long> idList, @RequestBody Item item) {
        List<UpdateItemResponseDto> responseDTO = itemService.updateList(idList, item)
            .stream()
            .map(i -> mapper.map(i, UpdateItemResponseDto.class))
            .collect(Collectors.toList());

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    @ServiceOperation("deleteItem")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") Long id) {
        itemService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/ids")
    @ServiceOperation("deleteItems")
    public ResponseEntity<HttpStatus> deleteItems(@RequestParam List<Long> idList) {
        idList.forEach(i -> itemService.delete(i));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PostMapping("/{id}/dispatch")
    @ServiceOperation("dispatchItem")
    public ResponseEntity<HttpStatus> dispatchItem(@PathVariable("id") Long id,
                                                   @RequestBody DispatchItemRequestDto request) {
        itemService.dispatch(id, request.getQuantity());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/ids/dispatch")
    @ServiceOperation("dispatchItems")
    public ResponseEntity<HttpStatus> dispatchItems(@RequestParam List<Long> idList,
                                                                  @RequestBody DispatchItemRequestDto request) {
        idList.forEach(i -> itemService.dispatch(i, request.getQuantity()));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PostMapping(value = "/{id}/block", produces = "application/json")
    @ServiceOperation("blockItem")
    public ResponseEntity<HttpStatus> blockItem(@PathVariable("id") Long id,
                                                @RequestBody DispatchItemRequestDto request) {
        itemService.block(id, request.getQuantity());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping(value = "/ids/block", produces = "application/json")
    @ServiceOperation("blockItems")
    public ResponseEntity<HttpStatus> blockItems(@RequestParam List<Long> idList,
                                                    @RequestBody DispatchItemRequestDto request) {
        idList.forEach(i -> itemService.block(i, request.getQuantity()));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PostMapping(value = "/{id}/block/{user}", produces = "application/json")
    @ServiceOperation("blockItemForUser")
    public ResponseEntity<HttpStatus> blockItemForUser(@PathVariable("id") Long id, @PathVariable("user") Long userId,
                                                       @RequestBody DispatchItemRequestDto request) {
        itemService.block(id, request.getQuantity());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping(value = "/ids/block/{user}", produces = "application/json")
    @ServiceOperation("blockItemsForUser")
    public ResponseEntity<HttpStatus> blockItemsForUser(@RequestParam List<Long> idList,
                                                 @RequestBody DispatchItemRequestDto request) {
        idList.forEach(i -> itemService.block(i, request.getQuantity()));

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Override
    @PostMapping("/{id}/restock")
    @ServiceOperation("restockItem")
    public ResponseEntity<HttpStatus> restockItem(@PathVariable("id") Long id,
                                                  @RequestBody RestockItemRequestDto request) {
        itemService.restock(id, request.getQuantity());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/ids/restock")
    @ServiceOperation("restockItems")
    public ResponseEntity<HttpStatus> restockItems(@RequestParam List<Long> idList,
                                                        @RequestBody RestockItemRequestDto request) {
        idList.forEach(i -> itemService.restock(i, request.getQuantity()));

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
