package com.training.springbootbuyitem.repository;

import com.training.springbootbuyitem.entity.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT item FROM Item item WHERE item.itemUid IN (:ids)")
    List<Item> findByIds(@Param("ids") List<Long> ids);

}
