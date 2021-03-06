package com.training.springbootbuyitem.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EnumOperation {

    //Item Operations
    LIST_ITEMS("listItems"),
    GET_ITEM("getItem"),
    GET_ITEMS("getItems"),
    CREATE_ITEM("createItem"),
    UPDATE_ITEM("updateItem"),
    UPDATE_ITEMS("updateItems"),
    DELETE_ITEM("deleteItem"),
    DELETE_ITEMS("deleteItems"),
    DISPATCH_ITEM("dispatchItem"),
    DISPATCH_ITEMS("dispatchItems"),
    BLOCK_ITEM("blockItem"),
    BLOCK_ITEMS("blockItems"),
    BLOCK_ITEM_FOR_USER("blockItemForUser"),
    BLOCK_ITEMS_FOR_USER("blockItemsForUser"),
    RESTOCK_ITEM("restockItem"),
    RESTOCK_ITEMS("restockItems"),
    //User Operations
    GET_USERS("getUsers"),
    GET_USER("getUser"),
    CREATE_USER("createUser"),
    UPDATE_USER("updateUser"),
    //Auth Operations
    CREATE_AUTH_TOKEN("createAuthenticationToken"),
    //Cart Operations
    GET_CART("getCart"),
    GET_USER_CART("getUserCart"),
    CREATE_CART("createCart"),
    UPDATE_CART("updateCart"),
    DELETE_CART("deleteCart");

    private final String name;

    EnumOperation(String name) {
        this.name = name;
    }

    public static EnumOperation getByName(String operationName) {
        return Arrays.stream(values())
            .filter(operation -> operation.name.equals(operationName))
            .findFirst()
            .orElse(null);
    }

}
