/*==============================================================*/
/* table: sys_user 系统用户表                                     */
/*==============================================================*/

drop table if exists sys_user;
create table sys_user(
    id int not null auto_increment comment '主键',
    create_time datetime not null comment '创建时间',
    modify_time datetime comment '最后修改时间',
    user_name  varchar(30) not null comment '用户名称',
    password varchar(64) comment '用户密码',
    mobile varchar(64) comment '电话号码',
    primary key (id)
);

/*==============================================================*/
/* table: trade_payment_order 支付订单表                        */
/* 业务名称_表的作用                                            */
/*==============================================================*/

drop table if exists trade_payment_order;
create table trade_payment_order(
    id int not null auto_increment comment '主键',
    create_time datetime not null comment '创建时间',
    modify_time datetime comment '最后修改时间',
    merchant_order_no  varchar(32) not null comment '商户订单号',
    amount decimal(16,2) comment '订单金额',
    actual_amount decimal(16,2) comment '实付金额',
    payment_way  varchar(50) comment '支付方式：微信、支付宝等',
    pay_status varchar(16) comment '支付状态',
    order_time datetime comment '下单时间',
    transaction_no varchar(64) comment '支付流水号',
    user_id int not null comment '用户id',
    primary key (id),
    unique key uk_order_no (merchant_order_no)
);

/*==============================================================*/
/* table: refund_record 退款记录表                              */
/*==============================================================*/

drop table if exists refund_record;
create table refund_record(
    id int not null auto_increment comment '主键',
    create_time datetime not null comment '创建时间',
    modify_time datetime comment '最后修改时间',
    refund_order_no  varchar(30) not null comment '退款订单号',
    payment_way  varchar(16) comment '原支付方式类型',
    refund_amount  decimal(16,2) comment '订单退款金额',
    refund_transaction_no  varchar(50) not null comment '退款流水号',
    refund_time datetime comment '退款时间',
    primary key (id)
);