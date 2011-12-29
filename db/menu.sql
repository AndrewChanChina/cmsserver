/*
SQLyog Enterprise - MySQL GUI v7.14 
MySQL - 5.5.10 : Database - smit
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Data for the table `menu` */

insert  into `menu`(`id`,`menu_name`,`menu_type`,`type_name`,`href`) values (1,'名站导航',4,'网上冲浪','mingwang.jsp'),(2,'旅游导航',4,'网上冲浪','lvyou.jsp'),(3,'视频导航',4,'网上冲浪','shiping.jsp'),(4,'财经导航',4,'网上冲浪','caijing.jsp'),(5,'新闻导航',4,'网上冲浪','news.jsp'),(6,'军事导航',4,'网上冲浪','junshi.jsp'),(7,'娱乐导航',4,'网上冲浪','yule.jsp'),(8,'体育导航',4,'网上冲浪','tiyu.jsp'),(9,'读书导航',4,'网上冲浪','dushu.jsp'),(10,'团购导航',4,'网上冲浪','tuangou.jsp'),(11,'最新影片',2,'高清影视','im/film_list.jsp?type=kehuan'),(12,'全部影片',2,'高清影视','im/film_list.jsp?type=kehuan'),(13,'科幻片',2,'高清影视','im/film_list.jsp?type=kehuan'),(14,'动作片',2,'高清影视','im/film_list.jsp?type=kehuan'),(15,'战争片',2,'高清影视','im/film_list.jsp?type=kehuan'),(16,'喜剧片',2,'高清影视','im/film_list.jsp?type=kehuan'),(17,'爱情片',2,'高清影视','im/film_list.jsp?type=kehuan'),(18,'恐怖片',2,'高清影视','im/film_list.jsp?type=kehuan'),(19,'热门游戏',0,'休闲游戏',NULL),(20,'本地网络',3,'休闲游戏','im/game_list.jsp?type=local'),(21,'网络游戏',3,'休闲游戏','im/game_list.jsp?type=net'),(22,'益智类',0,'休闲游戏',NULL),(23,'棋牌类',0,'休闲游戏',NULL),(24,'休闲类',0,'休闲游戏',NULL),(25,'flash类',0,'休闲游戏',NULL),(26,'餐饮类导航',5,'吃喝玩乐',NULL),(27,'娱乐类导航',5,'吃喝玩乐',NULL),(28,'交通类导航',5,'吃喝玩乐',NULL),(29,'购物类导航',5,'吃喝玩乐',NULL),(30,'银行类导航',5,'吃喝玩乐',NULL),(31,'医院类导航',5,'吃喝玩乐',NULL),(32,'聊天',6,'商务办公','im/shangwu_list.jsp?type=qq'),(33,'电邮',6,'商务办公','im/shangwu_list.jsp?type=email'),(34,'办公',6,'商务办公','im/shangwu_list.jsp?type=office'),(35,'词典',6,'商务办公','im/shangwu_list.jsp?type=cidian'),(36,'酒店介绍',1,'酒店介绍',NULL),(37,'餐饮服务',1,'酒店介绍',NULL),(38,'客房服务',1,'酒店介绍',NULL),(39,'康乐服务',1,'酒店介绍',NULL),(40,'快速结账',1,'酒店介绍',NULL),(41,'客户反馈',1,'酒店介绍',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
