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
    email            varchar(60),
    password         varchar(100),

    created_by       varchar(100),
    modified_at      TIMESTAMP WITH TIME ZONE,
    created_at       TIMESTAMP WITH TIME ZONE default CURRENT_DATE NOT NULL,
    last_modified_by varchar(100),

    CONSTRAINT pk_user PRIMARY KEY (user_uid)
);

ALTER TABLE itemStorage.user
    OWNER TO postgres;