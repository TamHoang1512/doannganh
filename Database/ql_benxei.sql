CREATE DATABASE  IF NOT EXISTS `ql_benxe` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ql_benxe`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: ql_benxe
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bus`
--

DROP TABLE IF EXISTS `bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus` (
  `id` int NOT NULL AUTO_INCREMENT,
  `direction` tinyint NOT NULL,
  `description` text COLLATE utf8_unicode_ci,
  `capacity_sit` int NOT NULL,
  `start_at` datetime NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `img` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bus_line_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bus__bus_line_idx` (`bus_line_id`),
  CONSTRAINT `bus__bus_line` FOREIGN KEY (`bus_line_id`) REFERENCES `bus_line` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus`
--

LOCK TABLES `bus` WRITE;
/*!40000 ALTER TABLE `bus` DISABLE KEYS */;
INSERT INTO `bus` VALUES (1,1,NULL,20,'2022-09-01 00:00:00',NULL,'https://res.cloudinary.com/hoangtam/image/upload/v1661943108/xe-khach-sai-gon-quang-binh-4_enwxqp.jpg',1),(2,0,NULL,24,'2022-05-01 00:00:00',NULL,'https://res.cloudinary.com/hoangtam/image/upload/v1661943108/xe-khach-sai-gon-quang-binh-4_enwxqp.jpg',2),(3,1,NULL,14,'2022-11-10 00:00:00',NULL,'https://res.cloudinary.com/hoangtam/image/upload/v1661943108/xe-khach-sai-gon-quang-binh-4_enwxqp.jpg',1),(4,0,'',15,'2022-12-24 00:00:00',NULL,'https://res.cloudinary.com/hoangtam/image/upload/v1661943108/xe-khach-sai-gon-quang-binh-4_enwxqp.jpg',2),(5,0,NULL,14,'2022-11-12 00:00:00',NULL,'https://res.cloudinary.com/hoangtam/image/upload/v1661943108/xe-khach-sai-gon-quang-binh-4_enwxqp.jpg',4),(6,1,NULL,7,'2022-10-15 00:00:00',NULL,'https://res.cloudinary.com/hoangtam/image/upload/v1661943108/xe-khach-sai-gon-quang-binh-4_enwxqp.jpg',4),(13,0,NULL,7,'2022-11-20 00:00:00',NULL,'https://res.cloudinary.com/hoangtam/image/upload/v1661943108/xe-khach-sai-gon-quang-binh-4_enwxqp.jpg',6),(14,0,'',15,'2022-09-23 00:30:00',NULL,'https://res.cloudinary.com/hoangtam/image/upload/v1660666079/psuuxpmapuh3vhgtwtxl.png',12),(15,1,NULL,15,'2022-01-09 00:00:00',NULL,'https://res.cloudinary.com/hoangtam/image/upload/v1661943108/xe-khach-sai-gon-quang-binh-4_enwxqp.jpg',2),(16,1,'updated',15,'2022-12-13 00:00:00',NULL,'https://res.cloudinary.com/hoangtam/image/upload/v1661943108/xe-khach-sai-gon-quang-binh-4_enwxqp.jpg',2);
/*!40000 ALTER TABLE `bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_line`
--

DROP TABLE IF EXISTS `bus_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus_line` (
  `id` int NOT NULL AUTO_INCREMENT,
  `from_pos` int DEFAULT NULL,
  `to_pos` int DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `img` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bus_owner_id` int NOT NULL,
  `ticket_price` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `from_pos_UNIQUE` (`from_pos`,`to_pos`,`bus_owner_id`) /*!80000 INVISIBLE */,
  KEY `owner_bus_idx` (`bus_owner_id`) /*!80000 INVISIBLE */,
  KEY `to__station_idx` (`to_pos`),
  KEY `from_station_idx` (`from_pos`),
  CONSTRAINT `from_station` FOREIGN KEY (`from_pos`) REFERENCES `bus_station` (`id`),
  CONSTRAINT `owner_bus` FOREIGN KEY (`bus_owner_id`) REFERENCES `bus_owner` (`id`),
  CONSTRAINT `to__station` FOREIGN KEY (`to_pos`) REFERENCES `bus_station` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_line`
--

LOCK TABLES `bus_line` WRITE;
/*!40000 ALTER TABLE `bus_line` DISABLE KEYS */;
INSERT INTO `bus_line` VALUES (1,4,1,NULL,'2020-08-12 00:00:00','https://res.cloudinary.com/hoangtam/image/upload/v1660666079/psuuxpmapuh3vhgtwtxl.png',1,200000),(2,1,3,'',NULL,'https://res.cloudinary.com/hoangtam/image/upload/v1660666079/psuuxpmapuh3vhgtwtxl.png',2,300000),(4,5,1,NULL,'2020-04-15 00:00:00','https://res.cloudinary.com/hoangtam/image/upload/v1660666079/psuuxpmapuh3vhgtwtxl.png',5,200000),(6,1,4,'',NULL,'https://res.cloudinary.com/hoangtam/image/upload/v1660666079/psuuxpmapuh3vhgtwtxl.png',2,200000),(12,2,5,'',NULL,'https://res.cloudinary.com/hoangtam/image/upload/v1660666079/psuuxpmapuh3vhgtwtxl.png',4,150000),(13,2,4,'',NULL,'https://res.cloudinary.com/hoangtam/image/upload/v1662144065/n6drnkthdo4bxmvhukst.jpg',4,300000);
/*!40000 ALTER TABLE `bus_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_owner`
--

DROP TABLE IF EXISTS `bus_owner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus_owner` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `img` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_active` tinyint DEFAULT NULL,
  `is_block` tinyint DEFAULT NULL,
  `owner_user_id` int NOT NULL,
  `is_freight` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bus_owner__user_idx` (`owner_user_id`),
  CONSTRAINT `bus_owner__user` FOREIGN KEY (`owner_user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_owner`
--

LOCK TABLES `bus_owner` WRITE;
/*!40000 ALTER TABLE `bus_owner` DISABLE KEYS */;
INSERT INTO `bus_owner` VALUES (1,'Nhà xe Hoa Mai','0344705460','Something','https://res.cloudinary.com/hoangtam/image/upload/v1661943078/102214698_mixzwr.jpg',1,0,3,0),(2,'Nhà xe Hoa Đào','0344705460',NULL,'https://res.cloudinary.com/hoangtam/image/upload/v1661943078/102214698_mixzwr.jpg',1,0,9,1),(4,'Nhà xe Hưng Thịnh','0344705460',NULL,'https://res.cloudinary.com/hoangtam/image/upload/v1661943078/102214698_mixzwr.jpg',1,0,10,0),(5,'Nhà xe Thành Bưởi','0344705460',NULL,'https://res.cloudinary.com/hoangtam/image/upload/v1661943078/102214698_mixzwr.jpg',1,0,4,1),(6,'Nha xe moi','0344705460','','https://res.cloudinary.com/hoangtam/image/upload/v1661943078/102214698_mixzwr.jpg',0,0,1,0);
/*!40000 ALTER TABLE `bus_owner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_station`
--

DROP TABLE IF EXISTS `bus_station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus_station` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `location` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `img` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `is_block` tinyint DEFAULT '0',
  `admin_id` int DEFAULT NULL,
  PRIMARY KEY (`id`,`name`,`location`),
  KEY `super_admin__station_idx` (`admin_id`),
  CONSTRAINT `super_admin__station` FOREIGN KEY (`admin_id`) REFERENCES `users` (`user_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_station`
--

LOCK TABLES `bus_station` WRITE;
/*!40000 ALTER TABLE `bus_station` DISABLE KEYS */;
INSERT INTO `bus_station` VALUES (1,'Bến xe trung tâm Cần Thơ','Nguyễn Văn Linh, phường Hưng Lợi, quận Ninh Kiều, thành phố Cần Thơ.','https://res.cloudinary.com/hoangtam/image/upload/v1661943078/102214698_mixzwr.jpg','2020-08-01 00:00:00',0,1),(2,'Bến xe Đức Long','QL1A, TP ĐÀ Nẵng','https://res.cloudinary.com/hoangtam/image/upload/v1661943078/102214698_mixzwr.jpg','2020-08-01 00:00:00',0,1),(3,'Bến xe khách Vũng Tàu','Nam Kì Khởi Nghĩa, Vũng Tàu','https://res.cloudinary.com/hoangtam/image/upload/v1661943078/102214698_mixzwr.jpg','2020-08-01 00:00:00',0,1),(4,'Bến xe Miền Đông','Bình Thạnh, TpHCM','https://res.cloudinary.com/hoangtam/image/upload/v1661943078/102214698_mixzwr.jpg','2020-08-01 00:00:00',0,1),(5,'Bến xe Miền Tây','Bình Tân, TpHCM','https://res.cloudinary.com/hoangtam/image/upload/v1661943078/102214698_mixzwr.jpg','2020-08-01 00:00:00',0,1),(9,'Test validate','123 sdfg',NULL,'2022-09-04 20:47:04',0,1);
/*!40000 ALTER TABLE `bus_station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_ticket`
--

DROP TABLE IF EXISTS `bus_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus_ticket` (
  `id` int NOT NULL AUTO_INCREMENT,
  `number_of_sit` int DEFAULT NULL,
  `is_purchased` tinyint DEFAULT NULL,
  `bus_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bus__ticket_idx` (`bus_id`),
  KEY `user__ticket_idx` (`user_id`),
  CONSTRAINT `bus__ticket` FOREIGN KEY (`bus_id`) REFERENCES `bus` (`id`),
  CONSTRAINT `user__ticket` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_ticket`
--

LOCK TABLES `bus_ticket` WRITE;
/*!40000 ALTER TABLE `bus_ticket` DISABLE KEYS */;
INSERT INTO `bus_ticket` VALUES (1,2,0,6,9),(2,5,0,5,9),(4,1,0,13,7),(5,1,0,13,7),(6,3,0,14,10),(7,7,0,4,9);
/*!40000 ALTER TABLE `bus_ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `freight`
--

DROP TABLE IF EXISTS `freight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `freight` (
  `id` int NOT NULL AUTO_INCREMENT,
  `send_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `send_phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `send_email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `send_date` datetime NOT NULL,
  `receive_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `receive_email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `receive_phone` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `receive_date` datetime NOT NULL,
  `info` text COLLATE utf8_unicode_ci,
  `total_price` decimal(10,0) NOT NULL,
  `owner_id` int DEFAULT NULL,
  `complete` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `owner_freight_idx` (`owner_id`),
  CONSTRAINT `owner_freight` FOREIGN KEY (`owner_id`) REFERENCES `bus_owner` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `freight`
--

LOCK TABLES `freight` WRITE;
/*!40000 ALTER TABLE `freight` DISABLE KEYS */;
INSERT INTO `freight` VALUES (1,'Hùng','123456789','hung@gmail.com','2022-05-05 00:00:00','Cường','cuong@gmail.com','123456789','2022-05-15 00:00:00','gui tu Hung',50000,2,1),(2,'Trángggg','123456789','hung@gmail.com','2022-05-05 00:00:00','Mạnh','cuong@gmail.com','123456789','2022-05-15 00:00:00','gui tu Hung',50000,2,0),(4,'Chong [updated]','0123456789','ahihi@gmail.com','2022-08-31 18:16:00','Vo','vylibra510@gmail.com','123456789','2022-09-11 18:17:00','',70000,2,0);
/*!40000 ALTER TABLE `freight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rating` (
  `id` int NOT NULL AUTO_INCREMENT,
  `rate` smallint DEFAULT NULL,
  `comment` text COLLATE utf8_unicode_ci,
  `bus_owner_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index4` (`bus_owner_id`,`user_id`),
  KEY `bus_owner_idx` (`bus_owner_id`),
  KEY `user__rating_idx` (`user_id`),
  CONSTRAINT `bus_owner__rating` FOREIGN KEY (`bus_owner_id`) REFERENCES `bus_owner` (`id`),
  CONSTRAINT `user__rating` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` VALUES (1,4,'Great',1,7),(18,5,'hay ne',1,8);
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin'),(2,'bus_owner'),(3,'customer'),(4,'staff');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff_bus`
--

DROP TABLE IF EXISTS `staff_bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff_bus` (
  `id` int NOT NULL,
  `bus_owner_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bus_owner__staff_idx` (`bus_owner_id`),
  KEY `user__staff_idx` (`user_id`),
  CONSTRAINT `bus_owner__staff` FOREIGN KEY (`bus_owner_id`) REFERENCES `bus_owner` (`id`),
  CONSTRAINT `user__staff` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_bus`
--

LOCK TABLES `staff_bus` WRITE;
/*!40000 ALTER TABLE `staff_bus` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff_bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station_news`
--

DROP TABLE IF EXISTS `station_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station_news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `img` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `content` longtext COLLATE utf8_unicode_ci,
  `created_at` datetime DEFAULT NULL,
  `is_block` tinyint DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user__news_idx` (`user_id`),
  CONSTRAINT `user__news` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station_news`
--

LOCK TABLES `station_news` WRITE;
/*!40000 ALTER TABLE `station_news` DISABLE KEYS */;
INSERT INTO `station_news` VALUES (2,'Bắt đầu hoạt động Bến xe Miền Tây',2,'https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png','Lorem Ipsum chỉ đơn giản là một đoạn văn bản giả, được dùng vào việc trình bày và dàn trang phục vụ cho in ấn. Lorem Ipsum đã được sử dụng như một văn bản chuẩn cho ngành công nghiệp in ấn từ những năm 1500, khi một họa sĩ vô danh ghép nhiều đoạn văn bản với nhau để tạo thành một bản mẫu văn bản. Đoạn văn bản này không những đã tồn tại năm thế kỉ, mà khi được áp dụng vào tin học văn phòng, nội dung của nó vẫn không hề bị thay đổi. Nó đã được phổ biến trong những năm 1960 nhờ việc bán những bản giấy Letraset in những đoạn Lorem Ipsum, và gần đây hơn, được sử dụng trong các ứng dụng dàn trang, như Aldus PageMaker.','2020-08-08 00:00:00',0),(4,'Tạm dừng chuyến xe ngày 26/3',4,'https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png','Lorem Ipsum chỉ đơn giản là một đoạn văn bản giả, được dùng vào việc trình bày và dàn trang phục vụ cho in ấn. Lorem Ipsum đã được sử dụng như một văn bản chuẩn cho ngành công nghiệp in ấn từ những năm 1500, khi một họa sĩ vô danh ghép nhiều đoạn văn bản với nhau để tạo thành một bản mẫu văn bản. Đoạn văn bản này không những đã tồn tại năm thế kỉ, mà khi được áp dụng vào tin học văn phòng, nội dung của nó vẫn không hề bị thay đổi. Nó đã được phổ biến trong những năm 1960 nhờ việc bán những bản giấy Letraset in những đoạn Lorem Ipsum, và gần đây hơn, được sử dụng trong các ứng dụng dàn trang, như Aldus PageMaker.','2020-11-08 00:00:00',0),(6,'Bắt đầu hoạt động Bến xe Đức Long',2,'https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png','Lorem Ipsum chỉ đơn giản là một đoạn văn bản giả, được dùng vào việc trình bày và dàn trang phục vụ cho in ấn. Lorem Ipsum đã được sử dụng như một văn bản chuẩn cho ngành công nghiệp in ấn từ những năm 1500, khi một họa sĩ vô danh ghép nhiều đoạn văn bản với nhau để tạo thành một bản mẫu văn bản. Đoạn văn bản này không những đã tồn tại năm thế kỉ, mà khi được áp dụng vào tin học văn phòng, nội dung của nó vẫn không hề bị thay đổi. Nó đã được phổ biến trong những năm 1960 nhờ việc bán những bản giấy Letraset in những đoạn Lorem Ipsum, và gần đây hơn, được sử dụng trong các ứng dụng dàn trang, như Aldus PageMaker.','2020-11-12 00:00:00',0),(8,'Tạm ngưng hoạt động với Bến Xe Vùng Tàu',1,'https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png','Lorem Ipsum chỉ đơn giản là một đoạn văn bản giả, được dùng vào việc trình bày và dàn trang phục vụ cho in ấn. Lorem Ipsum đã được sử dụng như một văn bản chuẩn cho ngành công nghiệp in ấn từ những năm 1500, khi một họa sĩ vô danh ghép nhiều đoạn văn bản với nhau để tạo thành một bản mẫu văn bản. Đoạn văn bản này không những đã tồn tại năm thế kỉ, mà khi được áp dụng vào tin học văn phòng, nội dung của nó vẫn không hề bị thay đổi. Nó đã được phổ biến trong những năm 1960 nhờ việc bán những bản giấy Letraset in những đoạn Lorem Ipsum, và gần đây hơn, được sử dụng trong các ứng dụng dàn trang, như Aldus PageMaker.','2021-06-30 00:00:00',0),(9,'Bến xe Vũng Tàu hoạt động trở lại',1,'https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png','Lorem Ipsum chỉ đơn giản là một đoạn văn bản giả, được dùng vào việc trình bày và dàn trang phục vụ cho in ấn. Lorem Ipsum đã được sử dụng như một văn bản chuẩn cho ngành công nghiệp in ấn từ những năm 1500, khi một họa sĩ vô danh ghép nhiều đoạn văn bản với nhau để tạo thành một bản mẫu văn bản. Đoạn văn bản này không những đã tồn tại năm thế kỉ, mà khi được áp dụng vào tin học văn phòng, nội dung của nó vẫn không hề bị thay đổi. Nó đã được phổ biến trong những năm 1960 nhờ việc bán những bản giấy Letraset in những đoạn Lorem Ipsum, và gần đây hơn, được sử dụng trong các ứng dụng dàn trang, như Aldus PageMaker.','2021-09-25 00:00:00',0),(10,'Nhà xe Hoa Mai tạm nghỉ tháng 7/2021',3,'https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png','Lorem Ipsum chỉ đơn giản là một đoạn văn bản giả, được dùng vào việc trình bày và dàn trang phục vụ cho in ấn. Lorem Ipsum đã được sử dụng như một văn bản chuẩn cho ngành công nghiệp in ấn từ những năm 1500, khi một họa sĩ vô danh ghép nhiều đoạn văn bản với nhau để tạo thành một bản mẫu văn bản. Đoạn văn bản này không những đã tồn tại năm thế kỉ, mà khi được áp dụng vào tin học văn phòng, nội dung của nó vẫn không hề bị thay đổi. Nó đã được phổ biến trong những năm 1960 nhờ việc bán những bản giấy Letraset in những đoạn Lorem Ipsum, và gần đây hơn, được sử dụng trong các ứng dụng dàn trang, như Aldus PageMaker.','2021-06-15 00:00:00',0),(39,'Bắt đầu hoạt động bến xe',1,'https://res.cloudinary.com/hoangtam/image/upload/v1660583061/vvimfd99mstssj6sdqn6.png','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.','2022-08-16 00:04:20',0),(40,'Bắt đầu hoạt động đặt vé online',1,'https://res.cloudinary.com/hoangtam/image/upload/v1660583615/pnuvbgcckimzcbt5ggd7.jpg','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.','2022-08-16 00:13:27',0),(43,'Tin tức mới hôm nay',1,'https://res.cloudinary.com/hoangtam/image/upload/v1661097742/x9rd2lq6nqss3ervyby8.png','Lorem Ipsum chỉ đơn giản là một đoạn văn bản giả, được dùng vào việc trình bày và dàn trang phục vụ cho in ấn. Lorem Ipsum đã được sử dụng như một văn bản chuẩn cho ngành công nghiệp in ấn từ những năm 1500, khi một họa sĩ vô danh ghép nhiều đoạn văn bản với nhau để tạo thành một bản mẫu văn bản. Đoạn văn bản này không những đã tồn tại năm thế kỉ, mà khi được áp dụng vào tin học văn phòng, nội dung của nó vẫn không hề bị thay đổi. Nó đã được phổ biến trong những năm 1960 nhờ việc bán những bản giấy Letraset in những đoạn Lorem Ipsum, và gần đây hơn, được sử dụng trong các ứng dụng dàn trang, như Aldus PageMaker.','2022-08-21 23:02:18',0),(44,'Thử với login',15,'https://res.cloudinary.com/hoangtam/image/upload/v1661443224/j4iiijinnjdk9k8myohm.jpg','Lorem Ipsum chỉ đơn giản là một đoạn văn bản giả, được dùng vào việc trình bày và dàn trang phục vụ cho in ấn. Lorem Ipsum đã được sử dụng như một văn bản chuẩn cho ngành công nghiệp in ấn từ những năm 1500, khi một họa sĩ vô danh ghép nhiều đoạn văn bản với nhau để tạo thành một bản mẫu văn bản. Đoạn văn bản này không những đã tồn tại năm thế kỉ, mà khi được áp dụng vào tin học văn phòng, nội dung của nó vẫn không hề bị thay đổi. Nó đã được phổ biến trong những năm 1960 nhờ việc bán những bản giấy Letraset in những đoạn Lorem Ipsum, và gần đây hơn, được sử dụng trong các ứng dụng dàn trang, như Aldus PageMaker.','2022-08-25 23:00:15',1),(46,'Tin tức mới',9,'https://res.cloudinary.com/hoangtam/image/upload/v1660666079/psuuxpmapuh3vhgtwtxl.png','Lorem Ipsum chỉ đơn giản là một đoạn văn bản giả, được dùng vào việc trình bày và dàn trang phục vụ cho in ấn. Lorem Ipsum đã được sử dụng như một văn bản chuẩn cho ngành công nghiệp in ấn từ những năm 1500, khi một họa sĩ vô danh ghép nhiều đoạn văn bản với nhau để tạo thành một bản mẫu văn bản. Đoạn văn bản này không những đã tồn tại năm thế kỉ, mà khi được áp dụng vào tin học văn phòng, nội dung của nó vẫn không hề bị thay đổi. Nó đã được phổ biến trong những năm 1960 nhờ việc bán những bản giấy Letraset in những đoạn Lorem Ipsum, và gần đây hơn, được sử dụng trong các ứng dụng dàn trang, như Aldus PageMaker.','2022-08-29 10:45:15',1),(47,'Tin mới ngày 29-08-2022',9,'https://res.cloudinary.com/hoangtam/image/upload/v1660666079/psuuxpmapuh3vhgtwtxl.png','Lorem Ipsum chỉ đơn giản là một đoạn văn bản giả, được dùng vào việc trình bày và dàn trang phục vụ cho in ấn. Lorem Ipsum đã được sử dụng như một văn bản chuẩn cho ngành công nghiệp in ấn từ những năm 1500, khi một họa sĩ vô danh ghép nhiều đoạn văn bản với nhau để tạo thành một bản mẫu văn bản. Đoạn văn bản này không những đã tồn tại năm thế kỉ, mà khi được áp dụng vào tin học văn phòng, nội dung của nó vẫn không hề bị thay đổi. Nó đã được phổ biến trong những năm 1960 nhờ việc bán những bản giấy Letraset in những đoạn Lorem Ipsum, và gần đây hơn, được sử dụng trong các ứng dụng dàn trang, như Aldus PageMaker.','2022-08-29 22:29:44',0),(48,'Tin tức mới',2,'https://res.cloudinary.com/hoangtam/image/upload/v1662019836/h0pbqaeirzn0hxbbnqq8.jpg','Kiểm tra unicode ','2022-09-01 15:10:31',1),(49,'Thông báo từ nhà xe hoa đào update',9,NULL,'Kiểm tra ký tự unicode','2022-09-02 04:21:55',0),(59,'Tin mới ngày 29-08-2020 updated',9,NULL,'Lorem Ipsum chỉ đơn giản là một đoạn văn bản giả, được dùng vào việc trình bày và dàn trang phục vụ cho in ấn. Lorem Ipsum đã được sử dụng như một văn bản chuẩn cho ngành công nghiệp in ấn từ những năm 1500, khi một họa sĩ vô danh ghép nhiều đoạn văn bản với nhau để tạo thành một bản mẫu văn bản. Đoạn văn bản này không những đã tồn tại năm thế kỉ, mà khi được áp dụng vào tin học văn phòng, nội dung của nó vẫn không hề bị thay đổi. Nó đã được phổ biến trong những năm 1960 nhờ việc bán những bản giấy Letraset in những đoạn Lorem Ipsum, và gần đây hơn, được sử dụng trong các ứng dụng dàn trang, như Aldus PageMaker.','2022-09-02 04:20:36',0),(63,'Test validate',2,'https://res.cloudinary.com/hoangtam/image/upload/v1658122999/sample.jpg','','2022-09-04 20:44:47',0);
/*!40000 ALTER TABLE `station_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `avatar` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `role_user_idx` (`role_id`),
  CONSTRAINT `role_user` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'hagh','$2a$10$lIIoAWQhwdwPXtSczmrleuO0bm7CRVrfRnL21ZQsZADQBJMg2EERG','https://res.cloudinary.com/hoangtam/image/upload/v1661941343/default-thumbnail_bscyvn.jpg','example1@gmail.com',NULL,1),(2,'admin','$2a$10$lIIoAWQhwdwPXtSczmrleuO0bm7CRVrfRnL21ZQsZADQBJMg2EERG','https://res.cloudinary.com/hoangtam/image/upload/v1661941343/default-thumbnail_bscyvn.jpg','example2@gmail.com',NULL,1),(3,'owner1','$2a$10$lIIoAWQhwdwPXtSczmrleuO0bm7CRVrfRnL21ZQsZADQBJMg2EERG','https://res.cloudinary.com/hoangtam/image/upload/v1661941343/default-thumbnail_bscyvn.jpg','example3@gmail.com',NULL,2),(4,'owner2','$2a$10$lIIoAWQhwdwPXtSczmrleuO0bm7CRVrfRnL21ZQsZADQBJMg2EERG','https://res.cloudinary.com/hoangtam/image/upload/v1661941343/default-thumbnail_bscyvn.jpg','example4@gmail.com',NULL,2),(5,'staff1','$2a$10$lIIoAWQhwdwPXtSczmrleuO0bm7CRVrfRnL21ZQsZADQBJMg2EERG','https://res.cloudinary.com/hoangtam/image/upload/v1661941343/default-thumbnail_bscyvn.jpg','example5@gmail.com',NULL,4),(6,'staff2','$2a$10$lIIoAWQhwdwPXtSczmrleuO0bm7CRVrfRnL21ZQsZADQBJMg2EERG','https://res.cloudinary.com/hoangtam/image/upload/v1661941343/default-thumbnail_bscyvn.jpg','example6@gmail.com',NULL,4),(7,'cus1','$2a$10$lIIoAWQhwdwPXtSczmrleuO0bm7CRVrfRnL21ZQsZADQBJMg2EERG','https://res.cloudinary.com/hoangtam/image/upload/v1661941343/default-thumbnail_bscyvn.jpg','example7@gmail.com',NULL,3),(8,'cus2','$2a$10$lIIoAWQhwdwPXtSczmrleuO0bm7CRVrfRnL21ZQsZADQBJMg2EERG','https://res.cloudinary.com/hoangtam/image/upload/v1661941343/default-thumbnail_bscyvn.jpg','example8@gmail.com',NULL,3),(9,'owner3','$2a$10$lIIoAWQhwdwPXtSczmrleuO0bm7CRVrfRnL21ZQsZADQBJMg2EERG','https://res.cloudinary.com/hoangtam/image/upload/v1661941343/default-thumbnail_bscyvn.jpg','example9@gmail.com',NULL,2),(10,'owner4','$2a$10$lIIoAWQhwdwPXtSczmrleuO0bm7CRVrfRnL21ZQsZADQBJMg2EERG','https://res.cloudinary.com/hoangtam/image/upload/v1661941343/default-thumbnail_bscyvn.jpg','example10@gmail.com',NULL,2),(15,'ahihi1','$2a$10$8AKyA/cJESZ5VjaSe.OqSuOG.8Fp7PVGM6QGXe24k/ZYIA/f0X20y','https://res.cloudinary.com/hoangtam/image/upload/v1661100679/yhk1mphfd62ccf85omby.jpg','3tam.nhtt0901@gmail.com',NULL,1),(23,'owner3123456','$2a$10$B0pJDAf/uaKMelq0uydGPuknlHBPSFPZ8/daKgg5inX5R8aYj4Er.','https://res.cloudinary.com/hoangtam/image/upload/v1661743896/fvxksvvwif0fgmpmwclv.jpg','tam.nhtt0901@gmail.com',NULL,3);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-04 21:24:42
