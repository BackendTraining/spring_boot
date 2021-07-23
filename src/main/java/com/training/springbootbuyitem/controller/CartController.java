package com.training.springbootbuyitem.controller;

import com.training.springbootbuyitem.component.CurrentUserHelper;
import com.training.springbootbuyitem.entity.model.Cart;
import com.training.springbootbuyitem.entity.request.CreateCartRequestDTO;
import com.training.springbootbuyitem.service.CartService;
import com.training.springbootbuyitem.utils.annotation.ServiceOperation;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;
    private final CurrentUserHelper currentUserHelper;

    public CartController(CartService cartService, CurrentUserHelper currentUserHelper) {
        this.cartService = cartService;
        this.currentUserHelper = currentUserHelper;
    }

    @GetMapping("/{id}")
    @ServiceOperation("getCart")
    public ResponseEntity<Cart> getCart(@PathVariable("id") Long id) {
        Cart cart = cartService.get(id);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/user/")
    @ServiceOperation("getUserCart")
    public ResponseEntity<Cart> getUserCart() {
        Long userUid = currentUserHelper.getUserUid();
        Cart cart = cartService.getUserCart(userUid);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping
    @ServiceOperation("createCart")
    public ResponseEntity<Cart> createCart(@RequestBody CreateCartRequestDTO request) {
        Long userUid = currentUserHelper.getUserUid();
        Cart cart = cartService.save(request.getCartUid(), userUid, request.getCartItems());

        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }


    @PatchMapping("/{id}")
    @ServiceOperation("updateCart")
    public ResponseEntity<Cart> updateCart(@PathVariable("id") Long id, @RequestBody CreateCartRequestDTO requestDTO) {
        Long userUid = currentUserHelper.getUserUid();
        Cart cart = cartService.save(id, userUid, requestDTO.getCartItems());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ServiceOperation("deleteCart")
    public ResponseEntity<HttpStatus> deleteCart(@PathVariable("id") Long id) {
        cartService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
