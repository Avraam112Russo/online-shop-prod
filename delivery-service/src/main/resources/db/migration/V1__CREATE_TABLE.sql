create table orders_to_delivery(
    id SERIAL PRIMARY KEY,
    order_id integer not null unique,
    username varchar (64) not null,
    delivery_price integer not null,
    email varchar (128) not null,
    delivery_address varchar (256) not null,
    phone_number varchar (32) not null
);
create table order_items(
                            id serial PRIMARY KEY,
                            sku_code varchar (64) not null,
                            quantity integer not null,
                            price integer not null,
                            orders_to_delivery_id integer not null,
                            foreign key (orders_to_delivery_id) references orders_to_delivery(id)
    )
