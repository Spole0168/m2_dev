drop database if exists db_homebank;
CREATE DATABASE db_homebank ;
USE db_homebank;

DROP TABLE IF EXISTS buget;
CREATE TABLE buget (
  id int(10) NOT NULL AUTO_INCREMENT,
  month char(6) NOT NULL,
  value varchar(10) NOT NULL,
  unit varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


insert  into buget(id,month,value,unit) values 
(1,'201310','1000','RMB'),
(2,'201311','2000','RMB'),
(4,'201312','1000','RMB'),
(5,'201401','1000','RMB');


DROP TABLE IF EXISTS datadict;

CREATE TABLE datadict (
  id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  catalog varchar(50) DEFAULT NULL COMMENT '数据类型',
  code varchar(50) DEFAULT NULL COMMENT '数据代码',
  codename varchar(200) DEFAULT NULL COMMENT '数据含义',
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;



insert  into datadict(id,catalog,code,codename) values 
(1,'root','payout','支出'),
(2,'root','income','收入'),
(3,'root','currency','币种'),
(4,'root','card','卡类别'),
(40,'payout','FZ','房租'),
(38,'card','BJYH','北京银行'),
(33,'currency','MY','美元'),
(32,'currency','RMB','元'),
(39,'card','ZSYH','招商银行'),
(41,'payout','CS','超市'),
(42,'payout','MC','买菜');


DROP TABLE IF EXISTS menu;

CREATE TABLE menu (
  id decimal(10,0) NOT NULL,
  menuname varchar(50) NOT NULL,
  url varchar(100) DEFAULT NULL,
  parentid decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



insert  into menu(id,menuname,url,parentid) values 
(1,'基础信息管理',NULL,1),
(2,'基础数据','jsp/datadict.jsp',1),
(3,'收支管理',NULL,3),
(4,'收入管理','jsp/income.jsp',3),
(5,'支出管理','jsp/payout.jsp',3),
(6,'预算管理',NULL,6),
(7,'预算设定','jsp/buget.jsp',6),
(8,'借入借出','jsp/borrow.jsp',3);



DROP TABLE IF EXISTS payments;

CREATE TABLE payments (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  value varchar(10) NOT NULL COMMENT '收支值',
  name varchar(200) DEFAULT NULL COMMENT '收支名称',
  paymenttype char(1) NOT NULL COMMENT '收或支',
  unit varchar(50) NOT NULL COMMENT '值的单位',
  descript varchar(1000) DEFAULT NULL COMMENT '描述',
  day char(8) NOT NULL COMMENT '收支时间',
  crttime datetime DEFAULT NULL COMMENT '记录时间',
  type varchar(50) NOT NULL COMMENT '收支类型',
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;


insert  into payments(id,value,name,paymenttype,unit,descript,day,crttime,type) values 
(55,'1000','衣服','2','RMB','','20150409','2015-04-09 10:51:58','MYF'),
(54,'125','12.1大润发','2','RMB','大米、秋衣、水果等','20131201','2013-12-02 11:15:51','CS'),
(56,'1000','食物','2','RMB','','20150409','2015-04-09 10:52:22','CS'),
(57,'1000','衣服','2','MY','','20150331','2015-04-09 10:53:32','MYF');

DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id decimal(10,0) NOT NULL,
  username varchar(12) DEFAULT NULL,
  password varchar(12) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


insert  into user(id,username,password) values 
(1,'username','password'),
(2,'张无忌','password'),
(3,'乔峰','password');
