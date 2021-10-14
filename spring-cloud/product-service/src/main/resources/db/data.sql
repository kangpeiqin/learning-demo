insert into tb_product (id, name, cover_image, price) values(1, 'product1', '/products/cover/001.png', 100);
insert into tb_product (id, name, cover_image, price) values(2, 'product2', '/products/cover/001.png', 200);
insert into tb_product (id, name, cover_image, price) values(3, 'product3', '/products/cover/001.png', 300);
insert into tb_product (id, name, cover_image, price) values(4, 'product4', '/products/cover/001.png', 400);


insert into tb_product_comment (id, product_id, author_id, content, created) values(1, 1, 1, 'very good', CURRENT_TIMESTAMP());
insert into tb_product_comment (id, product_id, author_id, content, created) values(2, 2, 2, 'I will buy next time', CURRENT_TIMESTAMP());
insert into tb_product_comment (id, product_id, author_id, content, created) values(3, 3, 3, 'funny', CURRENT_TIMESTAMP());