CREATE TABLE blog
(
  id int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  blog_title varchar(255) NULL COMMENT '标题',
  blog_content text NULL COMMENT '内容',
  author_id int NOT NULL COMMENT '作者ID',
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT '博客文章表';

CREATE TABLE author
(
  id int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  user_name varchar(255) NULL COMMENT '作者名称',
  user_info text NULL COMMENT '简介',
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8  COMMENT '作者信息表';

INSERT INTO author (user_name,user_info) values('zhang3','');
INSERT INTO author (user_name,user_info) values('li4','');


INSERT INTO blog (blog_title,blog_content,author_id) values('文章1','xxxxxx',1);
INSERT INTO blog (blog_title,blog_content,author_id) values('文章2','xxxxxx',2);
