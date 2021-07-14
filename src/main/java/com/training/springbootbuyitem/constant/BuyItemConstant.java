package com.training.springbootbuyitem.constant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuyItemConstant {

    /**
     * Entities
     */
    public static final String ITEM = "Item";
    public static final String USER = "User";
    /**
     * Header Names
     */
    public static final String TRACE_ID_HEADER = "traceId";
    public static final String OPERATION_HEADER = "operation";
    /**
     * Messages
     */
    public static final String ENTITY_NOT_FOUND_MSG = "Entity {%s} :: UID {%s} not found.";
    public static final String LOGGING_HANDLER_INBOUND_MSG = "Received HTTP [%s] Request to [%s] at [%s]";
    public static final String LOGGING_HANDLER_OUTBOUND_MSG = "Responded with Status [%s] at [%s]";
    public static final String LOGGING_HANDLER_PROCESS_TIME_MSG = "Total processing time [%s] ms";

    private BuyItemConstant() {
    }

}
