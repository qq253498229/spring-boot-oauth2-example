insert into t_user (id, username, password)
values (1, 'user', '$2a$10$UYfUeE54zgz3eb5sMDt2B.I7DZhXQxlboFHAkQjMGe3grcAmWXQCa'),
       (2, 'admin', '$2a$10$P05jOa7SIFEPf58XrPk1euoFcAgzLavabdLUXd8uFIrHZ/pflZr02');
insert into t_user_detail (id, user_id, name, age, email, gender)
values (1, 1, '测试用户', 12, 'test@test.com', 1);
insert into t_user_detail (id, user_id, name, age, email, gender)
values (2, 2, '管理员用户', 66, 'admin@test.com', 2);
insert into t_role (id, name, description)
values (1, 'user', '普通用户'),
       (2, 'admin', '系统管理员')
;
insert into r_user_role (user_id, role_id)
values (1, 1),
       (2, 1),
       (2, 2)
;