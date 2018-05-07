start transaction;

-- DB creation
drop database if exists `Acme-Newspaper`;
create database `Acme-Newspaper`;

use `Acme-Newspaper`;

-- Create users.
create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

-- Grant privilages.
grant select, insert, update, delete on `Acme-Newspaper`.* to 'acme-user'@'%';
grant select, insert, update, delete, create, drop, references, index, alter, create temporary tables, lock tables, create view, create routine, alter routine, execute, trigger, show view on `Acme-Newspaper`.* to 'acme-manager'@'%';

-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Newspaper
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `actor_emailaddresses`
--

DROP TABLE IF EXISTS `actor_emailaddresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_emailaddresses` (
  `Actor_id` int(11) NOT NULL,
  `emailAddresses` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_emailaddresses`
--

LOCK TABLES `actor_emailaddresses` WRITE;
/*!40000 ALTER TABLE `actor_emailaddresses` DISABLE KEYS */;
INSERT INTO `actor_emailaddresses` VALUES (160,'admin1@admins.com'),(161,'user1@users.com'),(161,'user1@acme.com'),(162,'user2@users.com'),(163,'user3@users.com'),(164,'customer1@customers.com'),(165,'customer2@customers.com'),(172,'agent1@agents.com'),(173,'agent2@agents.com');
/*!40000 ALTER TABLE `actor_emailaddresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_phonenumbers`
--

DROP TABLE IF EXISTS `actor_phonenumbers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_phonenumbers` (
  `Actor_id` int(11) NOT NULL,
  `phoneNumbers` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_phonenumbers`
--

LOCK TABLES `actor_phonenumbers` WRITE;
/*!40000 ALTER TABLE `actor_phonenumbers` DISABLE KEYS */;
INSERT INTO `actor_phonenumbers` VALUES (161,'678555342'),(161,'678555352'),(162,'678555343'),(163,'678555344'),(164,'678555345'),(165,'678555446');
/*!40000 ALTER TABLE `actor_phonenumbers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_postaladdresses`
--

DROP TABLE IF EXISTS `actor_postaladdresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_postaladdresses` (
  `Actor_id` int(11) NOT NULL,
  `postalAddresses` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_postaladdresses`
--

LOCK TABLES `actor_postaladdresses` WRITE;
/*!40000 ALTER TABLE `actor_postaladdresses` DISABLE KEYS */;
INSERT INTO `actor_postaladdresses` VALUES (161,'16th, Lollipop Street'),(161,'17th, Lollipop Street'),(162,'14th, Blueberry Street'),(163,'16th, Shadow Isles Street'),(164,'4th, Blank Street'),(165,'46th, Warden Road'),(172,'16th, Summer Street'),(173,'16th, Winter Street');
/*!40000 ALTER TABLE `actor_postaladdresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_idt4b4u259p6vs4pyr9lax4eg` (`userAccount_id`),
  CONSTRAINT `FK_idt4b4u259p6vs4pyr9lax4eg` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (160,0,'admin1','McAdmin1',152);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `advertisement`
--

DROP TABLE IF EXISTS `advertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertisement` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `bannerURL` varchar(255) DEFAULT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvv` int(11) NOT NULL,
  `expirationMonth` int(11) NOT NULL,
  `expirationYear` int(11) NOT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `targetPageURL` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `agent_id` int(11) DEFAULT NULL,
  `newspaper_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_pdtmvobr4ousebqlhebxwn7q0` (`title`),
  KEY `FK_7n9ussuxsi3k6rm34vajrccvn` (`agent_id`),
  KEY `FK_2a9jqcvexg35eohaebb71i4xu` (`newspaper_id`),
  CONSTRAINT `FK_2a9jqcvexg35eohaebb71i4xu` FOREIGN KEY (`newspaper_id`) REFERENCES `newspaper` (`id`),
  CONSTRAINT `FK_7n9ussuxsi3k6rm34vajrccvn` FOREIGN KEY (`agent_id`) REFERENCES `agent` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisement`
--

LOCK TABLES `advertisement` WRITE;
/*!40000 ALTER TABLE `advertisement` DISABLE KEYS */;
INSERT INTO `advertisement` VALUES (174,0,'https://image.flaticon.com/teams/slug/google.jpg','VISA',605,11,2020,'Agent1','4825545249826933','https://es.linkedin.com/','Title of Advertisement 1',172,166),(175,0,'https://cdn1.iconfinder.com/data/icons/logotypes/32/square-twitter-256.png','VISA',851,9,2023,'Agent2','4699600554960562','https://es.linkedin.com/','Title of Advertisement 2',173,167);
/*!40000 ALTER TABLE `advertisement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agent`
--

DROP TABLE IF EXISTS `agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5cg6nedtnilfs6spfq209syse` (`userAccount_id`),
  CONSTRAINT `FK_5cg6nedtnilfs6spfq209syse` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agent`
--

LOCK TABLES `agent` WRITE;
/*!40000 ALTER TABLE `agent` DISABLE KEYS */;
INSERT INTO `agent` VALUES (172,0,'agent1','McAgent1',158),(173,0,'agent2','McAgent2',159);
/*!40000 ALTER TABLE `agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `isDraft` bit(1) NOT NULL,
  `publicationMoment` datetime DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `newspaper_id` int(11) NOT NULL,
  `writer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_od3kyct246y2oubd4hsd9wro6` (`title`),
  KEY `UK_c2j4afkxjcuo4wlvlicpmhdnl` (`summary`),
  KEY `UK_l94diliox7893w8f0tx20yc5q` (`body`),
  KEY `UK_oipmkq84jx1djdxy3a2sa63cg` (`isDraft`),
  KEY `UK_dh00ud2c8ldl8fovca3blycjh` (`publicationMoment`),
  KEY `FK_pftm848lf5hu8sd0vghfs605l` (`newspaper_id`),
  KEY `FK_tushlj62v3v30iqifyful69d` (`writer_id`),
  CONSTRAINT `FK_tushlj62v3v30iqifyful69d` FOREIGN KEY (`writer_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_pftm848lf5hu8sd0vghfs605l` FOREIGN KEY (`newspaper_id`) REFERENCES `newspaper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (223,0,'An employee messed it all up after accidentally mixing two chemical components in a pool.','\0','2016-11-01 10:00:00','Toxic cloud made neighbours evacuate','Title of Article 1',166,161),(224,0,'Clients caused chaos in a supermarket. They pushed and kicked each other. The reason? The Nutella had a 70% discount.','\0','2016-11-01 10:00:00','Chaos in supermatket due to Nutella on sale','Title of Article 2',166,161),(225,0,'A student has been able to break the laws of nature. He is able to have an active social life while speeling well and passing all of his exams.','\0','2016-11-01 12:00:00','College student can sleep well and pass exams while having social life','Title of Article 3',167,162),(226,0,'A recent study has been published about our people bad habits. Datum are really unfavourable, and our mayoress Muriel Bowser, after noticing, declared:\'The law is the law, and everybody must obey it. Our laws about drugs are going to be stronger soon\'.','',NULL,'65% of Washington DC inhabitants assure to have smoked cannabis in last month','Title of Article 4',168,162);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_pictures`
--

DROP TABLE IF EXISTS `article_pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_pictures` (
  `Article_id` int(11) NOT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  KEY `FK_slh5rr6y2n4ml5s20v5nlr52g` (`Article_id`),
  CONSTRAINT `FK_slh5rr6y2n4ml5s20v5nlr52g` FOREIGN KEY (`Article_id`) REFERENCES `article` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_pictures`
--

LOCK TABLES `article_pictures` WRITE;
/*!40000 ALTER TABLE `article_pictures` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_pictures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chirp`
--

DROP TABLE IF EXISTS `chirp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chirp` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `publicationMoment` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_nfwffa0g4k4hfc47shk9deuiw` (`title`),
  KEY `UK_6bukmdjpe8bki9awlkt7tscti` (`description`),
  KEY `FK_t10lk4j2g8uw7k7et58ytrp70` (`user_id`),
  CONSTRAINT `FK_t10lk4j2g8uw7k7et58ytrp70` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chirp`
--

LOCK TABLES `chirp` WRITE;
/*!40000 ALTER TABLE `chirp` DISABLE KEYS */;
INSERT INTO `chirp` VALUES (170,0,'Description of Chirp1','2016-02-11 13:00:00','Title of Chirp1',161),(171,0,'Description of Chirp2','2016-05-11 14:00:00','Title of Chirp2',163);
/*!40000 ALTER TABLE `chirp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pwmktpkay2yx7v00mrwmuscl8` (`userAccount_id`),
  CONSTRAINT `FK_pwmktpkay2yx7v00mrwmuscl8` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (164,0,'customer1','McCustomer1',156),(165,0,'customer2','McCustomer2',157);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folder`
--

DROP TABLE IF EXISTS `folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `system` bit(1) DEFAULT NULL,
  `actor_id` int(11) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_l1kp977466ddsv762wign7kdh` (`name`),
  KEY `FK_e6lcmpm09goh6x4n16fbq5uka` (`parent_id`),
  CONSTRAINT `FK_e6lcmpm09goh6x4n16fbq5uka` FOREIGN KEY (`parent_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder`
--

LOCK TABLES `folder` WRITE;
/*!40000 ALTER TABLE `folder` DISABLE KEYS */;
INSERT INTO `folder` VALUES (177,0,'in box','',160,NULL),(178,0,'out box','',160,NULL),(179,0,'notification box','',160,NULL),(180,0,'trash box','',160,NULL),(181,0,'spam box','',160,NULL),(182,0,'in box','',161,NULL),(183,0,'out box','',161,NULL),(184,0,'notification box','',161,NULL),(185,0,'trash box','',161,NULL),(186,0,'spam box','',161,NULL),(187,0,'in box','',162,NULL),(188,0,'out box','',162,NULL),(189,0,'notification box','',162,NULL),(190,0,'trash box','',162,NULL),(191,0,'spam box','',162,NULL),(192,0,'in box','',163,NULL),(193,0,'out box','',163,NULL),(194,0,'notification box','',163,NULL),(195,0,'trash box','',163,NULL),(196,0,'spam box','',163,NULL),(197,0,'in box','',164,NULL),(198,0,'out box','',164,NULL),(199,0,'notification box','',164,NULL),(200,0,'trash box','',164,NULL),(201,0,'spam box','',164,NULL),(202,0,'in box','',165,NULL),(203,0,'out box','',165,NULL),(204,0,'notification box','',165,NULL),(205,0,'trash box','',165,NULL),(206,0,'spam box','',165,NULL),(207,0,'in box','',172,NULL),(208,0,'out box','',172,NULL),(209,0,'notification box','',172,NULL),(210,0,'trash box','',172,NULL),(211,0,'spam box','',172,NULL),(212,0,'in box','',173,NULL),(213,0,'out box','',173,NULL),(214,0,'notification box','',173,NULL),(215,0,'trash box','',173,NULL),(216,0,'spam box','',173,NULL);
/*!40000 ALTER TABLE `folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `followup`
--

DROP TABLE IF EXISTS `followup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `followup` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `publicationMoment` datetime DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `article_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_freq297hr07gk5gy5a4auue0q` (`publicationMoment`),
  KEY `FK_aer0q20rslre6418yqlfwmeek` (`article_id`),
  KEY `FK_pikn3prpshakeb3jseg908jf2` (`user_id`),
  CONSTRAINT `FK_pikn3prpshakeb3jseg908jf2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_aer0q20rslre6418yqlfwmeek` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `followup`
--

LOCK TABLES `followup` WRITE;
/*!40000 ALTER TABLE `followup` DISABLE KEYS */;
INSERT INTO `followup` VALUES (227,0,'2016-11-04 10:00:00','College student still breaks the immutable triangle','Believe it or not, the student has passed all his exams while having a hangover after last night\'s party. Truly impressive. \'It\'s true I didn\'t sleep that much last night, but hey! I am not so tired, because the previous day I slept for 12 hours!\'','FollowUp of Article 3',225,162);
/*!40000 ALTER TABLE `followup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `followup_pictures`
--

DROP TABLE IF EXISTS `followup_pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `followup_pictures` (
  `FollowUp_id` int(11) NOT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  KEY `FK_be66suxjlinls1y3yymi3tc0d` (`FollowUp_id`),
  CONSTRAINT `FK_be66suxjlinls1y3yymi3tc0d` FOREIGN KEY (`FollowUp_id`) REFERENCES `followup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `followup_pictures`
--

LOCK TABLES `followup_pictures` WRITE;
/*!40000 ALTER TABLE `followup_pictures` DISABLE KEYS */;
/*!40000 ALTER TABLE `followup_pictures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `folder_id` int(11) NOT NULL,
  `recipient_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7t1ls63lqb52igs4ms20cf94t` (`folder_id`),
  CONSTRAINT `FK_7t1ls63lqb52igs4ms20cf94t` FOREIGN KEY (`folder_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (217,0,'Hey there!','2017-10-01 00:00:00','LOW','Welcome from Admin to User1',178,161,160),(218,0,'Welcome from Admin to Customer1','2017-10-01 00:00:00','LOW','Hello!',178,164,160),(219,0,'Welcome from Admin to User1','2017-10-01 00:00:00','LOW','Hey there!',182,161,160),(220,0,'Welcome from Admin to Customer1','2017-10-01 00:00:00','LOW','Hello!',197,164,160);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `newspaper`
--

DROP TABLE IF EXISTS `newspaper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `newspaper` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isPrivate` bit(1) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `publicationDate` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `publisher_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_15qm6aqnn2o1axb0u7e5bi2xo` (`isPrivate`),
  KEY `UK_v2y7dxoqcqmpdhq0uscs773q` (`publicationDate`),
  KEY `UK_n9pn7mnxhhljb8af11yqbb803` (`title`),
  KEY `UK_aj2tjxce1hqktyfhm33ncj6bp` (`description`),
  KEY `FK_6w4ywp7unfjqi2kflvm35ie1g` (`publisher_id`),
  CONSTRAINT `FK_6w4ywp7unfjqi2kflvm35ie1g` FOREIGN KEY (`publisher_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newspaper`
--

LOCK TABLES `newspaper` WRITE;
/*!40000 ALTER TABLE `newspaper` DISABLE KEYS */;
INSERT INTO `newspaper` VALUES (166,0,'A newspaper dedicated to inform about ridiculously hard to believe yet true news.','\0',NULL,'2016-11-01 00:00:00','Difficult to believe (yet true)',161),(167,0,'A newspaper dedicated to inform about shocking news that happen around the globe.','',NULL,'2016-11-01 00:00:00','The World Hoy',162),(168,0,'The most important news about Washington D.C are published in this newspaper.','\0',NULL,NULL,'The Washington D.C Times',162);
/*!40000 ALTER TABLE `newspaper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `newspapersubscription`
--

DROP TABLE IF EXISTS `newspapersubscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `newspapersubscription` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvv` int(11) NOT NULL,
  `expirationMonth` int(11) NOT NULL,
  `expirationYear` int(11) NOT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  `newspaper_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_e1usfeoqhtyg3c2bfd5l44r46` (`customer_id`,`newspaper_id`),
  KEY `FK_84vno21ru8g1op49jk0t3b0xh` (`newspaper_id`),
  CONSTRAINT `FK_84vno21ru8g1op49jk0t3b0xh` FOREIGN KEY (`newspaper_id`) REFERENCES `newspaper` (`id`),
  CONSTRAINT `FK_3unui3l0tdg80j24rv601g2tm` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newspapersubscription`
--

LOCK TABLES `newspapersubscription` WRITE;
/*!40000 ALTER TABLE `newspapersubscription` DISABLE KEYS */;
INSERT INTO `newspapersubscription` VALUES (169,0,'VISA',673,6,2019,'Customer1','4485750721419113',164,167);
/*!40000 ALTER TABLE `newspapersubscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemconfiguration`
--

DROP TABLE IF EXISTS `systemconfiguration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemconfiguration` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemconfiguration`
--

LOCK TABLES `systemconfiguration` WRITE;
/*!40000 ALTER TABLE `systemconfiguration` DISABLE KEYS */;
INSERT INTO `systemconfiguration` VALUES (222,0);
/*!40000 ALTER TABLE `systemconfiguration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemconfiguration_taboowords`
--

DROP TABLE IF EXISTS `systemconfiguration_taboowords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemconfiguration_taboowords` (
  `SystemConfiguration_id` int(11) NOT NULL,
  `tabooWords` varchar(255) DEFAULT NULL,
  KEY `FK_ha53p6dvt0tff0e8p2gdlghai` (`SystemConfiguration_id`),
  CONSTRAINT `FK_ha53p6dvt0tff0e8p2gdlghai` FOREIGN KEY (`SystemConfiguration_id`) REFERENCES `systemconfiguration` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemconfiguration_taboowords`
--

LOCK TABLES `systemconfiguration_taboowords` WRITE;
/*!40000 ALTER TABLE `systemconfiguration_taboowords` DISABLE KEYS */;
INSERT INTO `systemconfiguration_taboowords` VALUES (222,'sex'),(222,'sexo'),(222,'cialis'),(222,'viagra');
/*!40000 ALTER TABLE `systemconfiguration_taboowords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_o6s94d43co03sx067ili5760c` (`userAccount_id`),
  CONSTRAINT `FK_o6s94d43co03sx067ili5760c` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (161,0,'user1','McUser1',153),(162,0,'user2','McUser2',154),(163,0,'user3','McUser3',155);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_user`
--

DROP TABLE IF EXISTS `user_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_user` (
  `followed_id` int(11) NOT NULL,
  `followers_id` int(11) NOT NULL,
  KEY `FK_ipxcfus1p41qgn9xbfhg2aa0r` (`followers_id`),
  KEY `FK_fkljans6a6pux89xnqbfcw3ho` (`followed_id`),
  CONSTRAINT `FK_fkljans6a6pux89xnqbfcw3ho` FOREIGN KEY (`followed_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_ipxcfus1p41qgn9xbfhg2aa0r` FOREIGN KEY (`followers_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_user`
--

LOCK TABLES `user_user` WRITE;
/*!40000 ALTER TABLE `user_user` DISABLE KEYS */;
INSERT INTO `user_user` VALUES (162,161),(163,161);
/*!40000 ALTER TABLE `user_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (152,0,'21232f297a57a5a743894a0e4a801fc3','admin'),(153,0,'24c9e15e52afc47c225b757e7bee1f9d','user1'),(154,0,'7e58d63b60197ceb55a1c487989a3720','user2'),(155,0,'92877af70a45fd6a2ed7fe81e1236b78','user3'),(156,0,'ffbc4675f864e0e9aab8bdf7a0437010','customer1'),(157,0,'5ce4d191fd14ac85a1469fb8c29b7a7b','customer2'),(158,0,'83a87fd756ab57199c0bb6d5e11168cb','agent1'),(159,0,'b1a4a6b01cc297d4677c4ca6656e14d7','agent2');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (152,'ADMIN'),(153,'USER'),(154,'USER'),(155,'USER'),(156,'CUSTOMER'),(157,'CUSTOMER'),(158,'AGENT'),(159,'AGENT');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volume`
--

DROP TABLE IF EXISTS `volume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `volume` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1g852qpv1irbrshl0rmqgfm3a` (`user_id`),
  CONSTRAINT `FK_1g852qpv1irbrshl0rmqgfm3a` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volume`
--

LOCK TABLES `volume` WRITE;
/*!40000 ALTER TABLE `volume` DISABLE KEYS */;
INSERT INTO `volume` VALUES (176,0,'Description of the Volume 1','Volume of Newspapers 1',2018,161);
/*!40000 ALTER TABLE `volume` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volume_newspaper`
--

DROP TABLE IF EXISTS `volume_newspaper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `volume_newspaper` (
  `volumes_id` int(11) NOT NULL,
  `newspapers_id` int(11) NOT NULL,
  KEY `FK_55de0xvt5cb2u4p2xkeofporj` (`newspapers_id`),
  KEY `FK_m83owyms7s3qmk2o1lssxkv8v` (`volumes_id`),
  CONSTRAINT `FK_m83owyms7s3qmk2o1lssxkv8v` FOREIGN KEY (`volumes_id`) REFERENCES `volume` (`id`),
  CONSTRAINT `FK_55de0xvt5cb2u4p2xkeofporj` FOREIGN KEY (`newspapers_id`) REFERENCES `newspaper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volume_newspaper`
--

LOCK TABLES `volume_newspaper` WRITE;
/*!40000 ALTER TABLE `volume_newspaper` DISABLE KEYS */;
INSERT INTO `volume_newspaper` VALUES (176,166);
/*!40000 ALTER TABLE `volume_newspaper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volumesubscription`
--

DROP TABLE IF EXISTS `volumesubscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `volumesubscription` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvv` int(11) NOT NULL,
  `expirationMonth` int(11) NOT NULL,
  `expirationYear` int(11) NOT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  `volume_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o7vahpw50vc0ucs0e496lodwe` (`customer_id`,`volume_id`),
  KEY `FK_h6p6vxx7om5fg6glkyji03clp` (`volume_id`),
  CONSTRAINT `FK_h6p6vxx7om5fg6glkyji03clp` FOREIGN KEY (`volume_id`) REFERENCES `volume` (`id`),
  CONSTRAINT `FK_a80sgasirworls6oxj81lstwp` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volumesubscription`
--

LOCK TABLES `volumesubscription` WRITE;
/*!40000 ALTER TABLE `volumesubscription` DISABLE KEYS */;
INSERT INTO `volumesubscription` VALUES (221,0,'VISA',851,9,2023,'Agent2','4699600554960562',164,176);
/*!40000 ALTER TABLE `volumesubscription` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-07 15:16:41

commit;