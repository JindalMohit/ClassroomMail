-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: classroomwunderlist
-- ------------------------------------------------------
-- Server version	5.7.12-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bugcomment`
--

DROP TABLE IF EXISTS `bugcomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bugcomment` (
  `timestamp` varchar(50) NOT NULL,
  `companyName` varchar(100) NOT NULL,
  `listName` varchar(100) NOT NULL,
  `bugName` varchar(500) NOT NULL,
  `emailId` varchar(50) NOT NULL,
  `comment` varchar(200) NOT NULL,
  PRIMARY KEY (`timestamp`,`companyName`,`listName`,`emailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bugcomment`
--

LOCK TABLES `bugcomment` WRITE;
/*!40000 ALTER TABLE `bugcomment` DISABLE KEYS */;
INSERT INTO `bugcomment` VALUES ('2017.07.27.19.55.28','microsoft','Website Bugs','lazy loading issue','hkbansal@gmail.com','@shubham Please check the slider.js file '),('2017.07.27.20.55.28','microsoft','Website Bugs','lazy loading issue','vkthakur@gmail.com','@shubham I made some changes yesterday. Pull latest code'),('2017.07.29.10.55.28','microsoft','Website Bugs','lazy loading issue','submiitr07@gmail.com','@harsh @vikrant corrected it. Working fine.'),('2017.07.29.12.29.12','microsoft','Website Bugs','lazy loading issue','submiitr07@gmail.com','Testing newly added add comment section'),('2017.07.29.12.29.43','microsoft','Website Bugs','lazy loading issue','submiitr07@gmail.com','Its fun. Trying again. Afterall I made it'),('2017.07.29.12.36.33','microsoft','Product v2.0 bugs','slides regions and more flavours dropdown indicator','submiitr07@gmail.com','@harsh May you please see this'),('2017.07.29.21.43.53','microsoft','Testing Issues','Too much white spaces in Regions dropdown','submiitr07@gmail.com','Sample Tsting here also\n:)'),('2017.07.29.22.57.46','microsoft','Product v2.0 bugs','slides regions and more flavours dropdown indicator','submiitr07@gmail.com','Enjoy guys ::)'),('2017.07.30.01.46.25','microsoft','Bugs and Issues','first to do in bugs and Issues','submiitr07@gmail.com','checking comment in newly added bug'),('2017.07.30.01.49.51','microsoft','List created bY .........','It works de.','hkbansal@gmail.com','Even comment too'),('2017.07.30.02.30.34','mozilla','initial list here','initial bug ','submiitr07@gmail.com','Testing comment section	');
/*!40000 ALTER TABLE `bugcomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bugsinlist`
--

DROP TABLE IF EXISTS `bugsinlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bugsinlist` (
  `timestamp` varchar(50) NOT NULL,
  `companyName` varchar(100) NOT NULL,
  `listName` varchar(100) NOT NULL,
  `bugName` varchar(500) NOT NULL,
  `assigneeEmailId` varchar(100) DEFAULT NULL,
  `deadline` varchar(100) DEFAULT NULL,
  `priority` varchar(100) DEFAULT NULL,
  `checked` varchar(10) NOT NULL,
  PRIMARY KEY (`timestamp`,`companyName`,`listName`,`bugName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bugsinlist`
--

LOCK TABLES `bugsinlist` WRITE;
/*!40000 ALTER TABLE `bugsinlist` DISABLE KEYS */;
INSERT INTO `bugsinlist` VALUES ('2017.07.26.19.55.28','microsoft','Technicals','cannot load moduler js','','2017.07.31.19.55.28','P5','false'),('2017.07.26.19.55.28','microsoft','Website Bugs','lazy loading issue','submiitr07@gmail.com','2017.07.31.19.55.28','P2','true'),('2017.07.26.19.55.28','microsoft','Website Bugs','lazy loading issue Part 2','submiitr07@gmail.com','2017.07.31.19.55.28','P5','false'),('2017.07.27.19.55.28','microsoft','Product v2.0 bugs','Amount payable for bebinca is not showing','hkbansal@gmail.com','2017.07.31.19.55.28','P4','true'),('2017.07.27.19.55.28','microsoft','Product v2.0 bugs','slides regions and more flavours dropdown indicator','submiitr07@gmail.com','2017.07.31.19.55.28','P5','false'),('2017.07.28.19.55.28','microsoft','Website Bugs','Too much white spaces in Regions dropdown','submiitr07@gmail.com','2017.07.31.19.55.28','P5','true'),('2017.07.28.19.55.28','microsoft','Website Bugs','Too much white spaces in Regions dropdown Part 2','submiitr07@gmail.com','2017.07.31.19.55.28','P3','true'),('2017.07.29.19.55.28','microsoft','Android getUserDetail API ','Amount payable for bebinca is not showing','hkbansal@gmail.com','2017.07.31.19.55.28','P5','false'),('2017.07.29.19.55.28','microsoft','Android getUserDetail API ','slides regions and more flavours dropdown indicator','submiitr07@gmail.com','2017.07.31.19.55.28','P5','true'),('2017.07.30.01.06.21','microsoft','Product v2.0 bugs','Testing new bug',NULL,NULL,NULL,'true'),('2017.07.30.01.07.48','microsoft','Android getUserDetail API ','API server error. Status code: 500',NULL,NULL,NULL,'false'),('2017.07.30.01.45.46','microsoft','Bugs and Issues','first to do in bugs and Issues',NULL,NULL,NULL,'false'),('2017.07.30.01.49.39','microsoft','List created bY .........','It works de.',NULL,NULL,NULL,'false'),('2017.07.30.02.30.14','mozilla','initial list here','initial bug ',NULL,NULL,NULL,'false'),('2017.07.30.19.55.28','microsoft','Testing Issues','slides regions and more flavours dropdown indicator','submiitr07@gmail.com','2017.07.31.19.55.28','P4','false'),('2017.07.30.19.55.28','microsoft','Testing Issues','Too much white spaces in Regions dropdown','submiitr07@gmail.com','2017.07.31.19.55.28','P5','true'),('2017.07.31.19.55.28','microsoft','Testing Issues','slides regions and more flavours dropdown indicator again','submiitr07@gmail.com','2017.07.31.19.55.28','P1','true'),('2017.07.31.19.55.28','microsoft','Testing Issues','Too much white spaces in Regions dropdown Part 2','submiitr07@gmail.com','2017.07.31.19.55.28','P2','false');
/*!40000 ALTER TABLE `bugsinlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `companyName` varchar(500) NOT NULL,
  `fullName` varchar(100) NOT NULL,
  `employeeEmailId` varchar(500) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`companyName`,`employeeEmailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES ('facebook','Harsh Bansal','hkbansal@gmail.com','iitr'),('facebook','Vikrant Thakur','vkthakur@gmail.com','iitr'),('microsoft','Harsh Bansal','hkbansal@gmail.com','iitr'),('microsoft','madHEYsia','ranark@gmail.com','iitr'),('microsoft','Shubham Madheysia','submiitr07@gmail.com','iitr'),('microsoft','Vikrant Thakur','vkthakur@gmail.com','iitr'),('mozilla','Shubham Madheysia','submiitr07@gmail.com','iitr'),('ONGC','Ravi Sharma','ravi','iitr');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currentuser`
--

DROP TABLE IF EXISTS `currentuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currentuser` (
  `id` varchar(100) NOT NULL,
  `companyName` varchar(100) NOT NULL,
  `fullName` varchar(400) NOT NULL,
  `employeeEmailId` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currentuser`
--

LOCK TABLES `currentuser` WRITE;
/*!40000 ALTER TABLE `currentuser` DISABLE KEYS */;
/*!40000 ALTER TABLE `currentuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lists`
--

DROP TABLE IF EXISTS `lists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lists` (
  `timestamp` varchar(50) NOT NULL,
  `companyName` varchar(100) NOT NULL,
  `listName` varchar(100) NOT NULL,
  PRIMARY KEY (`timestamp`,`companyName`,`listName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lists`
--

LOCK TABLES `lists` WRITE;
/*!40000 ALTER TABLE `lists` DISABLE KEYS */;
INSERT INTO `lists` VALUES ('2017.07.26.18.00.28','microsoft','Website Bugs'),('2017.07.27.18.00.28','microsoft','Technicals'),('2017.07.27.20.00.28','microsoft','Product v2.0 bugs'),('2017.07.28.19.02.22','microsoft','Android getUserDetail API '),('2017.07.28.19.06.17','microsoft','Testing Issues'),('2017.07.28.20.25.19','mozilla','initial list here'),('2017.07.29.00.59.09','microsoft','Bugs and Issues'),('2017.07.30.01.49.14','microsoft','List created bY .........');
/*!40000 ALTER TABLE `lists` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-30  3:23:53
