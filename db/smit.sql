/*
SQLyog Enterprise Trial - MySQL GUI v8.14 
MySQL - 5.1.55-community : Database - smit
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`smit` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `smit`;

/*Table structure for table `content` */

DROP TABLE IF EXISTS `content`;

CREATE TABLE `content` (
  `id` tinyint(10) NOT NULL AUTO_INCREMENT,
  `pid` tinyint(10) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `excerpt` varchar(50) DEFAULT NULL,
  `tag` varchar(50) DEFAULT NULL,
  `content` text,
  `author_id` tinyint(10) DEFAULT NULL,
  `putter` tinyint(2) DEFAULT '0',
  `onclickcount` int(11) DEFAULT NULL,
  `source` varchar(50) DEFAULT NULL,
  `langtype` tinyint(2) DEFAULT '0',
  `sortrank` tinyint(10) DEFAULT '100',
  `prime` tinyint(2) DEFAULT '2',
  `cteatetime` timestamp NULL DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK38B7347940840B17` (`pid`),
  CONSTRAINT `FK38B7347940840B17` FOREIGN KEY (`pid`) REFERENCES `part` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `content` */

insert  into `content`(`id`,`pid`,`title`,`excerpt`,`tag`,`content`,`author_id`,`putter`,`onclickcount`,`source`,`langtype`,`sortrank`,`prime`,`cteatetime`,`tags`,`createtime`) values (1,NULL,'kdkdk',NULL,NULL,'kdkdkdkd',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2011-04-01 14:43:52');

/*Table structure for table `group` */

DROP TABLE IF EXISTS `group`;

CREATE TABLE `group` (
  `id` tinyint(10) DEFAULT NULL,
  `groupname` varchar(150) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT NULL,
  `sortrank` tinyint(10) DEFAULT '100'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `group` */

/*Table structure for table `group_purview` */

DROP TABLE IF EXISTS `group_purview`;

CREATE TABLE `group_purview` (
  `gid` tinyint(10) NOT NULL,
  `pid` tinyint(10) NOT NULL,
  PRIMARY KEY (`pid`,`gid`),
  KEY `pidindex` (`pid`),
  KEY `FKF5EC4D72CF5343C8` (`gid`),
  CONSTRAINT `FKF5EC4D72CF5343C8` FOREIGN KEY (`gid`) REFERENCES `smit_group` (`id`),
  CONSTRAINT `pidref` FOREIGN KEY (`pid`) REFERENCES `purview` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `group_purview` */

/*Table structure for table `media` */

DROP TABLE IF EXISTS `media`;

CREATE TABLE `media` (
  `id` tinyint(10) NOT NULL AUTO_INCREMENT,
  `filename` varchar(50) DEFAULT NULL,
  `source` varchar(50) DEFAULT NULL,
  `path` varchar(50) DEFAULT NULL,
  `sortrank` tinyint(10) DEFAULT '100',
  `createtime` timestamp NULL DEFAULT NULL,
  `tid` tinyint(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK62F6FE47FCF43D4` (`tid`),
  CONSTRAINT `FK62F6FE47FCF43D4` FOREIGN KEY (`tid`) REFERENCES `mediatype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `media` */

insert  into `media`(`id`,`filename`,`source`,`path`,`sortrank`,`createtime`,`tid`) values (1,'ldldlld',NULL,'ldldl',NULL,NULL,NULL);

/*Table structure for table `mediatype` */

DROP TABLE IF EXISTS `mediatype`;

CREATE TABLE `mediatype` (
  `id` tinyint(10) NOT NULL AUTO_INCREMENT,
  `typename` varchar(50) DEFAULT NULL,
  `topid` tinyint(10) DEFAULT NULL,
  `father_id` tinyint(10) DEFAULT NULL,
  `isspecial` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `mediatype` */

insert  into `mediatype`(`id`,`typename`,`topid`,`father_id`,`isspecial`) values (1,'kdkdkdk',NULL,NULL,0);

/*Table structure for table `part` */

DROP TABLE IF EXISTS `part`;

CREATE TABLE `part` (
  `id` tinyint(10) NOT NULL AUTO_INCREMENT,
  `topid` tinyint(10) DEFAULT NULL,
  `father_id` tinyint(10) DEFAULT NULL,
  `typename` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `part` */

insert  into `part`(`id`,`topid`,`father_id`,`typename`) values (1,0,NULL,'all columns');
insert  into `part`(`id`,`topid`,`father_id`,`typename`) values (2,1,1,'column 1');
insert  into `part`(`id`,`topid`,`father_id`,`typename`) values (3,1,1,'column 2');
insert  into `part`(`id`,`topid`,`father_id`,`typename`) values (4,1,1,'column 3');
insert  into `part`(`id`,`topid`,`father_id`,`typename`) values (5,1,2,'column 1.1');
insert  into `part`(`id`,`topid`,`father_id`,`typename`) values (6,1,2,'column 1.2');
insert  into `part`(`id`,`topid`,`father_id`,`typename`) values (7,1,2,'column 1.3');
insert  into `part`(`id`,`topid`,`father_id`,`typename`) values (8,1,3,'column 2.1');
insert  into `part`(`id`,`topid`,`father_id`,`typename`) values (9,1,3,'column 2.2');
insert  into `part`(`id`,`topid`,`father_id`,`typename`) values (10,1,4,'column 3.1');

/* 1--------------------- */
/*     |--2--------- 								*/
/*            |--5------------------*/
/*            |--6------------------*/
/*            |--7------------------*/
/*     |--3--------- 								*/
/*            |--8------------------*/
/*            |--9------------------*/
/*     |--4--------- 								*/
/*            |--10-----------------*/



/*Table structure for table `purview` */

DROP TABLE IF EXISTS `purview`;

CREATE TABLE `purview` (
  `id` tinyint(10) NOT NULL AUTO_INCREMENT,
  `purviewname` varchar(50) DEFAULT NULL,
  `purviewinfo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `purview` */

insert  into `purview`(`id`,`purviewname`,`purviewinfo`) values (1,'xxx','YYY'),(2,'gdsfg','gh'),(3,'gdsfg','gh'),(4,'eeee','gh'),(5,'yffff','gh'),(6,'yffff','gh'),(7,'yffff','gh'),(8,'yffff','gh');

/*Table structure for table `smit_group` */

DROP TABLE IF EXISTS `smit_group`;

CREATE TABLE `smit_group` (
  `id` tinyint(10) NOT NULL AUTO_INCREMENT,
  `groupname` varchar(50) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  `sortrank` tinyint(10) DEFAULT '100',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `smit_group` */

insert  into `smit_group`(`id`,`groupname`,`createtime`,`sortrank`) values (2,NULL,'2011-04-01 10:37:14',100),(6,NULL,'2011-04-01 11:24:36',100),(7,NULL,'2011-04-01 13:26:01',100),(8,NULL,'2011-04-01 13:27:57',100),(9,NULL,'2011-04-01 13:35:52',100),(10,NULL,'2011-04-01 13:44:42',100),(11,NULL,'2011-04-01 13:46:33',100),(12,NULL,'2011-04-01 13:47:49',100),(13,NULL,'2011-04-01 13:50:39',100),(14,NULL,'2011-04-01 13:53:07',100),(15,NULL,'2011-04-01 14:15:10',100);

/*Table structure for table `smitadmin` */

DROP TABLE IF EXISTS `smitadmin`;

CREATE TABLE `smitadmin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) DEFAULT NULL,
  `passwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `smitadmin` */

insert  into `smitadmin`(`id`,`userName`,`passwd`) values (1,'admin','admin');

/*Table structure for table `sysinfo` */

DROP TABLE IF EXISTS `sysinfo`;

CREATE TABLE `sysinfo` (
  `id` tinyint(10) NOT NULL AUTO_INCREMENT,
  `info_key` varchar(50) DEFAULT NULL,
  `info_value` varchar(50) NOT NULL,
  `info_state` varchar(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sysinfo` */

insert  into `sysinfo`(`id`,`info_key`,`info_value`,`info_state`) values (1,'key1','value1','Y');
insert  into `sysinfo`(`id`,`info_key`,`info_value`,`info_state`) values (2,'key2','value2','Y');
insert  into `sysinfo`(`id`,`info_key`,`info_value`,`info_state`) values (3,'key3','value3','Y');
insert  into `sysinfo`(`id`,`info_key`,`info_value`,`info_state`) values (4,'key4','value4','Y');
insert  into `sysinfo`(`id`,`info_key`,`info_value`,`info_state`) values (5,'key5','value5','Y');
insert  into `sysinfo`(`id`,`info_key`,`info_value`,`info_state`) values (6,'key6','value6','Y');
insert  into `sysinfo`(`id`,`info_key`,`info_value`) values (7,'key7','value7');
/*Table structure for table `tags` */

DROP TABLE IF EXISTS `tags`;

CREATE TABLE `tags` (
  `id` tinyint(10) NOT NULL AUTO_INCREMENT,
  `tagname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tags` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` tinyint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `user_email` varchar(50) DEFAULT NULL,
  `tel` varchar(50) DEFAULT NULL,
  `user_explain` varchar(50) DEFAULT NULL,
  `state` tinyint(2) DEFAULT '0',
  `passwd` varchar(255) DEFAULT NULL,
  `gid` tinyint(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`user_email`,`tel`,`user_explain`,`state`,`passwd`,`gid`) values (2,'guns',NULL,'254122','supper admin',0,'123456',0),(3,'ligm',NULL,'254122','supper admin',0,'123456',0),(5,'gunstofire','ligm@szmg.com.cn','254122','supper admin',0,'123456',2),(8,'李光明',NULL,NULL,'KDKKDKD',NULL,NULL,NULL),(9,'李光明',NULL,NULL,'KDKKDKD',NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
