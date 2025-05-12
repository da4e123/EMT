CREATE TABLE country
(
    id        BIGINT primary key,
    name      varchar(255),
    continent varchar(255)
);

CREATE TABLE author
(
    id         BIGINT primary key,
    name       varchar(255),
    surname    varchar(255),
    country_id BIGINT references country (id)
);

CREATE TABLE book
(
    id               BIGINT primary key,
    name             varchar(255),
    category         varchar(255),
    author_id references author (id),
    available_copies BIGINT
);

CREATE TABLE users(
    username        varchar(255) primary key,
    password        varchar(255),
    name            varchar(255),
    surname         varchar(255),
    role            varchar(255)
);

CREATE TABLE user_book_wishlist(
    user_username   varchar(255) references users(username),
    book_id         BIGINT references book(id),
    PRIMARY KEY (user_username,book_id)
);

CREATE TABLE user_rented_books(
    user_username varchar(255) references users(username),
    book_id         BIGINT references book(id),
    PRIMARY KEY (user_username,book_id)
);

