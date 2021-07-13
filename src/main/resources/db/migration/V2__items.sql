insert into "item-storage-db".itemstorage.item(item_uid, name, description, market, stock, price_tag, state, created_by)
values (1, 'item 1', '', 'PT', 20, 30.0, 'AVAILABLE', 'admin');

insert into "item-storage-db".itemstorage.item(item_uid, name, description, market, stock, price_tag, state, created_by)
values (2, 'item 2', '', 'PT', 10, 10.0, 'AVAILABLE', 'admin');

insert into "item-storage-db".itemstorage.item(item_uid, name, description, market, stock, price_tag, state, created_by)
values (3, 'item 3', '', 'PT', 100, 5.0, 'AVAILABLE', 'admin');

insert into "item-storage-db".itemstorage.item(item_uid, name, description, market, stock, price_tag, state, created_by)
values (4, 'item 4', '', 'PT', 2, 100.0, 'AVAILABLE', 'admin');

insert into "item-storage-db".itemstorage.item(item_uid, name, description, market, stock, price_tag, state, created_by)
values (5, 'item 5', '', 'PT', 20, 2.5, 'AVAILABLE', 'admin');

SELECT setval(
               pg_get_serial_sequence('item-storage-db.itemstorage.item', 'item_uid'),
               coalesce(max(item_uid), 0) + 1, false
           )
FROM "item-storage-db".itemstorage.item;