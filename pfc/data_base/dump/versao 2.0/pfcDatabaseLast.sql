-- MySQL dump 10.13  Distrib 5.1.68, for Win64 (unknown)
--
-- Host: localhost    Database: pfc2
-- ------------------------------------------------------
-- Server version	5.1.68-community

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
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curso` (
  `codigocurso` varchar(20) NOT NULL,
  `designacao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigocurso`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES ('LEIT','Licenciatura em Engenharia Informatica e de Telecomunicacoes'),('LEMT','Licenciatura em Engenharia Mecanica e de Transportes'),('LGF','Licenciatura em GestÆo e Finan‡as');
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `docente`
--

DROP TABLE IF EXISTS `docente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `docente` (
  `numeropessoa` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`numeropessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docente`
--

LOCK TABLES `docente` WRITE;
/*!40000 ALTER TABLE `docente` DISABLE KEYS */;
INSERT INTO `docente` VALUES (1,'t2','t3'),(2,'o1','o2'),(3,'p1','p2'),(4,'o1','o2'),(5,'p1','p2'),(6,'o1','o2'),(7,'p1','p2'),(8,'Marcel Saraiva','marcel.saraiva@isutc.transcom.co.mz'),(9,'Marcel Saraiva','marcel.saraiva@isutc.transcom.co.mz'),(10,'Marcel Saraiva','marcel.saraiva@isutc.transcom.co.mz'),(11,'Marcel Saraiva','marcel.saraiva@isutc.transcom.co.mz'),(12,'Marcel Saraiva','marcel.saraiva@isutc.transcom.co.mz'),(13,'Marcel Saraiva','marcel.saraiva@isutc.transcom.co.mz'),(14,'Marcel Saraiva','marcel.saraiva@isutc.transcom.co.mz');
/*!40000 ALTER TABLE `docente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudante`
--

DROP TABLE IF EXISTS `estudante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estudante` (
  `numeropessoa` int(11) NOT NULL,
  `codigocurso` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`numeropessoa`),
  KEY `curso` (`codigocurso`),
  CONSTRAINT `estudante_ibfk_1` FOREIGN KEY (`numeropessoa`) REFERENCES `pessoa` (`numeropessoa`),
  CONSTRAINT `estudante_ibfk_2` FOREIGN KEY (`codigocurso`) REFERENCES `curso` (`codigocurso`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudante`
--

LOCK TABLES `estudante` WRITE;
/*!40000 ALTER TABLE `estudante` DISABLE KEYS */;
INSERT INTO `estudante` VALUES (2729,'LEIT');
/*!40000 ALTER TABLE `estudante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exemplar`
--

DROP TABLE IF EXISTS `exemplar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exemplar` (
  `numero` int(11) NOT NULL AUTO_INCREMENT,
  `numeropfc` int(11) NOT NULL DEFAULT '0',
  `numeroversao` int(11) NOT NULL DEFAULT '0',
  `data` date DEFAULT NULL,
  `document` longblob,
  `estado` int(1) DEFAULT '0',
  `formato` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`numero`),
  KEY `numeropfc` (`numeropfc`),
  KEY `numeroversao` (`numeroversao`),
  CONSTRAINT `exemplar_ibfk_1` FOREIGN KEY (`numeropfc`) REFERENCES `pfc` (`numeropfc`),
  CONSTRAINT `exemplar_ibfk_2` FOREIGN KEY (`numeroversao`) REFERENCES `versao` (`numeroversao`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exemplar`
--

LOCK TABLES `exemplar` WRITE;
/*!40000 ALTER TABLE `exemplar` DISABLE KEYS */;
/*!40000 ALTER TABLE `exemplar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `juri`
--

DROP TABLE IF EXISTS `juri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `juri` (
  `numero` int(11) NOT NULL AUTO_INCREMENT,
  `codigocurso` varchar(20) NOT NULL DEFAULT '',
  `posicao` varchar(20) NOT NULL DEFAULT '',
  `numeropessoa` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`numero`),
  KEY `codigocurso` (`codigocurso`),
  KEY `numeropessoa` (`numeropessoa`),
  CONSTRAINT `juri_ibfk_1` FOREIGN KEY (`codigocurso`) REFERENCES `curso` (`codigocurso`),
  CONSTRAINT `juri_ibfk_2` FOREIGN KEY (`numeropessoa`) REFERENCES `docente` (`numeropessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `juri`
--

LOCK TABLES `juri` WRITE;
/*!40000 ALTER TABLE `juri` DISABLE KEYS */;
INSERT INTO `juri` VALUES (1,'LEIT','supervisor',1),(2,'LEIT','oponente',2),(3,'LEIT','presidente',3),(4,'LEIT','oponente',4),(5,'LEIT','presidente',5),(6,'LEIT','oponente',6),(7,'LEIT','presidente',7),(8,'LEIT','supervisor',10),(9,'LEIT','supervisor',11),(10,'LEIT','supervisor',12),(11,'LEIT','supervisor',13),(12,'LEIT','supervisor',14);
/*!40000 ALTER TABLE `juri` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pendente`
--

DROP TABLE IF EXISTS `pendente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pendente` (
  `numero` int(11) NOT NULL AUTO_INCREMENT,
  `numeroexemplar` int(11) DEFAULT NULL,
  `copia` tinyint(1) DEFAULT '0',
  `regularizada` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`numero`),
  KEY `numeroexemplar` (`numeroexemplar`),
  CONSTRAINT `pendente_ibfk_1` FOREIGN KEY (`numeroexemplar`) REFERENCES `exemplar` (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pendente`
--

LOCK TABLES `pendente` WRITE;
/*!40000 ALTER TABLE `pendente` DISABLE KEYS */;
/*!40000 ALTER TABLE `pendente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pessoa` (
  `numeropessoa` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`numeropessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=10002 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (2729,'Gulamo Usmane - 1300','gulamo.usmane@isutc.transcom.co.mz'),(10001,'t2','t3');
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pfc`
--

DROP TABLE IF EXISTS `pfc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pfc` (
  `numeropfc` int(11) NOT NULL AUTO_INCREMENT,
  `tema` varchar(255) NOT NULL,
  `estudante` int(11) DEFAULT NULL,
  `supervisor` int(11) DEFAULT NULL,
  `oponente` int(11) DEFAULT NULL,
  `presidente` int(11) DEFAULT NULL,
  `publicado` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`numeropfc`),
  KEY `estudante` (`estudante`),
  KEY `supervisor` (`supervisor`),
  KEY `oponente` (`oponente`),
  KEY `presidente` (`presidente`),
  CONSTRAINT `pfc_ibfk_1` FOREIGN KEY (`estudante`) REFERENCES `pessoa` (`numeropessoa`),
  CONSTRAINT `pfc_ibfk_2` FOREIGN KEY (`supervisor`) REFERENCES `docente` (`numeropessoa`),
  CONSTRAINT `pfc_ibfk_3` FOREIGN KEY (`oponente`) REFERENCES `docente` (`numeropessoa`),
  CONSTRAINT `pfc_ibfk_4` FOREIGN KEY (`presidente`) REFERENCES `docente` (`numeropessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pfc`
--

LOCK TABLES `pfc` WRITE;
/*!40000 ALTER TABLE `pfc` DISABLE KEYS */;
INSERT INTO `pfc` VALUES (1,'PROPOSTA DE IMPLEMENTA€ÇO DE TECNOLOGIAS DE MONITORIZA€ÇO DE DATA CENTER COMO ESTRATGIA DE MANUTEN€ÇO NA EMPRESA CILIX SOFTWARE',2729,1,6,7,1);
/*!40000 ALTER TABLE `pfc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `versao`
--

DROP TABLE IF EXISTS `versao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `versao` (
  `numeroversao` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`numeroversao`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `versao`
--

LOCK TABLES `versao` WRITE;
/*!40000 ALTER TABLE `versao` DISABLE KEYS */;
INSERT INTO `versao` VALUES (1,'Pre-projecto'),(2,'Marco-teorico'),(3,'Projecto final');
/*!40000 ALTER TABLE `versao` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-08-29 16:19:03