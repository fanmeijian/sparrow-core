/*
-- Query: SELECT * FROM spr.spr_sysrole
LIMIT 0, 1000

-- Date: 2022-03-30 13:58
*/
INSERT INTO spr_sysrole (`id`,`created_by`,`created_date`,`modified_by`,`modified_date`,`code`,`is_system`,`name`,`data_permission_token_id`) VALUES ('0e1a23631cdb43c7ac8d3b3c537028dd','SparrowSystem','2021-11-11 13:34:02','SparrowSystem','2021-11-11 13:34:02','SYSADMIN','1','系统管理员',NULL);
INSERT INTO spr_sysrole (`id`,`created_by`,`created_date`,`modified_by`,`modified_date`,`code`,`is_system`,`name`,`data_permission_token_id`) VALUES ('7311c3fbcf384de49b841931cde0a864','SparrowSystem','2021-11-11 13:34:02','SparrowSystem','2021-11-11 13:34:02','ADMIN','1','管理员',NULL);
INSERT INTO spr_sysrole (`id`,`created_by`,`created_date`,`modified_by`,`modified_date`,`code`,`is_system`,`name`,`data_permission_token_id`) VALUES ('ff8080817ea4854e017ea49214cb0000','SparrowSystem','2022-01-29 06:42:24','SparrowSystem','2022-03-27 06:44:51','USER','1','普通用户',NULL);
INSERT INTO spr_sysrole (`id`,`created_by`,`created_date`,`modified_by`,`modified_date`,`code`,`is_system`,`name`,`data_permission_token_id`) VALUES ('ff8080817fe92689017fe93493670000','SparrowSystem','2022-01-29 06:42:24','SparrowSystem','2022-03-27 06:44:51','SUPER_SYSADMIN','1','超级系统管理员',NULL);
INSERT INTO spr_sysrole (`id`,`created_by`,`created_date`,`modified_by`,`modified_date`,`code`,`is_system`,`name`,`data_permission_token_id`) VALUES ('ff8080817fe92689017fe934e6030001','SparrowSystem','2022-01-29 06:42:24','SparrowSystem','2022-03-27 06:44:51','SUPER_ADMIN','1','超级管理员',NULL);
insert into spr_user(username,password, disabled,account_expired,account_locked,credentials_expired) values('ROOT','{bcrypt}$2a$10$94ydfzK8tYA1bXgpKRPxZulhk9b1ms9RoG4jLrtdpnQJtqZJO4TES',0,0,0,0);
insert into spr_user(username,password, disabled,account_expired,account_locked,credentials_expired) values('fanmeijian','{bcrypt}$2a$10$94ydfzK8tYA1bXgpKRPxZulhk9b1ms9RoG4jLrtdpnQJtqZJO4TES',0,0,0,0);
insert into spr_user_sysrole(username, sysrole_id) values('ROOT','0e1a23631cdb43c7ac8d3b3c537028dd');
insert into spr_user_sysrole(username, sysrole_id) values('ROOT','7311c3fbcf384de49b841931cde0a864');
insert into spr_user_sysrole(username, sysrole_id) values('ROOT','ff8080817fe92689017fe93493670000');
insert into spr_user_sysrole(username, sysrole_id) values('ROOT','ff8080817fe92689017fe934e6030001');
insert into spr_user_sysrole(username, sysrole_id) values('fanmeijian','7311c3fbcf384de49b841931cde0a864');