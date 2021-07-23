package com.training.springbootbuyitem.entity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCartRequestDTO {

    private Long cartUid;

    private List<CartItemRequestDTO> cartItems;


}
