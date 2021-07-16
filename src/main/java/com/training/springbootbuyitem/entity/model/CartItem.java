package com.training.springbootbuyitem.entity.model;

import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigInteger;

@Proxy(lazy = false)
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CartItemUid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_uid", insertable = false, updatable = false)
    private Long itemUid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_uid", insertable = false, updatable = false)
    private Long cartUid;
    private BigInteger qty;

}