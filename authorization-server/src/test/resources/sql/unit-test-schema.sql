create schema `spring-boot-oauth2-example`;

create table `t_client`
(
    id                             int auto_increment primary key,
    client_id                      varchar(100)                                                     not null,
    client_secret                  varchar(100)                                                     not null,
    access_token_validity_seconds  int          default 7200                                        not null comment 'access_token 有效期，单位：秒，默认7200秒，即2小时。',
    refresh_token_validity_seconds int          default 172800                                      null comment 'refresh_token 有效期，单位：秒。默认172800秒，即2天。',
    authorized_grant_types_str     varchar(200) default 'authorization_code,refresh_token,password' not null comment '授权类型',
    registered_redirect_uri_str    varchar(500)                                                     null comment '跳转 uri',
    scope_str                      varchar(200) default 'app'                                       not null,
    auto_approve_scope             varchar(200) default 'app'                                       null,
    authorities_str                varchar(200)                                                     null,
    additional_information_str     varchar(200)                                                     null,
    resource_ids_str               varchar(200)                                                     null,
    constraint t_client_client_id_uindex unique (client_id)
);

create table t_user
(
    id       int auto_increment primary key,
    username varchar(100)  not null comment '用户名',
    password varchar(100)  not null comment '密码，根据需求加密后存储',
    enable   bit default 1 not null comment '是否可用，1为可用，0为不可用，默认1可用',
    constraint t_user_username_uindex unique (username)
);

create table t_role
(
    id          int auto_increment primary key,
    name        varchar(200) not null comment '角色名',
    description varchar(200) not null comment '角色描述',
    constraint t_role_name_uindex unique (name)
);

create table t_resource
(
    id          int auto_increment primary key,
    name        varchar(40)  not null comment '资源名，唯一，英文，尽量短',
    description varchar(200) not null comment '资源描述',
    constraint t_resource_name_uindex unique (name)
);

create table r_role_resource
(
    role_id     int not null,
    resource_id int not null,
    primary key (role_id, resource_id)
);

create table r_user_role
(
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id)
);

