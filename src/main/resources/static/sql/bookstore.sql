/*
MySQL Backup
Database: bookstore
Backup Time: 2022-05-03 16:35:34
*/

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `bookstore`.`book`;
DROP TABLE IF EXISTS `bookstore`.`cart`;
DROP TABLE IF EXISTS `bookstore`.`order`;
DROP TABLE IF EXISTS `bookstore`.`user`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(24) DEFAULT NULL,
  `author` varchar(512) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bookId` (`book_id`),
  KEY `userId` (`user_id`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `sum_price` int(11) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `book_id` (`book_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(24) DEFAULT NULL,
  `password` varchar(512) DEFAULT NULL,
  `telephone` varchar(512) DEFAULT NULL,
  `address` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
BEGIN;
LOCK TABLES `bookstore`.`book` WRITE;
DELETE FROM `bookstore`.`book`;
INSERT INTO `bookstore`.`book` (`id`,`book_name`,`author`,`stock`,`price`) VALUES (1, 'JAVA', '零壹快学', 96, 33),(2, 'C++', '零壹快学', 113, 10),(3, 'PYTHON', '黑马程序员', 100, 11),(4, 'C', '黑马程序员', 100, 20),(5, '数据结构', '严薇敏', 100, 50),(6, '离散数学', '屈婉玲', 100, 35),(7, '算法竞赛入门经典', '刘汝佳', 100, 60),(8, '计算机组成原理', '唐朔飞', 4, 20);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `bookstore`.`cart` WRITE;
DELETE FROM `bookstore`.`cart`;
INSERT INTO `bookstore`.`cart` (`id`,`count`,`book_id`,`user_id`) VALUES (17, 1, 3, 1),(19, 1, 8, 9);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `bookstore`.`order` WRITE;
DELETE FROM `bookstore`.`order`;
INSERT INTO `bookstore`.`order` (`id`,`count`,`sum_price`,`book_id`,`user_id`,`date`) VALUES (11, 2, 66, 1, 1, '2022-05-03 15:47:20'),(12, 1, 10, 2, 1, '2022-05-03 15:49:45'),(13, 1, 33, 1, 1, '2022-05-03 15:55:01'),(14, 1, 33, 1, 1, '2022-05-03 15:56:59'),(15, 3, 30, 2, 1, '2022-05-03 15:59:04'),(16, 3, 99, 1, 9, '2022-05-03 16:01:35'),(17, 1, 20, 8, 9, '2022-05-03 16:02:09'),(18, 1, 20, 8, 1, '2022-05-03 16:31:19');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `bookstore`.`user` WRITE;
DELETE FROM `bookstore`.`user`;
INSERT INTO `bookstore`.`user` (`id`,`username`,`password`,`telephone`,`address`) VALUES (1, 'wmz', '0725', '18918522833', 'xxx'),(9, 'wmmmz', '010725', '18918522888', 'xxx'),(10, 'w', '0725', '18918588888', 'xxxx');
UNLOCK TABLES;
COMMIT;
