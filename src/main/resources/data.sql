/*
drop table if exists PRODUCT;
CREATE TABLE PRODUCT (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              barcode INT,
                              product_name VARCHAR(250) NOT NULL,
                              price_of_sell double not null ,
                              measure_type boolean,
                              quantity double not null ,
                              created_time date not null ,
                              last_updated_time date not null,
                     price_of_buy double not  null



);

INSERT INTO PRODUCT(id, barcode, product_name, price_of_sell, measure_type, quantity, created_time, last_updated_time,price_of_buy)
VALUES (1,1235,'cola' ,10000.0,true,25.0,'2020-02-03','2021-02-03',8000.0);


INSERT INTO PRODUCT(id, barcode, product_name, price_of_sell, measure_type, quantity, created_time, last_updated_time,price_of_buy)
VALUES (2,2235,'Fanta' ,10000.0,true,25.0,'2020-02-03','2021-02-03',7000);


INSERT INTO PRODUCT(id, barcode, product_name, price_of_sell, measure_type, quantity, created_time, last_updated_time, price_of_buy)
VALUES (3,3235,'Adrenalin' ,12000.0,true,25.0,'2020-02-03','2021-02-03',10000);


INSERT INTO PRODUCT(id, barcode, product_name, price_of_sell, measure_type, quantity, created_time, last_updated_time,price_of_buy)
VALUES (4,4235,'Flash' ,8000.0,true,25.0,'2020-02-03','2021-02-03',6900);


INSERT INTO PRODUCT(id, barcode, product_name, price_of_sell, measure_type, quantity, created_time, last_updated_time,price_of_buy)
VALUES (5,5235,'Monster' ,9000.0,true,25.0,'2020-02-03','2021-02-03',7100);


INSERT INTO PRODUCT(id, barcode, product_name, price_of_sell, measure_type, quantity, created_time, last_updated_time,price_of_buy)
VALUES (6,6235,'Jaguar' ,12000.0,true,25.0,'2020-02-03','2021-02-03');


INSERT INTO PRODUCT(id, barcode, product_name, price_of_sell, measure_type, quantity, created_time, last_updated_time,price_of_buy)
VALUES (7,7235,'Guava' ,8000.0,true,25.0,'2020-02-03','2021-02-03',6800);


INSERT INTO PRODUCT(id, barcode, product_name, price_of_sell, measure_type, quantity, created_time, last_updated_time,price_of_buy)
VALUES (8,8235,'Meva sok' ,9000.0,true,25.0,'2020-02-03','2021-02-03',7300);


INSERT INTO PRODUCT(id, barcode, product_name, price_of_sell, measure_type, quantity, created_time, last_updated_time,price_of_buy)
VALUES (9,9235,'bliss' ,11000.0,true,25.0,'2020-02-03','2021-02-03',9400);


INSERT INTO PRODUCT(id, barcode, product_name, price_of_sell, measure_type, quantity, created_time, last_updated_time,price_of_buy)
VALUES (10,5335,'Montella 0.5' ,2000.0,true,25.0,'2020-02-03','2021-02-03',1700);


INSERT INTO PRODUCT(id, barcode, product_name, price_of_sell, measure_type, quantity, created_time, last_updated_time,price_of_buy)
VALUES (11,6535,'hydrolife 1l' ,3000.0,true,25.0,'2020-02-03','2021-02-03',2500);

*//*


create table unload(
    id long not null auto_increment ,
    amount double not null ,
    price_of_buy double not null ,
    created_time date not null
);

INSERT INTO unload (id,amount,price_of_buy,created_time) values ( 1, 50 , 1000.0 ,'2020-02-04' );
*/
