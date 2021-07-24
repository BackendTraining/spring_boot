package com.training.springbootbuyitem.service;

import com.training.springbootbuyitem.entity.model.Cart;
import com.training.springbootbuyitem.entity.model.CartItem;
import com.training.springbootbuyitem.entity.model.Item;
import com.training.springbootbuyitem.entity.model.User;
import com.training.springbootbuyitem.entity.request.CartItemRequestDTO;
import com.training.springbootbuyitem.enums.EnumEntity;
import com.training.springbootbuyitem.error.EntityNotFoundException;
import com.training.springbootbuyitem.repository.CartItemRepository;
import com.training.springbootbuyitem.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public Cart get(Long cartUid) {
        return cartRepository.findById(cartUid)
            .orElseThrow(() -> new EntityNotFoundException(EnumEntity.CART.name(), cartUid));
    }

    public Cart save(Long cartUid, Long userUid, List<CartItemRequestDTO> itemsDTO) {
        Cart cart;
        if (cartUid != null) {
            cart = cartRepository.getOne(cartUid);
        } else {
            cart = cartRepository.save(Cart.builder().user(new User(userUid)).build());
        }

        List<CartItem> cartItems = cart.getItemList() != null ? cart.getItemList() : new ArrayList<>();

        itemsDTO.forEach(itemDTO -> {
            Item build = Item.builder().itemUid(itemDTO.getItemUid()).build();
            BigInteger qty = BigInteger.valueOf(itemDTO.getQuantity());

            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setItem(build);
            cartItem.setQuantity(qty);
            cartItems.add(cartItem);
        });

        List<CartItem> cartItemsSaved = cartItemRepository.saveAll(cartItems);

        cart.setItemList(cartItemsSaved);
        return cartRepository.save(cart);
    }

    public Cart save(Long cartUid, Long userUid, CartItemRequestDTO itemDTO) {
        return save(cartUid, userUid, Collections.singletonList(itemDTO));
    }

    public void delete(Long cartUid) {
        cartRepository.deleteById(cartUid);
    }

    public Cart getUserCart(Long userUid) {
        return cartRepository.getUserCart(userUid);
    }
}
