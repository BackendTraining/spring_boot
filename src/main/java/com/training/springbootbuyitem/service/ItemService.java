package com.training.springbootbuyitem.service;

import com.training.springbootbuyitem.entity.model.Item;
import com.training.springbootbuyitem.enums.EnumEntity;
import com.training.springbootbuyitem.enums.EnumItemState;
import com.training.springbootbuyitem.error.EntityNotFoundException;
import com.training.springbootbuyitem.error.GreedyBuyerException;
import com.training.springbootbuyitem.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ItemService implements IItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
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

    @Override
    public List<Item> get(List<Long> ids) {
        return itemRepository.findByIds(ids);
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
        item.setState(
            item.getStock().signum() > 0
                ? EnumItemState.AVAILABLE
                : EnumItemState.RESTOCK
        );
        return itemRepository.save(item);
    }

    @Override
    public void restock(Long id, Integer quantity) {
        Item item = get(id);
        item.setStock(item.getStock().add(BigInteger.valueOf(quantity)));
        save(item);
    }

    //TODO create the dispatch method that use "quantity" items from item stock for the item represented by id
    @Override
    public void dispatch(Long id, Integer quantity) {
        Item item = get(id);
        BigInteger newStock = item.getStock().subtract(BigInteger.valueOf(quantity));

        if (newStock.signum() < 0) {
            throw new GreedyBuyerException(quantity, item.getName(), item.getStock().intValue());
        }

        item.setStock(newStock);
        save(item);
    }

    @Override
    public void block(Long id, Integer quantity) {
        Item item = get(id);
        item.setStock(item.getStock().subtract(BigInteger.valueOf(quantity)));
        save(item);
    }
}
