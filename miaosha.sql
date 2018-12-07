create table miaosha_user(
	id bigint(20) not null comment '用户ID，手机号码',
	nickname varchar(255) not null,
	password varchar(32) default null comment 'MD5(MD5(pass明文+固定salt) + salt)',
	salt varchar(10) default null,
	head varchar(128) default null comment '头像，云存储的id',
	register_date datetime default null comment '注册时间',
	last_login_date datetime default null comment '上次登录的时间',
	login_count int(11) default '0' comment '登录次数',
	primary key(id)
)engine=InnoDB default charset=utf8mb4