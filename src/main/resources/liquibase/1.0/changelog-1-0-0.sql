create table companies(
    id serial primary key,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    website VARCHAR(255),
    description VARCHAR(255),
    status VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
create table countries(
    id serial primary key,
    name VARCHAR(255)
);

create table events(
    id serial primary key,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    expired BOOLEAN,
    category VARCHAR(255),
    event_date TIMESTAMP,
    created_date TIMESTAMP
);

CREATE TABLE images(
    id serial primary key,
    name VARCHAR(255),
    bucket VARCHAR(255),
    path VARCHAR(1000),
    created_at TIMESTAMP
);

CREATE TABLE payment_histories (
    id serial primary key,
    total_payment DOUBLE PRECISION,
    ticket_count INTEGER,
    error_message VARCHAR(255),
    created TIMESTAMP,
    status VARCHAR(255)
);

CREATE TABLE performers (
    id serial primary key,
    name VARCHAR(255),
    surname VARCHAR(255),
    username VARCHAR(255)
);

CREATE TABLE phones (
    id serial primary key,
    phone VARCHAR(50)
);

CREATE TABLE places (
    id serial primary key,
    name VARCHAR(255),
    location VARCHAR(255),
    address VARCHAR(500)
);

CREATE TABLE tickets (
    id serial primary key,
    count INTEGER,
    price DOUBLE PRECISION,
    category VARCHAR(50)
);

CREATE TABLE users (
    id serial primary key,
    name VARCHAR(255),
    surname VARCHAR(255),
    email VARCHAR(255),
    birth_date DATE,
    gender VARCHAR(255)
);

CREATE TABLE wallets (
    id serial primary key,
    balance DOUBLE PRECISION
);