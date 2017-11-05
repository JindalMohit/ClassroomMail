-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: classroommail
-- ------------------------------------------------------
-- Server version	5.7.20-0ubuntu0.16.04.1

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
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat` (
  `timestamp` varchar(50) NOT NULL,
  `sender` varchar(100) NOT NULL,
  `receiver` varchar(100) NOT NULL,
  `message` varchar(200) NOT NULL,
  PRIMARY KEY (`timestamp`,`sender`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES ('2017.11.05.22.48.46','nitishbansal2297@gmail.com','mohit@gmail.com','Hello there !!'),('2017.11.05.22.53.15','nitishbansal2297@gmail.com','mohit@gmail.com','mc '),('2017.11.05.23.14.37','nitishbansal2297@gmail.com','mohit@gmail.com','Helllo \nHows You'),('2017.11.05.23.16.42','nitishbansal2297@gmail.com','mohit@gmail.com','I\'m Fine ');
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currentuser`
--

DROP TABLE IF EXISTS `currentuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currentuser` (
  `id` varchar(100) NOT NULL,
  `FirstName` varchar(400) NOT NULL,
  `emailId` varchar(100) NOT NULL,
  PRIMARY KEY (`id`,`emailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currentuser`
--

LOCK TABLES `currentuser` WRITE;
/*!40000 ALTER TABLE `currentuser` DISABLE KEYS */;
INSERT INTO `currentuser` VALUES ('1000','Nitish','nitishbansal2297@gmail.com');
/*!40000 ALTER TABLE `currentuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mails`
--

DROP TABLE IF EXISTS `mails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mails` (
  `subjectId` varchar(100) NOT NULL,
  `messageTimestamp` varchar(100) NOT NULL,
  `senderMail` varchar(100) NOT NULL,
  `receiverMail` varchar(100) NOT NULL,
  `message` varchar(5000) DEFAULT NULL,
  `attachmenttype` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`subjectId`,`messageTimestamp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mails`
--

LOCK TABLES `mails` WRITE;
/*!40000 ALTER TABLE `mails` DISABLE KEYS */;
INSERT INTO `mails` VALUES ('2017.11.05.22.20.431000','2017.11.05.22.20.43','mohit@gmail.com','nitish@iitr.com','hellomsg',NULL),('2017.11.05.22.20.431000','2017.11.05.22.22.20','nitish@iitr.com','test@test.test','tester\n-----\n',NULL),('2017.11.05.22.57.591000','2017.11.05.22.57.59','nitishbansal2297@gmail.com','mohit@gmail.com','jindal bcha la ',NULL),('2017.11.05.23.15.021000','2017.11.05.23.15.02','ketan@gmail.com','nitishbansal2297@gmail.com','Coolll',NULL),('2017.11.05.23.15.021000','2017.11.05.23.16.27','nitishbansal2297@gmail.com','ketan@gmail.com','Yo ',NULL),('2017.11.05.23.15.361000','2017.11.05.23.15.36','ketan@gmail.com','ketan@gmail.com','This project was really difficult',NULL),('2017.11.05.23.17.131000','2017.11.05.23.17.18','nitishbansal2297@gmail.com','ankita@gmail.com','Yoooooo',NULL);
/*!40000 ALTER TABLE `mails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjectdetails`
--

DROP TABLE IF EXISTS `subjectdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subjectdetails` (
  `subjectId` varchar(100) NOT NULL,
  `mailId` varchar(100) NOT NULL,
  `subjectName` varchar(1000) NOT NULL DEFAULT '(no subject)',
  `important` varchar(10) NOT NULL DEFAULT 'false',
  `deleted` varchar(10) NOT NULL DEFAULT 'false',
  `latestMessageRead` varchar(10) NOT NULL DEFAULT 'false',
  `isDraft` varchar(10) NOT NULL DEFAULT 'false',
  `draftMessage` varchar(2000) DEFAULT NULL,
  `draftReceipents` varchar(1000) DEFAULT NULL,
  `draftTimestamp` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`subjectId`,`mailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjectdetails`
--

LOCK TABLES `subjectdetails` WRITE;
/*!40000 ALTER TABLE `subjectdetails` DISABLE KEYS */;
INSERT INTO `subjectdetails` VALUES ('2017.11.05.22.20.431000','mohit@gmail.com','hello','false','false','true','false','','',''),('2017.11.05.22.20.431000','nitish@iitr.com','hello','false','false','true','false','','',''),('2017.11.05.22.20.431000','test@test.test','Fwd: hello','true','false','true','false','','',''),('2017.11.05.22.57.591000','mohit@gmail.com','helllooo','false','false','false','false','','',''),('2017.11.05.22.57.591000','nitishbansal2297@gmail.com','helllooo','false','false','true','true','','','2017.11.05.23.04.36'),('2017.11.05.23.15.021000','ketan@gmail.com','Message','false','false','false','false','','',''),('2017.11.05.23.15.021000','nitishbansal2297@gmail.com','Message','false','false','true','false','','',''),('2017.11.05.23.15.361000','ketan@gmail.com','Ketan ','true','false','true','true','','','2017.11.05.23.15.44'),('2017.11.05.23.17.131000','ankita@gmail.com','Ankita','false','false','false','false','','',''),('2017.11.05.23.17.131000','nitishbansal2297@gmail.com','Ankita','false','false','true','false','','','');
/*!40000 ALTER TABLE `subjectdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userdetail`
--

DROP TABLE IF EXISTS `userdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userdetail` (
  `FirstName` varchar(100) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `emailId` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `contact` varchar(45) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`emailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userdetail`
--

LOCK TABLES `userdetail` WRITE;
/*!40000 ALTER TABLE `userdetail` DISABLE KEYS */;
INSERT INTO `userdetail` VALUES ('Ankita','Saxena','ankita@gmail.com','iitr','Female','9529525242','Gorakhpur'),('Haresh','Khanna','haresh@gmail.com','iitr','Male','93431341531','Amritsar'),('Ketan','Gupta','ketan@gmail.com','iitr','Male','9351851545','Haridwar'),('Mohit ','JIndal','mohit@gmail.com','iitr','Male','94565','roorkee'),('bansal','bansal','nitish@iitr.com','iitr','male','987','rk'),('Nitish','Bansal','nitish@yahoo.com','iitr','Male','93565','Roorkee'),('Nitish','Bansal','nitishbansal2297@gmail.com','iitr','Male','9356500007','Bathinda'),('test','','test@test.test','iitr','male',NULL,'roorkee'),('yolo','yolo','yolo@yolo.yolo','iitr','female','9879879879','roorkee');
/*!40000 ALTER TABLE `userdetail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-05 23:21:09
