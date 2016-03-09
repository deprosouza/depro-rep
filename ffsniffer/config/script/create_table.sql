-- MySQL dump 10.13  Distrib 5.1.60, for redhat-linux-gnu (x86_64)
--
-- Host: localhost    Database: ffsniffer
-- ------------------------------------------------------
-- Server version	5.1.60

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
-- Table structure for table `Classe`
--

DROP TABLE IF EXISTS `Classe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Classe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataInsercao` date DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `versao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Classe`
--

LOCK TABLES `Classe` WRITE;
/*!40000 ALTER TABLE `Classe` DISABLE KEYS */;
/*!40000 ALTER TABLE `Classe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ClassePlayer`
--

DROP TABLE IF EXISTS `ClassePlayer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ClassePlayer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `itemLevel` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `classe_fk` bigint(20) DEFAULT NULL,
  `classePlayers_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gvs9vqcgj73akv84tygh2a3ks` (`classe_fk`),
  KEY `FK_autfiyep2iym60ejfl4v8db3v` (`classePlayers_id`),
  CONSTRAINT `FK_autfiyep2iym60ejfl4v8db3v` FOREIGN KEY (`classePlayers_id`) REFERENCES `Player` (`id`),
  CONSTRAINT `FK_gvs9vqcgj73akv84tygh2a3ks` FOREIGN KEY (`classe_fk`) REFERENCES `Classe` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ClassePlayer`
--

LOCK TABLES `ClassePlayer` WRITE;
/*!40000 ALTER TABLE `ClassePlayer` DISABLE KEYS */;
/*!40000 ALTER TABLE `ClassePlayer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LSPlayer`
--

DROP TABLE IF EXISTS `LSPlayer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LSPlayer` (
  `linkshell_fk` bigint(20) NOT NULL,
  `player_fk` bigint(20) NOT NULL,
  PRIMARY KEY (`linkshell_fk`,`player_fk`),
  KEY `FK_9fjxfpwpw6ehn7ngep14ybwlw` (`player_fk`),
  CONSTRAINT `FK_n8uhtju8it53hloo21ajugyt1` FOREIGN KEY (`linkshell_fk`) REFERENCES `Linkshell` (`id`),
  CONSTRAINT `FK_9fjxfpwpw6ehn7ngep14ybwlw` FOREIGN KEY (`player_fk`) REFERENCES `Player` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LSPlayer`
--

LOCK TABLES `LSPlayer` WRITE;
/*!40000 ALTER TABLE `LSPlayer` DISABLE KEYS */;
/*!40000 ALTER TABLE `LSPlayer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Linkshell`
--

DROP TABLE IF EXISTS `Linkshell`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Linkshell` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `idLodestone` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `server` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Linkshell`
--

LOCK TABLES `Linkshell` WRITE;
/*!40000 ALTER TABLE `Linkshell` DISABLE KEYS */;
/*!40000 ALTER TABLE `Linkshell` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Login`
--

DROP TABLE IF EXISTS `Login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Login` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataCadastro` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Login`
--

LOCK TABLES `Login` WRITE;
/*!40000 ALTER TABLE `Login` DISABLE KEYS */;
/*!40000 ALTER TABLE `Login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LoginRole`
--

DROP TABLE IF EXISTS `LoginRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LoginRole` (
  `login_fk` bigint(20) NOT NULL,
  `role_fk` bigint(20) NOT NULL,
  KEY `FK_tbtk171aimoiflclix58e5ap8` (`role_fk`),
  KEY `FK_8pn8pbx6fvakaq1o6ryfy71k8` (`login_fk`),
  CONSTRAINT `FK_8pn8pbx6fvakaq1o6ryfy71k8` FOREIGN KEY (`login_fk`) REFERENCES `Login` (`id`),
  CONSTRAINT `FK_tbtk171aimoiflclix58e5ap8` FOREIGN KEY (`role_fk`) REFERENCES `Role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LoginRole`
--

LOCK TABLES `LoginRole` WRITE;
/*!40000 ALTER TABLE `LoginRole` DISABLE KEYS */;
/*!40000 ALTER TABLE `LoginRole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Permissao`
--

DROP TABLE IF EXISTS `Permissao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Permissao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chave` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `habilitado` bit(1) NOT NULL,
  `nivel` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Permissao`
--

LOCK TABLES `Permissao` WRITE;
/*!40000 ALTER TABLE `Permissao` DISABLE KEYS */;
/*!40000 ALTER TABLE `Permissao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Player`
--

DROP TABLE IF EXISTS `Player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Player` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `idLodestone` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `server` varchar(255) DEFAULT NULL,
  `login_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ghydy52xasxc3i729b6hj9l` (`login_fk`),
  CONSTRAINT `FK_ghydy52xasxc3i729b6hj9l` FOREIGN KEY (`login_fk`) REFERENCES `Login` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Player`
--

LOCK TABLES `Player` WRITE;
/*!40000 ALTER TABLE `Player` DISABLE KEYS */;
/*!40000 ALTER TABLE `Player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chave` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `nivel` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RolePermissao`
--

DROP TABLE IF EXISTS `RolePermissao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RolePermissao` (
  `role_fk` bigint(20) NOT NULL,
  `permissao_fk` bigint(20) NOT NULL,
  KEY `FK_7fdi1w109w2h8kummvea7tgvf` (`permissao_fk`),
  KEY `FK_fvbf2p5crqy403x9h14xehh1u` (`role_fk`),
  CONSTRAINT `FK_fvbf2p5crqy403x9h14xehh1u` FOREIGN KEY (`role_fk`) REFERENCES `Role` (`id`),
  CONSTRAINT `FK_7fdi1w109w2h8kummvea7tgvf` FOREIGN KEY (`permissao_fk`) REFERENCES `Permissao` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RolePermissao`
--

LOCK TABLES `RolePermissao` WRITE;
/*!40000 ALTER TABLE `RolePermissao` DISABLE KEYS */;
/*!40000 ALTER TABLE `RolePermissao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Session`
--

DROP TABLE IF EXISTS `Session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Session` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fimSessao` datetime DEFAULT NULL,
  `inicioSessao` datetime DEFAULT NULL,
  `ipAcesso` varchar(255) DEFAULT NULL,
  `localAcesso` varchar(255) DEFAULT NULL,
  `login_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5v3kbc6kvuye1xl506rgvnlwp` (`login_fk`),
  CONSTRAINT `FK_5v3kbc6kvuye1xl506rgvnlwp` FOREIGN KEY (`login_fk`) REFERENCES `Login` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Session`
--

LOCK TABLES `Session` WRITE;
/*!40000 ALTER TABLE `Session` DISABLE KEYS */;
/*!40000 ALTER TABLE `Session` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-03-19 10:31:01
