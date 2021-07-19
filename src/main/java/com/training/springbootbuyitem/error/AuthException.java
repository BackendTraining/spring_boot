package com.training.springbootbuyitem.error;


import com.training.springbootbuyitem.constant.BuyItemConstant;

public class AuthException extends Exception {

    public AuthException(Throwable cause) {
        super(BuyItemConstant.INVALID_CREDENTIALS_MSG, cause);
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }
}
