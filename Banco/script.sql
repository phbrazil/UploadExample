CREATE TABLE files (
  file_id int(11) NOT NULL AUTO_INCREMENT,
  emailuser varchar(60) NOT NULL,
  date varchar(20) NOT NULL,
  file mediumblob,
  path varchar(100) not null,
  PRIMARY KEY (file_id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;