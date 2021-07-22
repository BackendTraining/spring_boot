package com.training.springbootbuyitem.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.training.springbootbuyitem.entity.model.Item;
import com.training.springbootbuyitem.enums.EnumEntity;
import com.training.springbootbuyitem.enums.EnumItemState;
import com.training.springbootbuyitem.error.EntityNotFoundException;
import com.training.springbootbuyitem.repository.ItemRepository;
import com.training.springbootbuyitem.utils.MapNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.*;

@Slf4j
@Service
public class ItemService implements IItemService, MapNames {

    private final ItemRepository itemRepository;
    private HazelcastInstance hazelcastInstance;
    private IMap<Long, Item> itemsMap;

    @Autowired
    public ItemService(ItemRepository itemRepository, @Qualifier("ClientInstance") HazelcastInstance hazelcastInstance) {
        this.itemRepository = itemRepository;
        this.hazelcastInstance = hazelcastInstance;
    }

    @PostConstruct
    public void init(){
        itemsMap = hazelcastInstance.getMap(ITEMS_MAP);
    }

    @Override
    public List<Item> list() {
        return itemRepository.findAll();
    }

    @Override
    public Item get(Long id) {
        return itemRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(EnumEntity.ITEM.name(), id));
    }

    public void addItemHazelcast(Item item){
        itemsMap.put(item.getItemUid(), item);
    }
    public void addItemsHazelcast(Collection<Item> items){
        Map<Long, Item> itemsLocalMap = new HashMap<>();

        for ( Item item : items){
            itemsLocalMap.put(item.getItemUid(), item);
        }

        itemsMap.putAll(itemsLocalMap);
    }

    // TODO - ex 10
    @Override
    public List<Item> get(List<Long> ids) {
        List<Item> itemList = new ArrayList<>();
        ids.forEach(id -> {
            Item item = itemRepository.findById(id).orElse(new Item());

            itemList.add(item);
        });

        return itemList;
    }

    @Override
    public void delete(Long id) {
        itemRepository.delete(get(id));
    }

    @Override
    public Item update(Item item) {
        Long id = item.getItemUid();
        Item persistedItem = itemRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException(EnumEntity.ITEM.name(), id));
        if (StringUtils.hasText(item.getName())) {
            persistedItem.setName(item.getName());
        }
        if (StringUtils.hasText(item.getDescription())) {
            persistedItem.setDescription(item.getDescription());
        }
        if (StringUtils.hasText(item.getMarket())) {
            persistedItem.setMarket(item.getMarket());
        }
        if (item.getStock() != null && item.getStock().intValue() >= 0) {
            persistedItem.setStock(item.getStock());
        }
        if (item.getPriceTag() != null && item.getPriceTag().longValue() >= 0.0) {
            persistedItem.setPriceTag(item.getPriceTag());
        }
        return itemRepository.save(persistedItem);
    }

    @Override
    @Transactional
    public List<Item> updateList(List<Long> idList, Item newItemInfo) {
        List<Item> updatedItemList = new ArrayList<>();
        idList.forEach(item -> {
            newItemInfo.setItemUid(item);
            updatedItemList.add(update(newItemInfo));
        });
        return updatedItemList;
    }

    @Override
    public Item save(Item item) {
        item.setState(EnumItemState.AVAILABLE.name());
        return itemRepository.save(item);
    }


    @Override
    public void restock(Long id, Integer quantity) {
        Item item = get(id);
        item.setStock(item.getStock().add(BigInteger.valueOf(quantity)));
        save(item);
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
