package com.training.springbootbuyitem.entity.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Proxy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Table(name = "item", schema = "itemstorage")
@Proxy(lazy = false)
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Item extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemUid;
    @Column(unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    private String state;
    private String description;
    private String market;
    private BigInteger stock;
    private BigDecimal priceTag;

    public Item(String name) {
        this.name = name;
    }

}
