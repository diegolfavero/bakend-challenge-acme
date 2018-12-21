create database invillia_db;
use invillia_db;

create table if not exists order_acme(
	id integer auto_increment primary key,
    address varchar(255) not null,
    confirm_date date not null,
    status varchar(255) not null
);

create table if not exists order_item_acme(
	id integer auto_increment primary key,
    descricao varchar(255) not null,
    preco decimal(10,2) not null,
    quantidade integer not null
);

create table if not exists order_order_item_acme(
    id_order  integer not null,
    id_order_item integer not null,
    primary key (id_order, id_order_item),
    foreign key (id_order) references order_acme(id),
    foreign key (id_order_item) references order_item_acme(id)
);

create table if not exists payment(
	id integer auto_increment primary key,
    status varchar(255) not null,
    number_card bigint not null,
    paym_date date not null
);

create table if not exists payment_order(
	id_order  integer not null,
    id_payment integer not null,
    primary key (id_order, id_payment),
    foreign key (id_order) references order_acme(id),
    foreign key (id_payment) references payment(id)
);

create table if not exists store(
	id integer auto_increment primary key,
    name varchar(255) not null,
    address varchar(255) not null
);

commit;