-- MySQL dump 10.13  Distrib 5.1.41, for debian-linux-gnu (i486)
--
-- Host: localhost    Database: pfc
-- ------------------------------------------------------
-- Server version	5.1.41-3ubuntu12.10

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
-- Table structure for table `COORDENADOR`
--

DROP TABLE IF EXISTS `COORDENADOR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `COORDENADOR` (
  `coordenador_id` bigint(6) NOT NULL,
  `nome` char(20) DEFAULT NULL,
  `apelido` char(20) DEFAULT NULL,
  `email` char(20) DEFAULT NULL,
  `curso_id` varchar(5) NOT NULL,
  PRIMARY KEY (`coordenador_id`),
  UNIQUE KEY `coordenador_id` (`coordenador_id`),
  KEY `coordena` (`curso_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CURSO`
--

DROP TABLE IF EXISTS `CURSO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CURSO` (
  `curso_id` varchar(5) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`curso_id`),
  UNIQUE KEY `curso_id` (`curso_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `DEFESA`
--

DROP TABLE IF EXISTS `DEFESA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DEFESA` (
  `supervisor` varchar(30) NOT NULL,
  `oponente` varchar(30) DEFAULT NULL,
  `presidente` varchar(30) DEFAULT NULL,
  `data` datetime DEFAULT NULL,
  `local` varchar(10) DEFAULT NULL,
  `nota` int(6) DEFAULT NULL,
  `pfc_id` int(6) NOT NULL,
  `studant` varchar(30) NOT NULL,
  PRIMARY KEY (`pfc_id`,`studant`),
  UNIQUE KEY `pfc_id` (`pfc_id`),
  UNIQUE KEY `studant` (`studant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `FASE`
--

DROP TABLE IF EXISTS `FASE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FASE` (
  `id` enum('Pré-Projecto','Marco-Teórico','Final') NOT NULL DEFAULT 'Pré-Projecto',
  `description` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `pfc_id` int(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `OBSERVATION`
--

DROP TABLE IF EXISTS `OBSERVATION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OBSERVATION` (
  `obsevation_id` bigint(6) NOT NULL,
  `coordenador_id` bigint(6) NOT NULL,
  `curso_id` varchar(5) CHARACTER SET latin1 NOT NULL,
  `supervisor_id` bigint(6) NOT NULL,
  PRIMARY KEY (`obsevation_id`),
  KEY `obs_coord` (`coordenador_id`,`curso_id`),
  KEY `obs_sup` (`supervisor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PFC`
--

DROP TABLE IF EXISTS `PFC`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PFC` (
  `pfc_id` int(11) NOT NULL AUTO_INCREMENT,
  `tema_id` int(11) DEFAULT NULL,
  `data_inicio` datetime DEFAULT NULL,
  `data_fim` datetime DEFAULT NULL,
  `nota` int(11) DEFAULT NULL,
  `situation` enum('Análise','PR','Reprovado','Aprovado') NOT NULL DEFAULT 'Análise',
  `supervisor_name` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `titulo` varchar(100) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `fullName` varchar(30) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `document` longblob,
  `oponente` varchar(30) DEFAULT NULL,
  `presidente` varchar(30) DEFAULT NULL,
  `plagium` double DEFAULT NULL,
  `fase` enum('Pré-Projecto','Marco-Teórico','Final','Defesa','Fechado') NOT NULL DEFAULT 'Pré-Projecto',
  PRIMARY KEY (`pfc_id`) USING BTREE,
  UNIQUE KEY `pfc_id` (`pfc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PLAGIUM`
--

DROP TABLE IF EXISTS `PLAGIUM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PLAGIUM` (
  `pfc_id` int(11) NOT NULL,
  `estimativa` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`pfc_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TEMA`
--

DROP TABLE IF EXISTS `TEMA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TEMA` (
  `tema_id` int(11) NOT NULL AUTO_INCREMENT,
  `data_inicio` datetime NOT NULL,
  `data_fim` datetime NOT NULL,
  `titulo` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `nota` int(11) DEFAULT NULL,
  `description` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `situation` enum('Análise','PR','Reprovado','Aprovado') DEFAULT 'Análise',
  `supervisorName` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `fullName` varchar(30) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`tema_id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `fullName` (`fullName`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class` (
  `description` varchar(20) NOT NULL,
  `class_id` varchar(6) NOT NULL,
  `curse_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pfc_curso`
--

DROP TABLE IF EXISTS `pfc_curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pfc_curso` (
  `curso_id` varchar(5) CHARACTER SET latin1 NOT NULL,
  `pfc_id` bigint(6) NOT NULL,
  PRIMARY KEY (`curso_id`,`pfc_id`),
  UNIQUE KEY `pfc_id` (`pfc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pfc_fase`
--

DROP TABLE IF EXISTS `pfc_fase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pfc_fase` (
  `pfc_id` int(11) NOT NULL,
  `tema_id` int(11) NOT NULL,
  `id` varchar(20) CHARACTER SET latin1 NOT NULL DEFAULT 'COOR_ANALISE',
  PRIMARY KEY (`pfc_id`),
  KEY `Relationship13` (`pfc_id`,`tema_id`),
  KEY `Relationship14` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pfc_studant`
--

DROP TABLE IF EXISTS `pfc_studant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pfc_studant` (
  `turma_id` varchar(6) CHARACTER SET latin1 NOT NULL,
  `estudante_nr` bigint(6) NOT NULL,
  `pfc_id` bigint(6) NOT NULL,
  `curso_id` varchar(5) CHARACTER SET latin1 NOT NULL,
  `data_submit` char(20) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`estudante_nr`,`pfc_id`),
  UNIQUE KEY `pfc_id` (`pfc_id`),
  UNIQUE KEY `estudante_nr` (`estudante_nr`),
  KEY `submete` (`turma_id`,`estudante_nr`,`curso_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tema_curso`
--

DROP TABLE IF EXISTS `tema_curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tema_curso` (
  `curso_id` varchar(5) CHARACTER SET latin1 NOT NULL,
  `tema_id` bigint(6) NOT NULL,
  PRIMARY KEY (`curso_id`,`tema_id`),
  UNIQUE KEY `tema_id` (`tema_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tema_studant`
--

DROP TABLE IF EXISTS `tema_studant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tema_studant` (
  `turma_id` varchar(6) CHARACTER SET latin1 NOT NULL,
  `estudante_nr` bigint(6) NOT NULL,
  `tema_id` bigint(6) NOT NULL,
  `curso_id` varchar(5) CHARACTER SET latin1 NOT NULL,
  `data_submit` char(20) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`estudante_nr`,`tema_id`),
  UNIQUE KEY `curso_id` (`tema_id`),
  UNIQUE KEY `tema_id` (`tema_id`),
  UNIQUE KEY `estudante_nr` (`estudante_nr`),
  KEY `submete` (`turma_id`,`estudante_nr`,`curso_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tema_sup`
--

DROP TABLE IF EXISTS `tema_sup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tema_sup` (
  `supervisor_id` bigint(6) NOT NULL,
  `tema_id` bigint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `test_doc`
--

DROP TABLE IF EXISTS `test_doc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_doc` (
  `ficheiro` longblob,
  `title` varchar(50) NOT NULL,
  `description` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-10-30  0:58:57
