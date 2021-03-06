insert into "item-storage-db".itemstorage.role(role_uid, name, description)
values (1, 'USER', 'User role');

insert into "item-storage-db".itemstorage.role(role_uid, name, description)
values (2, 'ADMIN', 'Admin role');


SELECT setval(
               pg_get_serial_sequence('item-storage-db.itemstorage.role', 'role_uid'),
               coalesce(max(role_uid), 0) + 1, false
           )
FROM "item-storage-db".itemstorage.role;