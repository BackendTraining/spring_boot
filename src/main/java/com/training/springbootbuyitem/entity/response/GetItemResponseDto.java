package com.training.springbootbuyitem.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetItemResponseDto extends CreateItemResponseDto {

    private String name;
    private String state;
    private String description;
    private String market;
    private BigInteger stock;
    private BigDecimal priceTag;

}
