CREATE DATABASE  IF NOT EXISTS `ShipPort` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `ShipPort`;
-- MySQL dump 10.13  Distrib 8.0.15, for macos10.14 (x86_64)
--
-- Host: localhost    Database: ShipPort
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `port`
--

DROP TABLE IF EXISTS `port`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `port` (
  `id` varchar(40) NOT NULL,
  `name` varchar(45) NOT NULL,
  `num_add` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `port`
--

LOCK TABLES `port` WRITE;
/*!40000 ALTER TABLE `port` DISABLE KEYS */;
INSERT INTO `port` VALUES ('1','Breath1',1),('2','Breath2',2),('3','Breath3',3),('4','Breath4',4),('5','Breath5',5),('6','Breath6',6),('7','Breath7',7),('8','Breath8',8),('9','Breath9',9);
/*!40000 ALTER TABLE `port` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `portship`
--

DROP TABLE IF EXISTS `portship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `portship` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shipid` varchar(45) NOT NULL,
  `arrive` datetime NOT NULL,
  `leaves` datetime NOT NULL,
  `portid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2123 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `portship`
--

LOCK TABLES `portship` WRITE;
/*!40000 ALTER TABLE `portship` DISABLE KEYS */;
INSERT INTO `portship` VALUES (2076,'4444','2019-06-06 12:34:00','2019-06-08 16:17:08',5),(2077,'1010','2019-06-12 01:12:11','2019-06-15 01:12:11',5),(2089,'8888','2019-06-10 01:12:11','2019-06-11 01:12:11',5),(2106,'5222','2019-06-08 01:12:11','2019-06-11 01:12:11',1),(2107,'1111','2019-06-06 12:34:00','2019-06-07 06:07:08',3),(2108,'1111','2019-06-06 12:34:00','2019-06-07 06:07:08',1),(2109,'1111','2019-06-06 12:34:00','2019-06-07 06:07:08',2),(2110,'1111','2019-06-06 12:34:00','2019-06-07 06:07:08',4),(2111,'1111','2019-06-06 12:34:00','2019-06-07 06:07:08',6),(2112,'1111','2019-06-06 12:34:00','2019-06-07 06:07:08',7),(2113,'1111','2019-06-06 12:34:00','2019-06-07 06:07:08',8),(2114,'1111','2019-06-06 12:34:00','2019-06-07 06:07:08',9),(2115,'8888','2019-06-10 01:12:11','2019-06-11 01:12:11',2),(2116,'5222','2019-06-08 01:12:11','2019-06-11 01:12:11',3),(2117,'5222','2019-06-08 01:12:11','2019-06-11 01:12:11',4),(2118,'6666','2019-06-08 01:12:11','2019-06-10 01:12:11',6),(2119,'6666','2019-06-08 01:12:11','2019-06-10 01:12:11',9),(2120,'6666','2019-06-08 01:12:11','2019-06-10 01:12:11',7);
/*!40000 ALTER TABLE `portship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ship`
--

DROP TABLE IF EXISTS `ship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ship` (
  `id` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `arrive` datetime NOT NULL,
  `leaves` datetime DEFAULT NULL,
  `perfer` varchar(10) DEFAULT NULL,
  `site` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ship`
--

LOCK TABLES `ship` WRITE;
/*!40000 ALTER TABLE `ship` DISABLE KEYS */;
INSERT INTO `ship` VALUES ('1010','十九号','2019-06-12 01:12:11','2019-06-15 01:12:11','5','5'),('1111','北京号','2019-06-06 12:34:00','2019-06-07 06:07:08','3','9'),('2222','天津号','2019-06-06 12:34:00','2019-06-07 06:07:08','3','3'),('3333','广东号','2019-06-07 02:34:00','2019-06-09 06:17:08','4','6'),('4444','澳门号','2019-06-06 12:34:00','2019-06-08 16:17:08','5','5'),('5222','拥挤号','2019-06-08 01:12:11','2019-06-11 01:12:11','5','4'),('5555','晋商贷','2019-06-06 12:12:00','2019-06-07 15:12:00','4','1'),('6666','鲁班七号','2019-06-08 01:12:11','2019-06-10 01:12:11','2','7'),('7777','墨子号','2019-06-09 01:12:11','2019-06-10 01:12:11','4',''),('8888','墨子号','2019-06-10 01:12:11','2019-06-11 01:12:11','5','2'),('9999','长江号','2019-06-09 01:12:11','2019-06-12 01:12:11','6','');
/*!40000 ALTER TABLE `ship` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-14 22:03:26
