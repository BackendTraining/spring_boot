CREATE SCHEMA IF NOT EXISTS itemStorage;
ALTER SCHEMA itemStorage OWNER TO postgres;

CREATE TABLE IF NOT EXISTS itemStorage.item
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

ALTER TABLE itemStorage.item
    OWNER TO postgres;

CREATE TABLE IF NOT EXISTS itemStorage.user
(
    user_uid         serial,
    first_name       varchar(255),
    last_name        varchar(255),
    email            varchar(60) UNIQUE,
    password         varchar(100),

    created_by       varchar(100),
    modified_at      TIMESTAMP WITH TIME ZONE,
    created_at       TIMESTAMP WITH TIME ZONE default CURRENT_DATE NOT NULL,
    last_modified_by varchar(100),

    CONSTRAINT pk_user PRIMARY KEY (user_uid)
);

ALTER TABLE itemStorage.user
    OWNER TO postgres;

CREATE TABLE IF NOT EXISTS itemStorage.cart
(
    cart_uid         serial,
    fk_user_uid      integer,

    created_by       varchar(100),
    modified_at      TIMESTAMP WITH TIME ZONE,
    created_at       TIMESTAMP WITH TIME ZONE default CURRENT_DATE NOT NULL,
    last_modified_by varchar(100),

    CONSTRAINT pk_cart PRIMARY KEY (cart_uid),
    CONSTRAINT fk_user FOREIGN KEY (fk_user_uid) REFERENCES itemStorage.user (user_uid)
);

ALTER TABLE itemStorage.cart
    OWNER TO postgres;

CREATE TABLE IF NOT EXISTS itemStorage.cart_item
(
    cart_item_uid    serial,
    fk_cart_uid      integer,
    fk_item_uid      integer,
    quantity         bigint,

    created_by       varchar(100),
    modified_at      TIMESTAMP WITH TIME ZONE,
    created_at       TIMESTAMP WITH TIME ZONE default CURRENT_DATE NOT NULL,
    last_modified_by varchar(100),

    CONSTRAINT pk_cart_item PRIMARY KEY (cart_item_uid),
    CONSTRAINT fk_cart FOREIGN KEY (fk_cart_uid) REFERENCES itemStorage.cart (cart_uid),
    CONSTRAINT fk_item FOREIGN KEY (fk_item_uid) REFERENCES itemStorage.item (item_uid)
);

ALTER TABLE itemStorage.cart_item
    OWNER TO postgres;

CREATE TABLE IF NOT EXISTS itemStorage.role
(
    role_uid    serial,
    name        varchar(50),
    description varchar(255),

    CONSTRAINT pk_role PRIMARY KEY (role_uid)
);

ALTER TABLE itemStorage.role
    OWNER TO postgres;

CREATE TABLE IF NOT EXISTS itemStorage.user_roles
(
    role_uid integer,
    user_uid integer,

    CONSTRAINT pk_user_roles PRIMARY KEY (role_uid, user_uid),
    CONSTRAINT fk_role FOREIGN KEY (role_uid) REFERENCES itemStorage.role (role_uid),
    CONSTRAINT fk_user FOREIGN KEY (user_uid) REFERENCES itemStorage.user (user_uid)
);

ALTER TABLE itemStorage.user_roles
    OWNER TO postgres;

CREATE TABLE IF NOT EXISTS itemStorage.session
(
    session_uid serial,
    token       varchar(256),

    CONSTRAINT pk_session PRIMARY KEY (session_uid)
);

ALTER TABLE itemStorage.session
    OWNER TO postgres;