package com.training.springbootbuyitem.error;

public class GreedyBuyerException extends RuntimeException {

    public GreedyBuyerException(int cartQuantity, String itemName, int stockQuantity) {
        super(String.format("You ordered %d '%s' but there are only %s in stock", cartQuantity, itemName, stockQuantity));
    }

}
