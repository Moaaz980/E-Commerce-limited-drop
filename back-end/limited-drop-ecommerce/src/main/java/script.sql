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

ALTER TABLE `User`
    MODIFY `role` Enum('ADMIN' , 'USER');

ALTER TABLE `Order`
    MODIFY total_price decimal(10 , 2) NOT NULL;

ALTER TABLE Product
    MODIFY price decimal(10 , 2) NOT NULL;

ALTER TABLE ord_prod
    MODIFY price decimal(10 , 2) NOT NULL;

ALTER TABLE Payment
    MODIFY state Enum('CONFIRMED' , 'FAILED');

ALTER TABLE `User`
    MODIFY role Enum('ADMIN' , 'AUTHENTICATED');
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

-- Table Drop
CREATE TABLE `Drop`(
                       id varchar(36) NOT NULL PRIMARY KEY ,
                       start_date_time datetime NOT NULL ,
                       end_date_time datetime NOT null
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


CREATE TABLE Trolley(
                        id varchar(36) NOT NULL PRIMARY KEY ,
                        total_items integer NOT NULL ,
                        user_id varchar(36) NOT NULL UNIQUE ,
                        FOREIGN KEY(user_id) REFERENCES `User`(id)
);


CREATE TABLE tro_prod(
                         id varchar(36) PRIMARY KEY NOT NULL ,
                         trolley_id varchar(36) NOT NULL ,
                         product_id  varchar(36) NOT NULL ,
                         FOREIGN KEY (trolley_id) REFERENCES Trolley(id) ,
                         FOREIGN KEY (product_id) REFERENCES Product(id)
);

-- Table Payment
CREATE TABLE Payment(
                        id varchar(36) NOT NULL PRIMARY KEY ,
                        amount_paid double NOT NULL ,
                        date datetime NOT NULL ,
                        state Enum('CONFIRMED' , 'PENDING' , 'FAILED') ,
                        order_id varchar(36) NOT NULL UNIQUE  ,
                        FOREIGN KEY(order_id) REFERENCES `Order`(id)
);


INSERT INTO
    `user`
(id , name , surname , email , password , role)
VALUES
    (UUID() , "Mizo" , "Bahnassawi" , "mizo34@gmail.com" , SHA("1234") , "ADMIN");

DESCRIBE trolley;

UPDATE `user`
SET password = "$2a$12$3ASgoZ45788lkOHVl6rx5ONSfSqGZ8lCo9CyOGIslwTwwWhx7pdwW"
WHERE password = "fba7ec2846832d1f947efb770d58a333b198543a";


ALTER TABLE trolley
    ADD user_id varchar(36) NOT NULL UNIQUE;

ALTER TABLE trolley
    ADD CONSTRAINT fk_user_id
        FOREIGN KEY(user_id)
            REFERENCES `user`(id);


ALTER TABLE `user`
    MODIFY ROLE Enum('USER' , 'ADMIN') NOT NULL;



