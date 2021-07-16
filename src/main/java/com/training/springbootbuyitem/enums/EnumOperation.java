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
    DISPATCH_ITEM("dispatchItem"),
    BLOCK_ITEM("blockItem"),
    BLOCK_ITEM_FOR_USER("blockItemForUser"),
    RESTOCK_ITEM("restockItem"),
    //User Operations
    GET_USERS("getUsers"),
    GET_USER("getUser"),
    CREATE_USER("createUser"),
    UPDATE_USER("updateUser");

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
