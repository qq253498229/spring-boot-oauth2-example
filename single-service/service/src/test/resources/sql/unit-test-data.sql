insert into t_client (id, client_id, client_secret, registered_redirect_uri)
values (1, 'client', '$2a$10$4exGEs2hdi0C5C8wiDzirOft5WLC7/WJFXVqz7cXhVw6/tnFOstZi', 'http://localhost:4200/login');
insert into t_user (id, username, password)
values (1, 'user', '$2a$10$UYfUeE54zgz3eb5sMDt2B.I7DZhXQxlboFHAkQjMGe3grcAmWXQCa'),
       (2, 'admin', '$2a$10$P05jOa7SIFEPf58XrPk1euoFcAgzLavabdLUXd8uFIrHZ/pflZr02');
insert into t_role (id, name, description)
values (1, 'user', '普通用户'),
       (2, 'admin', '系统管理员')
;
insert into r_user_role (user_id, role_id)
values (1, 1),
       (2, 1),
       (2, 2)
;
insert into t_resource (id, name, description)
values (1, 'showUserPersonalInformation', '用户个人信息'),
       (2, 'changePersonalPassword', '修改个人密码'),
       (3, 'showUserList', '查看用户列表'),
       (4, 'resetPassword', '重置用户密码')
;
insert into r_role_resource (role_id, resource_id)
values (1, 1),
       (1, 2),
       (2, 3),
       (2, 4)
;