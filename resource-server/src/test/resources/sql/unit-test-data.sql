insert into t_user (id, username, password)
values (1, 'user', '$2a$10$UYfUeE54zgz3eb5sMDt2B.I7DZhXQxlboFHAkQjMGe3grcAmWXQCa'),
       (2, 'admin', '$2a$10$P05jOa7SIFEPf58XrPk1euoFcAgzLavabdLUXd8uFIrHZ/pflZr02');
insert into t_user_detail (id, user_id, name, age, email, gender)
values (1, 1, '测试用户', 12, 'test@test.com', 1);