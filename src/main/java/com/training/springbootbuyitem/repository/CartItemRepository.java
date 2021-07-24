package com.training.springbootbuyitem.repository;

import com.training.springbootbuyitem.entity.model.CartItem;
import com.training.springbootbuyitem.entity.response.CartItemResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("select new com.training.springbootbuyitem.entity.response.CartItemResponseDTO(ci.item.name, ci.item.description, ci.quantity) from CartItem ci where ci.cart.cartUid = :id")
    List<CartItemResponseDTO> findAllItemsInCart(@Param("id") Long cartUid);

}
