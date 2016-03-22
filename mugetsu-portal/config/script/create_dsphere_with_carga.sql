-- MySQL dump 10.13  Distrib 5.1.60, for redhat-linux-gnu (x86_64)
--
-- Host: localhost    Database: dsphere
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
-- Table structure for table `Ativacao`
--

DROP TABLE IF EXISTS `Ativacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ativacao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigoAtivacao` varchar(255) DEFAULT NULL,
  `dataCriacao` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `login_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigoAtivacao` (`codigoAtivacao`),
  KEY `FK12588490AD6CF486` (`login_fk`),
  CONSTRAINT `FK12588490AD6CF486` FOREIGN KEY (`login_fk`) REFERENCES `Login` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=901 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ativacao`
--

LOCK TABLES `Ativacao` WRITE;
/*!40000 ALTER TABLE `Ativacao` DISABLE KEYS */;
INSERT INTO `Ativacao` VALUES (900,'21102783-e362-4f4a-bd97-1f073d0d36701653004451','2013-04-13 18:48:07','PENDENTE',1);
/*!40000 ALTER TABLE `Ativacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Broadcast`
--

DROP TABLE IF EXISTS `Broadcast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Broadcast` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `emissora` varchar(255) DEFAULT NULL,
  `encerramento` date DEFAULT NULL,
  `lacamento` date DEFAULT NULL,
  `localidade_id` bigint(20) DEFAULT NULL,
  `media_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK16F408A16421946A` (`localidade_id`),
  KEY `FK16F408A1711A7E2A` (`media_fk`),
  CONSTRAINT `FK16F408A1711A7E2A` FOREIGN KEY (`media_fk`) REFERENCES `Media` (`id`),
  CONSTRAINT `FK16F408A16421946A` FOREIGN KEY (`localidade_id`) REFERENCES `Localidade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Broadcast`
--

LOCK TABLES `Broadcast` WRITE;
/*!40000 ALTER TABLE `Broadcast` DISABLE KEYS */;
/*!40000 ALTER TABLE `Broadcast` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Conteudo`
--

DROP TABLE IF EXISTS `Conteudo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Conteudo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataInsercao` datetime DEFAULT NULL,
  `descricao` longtext,
  `filler` bit(1) NOT NULL,
  `nomeImagem` varchar(255) DEFAULT NULL,
  `numeroEpisodio` int(11) DEFAULT NULL,
  `media_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE209D78D711A7E80` (`media_id`),
  CONSTRAINT `FKE209D78D711A7E80` FOREIGN KEY (`media_id`) REFERENCES `Media` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Conteudo`
--

LOCK TABLES `Conteudo` WRITE;
/*!40000 ALTER TABLE `Conteudo` DISABLE KEYS */;
/*!40000 ALTER TABLE `Conteudo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ConteudoTitulo`
--

DROP TABLE IF EXISTS `ConteudoTitulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ConteudoTitulo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `principal` bit(1) NOT NULL,
  `localidade_id` bigint(20) DEFAULT NULL,
  `conteudo_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8B3071A6234C994` (`conteudo_id`),
  KEY `FK8B3071A66421946A` (`localidade_id`),
  CONSTRAINT `FK8B3071A66421946A` FOREIGN KEY (`localidade_id`) REFERENCES `Localidade` (`id`),
  CONSTRAINT `FK8B3071A6234C994` FOREIGN KEY (`conteudo_id`) REFERENCES `Conteudo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ConteudoTitulo`
--

LOCK TABLES `ConteudoTitulo` WRITE;
/*!40000 ALTER TABLE `ConteudoTitulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `ConteudoTitulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Galeria`
--

DROP TABLE IF EXISTS `Galeria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Galeria` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataUpload` datetime DEFAULT NULL,
  `formatoImagem` varchar(255) DEFAULT NULL,
  `imagemAtual` bit(1) NOT NULL,
  `pathImagem` varchar(255) DEFAULT NULL,
  `media_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK57821037711A7E80` (`media_id`),
  CONSTRAINT `FK57821037711A7E80` FOREIGN KEY (`media_id`) REFERENCES `Media` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Galeria`
--

LOCK TABLES `Galeria` WRITE;
/*!40000 ALTER TABLE `Galeria` DISABLE KEYS */;
/*!40000 ALTER TABLE `Galeria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Genero`
--

DROP TABLE IF EXISTS `Genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Genero` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricaoCurta` varchar(255) DEFAULT NULL,
  `descricaoLonga` longtext,
  `prefixo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `prefixo` (`prefixo`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Genero`
--

LOCK TABLES `Genero` WRITE;
/*!40000 ALTER TABLE `Genero` DISABLE KEYS */;
INSERT INTO `Genero` VALUES (1,'G.DESC.SHORT.ACAO','G.DESC.LONG.ACAO','G.TYPE.ACAO'),(2,'G.DESC.SHORT.ADULTO','G.DESC.LONG.ADULTO','G.TYPE.ADULTO'),(3,'G.DESC.SHORT.AVENTURA','G.DESC.LONG.AVENTURA','G.TYPE.AVENTURA'),(4,'G.DESC.SHORT.COMEDIA','G.DESC.LONG.COMEDIA','G.TYPE.COMEDIA'),(5,'G.DESC.SHORT.DRAMA','G.DESC.LONG.DRAMA','G.TYPE.DRAMA'),(6,'G.DESC.SHORT.DOC','G.DESC.LONG.DOC','G.TYPE.DOC'),(7,'G.DESC.SHORT.ECCHI','G.DESC.LONG.ECCHI','G.TYPE.ECCHI'),(8,'G.DESC.SHORT.FANTASIA','G.DESC.LONG.FANTASIA','G.TYPE.FANTASIA'),(9,'G.DESC.SHORT.FICC','G.DESC.LONG.FICC','G.TYPE.FICC'),(10,'G.DESC.SHORT.HEN','G.DESC.LONG.HEN','G.TYPE.HEN'),(11,'G.DESC.SHORT.HIS','G.DESC.LONG.HIS','G.TYPE.HIS'),(12,'G.DESC.SHORT.HOR','G.DESC.LONG.HOR','G.TYPE.HOR'),(13,'G.DESC.SHORT.MAGIA','G.DESC.LONG.MAGIA','G.TYPE.MAGIA'),(14,'G.DESC.SHORT.MECHA','G.DESC.LONG.MECHA','G.TYPE.MECHA'),(15,'G.DESC.SHORT.MILITAR','G.DESC.LONG.MILITAR','G.TYPE.MILITAR'),(16,'G.DESC.SHORT.MISTERIO','G.DESC.LONG.MISTERIO','G.TYPE.MISTERIO'),(17,'G.DESC.SHORT.MUSICA','G.DESC.LONG.MUSICA','G.TYPE.MUSICA'),(18,'G.DESC.SHORT.POLICE','G.DESC.LONG.POLICE','G.TYPE.POLICE'),(19,'G.DESC.SHORT.PSY','G.DESC.LONG.PSY','G.TYPE.PSY'),(20,'G.DESC.SHORT.ROMANCE','G.DESC.LONG.ROMANCE','G.TYPE.ROMANCE'),(21,'G.DESC.SHORT.SHOUJO','G.DESC.LONG.SHOUJO','G.TYPE.SHOUJO'),(22,'G.DESC.SHORT.SHOUJOAI','G.DESC.LONG.SHOUJOAI','G.TYPE.SHOUJOAI'),(23,'G.DESC.SHORT.SHOUNEN','G.DESC.LONG.SHOUNEN','G.TYPE.SHOUNEN'),(24,'G.DESC.SHORT.SHOUNENAI','G.DESC.LONG.SHOUNENAI','G.TYPE.SHOUNENAI'),(25,'G.DESC.SHORT.SLL','G.DESC.LONG.SLL','G.TYPE.SLL'),(26,'G.DESC.SHORT.ESPORTE','G.DESC.LONG.ESPORTE','G.TYPE.ESPORTE'),(27,'G.DESC.SHORT.SUPERN','G.DESC.LONG.SUPERN','G.TYPE.SUPERN'),(28,'G.DESC.SHORT.TORNEIO','G.DESC.LONG.TORNEIO','G.TYPE.TORNEIO'),(29,'G.DESC.SHORT.YAOI','G.DESC.LONG.YAOI','G.TYPE.YAOI'),(30,'G.DESC.SHORT.YURI','G.DESC.LONG.YURI','G.TYPE.YURI');
/*!40000 ALTER TABLE `Genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Grupo`
--

DROP TABLE IF EXISTS `Grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Grupo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `categoria` int(11) DEFAULT NULL,
  `dataCadastro` datetime DEFAULT NULL,
  `dataCriacao` date DEFAULT NULL,
  `descricao` longtext,
  `homePage` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`),
  KEY `FK41E1C4997FDB552` (`owner_id`),
  CONSTRAINT `FK41E1C4997FDB552` FOREIGN KEY (`owner_id`) REFERENCES `Login` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Grupo`
--

LOCK TABLES `Grupo` WRITE;
/*!40000 ALTER TABLE `Grupo` DISABLE KEYS */;
/*!40000 ALTER TABLE `Grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Localidade`
--

DROP TABLE IF EXISTS `Localidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Localidade` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prefixo` varchar(255) NOT NULL,
  `tipoLocalidade` char(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `prefixo` (`prefixo`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Localidade`
--

LOCK TABLES `Localidade` WRITE;
/*!40000 ALTER TABLE `Localidade` DISABLE KEYS */;
INSERT INTO `Localidade` VALUES (1,'language.default','P'),(2,'language.pt.br','I'),(3,'language.en.us','I'),(4,'language.ja.jp.romaji','I'),(5,'language.ja.jp','I'),(6,'language.ru.ru','I'),(7,'language.zh.hk','I'),(8,'language.zh.tw','I'),(9,'language.ko.kr','I'),(10,'language.de.de','I'),(11,'language.pl.pl','I'),(12,'language.fr.fr','I'),(13,'language.es.es','I'),(14,'language.it.it','I'),(15,'language.tl.ph','I'),(16,'language.ar.sa','I'),(17,'language.sv.se','I'),(18,'language.vi.vn','I'),(19,'language.nl_nl','I'),(20,'language.da.dk','I'),(21,'language.fa.ir','I'),(22,'language.ca.es','I'),(23,'language.ro.ro','I'),(24,'language.id.id','I'),(25,'language.fi_fi','I');
/*!40000 ALTER TABLE `Localidade` ENABLE KEYS */;
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
  `email` varchar(255) NOT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `grupo_id` bigint(20) DEFAULT NULL,
  `usuario_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `usuario_id` (`usuario_id`),
  KEY `FK462FF49DFD8BA3C` (`usuario_id`),
  KEY `FK462FF49DABF34DA` (`grupo_id`),
  CONSTRAINT `FK462FF49DABF34DA` FOREIGN KEY (`grupo_id`) REFERENCES `Grupo` (`id`),
  CONSTRAINT `FK462FF49DFD8BA3C` FOREIGN KEY (`usuario_id`) REFERENCES `Usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Login`
--

LOCK TABLES `Login` WRITE;
/*!40000 ALTER TABLE `Login` DISABLE KEYS */;
INSERT INTO `Login` VALUES (1,NULL,'rsouza.ds@gmail.com','pm1ymo0b84ls7s9u753gt1usfdf4aow','ATIVO','rsouza',NULL,1);
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
  KEY `FK10F7275FE02BEDA2` (`role_fk`),
  KEY `FK10F7275FAD6CF486` (`login_fk`),
  CONSTRAINT `FK10F7275FAD6CF486` FOREIGN KEY (`login_fk`) REFERENCES `Login` (`id`),
  CONSTRAINT `FK10F7275FE02BEDA2` FOREIGN KEY (`role_fk`) REFERENCES `Role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LoginRole`
--

LOCK TABLES `LoginRole` WRITE;
/*!40000 ALTER TABLE `LoginRole` DISABLE KEYS */;
INSERT INTO `LoginRole` VALUES (1,1),(1,2),(1,9);
/*!40000 ALTER TABLE `LoginRole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Media`
--

DROP TABLE IF EXISTS `Media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Media` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ano` int(11) NOT NULL,
  `capitulos` int(11) NOT NULL,
  `duracao` int(11) NOT NULL,
  `episodios` int(11) NOT NULL,
  `formatoAnime` varchar(255) DEFAULT NULL,
  `formatoDorama` varchar(255) DEFAULT NULL,
  `formatoMedia` varchar(255) DEFAULT NULL,
  `nomePrincipal` varchar(255) DEFAULT NULL,
  `Origem` varchar(255) DEFAULT NULL,
  `pathImagem` varchar(255) DEFAULT NULL,
  `sinopse` longtext,
  `temporadas` int(11) NOT NULL,
  `volumes` int(11) NOT NULL,
  `idOrigem` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Media`
--

LOCK TABLES `Media` WRITE;
/*!40000 ALTER TABLE `Media` DISABLE KEYS */;
/*!40000 ALTER TABLE `Media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MediaGenero`
--

DROP TABLE IF EXISTS `MediaGenero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MediaGenero` (
  `media_fk` bigint(20) NOT NULL,
  `genero_fk` bigint(20) NOT NULL,
  PRIMARY KEY (`media_fk`,`genero_fk`),
  KEY `FK3A0180F6711A7E2A` (`media_fk`),
  KEY `FK3A0180F63983C19E` (`genero_fk`),
  CONSTRAINT `FK3A0180F63983C19E` FOREIGN KEY (`genero_fk`) REFERENCES `Genero` (`id`),
  CONSTRAINT `FK3A0180F6711A7E2A` FOREIGN KEY (`media_fk`) REFERENCES `Media` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MediaGenero`
--

LOCK TABLES `MediaGenero` WRITE;
/*!40000 ALTER TABLE `MediaGenero` DISABLE KEYS */;
/*!40000 ALTER TABLE `MediaGenero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MediaTema`
--

DROP TABLE IF EXISTS `MediaTema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MediaTema` (
  `media_fk` bigint(20) NOT NULL,
  `tema_fk` bigint(20) NOT NULL,
  PRIMARY KEY (`media_fk`,`tema_fk`),
  KEY `FKF706B5A9711A7E2A` (`media_fk`),
  KEY `FKF706B5A923DB573E` (`tema_fk`),
  CONSTRAINT `FKF706B5A923DB573E` FOREIGN KEY (`tema_fk`) REFERENCES `Tema` (`id`),
  CONSTRAINT `FKF706B5A9711A7E2A` FOREIGN KEY (`media_fk`) REFERENCES `Media` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MediaTema`
--

LOCK TABLES `MediaTema` WRITE;
/*!40000 ALTER TABLE `MediaTema` DISABLE KEYS */;
/*!40000 ALTER TABLE `MediaTema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MediaTitulo`
--

DROP TABLE IF EXISTS `MediaTitulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MediaTitulo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `principal` bit(1) NOT NULL,
  `localidade_id` bigint(20) DEFAULT NULL,
  `media_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK506BD49D6421946A` (`localidade_id`),
  KEY `FK506BD49D711A7E80` (`media_id`),
  CONSTRAINT `FK506BD49D711A7E80` FOREIGN KEY (`media_id`) REFERENCES `Media` (`id`),
  CONSTRAINT `FK506BD49D6421946A` FOREIGN KEY (`localidade_id`) REFERENCES `Localidade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MediaTitulo`
--

LOCK TABLES `MediaTitulo` WRITE;
/*!40000 ALTER TABLE `MediaTitulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `MediaTitulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Origem`
--

DROP TABLE IF EXISTS `Origem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Origem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `idOrigem` bigint(20) DEFAULT NULL,
  `siteOrigem` varchar(255) DEFAULT NULL,
  `ultimaAtualizacao` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Origem`
--

LOCK TABLES `Origem` WRITE;
/*!40000 ALTER TABLE `Origem` DISABLE KEYS */;
/*!40000 ALTER TABLE `Origem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Permissao`
--

DROP TABLE IF EXISTS `Permissao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Permissao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `habilitado` bit(1) NOT NULL,
  `nivel` bigint(20) DEFAULT NULL,
  `prefixo` varchar(255) DEFAULT NULL,
  `tipoSeguranca` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Permissao`
--

LOCK TABLES `Permissao` WRITE;
/*!40000 ALTER TABLE `Permissao` DISABLE KEYS */;
INSERT INTO `Permissao` VALUES (1,'Menu Administrador','',NULL,'RM_ADMIN','SESSAO'),(2,'Submenu de Administrador - Midia','',1,'RM_IM_ADMIN_MIDIA','SESSAO'),(3,'Submenu de Midia em Administrador - Inserir Midia','',2,'RM_IMIM_INSERIR_MIDIA','SESSAO'),(4,'Submenu de Midia em Administrador - Midias Pendentes','',2,'RM_IMIM_PENDENTE','SESSAO'),(5,'Menu SeguranÃ§a','',NULL,'RM_SECURITY','SESSAO'),(6,'Submenu Detalhe de PermissÃ£o','',5,'RM_IM_PERMISSAO','SESSAO'),(7,'Submenu Detalhe de Role','',5,'RM_IM_ROLE','SESSAO'),(8,'Menu Media','',NULL,'RM_MEDIA','SESSAO'),(9,'Submenu Consulta em Media','',8,'RM_IM_MEDIA_CONSULTA','SESSAO'),(10,'Habilita botÃ£o para alterar filler do conteudo','',NULL,'GRANT_ALTERAR_FILLER','SESSAO'),(11,'habilita botÃ£o para adicionar conteudo','',NULL,'GRANT_ADD_CONTEUDO','SESSAO');
/*!40000 ALTER TABLE `Permissao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Projeto`
--

DROP TABLE IF EXISTS `Projeto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Projeto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataFinal` datetime DEFAULT NULL,
  `dataInicial` datetime DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `quantidadeEpisodios` int(11) NOT NULL,
  `ranking` decimal(19,2) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `grupo_id` bigint(20) DEFAULT NULL,
  `media_id` bigint(20) DEFAULT NULL,
  `usuario_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK50C8E503C2D49EF7` (`usuario_id`),
  KEY `FK50C8E503DABF34DA` (`grupo_id`),
  KEY `FK50C8E503711A7E80` (`media_id`),
  CONSTRAINT `FK50C8E503711A7E80` FOREIGN KEY (`media_id`) REFERENCES `Media` (`id`),
  CONSTRAINT `FK50C8E503C2D49EF7` FOREIGN KEY (`usuario_id`) REFERENCES `Login` (`id`),
  CONSTRAINT `FK50C8E503DABF34DA` FOREIGN KEY (`grupo_id`) REFERENCES `Grupo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Projeto`
--

LOCK TABLES `Projeto` WRITE;
/*!40000 ALTER TABLE `Projeto` DISABLE KEYS */;
/*!40000 ALTER TABLE `Projeto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProjetoConteudo`
--

DROP TABLE IF EXISTS `ProjetoConteudo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProjetoConteudo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `assistido` bit(1) NOT NULL,
  `baixado` bit(1) NOT NULL,
  `dataDownload` datetime DEFAULT NULL,
  `dataVisualizacao` datetime DEFAULT NULL,
  `conteudo_fk` bigint(20) DEFAULT NULL,
  `projeto_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKDEEB0990234C93E` (`conteudo_fk`),
  KEY `FKDEEB09901905BA89` (`projeto_fk`),
  CONSTRAINT `FKDEEB09901905BA89` FOREIGN KEY (`projeto_fk`) REFERENCES `Projeto` (`id`),
  CONSTRAINT `FKDEEB0990234C93E` FOREIGN KEY (`conteudo_fk`) REFERENCES `Conteudo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProjetoConteudo`
--

LOCK TABLES `ProjetoConteudo` WRITE;
/*!40000 ALTER TABLE `ProjetoConteudo` DISABLE KEYS */;
/*!40000 ALTER TABLE `ProjetoConteudo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `prefixo` varchar(255) DEFAULT NULL,
  `tipoSeguranca` varchar(255) DEFAULT NULL,
  `tipoAcesso` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` VALUES (1,'Administrador geral do sistema','ROLE_ROOT',NULL,NULL),(2,'Administrador das midias em geral','ROLE_ADMIN',NULL,NULL),(3,'Atende as solicitaÃ§Ãµes efetuadas','ROLE_ADMIN_LISTENER',NULL,NULL),(4,'Administrador de um grupo','ROLE_GROUP_ADMIN',NULL,NULL),(5,'Uploader de um grupo','ROLE_GROUP_UPLOADER',NULL,NULL),(6,'Membro de um grupo','ROLE_GROUP_MEMBER',NULL,NULL),(7,'Uploader de um grupo','ROLE_GROUP_MODERADOR',NULL,NULL),(8,'UsuÃ¡rio comum do sistema','ROLE_USER',NULL,NULL),(9,'Visitante do sistema','NO_ROLE',NULL,NULL);
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
  KEY `FK8D317D91E02BEDA2` (`role_fk`),
  KEY `FK8D317D916A9EA6C6` (`permissao_fk`),
  CONSTRAINT `FK8D317D916A9EA6C6` FOREIGN KEY (`permissao_fk`) REFERENCES `Permissao` (`id`),
  CONSTRAINT `FK8D317D91E02BEDA2` FOREIGN KEY (`role_fk`) REFERENCES `Role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RolePermissao`
--

LOCK TABLES `RolePermissao` WRITE;
/*!40000 ALTER TABLE `RolePermissao` DISABLE KEYS */;
INSERT INTO `RolePermissao` VALUES (1,5),(1,6),(1,7),(2,1),(2,2),(2,3),(2,4),(2,10),(2,11),(9,8),(9,9);
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
  `dataInicio` datetime DEFAULT NULL,
  `datatFim` datetime DEFAULT NULL,
  `numeroControle` varchar(255) DEFAULT NULL,
  `remoteAddress` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `login_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD9891A76AD6CF4DC` (`login_id`),
  CONSTRAINT `FKD9891A76AD6CF4DC` FOREIGN KEY (`login_id`) REFERENCES `Login` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Session`
--

LOCK TABLES `Session` WRITE;
/*!40000 ALTER TABLE `Session` DISABLE KEYS */;
/*!40000 ALTER TABLE `Session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tema`
--

DROP TABLE IF EXISTS `Tema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tema` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `prefixo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `prefixo` (`prefixo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tema`
--

LOCK TABLES `Tema` WRITE;
/*!40000 ALTER TABLE `Tema` DISABLE KEYS */;
/*!40000 ALTER TABLE `Tema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataNascimento` date DEFAULT NULL,
  `primeiroNome` varchar(255) DEFAULT NULL,
  `sexo` char(1) DEFAULT NULL,
  `ultimoNome` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
INSERT INTO `Usuario` VALUES (1,NULL,'Rafael','M','Souza',NULL);
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-15 13:04:54
