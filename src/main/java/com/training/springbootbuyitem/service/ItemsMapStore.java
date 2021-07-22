package com.training.springbootbuyitem.service;

import com.hazelcast.map.MapStore;
import com.training.springbootbuyitem.entity.model.Item;
import com.training.springbootbuyitem.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class ItemsMapStore implements MapStore<Long, Item> {

    private ItemRepository itemDao;

    @Override
    public void store(Long aLong, Item item) {

    }

    @Override
    public void storeAll(Map<Long, Item> map) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void deleteAll(Collection<Long> collection) {

    }

    @Override
    public Item load(Long aLong) {
        return null;
    }

    @Override
    public Map<Long, Item> loadAll(Collection<Long> collection) {
        return null;
    }

    @Override
    public Iterable<Long> loadAllKeys() {
        return null;
    }
}
