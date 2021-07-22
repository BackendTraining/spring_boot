insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (2, 1);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 2);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 3);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 4);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 5);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 6);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 7);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 8);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 9);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 10);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 11);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 12);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 13);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 14);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 15);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 16);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 17);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 18);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 19);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 20);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 21);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 22);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 23);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 24);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 25);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 26);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 27);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 28);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 29);

insert into "item-storage-db".itemstorage.user_roles(role_uid, user_uid)
values (1, 30);

SELECT setval(
               pg_get_serial_sequence('item-storage-db.itemstorage.role', 'role_uid'),
               coalesce(max(role_uid), 0) + 1, false
           )
FROM "item-storage-db".itemstorage.role;