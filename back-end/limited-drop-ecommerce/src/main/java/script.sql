CREATE DATABASE ECommerce;
USE ecommerce;


-- modificato la colonna stato della tabella pagament
ALTER TABLE Payment
    MODIFY state Enum('CONFIRMED' , 'FAILED');


-- aggiungo la colonna della chiave esterna
ALTER TABLE Product
    ADD COLUMN trello_id varchar(36) NOT NULL;


ALTER TABLE Product
    RENAME COLUMN trello_id TO trolley_id;

-- aggiungo il riferimento della tabella carello alla tabella prodotto un carrello ha n prodotti
ALTER TABLE Product
    ADD CONSTRAINT trolley_fk
        FOREIGN KEY (trolley_id) REFERENCES Trolley(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE;


ALTER TABLE Product
DROP FOREIGN KEY trolley_fk;

ALTER TABLE Product
DROP COLUMN trolley_id;

-- Table user
CREATE TABLE `User`(
                       id varchar(36) PRIMARY KEY NOT NULL,
                       name varchar(255) NOT NULL ,
                       surname varchar(255) NOT NULL ,
                       email varchar(255) NOT NULL UNIQUE ,
                       password varchar(255) NOT NULL ,
                       role Enum('ADMIN' , 'AUTHENTICATED' , 'NOT_AUTHENTICATED')
);


-- Table Order
CREATE TABLE `Order`(
                        id varchar(36) PRIMARY KEY NOT NULL ,
                        date DATETIME  NOT NULL ,
                        total_price double NOT NULL ,
                        state Enum('CONFIRMED' , 'PENDING' , 'CANCELED') NOT NULL,
                        user_id varchar(36) NOT NULL ,
                        FOREIGN KEY (user_id) REFERENCES `User`(id)
);

-- Table Product
CREATE TABLE Product (
                         id varchar(36) PRIMARY KEY NOT NULL ,
                         name varchar(255) NOT NULL ,
                         description varchar(255) NOT NULL ,
                         price double NOT NULL ,
                         image varchar(255) NOT NULL ,
                         tatal_available integer NOT NULL ,
                         drop_id varchar(36) NOT NULL ,
                         FOREIGN KEY(drop_id) REFERENCES `Drop`(id)
);


-- Tabella di join relazione N a N tra product e order
CREATE TABLE ord_prod(
                         id varchar(36) PRIMARY KEY NOT NULL ,
                         quantity integer NOT NULL ,
                         price double NOT NULL ,
                         order_id varchar(36) NOT NULL ,
                         product_id varchar(36) NOT NULL ,
                         FOREIGN KEY(order_id) REFERENCES `Order`(id) ,
                         FOREIGN KEY(product_id) REFERENCES Product(id)
);

-- Table Drop
CREATE TABLE `Drop`(
                       id varchar(36) NOT NULL PRIMARY KEY ,
                       start_date_time datetime NOT NULL ,
                       end_date_time datetime NOT null
);


CREATE TABLE Trolley(
                        id varchar(36) NOT NULL PRIMARY KEY ,
                        total_items integer NOT NULL ,
                        user_id varchar(36) NOT NULL UNIQUE ,
                        FOREIGN KEY(user_id) REFERENCES `User`(id)
);


-- Table Payment
CREATE TABLE Payment(
                        id varchar(36) NOT NULL PRIMARY KEY ,
                        amount_paid double NOT NULL ,
                        date datetime NOT NULL ,
                        state Enum('CONFIRMED' , 'PENDING , 'FAILED') ,
	order_id varchar(36) NOT NULL UNIQUE  ,
-- 	FOREIGN KEY(order_id) REFERENCES `Order`(id)
);