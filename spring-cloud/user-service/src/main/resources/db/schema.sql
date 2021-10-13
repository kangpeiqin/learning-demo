drop table if exists tb_user;

/*==============================================================*/
/* Table: tbUser                                                */
/*==============================================================*/
create table tb_user
(
   id                   int unsigned not null auto_increment comment '主键',
   nickname             varchar(50) comment '用户昵称',
   avatar               varchar(255) comment '用户头像',
   primary key (id)
);
