package com.training.springbootbuyitem.repository;

import com.training.springbootbuyitem.entity.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT cart FROM Cart cart where cart.user.userUid = :userUid")
    Cart getUserCart(@Param("userUid") Long userUid);

}
