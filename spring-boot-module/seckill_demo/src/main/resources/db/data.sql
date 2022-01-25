insert into product (id, name, num, sale,version) values(1, 'product1', 50, 0,0);
DELETE FROM order_detail;
update product SET num = 50,sale = 0 WHERE id = 1;