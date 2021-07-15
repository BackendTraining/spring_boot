package com.training.springbootbuyitem.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EnumOperation {

    //Item Operations
    CREATE_ITEM("createItem"),
    UPDATE_ITEM("updateItem"),
    GET_ITEM("getItem"),
    LIST_ITEM("listItem"),
    DELETE_ITEM("deleteItem"),
    RESTOCK_ITEM("restockItem"),
    DISPATCH_ITEM("dispatchItem"),
    //User Operations
    GET_USER("getUser"),
    GET_USERS("getUsers"),
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
