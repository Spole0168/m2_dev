--drop database if mvc exists;
--create database mvc;

use mvc;
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













