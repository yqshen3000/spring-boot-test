DROP TABLE IF EXISTS `Account0`;  
CREATE TABLE `Account0` (  
  `id` long(11) NOT NULL AUTO_INCREMENT,  
  `email` varchar(32) NOT NULL,   
  `password` varchar(32) NOT NULL,   
  `registerIp` varchar(30) NOT NULL, 
  `registerTime` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email0` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Account1`;  
CREATE TABLE `Account1` (  
  `id` long(11) NOT NULL AUTO_INCREMENT,  
  `email` varchar(32) NOT NULL,   
  `password` varchar(32) NOT NULL,   
  `registerIp` varchar(30) NOT NULL, 
  `registerTime` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email1` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Account2`;  
CREATE TABLE `Account2` (  
  `id` long(11) NOT NULL AUTO_INCREMENT,  
  `email` varchar(32) NOT NULL,   
  `password` varchar(32) NOT NULL,   
  `registerIp` varchar(30) NOT NULL, 
  `registerTime` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email2` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Account3`;  
CREATE TABLE `Account3` (  
  `id` long(11) NOT NULL AUTO_INCREMENT,  
  `email` varchar(32) NOT NULL,   
  `password` varchar(32) NOT NULL,   
  `registerIp` varchar(30) NOT NULL, 
  `registerTime` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email3` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;