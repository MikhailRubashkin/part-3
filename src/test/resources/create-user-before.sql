delete from user_role;
delete from usr;

insert into usr(id, username, password, active) values
(1, 'Lexa', '$2a$08$QVXQlu9E8VZjVUFPZ.hdbOssDWWXhKLsxHgJ/xwzl9r8CnE56IKjW', true),
(2, 'mike', '1', true);

insert into user_role(user_id, roles) values
(1, 'ADMIN'), (1, 'USER'),
(2, 'USER');