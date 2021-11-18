-- 系统角色表，不同的角色拥有不同的权限
drop table if exists sys_role;
create table sys_role
(
    id          bigint auto_increment primary key,
    name        varchar(55) not null,
    description varchar(55) null
);
-- 系统用户表，存储当前系统的用户信息
drop table if exists sys_user;
create table sys_user
(
    id       bigint auto_increment primary key,
    username varchar(55)  not null,
    password varchar(128) not null
);
-- 用户角色表
drop table if exists sys_user_role;
create table sys_user_role
(
    id      bigint auto_increment  primary key,
    user_id bigint not null,
    role_id bigint not null,
    constraint sys_user_role_sys_role_id_fk
        foreign key (role_id) references sys_role (id),
    constraint sys_user_role_sys_user_id_fk
        foreign key (user_id) references sys_user (id)
);

