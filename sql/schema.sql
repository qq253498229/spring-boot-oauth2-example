create database if not exists `spring-boot-oauth2-example`;

create table if not exists `t_client`
(
    id                             int auto_increment primary key,
    client_id                      varchar(50)                                                      not null,
    client_secret                  varchar(100)                                                     not null,
    access_token_validity_seconds  int          default 7200                                        not null comment 'access_token 有效期，单位：秒，默认7200秒，即2小时。',
    refresh_token_validity_seconds int          default 172800                                      null comment 'refresh_token 有效期，单位：秒。默认172800秒，即2天。',
    authorized_grant_types         varchar(200) default 'authorization_code,refresh_token,password' not null comment '授权类型',
    registered_redirect_uri        varchar(500)                                                     null comment '跳转 uri',
    scope                          varchar(200) default 'app'                                       not null,
    auto_approve_scope             varchar(200) default 'app'                                       null,
    authorities                    varchar(200)                                                     null,
    additional_information         varchar(200)                                                     null,
    resource_ids                   varchar(200)                                                     null,
    constraint t_client_client_id_uindex unique (client_id)
) comment 'client表';

create table if not exists t_resource
(
    id          int auto_increment primary key,
    name        varchar(40)  not null comment '资源名，唯一，英文，尽量短',
    description varchar(200) not null comment '资源描述',
    constraint t_resource_name_uindex unique (name)
) comment '资源表';

create table if not exists r_role_resource
(
    role_id     int not null,
    resource_id int not null,
    primary key (role_id, resource_id)
) comment '角色资源中间表';

create table if not exists t_user
(
    id       int auto_increment primary key,
    username varchar(50)      not null comment '用户名',
    password varchar(50)      not null comment '密码，根据需求加密后存储',
    enable   bit default b'1' not null comment '是否可用，1为可用，0为不可用，默认1可用',
    constraint t_user_username_uindex unique (username)
) comment '用户表';

create table if not exists r_user_role
(
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id)
) comment '用户角色中间表';

