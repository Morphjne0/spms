CREATE DATABASE  IF NOT EXISTS `spmsdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `spmsdb`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: spmsdb
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `departid` varchar(45) NOT NULL,
  `departname` varchar(45) NOT NULL,
  `represent` varchar(45) NOT NULL,
  `students` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`departid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('20050821','정보통신','비지정',0),('20210001','컴퓨터정보','비지정',2);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecture`
--

DROP TABLE IF EXISTS `lecture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecture` (
  `id` int NOT NULL AUTO_INCREMENT,
  `lec_Name` varchar(30) NOT NULL,
  `depart` varchar(45) NOT NULL,
  `professor` varchar(45) NOT NULL,
  `lec_date` varchar(45) NOT NULL,
  `lec_type` varchar(45) NOT NULL,
  `lec_score` int NOT NULL,
  `lec_semester` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture`
--

LOCK TABLES `lecture` WRITE;
/*!40000 ALTER TABLE `lecture` DISABLE KEYS */;
INSERT INTO `lecture` VALUES (1,'영어','컴퓨터정보과','김OO','수요일 2~4교시','교양 선택',3,'1학년 1학기'),(2,'산술','컴퓨터정보','김산영','수요일 1~2교시','교양 선택',3,'1학년 1학기'),(3,'자바실습','컴퓨터정보','김산영','목요일 6~8교시','전공 필수',4,'1학년 1학기');
/*!40000 ALTER TABLE `lecture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `id` varchar(30) NOT NULL,
  `type` varchar(30) NOT NULL,
  `pwd` varchar(30) NOT NULL,
  `birthday` varchar(30) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `depart` varchar(45) DEFAULT NULL,
  `grade` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('200100458','P','0000','19301205','김산영','컴퓨터정보',NULL),('200100852','SR','19920324','19920324','이다음','컴퓨터정보',1),('2002110964','S','0000','20010101','이진곤','컴퓨터정보',1),('2021008415','S','19851001','19851001','김아무개','컴퓨터정보',1),('Admin','A','0000',NULL,'ADMIN',NULL,NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scoredata`
--

DROP TABLE IF EXISTS `scoredata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scoredata` (
  `num` int NOT NULL AUTO_INCREMENT,
  `std_id` varchar(45) NOT NULL,
  `lec_name` varchar(45) NOT NULL,
  `std_name` varchar(45) NOT NULL,
  `score` int DEFAULT NULL,
  `semester` varchar(45) NOT NULL,
  `p_name` varchar(45) NOT NULL,
  `lec_score` int NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scoredata`
--

LOCK TABLES `scoredata` WRITE;
/*!40000 ALTER TABLE `scoredata` DISABLE KEYS */;
INSERT INTO `scoredata` VALUES (1,'2002110964','영어','이진곤',0,'1학년 1학기','김OO',3),(2,'2002110964','산술','이진곤',100,'1학년 1학기','김산영',3),(3,'2021008415','산술','김아무개',100,'1학년 1학기','김산영',3);
/*!40000 ALTER TABLE `scoredata` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-11  9:13:15
