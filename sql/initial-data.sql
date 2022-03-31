# client表
# client:secret
insert ignore into t_client (id, client_id, client_secret, registered_redirect_uri_str)
values (1, 'client', '$2a$10$4exGEs2hdi0C5C8wiDzirOft5WLC7/WJFXVqz7cXhVw6/tnFOstZi', 'http://localhost:4200/login');
#用户表
# user:user
# admin:admin
insert ignore into t_user (id, username, password)
values (1, 'user', '$2a$10$UYfUeE54zgz3eb5sMDt2B.I7DZhXQxlboFHAkQjMGe3grcAmWXQCa'),
       (2, 'admin', '$2a$10$P05jOa7SIFEPf58XrPk1euoFcAgzLavabdLUXd8uFIrHZ/pflZr02');
# 角色表
insert ignore into t_role (id, name, description)
values (1, 'user', '普通用户'),
       (2, 'admin', '系统管理员')
;
# 用户角色关系表
insert ignore into r_user_role (user_id, role_id)
values (1, 1),
       (2, 1),
       (2, 2)
;
# 资源表
insert ignore into t_resource (id, name, description)
values (1, 'showPersonalRole', '查看个人角色'),
       (2, 'updatePersonalDetail', '编辑个人信息'),
       (3, 'showUserList', '查看用户列表'),
       (4, 'showUserDetail', '查看用户详情'),
       (5, 'resetUserPassword', '重置用户密码')
;
# 角色资源关系表
insert ignore into r_role_resource (role_id, resource_id)
values (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (2, 5)
;