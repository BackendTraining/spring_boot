create SCHEMA IF NOT EXISTS itemStorage;
alter SCHEMA itemStorage OWNER TO postgres;

create TABLE IF NOT EXISTS itemStorage.item
(
    item_uid         serial,
    name             varchar(255) UNIQUE,
    description      varchar(255),
    market           varchar(255),
    stock            bigint,
    price_tag        decimal,
    state            varchar(50),

    created_by       varchar(100),
    modified_at      TIMESTAMP WITH TIME ZONE,
    created_at       TIMESTAMP WITH TIME ZONE default CURRENT_DATE NOT NULL,
    last_modified_by varchar(100),

    CONSTRAINT pk_item PRIMARY KEY (item_uid)
);

alter table itemStorage.item
    OWNER TO postgres;

create TABLE IF NOT EXISTS itemStorage.user
(
    user_uid         serial,
    first_name       varchar(255),
    last_name        varchar(255),
    email            varchar(100),
    password         varchar(30),

    created_by       varchar(100),
    modified_at      TIMESTAMP WITH TIME ZONE,
    created_at       TIMESTAMP WITH TIME ZONE default CURRENT_DATE NOT NULL,
    last_modified_by varchar(100),

    CONSTRAINT pk_user PRIMARY KEY (user_uid)
);

create TABLE IF NOT EXISTS itemStorage.cart
(
    cart_uid         serial,
    fk_user_uid      serial,

    created_by       varchar(100),
    modified_at      TIMESTAMP WITH TIME ZONE,
    created_at       TIMESTAMP WITH TIME ZONE default CURRENT_DATE NOT NULL,
    last_modified_by varchar(100),

    CONSTRAINT pk_cart PRIMARY KEY (cart_uid)
    CONSTRAINT fk_user FOREIGN KEY (fk_user_id) REFERENCER user(user_uid)
);

create TABLE IF NOT EXISTS itemStorage.cart_item
(
    cart_item_uid    serial
    fk_cart_uid      serial,
    fk_item_uid      serial,
    quantity         bigint,

    created_by       varchar(100),
    modified_at      TIMESTAMP WITH TIME ZONE,
    created_at       TIMESTAMP WITH TIME ZONE default CURRENT_DATE NOT NULL,
    last_modified_by varchar(100),

    CONSTRAINT pk_cart_item PRIMARY KEY (cart_item_uid)
    CONSTRAINT fk_cart FOREIGN KEY (fk_cart_uid) REFERENCER cart(cart_uid)
    CONSTRAINT fk_item FOREIGN KEY (fk_item_uid) REFERENCER item(item_uid)
);

alter table itemStorage.user
    OWNER TO postgres;