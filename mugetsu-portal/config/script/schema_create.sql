-- MySQL dump 10.13  Distrib 5.6.27, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: dsphere
-- ------------------------------------------------------
-- Server version	5.6.27-0ubuntu1

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
  UNIQUE KEY `UK_edux5xeyr5mxwx6919dw3v2be` (`codigoAtivacao`),
  KEY `FK_9ioenvt10aapnqkqhevet10hw` (`login_fk`),
  CONSTRAINT `FK_9ioenvt10aapnqkqhevet10hw` FOREIGN KEY (`login_fk`) REFERENCES `Login` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=901 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ativacao`
--

LOCK TABLES `Ativacao` WRITE;
/*!40000 ALTER TABLE `Ativacao` DISABLE KEYS */;
INSERT INTO `Ativacao` VALUES (900,'21102783-e362-4f4a-bd97-1f073d0d36701653004451','2015-11-23 22:41:05','PENDENTE',1);
/*!40000 ALTER TABLE `Ativacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BroadcastInfo`
--

DROP TABLE IF EXISTS `BroadcastInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BroadcastInfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `areaTrasmissao` varchar(255) DEFAULT NULL,
  `emissora` varchar(255) DEFAULT NULL,
  `encerramento` date DEFAULT NULL,
  `lacamento` date DEFAULT NULL,
  `patternEncerramento` varchar(255) DEFAULT NULL,
  `patternLancamento` varchar(255) DEFAULT NULL,
  `localidade_id` bigint(20) DEFAULT NULL,
  `conteudoinfo_fk` bigint(20) DEFAULT NULL,
  `media_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_co1l26vw55wnpe8328lbsi0b7` (`localidade_id`),
  KEY `FK_abbc7baei32tjg76vl62nq39k` (`conteudoinfo_fk`),
  KEY `FK_7mnpm1c4g5vyht6aon4p4sl8v` (`media_fk`),
  CONSTRAINT `FK_7mnpm1c4g5vyht6aon4p4sl8v` FOREIGN KEY (`media_fk`) REFERENCES `Media` (`id`),
  CONSTRAINT `FK_abbc7baei32tjg76vl62nq39k` FOREIGN KEY (`conteudoinfo_fk`) REFERENCES `ConteudoInfo` (`id`),
  CONSTRAINT `FK_co1l26vw55wnpe8328lbsi0b7` FOREIGN KEY (`localidade_id`) REFERENCES `Localidade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BroadcastInfo`
--

LOCK TABLES `BroadcastInfo` WRITE;
/*!40000 ALTER TABLE `BroadcastInfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `BroadcastInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ConteudoInfo`
--

DROP TABLE IF EXISTS `ConteudoInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ConteudoInfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `numero` int(11) DEFAULT NULL,
  `media_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_k3n3m701cxbe97eko9k54hmd5` (`media_fk`),
  CONSTRAINT `FK_k3n3m701cxbe97eko9k54hmd5` FOREIGN KEY (`media_fk`) REFERENCES `Media` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ConteudoInfo`
--

LOCK TABLES `ConteudoInfo` WRITE;
/*!40000 ALTER TABLE `ConteudoInfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `ConteudoInfo` ENABLE KEYS */;
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
  `media_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rw02nagn3xc4r5s1a45ywkfwa` (`media_fk`),
  CONSTRAINT `FK_rw02nagn3xc4r5s1a45ywkfwa` FOREIGN KEY (`media_fk`) REFERENCES `Media` (`id`)
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
  `descricaoLonga` varchar(255) DEFAULT NULL,
  `prefixo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_87dd7j83bw6ceh6kgkcff09g` (`prefixo`)
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
  UNIQUE KEY `UK_pdagrpciapfnixp1t6i1rmyaa` (`prefixo`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Localidade`
--

LOCK TABLES `Localidade` WRITE;
/*!40000 ALTER TABLE `Localidade` DISABLE KEYS */;
INSERT INTO `Localidade` VALUES (1,'language.default','P'),(2,'language.pt.br','I'),(3,'language.en.us','I'),(4,'language.ja.jp.romaji','I'),(5,'language.ja.jp','I'),(6,'language.ru.ru','I'),(7,'language.zh.hk','I'),(8,'language.zh.tw','I'),(9,'language.ko.kr','I'),(10,'language.de.de','I'),(11,'language.pl.pl','I'),(12,'language.fr.fr','I'),(13,'language.es.es','I'),(14,'language.it.it','I'),(15,'language.tl.ph','I'),(16,'language.ar.sa','I'),(17,'language.sv.se','I'),(18,'language.vi.vn','I'),(19,'language.nl.nl','I'),(20,'language.da.dk','I'),(21,'language.fa.ir','I'),(22,'language.ca.es','I'),(23,'language.ro.ro','I'),(24,'language.id.id','I'),(25,'language.fi.fi','I');
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
  `usuario_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2d6k91bwp84hil8onktqv1xrv` (`email`),
  UNIQUE KEY `UK_eaj8o68avp2lp29ufm8cdaqqd` (`username`),
  UNIQUE KEY `UK_h5axbnbxjg50fb9qf078oqoge` (`usuario_id`),
  CONSTRAINT `FK_h5axbnbxjg50fb9qf078oqoge` FOREIGN KEY (`usuario_id`) REFERENCES `Usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Login`
--

LOCK TABLES `Login` WRITE;
/*!40000 ALTER TABLE `Login` DISABLE KEYS */;
INSERT INTO `Login` VALUES (1,NULL,'rsouza.ds@gmail.com','pm1ymo0b84ls7s9u753gt1usfdf4aow','ATIVO','rsouza',1);
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
  `duracao` int(11) NOT NULL,
  `formatoAnime` varchar(255) DEFAULT NULL,
  `formatoDorama` varchar(255) DEFAULT NULL,
  `formatoMedia` varchar(255) DEFAULT NULL,
  `idOrigem` varchar(255) DEFAULT NULL,
  `nomePrincipal` varchar(255) DEFAULT NULL,
  `origem` varchar(255) DEFAULT NULL,
  `quantidadeCapitulos` int(11) NOT NULL,
  `quantidadeTemporadas` int(11) NOT NULL,
  `quantidadeVolumes` int(11) NOT NULL,
  `quantidedaEpisodios` int(11) NOT NULL,
  `sinopse` longtext,
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
  KEY `FK_2y3ov4ndcc16eboxj93ytqpk8` (`genero_fk`),
  CONSTRAINT `FK_2y3ov4ndcc16eboxj93ytqpk8` FOREIGN KEY (`genero_fk`) REFERENCES `Genero` (`id`),
  CONSTRAINT `FK_m9j6nnb88jc21yi1fldfmi4bk` FOREIGN KEY (`media_fk`) REFERENCES `Media` (`id`)
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
  KEY `FK_jjc3hswdjrhqkjgvsr59pjjhs` (`tema_fk`),
  CONSTRAINT `FK_9qkpcaekgi7py8a4pvvrj6jyv` FOREIGN KEY (`media_fk`) REFERENCES `Media` (`id`),
  CONSTRAINT `FK_jjc3hswdjrhqkjgvsr59pjjhs` FOREIGN KEY (`tema_fk`) REFERENCES `Tag` (`id`)
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
-- Table structure for table `NomeInfo`
--

DROP TABLE IF EXISTS `NomeInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NomeInfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `principal` bit(1) NOT NULL,
  `localidade_fk` bigint(20) DEFAULT NULL,
  `conteudoinfo_fk` bigint(20) DEFAULT NULL,
  `media_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_35yc1gex2s55g5op0n5aebvgr` (`localidade_fk`),
  KEY `FK_igrnw60l3pawmicpyy3tm3fpc` (`conteudoinfo_fk`),
  KEY `FK_gfnpg4xicito3n6hhfod9gnnx` (`media_fk`),
  CONSTRAINT `FK_35yc1gex2s55g5op0n5aebvgr` FOREIGN KEY (`localidade_fk`) REFERENCES `Localidade` (`id`),
  CONSTRAINT `FK_gfnpg4xicito3n6hhfod9gnnx` FOREIGN KEY (`media_fk`) REFERENCES `Media` (`id`),
  CONSTRAINT `FK_igrnw60l3pawmicpyy3tm3fpc` FOREIGN KEY (`conteudoinfo_fk`) REFERENCES `ConteudoInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NomeInfo`
--

LOCK TABLES `NomeInfo` WRITE;
/*!40000 ALTER TABLE `NomeInfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `NomeInfo` ENABLE KEYS */;
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
INSERT INTO `Permissao` VALUES (1,'Menu Administrador','',NULL,'RM_ADMIN','SESSAO'),(2,'Submenu de Administrador - Midia','',1,'RM_IM_ADMIN_MIDIA','SESSAO'),(3,'Submenu de Midia em Administrador - Inserir Midia','',2,'RM_IMIM_INSERIR_MIDIA','SESSAO'),(4,'Submenu de Midia em Administrador - Midias Pendentes','',2,'RM_IMIM_PENDENTE','SESSAO'),(5,'Menu Segurança','',NULL,'RM_SECURITY','SESSAO'),(6,'Submenu Detalhe de Permissão','',5,'RM_IM_PERMISSAO','SESSAO'),(7,'Submenu Detalhe de Role','',5,'RM_IM_ROLE','SESSAO'),(8,'Menu Media','',NULL,'RM_MEDIA','SESSAO'),(9,'Submenu Consulta em Media','',8,'RM_IM_MEDIA_CONSULTA','SESSAO'),(10,'Habilita botão para alterar filler do conteudo','',NULL,'GRANT_ALTERAR_FILLER','SESSAO'),(11,'habilita botão para adicionar conteudo','',NULL,'GRANT_ADD_CONTEUDO','SESSAO');
/*!40000 ALTER TABLE `Permissao` ENABLE KEYS */;
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
  `tipoAcesso` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` VALUES (1,'Administrador geral do sistema','ROLE_ROOT',NULL),(2,'Administrador das midias em geral','ROLE_ADMIN',NULL),(3,'Atende as solicitações efetuadas','ROLE_ADMIN_LISTENER',NULL),(4,'Administrador de um grupo','ROLE_GROUP_ADMIN',NULL),(5,'Uploader de um grupo','ROLE_GROUP_UPLOADER',NULL),(6,'Membro de um grupo','ROLE_GROUP_MEMBER',NULL),(7,'Uploader de um grupo','ROLE_GROUP_MODERADOR',NULL),(8,'Usuário comum do sistema','ROLE_USER',NULL),(9,'Visitante do sistema','NO_ROLE',NULL);
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
  CONSTRAINT `FK_7fdi1w109w2h8kummvea7tgvf` FOREIGN KEY (`permissao_fk`) REFERENCES `Permissao` (`id`),
  CONSTRAINT `FK_fvbf2p5crqy403x9h14xehh1u` FOREIGN KEY (`role_fk`) REFERENCES `Role` (`id`)
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
  KEY `FK_8qvi8ey5yy0241ca4kjoj2tva` (`login_id`),
  CONSTRAINT `FK_8qvi8ey5yy0241ca4kjoj2tva` FOREIGN KEY (`login_id`) REFERENCES `Login` (`id`)
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
-- Table structure for table `Tag`
--

DROP TABLE IF EXISTS `Tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tag`
--

LOCK TABLES `Tag` WRITE;
/*!40000 ALTER TABLE `Tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `Tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `dataNascimento` date DEFAULT NULL,
  `primeiroNome` varchar(255) DEFAULT NULL,
  `sexo` char(1) DEFAULT NULL,
  `ultimoNome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
INSERT INTO `Usuario` VALUES (1,'3731c2c6-91b8-4988-ba7d-1afc26108234-1375584216775.jpg',NULL,'Rafael','M','Souza');
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

-- Dump completed on 2015-11-23 22:41:11
