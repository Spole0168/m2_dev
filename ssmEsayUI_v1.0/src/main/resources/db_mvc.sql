drop database if exists db_mvc;
create database db_mvc;

use db_mvc;
drop table if exists student;    	
create table student (    	
	id varchar(40) not null comment '主键',
	name varchar(20) null comment '姓名',
    sex varchar(4) null  comment '性别',
    age int default 0  comment '年龄',
    
    is_valid char(1) null default '0' comment '是否有效 0-有效，1-删除 ',
    creator varchar(20) comment '创建者',
    create_time timestamp  default current_timestamp comment '创建时间',
    updator varchar(20) comment '最后修改者',
    update_time datetime comment '最后修改时间',
	primary key (id)    
)comment = '学生信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;   

drop table if exists t_user;    	
create table t_user (    	
    id varchar(40) not null comment '主键',
    username varchar(50) null comment '用户登录名 唯一性',
    password varchar(500) null comment '用户密码',
    tel varchar(15) null comment '手机号  唯一性',
    email varchar(60) null comment '用户邮箱  唯一性',
    name varchar(50) null comment '用户名',
    
    is_valid char(1) null default '0' comment '是否有效 0-有效，1-删除 ',
    creator varchar(20) comment '创建者',
    create_time timestamp  default current_timestamp comment '创建时间',
    updator varchar(20) comment '最后修改者',
    update_time datetime comment '最后修改时间',
	primary key (id)    
)comment = '用户信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists t_menu;    	
create table t_menu (    	
    id varchar(40) not null comment '主键',
    
    menu_pid varchar(40) null comment '父级菜单id',
    menu_name varchar(60) null comment '菜单名称',
    menu_code varchar(20) null comment '菜单编码   唯一性',
    menu_type varchar(50) null comment '菜单类型',
    menu_level int null comment '菜单级别 1-一级菜单 2-二级菜单 3-三级菜单',
    menu_url varchar(50) null comment '菜单连接地址 url',
    menu_order varchar(2) null comment '菜单排序',
    
    
    is_valid char(1) null default '0' comment '是否有效 0-有效，1-删除 ',
    creator varchar(20) comment '创建者',
    create_time timestamp  default current_timestamp comment '创建时间',
    updator varchar(20) comment '最后修改者',
    update_time datetime comment '最后修改时间',
	primary key (id)    
)comment = '菜单信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists t_role;    	
create table t_role (    	
    id varchar(40) not null comment '主键',
    
    role_name varchar(60) null comment '角色名称',
    role_code varchar(20) null comment '角色编码  唯一性',
    role_desc varchar(300) null comment '角色描述',
    
    is_valid char(1) null default '0' comment '是否有效 0-有效，1-删除 ',
    creator varchar(20) comment '创建者',
    create_time timestamp  default current_timestamp comment '创建时间',
    updator varchar(20) comment '最后修改者',
    update_time datetime comment '最后修改时间',
	primary key (id)    
)comment = '角色信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop table if exists t_role_menus;    	
create table t_role_menus (    	
    id varchar(40) not null comment '主键',
    
    role_id varchar(40) null comment '角色id',
    menu_id varchar(40) null comment '菜单编码',
    
    is_valid char(1) null default '0' comment '是否有效 0-有效，1-删除 ',
    creator varchar(20) comment '创建者',
    create_time timestamp  default current_timestamp comment '创建时间',
    updator varchar(20) comment '最后修改者',
    update_time datetime comment '最后修改时间',
	primary key (id)    
)comment = '角色菜单关系信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists t_user_roles;    	
create table t_user_roles (    	
    id varchar(40) not null comment '主键',
    
    user_id varchar(40) null comment '用户登录名',
    role_id varchar(40) null comment '角色id',
    
    is_valid char(1) null default '0' comment '是否有效 0-有效，1-删除 ',
    creator varchar(20) comment '创建者',
    create_time timestamp  default current_timestamp comment '创建时间',
    updator varchar(20) comment '最后修改者',
    update_time datetime comment '最后修改时间',
	primary key (id)    
)comment = '角色菜单关系信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO t_menu (id, menu_pid, menu_name, menu_code, menu_type, menu_level, menu_url, menu_order, is_valid, creator, create_time, updator, update_time) VALUES ('3d5c2cea-cc33-11e7-aba4-f832e4d9210b', null, '师生管理', 'school', null, 1, '#', null, '0', null, '2017-11-18 15:36:45', null, null);
INSERT INTO t_menu (id, menu_pid, menu_name, menu_code, menu_type, menu_level, menu_url, menu_order, is_valid, creator, create_time, updator, update_time) VALUES ('7466a39d-cc33-11e7-aba4-f832e4d9210b', '3d5c2cea-cc33-11e7-aba4-f832e4d9210b', '教师管理', 'tecaher', 'leaf', 2, '#', null, '0', null, '2017-11-18 15:38:17', null, null);
INSERT INTO t_menu (id, menu_pid, menu_name, menu_code, menu_type, menu_level, menu_url, menu_order, is_valid, creator, create_time, updator, update_time) VALUES ('7a176e35-cc33-11e7-aba4-f832e4d9210b', '3d5c2cea-cc33-11e7-aba4-f832e4d9210b', '学生管理', 'student', 'leaf', 2, 'url-c2', null, '0', null, '2017-11-18 15:38:27', null, null);
INSERT INTO t_menu (id, menu_pid, menu_name, menu_code, menu_type, menu_level, menu_url, menu_order, is_valid, creator, create_time, updator, update_time) VALUES ('8e8c68b9-cc33-11e7-aba4-f832e4d9210b', 'ef5f167b-cc32-11e7-aba4-f832e4d9210b', '用户管理', 'user', 'leaf', 2, 'url-a1', null, '0', null, '2017-11-18 15:39:01', null, null);
INSERT INTO t_menu (id, menu_pid, menu_name, menu_code, menu_type, menu_level, menu_url, menu_order, is_valid, creator, create_time, updator, update_time) VALUES ('96682ffa-cc33-11e7-aba4-f832e4d9210b', 'ef5f167b-cc32-11e7-aba4-f832e4d9210b', '角色管理', 'role', 'leaf', 2, 'url-a2', null, '0', null, '2017-11-18 15:39:14', null, null);
INSERT INTO t_menu (id, menu_pid, menu_name, menu_code, menu_type, menu_level, menu_url, menu_order, is_valid, creator, create_time, updator, update_time) VALUES ('a39b8d65-cc33-11e7-aba4-f832e4d9210b', 'ef5f167b-cc32-11e7-aba4-f832e4d9210b', '菜单管理', 'menu', 'leaf', 2, '/ssu/menu/list.do', null, '0', null, '2017-11-18 15:39:36', null, null);
INSERT INTO t_menu (id, menu_pid, menu_name, menu_code, menu_type, menu_level, menu_url, menu_order, is_valid, creator, create_time, updator, update_time) VALUES ('b4c2775b-ccfb-11e7-aba4-f832e4d9210b', null, 'C', null, null, null, 'url-C', null, '0', null, '2017-11-19 15:31:36', null, null);
INSERT INTO t_menu (id, menu_pid, menu_name, menu_code, menu_type, menu_level, menu_url, menu_order, is_valid, creator, create_time, updator, update_time) VALUES ('be72edef-cc33-11e7-aba4-f832e4d9210b', '3d5c2cea-cc33-11e7-aba4-f832e4d9210b', '师生关系', 'teacher_student', 'leaf', 2, 'url-c1', null, '0', null, '2017-11-18 15:40:21', null, null);
INSERT INTO t_menu (id, menu_pid, menu_name, menu_code, menu_type, menu_level, menu_url, menu_order, is_valid, creator, create_time, updator, update_time) VALUES ('ef5f167b-cc32-11e7-aba4-f832e4d9210b', null, '系统管理', 'system', null, 1, 'url_A', null, '0', null, '2017-11-18 15:34:34', null, null);











