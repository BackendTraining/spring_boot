package com.training.springbootbuyitem.service;

import com.training.springbootbuyitem.entity.model.Item;
import com.training.springbootbuyitem.enums.EnumEntity;
import com.training.springbootbuyitem.enums.EnumItemState;
import com.training.springbootbuyitem.error.EntityNotFoundException;
import com.training.springbootbuyitem.repository.ItemRepository;
import com.training.springbootbuyitem.utils.properties.ItemStorageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ItemService implements IItemService {

    private final ItemRepository itemRepository;

    private final ItemStorageProperties itemStorageProperties;

    private final RestTemplate restTemplate;

    public ItemService(ItemRepository itemRepository, ItemStorageProperties itemStorageProperties, RestTemplate restTemplate) {
        this.itemRepository = itemRepository;
        this.itemStorageProperties = itemStorageProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Item> list() {
        return itemRepository.findAll();
    }

    @Override
    public Item get(Long id) {
        return itemRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException(EnumEntity.ITEM.name(), id));
    }

    // TODO - ex 10
    @Override
    public List<Item> get(List<Long> id) {
        return new ArrayList<>();
    }

    @Override
    public void delete(Long id) {
        itemRepository.delete(get(id));
    }

    @Override
    public Item update(Item item) {
        Item persistedItem = get(item.getItemUid());
        if (!StringUtils.hasText(item.getName())) {
            persistedItem.setName(item.getName());
        }
        if (!StringUtils.isEmpty(item.getDescription())) {
            persistedItem.setDescription(item.getDescription());
        }
        if (!StringUtils.isEmpty(item.getMarket())) {
            persistedItem.setMarket(item.getMarket());
        }
        if (item.getStock() != null && item.getStock().intValue() >= 0) {
            persistedItem.setStock(item.getStock());
        }
        if (item.getPriceTag() != null && item.getPriceTag().longValue() >= 0.0) {
            persistedItem.setPriceTag(item.getPriceTag());
        }
        return persistedItem;
    }

    @Override
    public Item save(Item item) {
        item.setState(EnumItemState.AVAILABLE.name());
        return itemRepository.save(item);
    }


    @Override
    public void restock(Long id, Integer quantity) {
        // TODO
    }

    //TODO create the dispatch method that use "quantity"  items from item stock for the item represented by id
    @Override
    public void dispatch(Long id, Integer quantity) {
        Item item = get(id);
        item.setStock(item.getStock().subtract(BigInteger.valueOf(quantity)));
        save(item);
    }

    @Override
    public void block(Long id, Integer quantity) {
        Item item = get(id);
        item.setStock(item.getStock().subtract(BigInteger.valueOf(quantity)));
        save(item);
    }
}
