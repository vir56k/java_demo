CREATE TABLE city
(
    id INT PRIMARY KEY auto_increment,
    name VARCHAR(100) NULL,
    status INT NOT NULL,
    country VARCHAR(100) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '城市';


INSERT INTO city (name,STATUS) VALUES ('北京',0);
INSERT INTO city (name,STATUS) VALUES ('上海',0);
INSERT INTO city (name,STATUS) VALUES ('河北',1);

INSERT INTO city (name,STATUS) VALUES ('city4',0);
INSERT INTO city (name,STATUS) VALUES ('city5',0);
INSERT INTO city (name,STATUS) VALUES ('city6',0);
INSERT INTO city (name,STATUS) VALUES ('city7',0);
INSERT INTO city (name,STATUS) VALUES ('city8',0);
INSERT INTO city (name,STATUS) VALUES ('city9',0);
INSERT INTO city (name,STATUS) VALUES ('city10',0);
INSERT INTO city (name,STATUS) VALUES ('city11',0);

