create schema `spring-boot-oauth2-example`;

create table t_user
(
    id       int auto_increment primary key,
    username varchar(100)  not null comment '用户名',
    password varchar(100)  not null comment '密码，根据需求加密后存储',
    enable   bit default 1 not null comment '是否可用，1为可用，0为不可用，默认1可用',
    constraint t_user_username_uindex unique (username)
);

create table t_user_detail
(
    id      int auto_increment primary key,
    user_id int          not null comment '关联t_user表id',
    name    varchar(20)  null comment '用户姓名',
    age     int          null comment '年龄',
    email   varchar(100) null comment '电子邮箱',
    gender  int          null comment '性别，1为男性，2为女性，3为其它',
    constraint t_user_detail_user_id_uindex unique (user_id)
);

create table t_role
(
    id          int auto_increment primary key,
    name        varchar(200) not null comment '角色名',
    description varchar(200) not null comment '角色描述',
    constraint t_role_name_uindex unique (name)
);

create table r_user_role
(
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id)
);
