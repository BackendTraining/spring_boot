package com.training.springbootbuyitem.repository;

import com.training.springbootbuyitem.entity.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
