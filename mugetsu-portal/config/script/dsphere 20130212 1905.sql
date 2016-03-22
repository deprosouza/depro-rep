-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.51-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema dsphere
--

CREATE DATABASE IF NOT EXISTS dsphere;
USE dsphere;

--
-- Definition of table `ativacao`
--

DROP TABLE IF EXISTS `ativacao`;
CREATE TABLE `ativacao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigoAtivacao` varchar(255) DEFAULT NULL,
  `dataCriacao` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `login_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigoAtivacao` (`codigoAtivacao`),
  KEY `FK12588490AD6CF486` (`login_fk`),
  CONSTRAINT `FK12588490AD6CF486` FOREIGN KEY (`login_fk`) REFERENCES `login` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=901 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ativacao`
--

/*!40000 ALTER TABLE `ativacao` DISABLE KEYS */;
INSERT INTO `ativacao` (`id`,`codigoAtivacao`,`dataCriacao`,`status`,`login_fk`) VALUES 
 (900,'21102783-e362-4f4a-bd97-1f073d0d36701653004451','2013-02-12 19:04:35','PENDENTE',1);
/*!40000 ALTER TABLE `ativacao` ENABLE KEYS */;


--
-- Definition of table `broadcast`
--

DROP TABLE IF EXISTS `broadcast`;
CREATE TABLE `broadcast` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `emissora` varchar(255) DEFAULT NULL,
  `encerramento` date DEFAULT NULL,
  `lacamento` date DEFAULT NULL,
  `localidade_id` bigint(20) DEFAULT NULL,
  `media_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK16F408A16421946A` (`localidade_id`),
  KEY `FK16F408A1711A7E2A` (`media_fk`),
  CONSTRAINT `FK16F408A1711A7E2A` FOREIGN KEY (`media_fk`) REFERENCES `media` (`id`),
  CONSTRAINT `FK16F408A16421946A` FOREIGN KEY (`localidade_id`) REFERENCES `localidade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `broadcast`
--

/*!40000 ALTER TABLE `broadcast` DISABLE KEYS */;
/*!40000 ALTER TABLE `broadcast` ENABLE KEYS */;


--
-- Definition of table `conteudo`
--

DROP TABLE IF EXISTS `conteudo`;
CREATE TABLE `conteudo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataInsercao` datetime DEFAULT NULL,
  `descricao` longtext,
  `filler` bit(1) NOT NULL,
  `nomeImagem` varchar(255) DEFAULT NULL,
  `numeroEpisodio` int(11) DEFAULT NULL,
  `media_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE209D78D711A7E80` (`media_id`),
  CONSTRAINT `FKE209D78D711A7E80` FOREIGN KEY (`media_id`) REFERENCES `media` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `conteudo`
--

/*!40000 ALTER TABLE `conteudo` DISABLE KEYS */;
/*!40000 ALTER TABLE `conteudo` ENABLE KEYS */;


--
-- Definition of table `conteudotitulo`
--

DROP TABLE IF EXISTS `conteudotitulo`;
CREATE TABLE `conteudotitulo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `principal` bit(1) NOT NULL,
  `localidade_id` bigint(20) DEFAULT NULL,
  `conteudo_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8B3071A6234C994` (`conteudo_id`),
  KEY `FK8B3071A66421946A` (`localidade_id`),
  CONSTRAINT `FK8B3071A66421946A` FOREIGN KEY (`localidade_id`) REFERENCES `localidade` (`id`),
  CONSTRAINT `FK8B3071A6234C994` FOREIGN KEY (`conteudo_id`) REFERENCES `conteudo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `conteudotitulo`
--

/*!40000 ALTER TABLE `conteudotitulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `conteudotitulo` ENABLE KEYS */;


--
-- Definition of table `galeria`
--

DROP TABLE IF EXISTS `galeria`;
CREATE TABLE `galeria` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataUpload` datetime DEFAULT NULL,
  `formatoImagem` varchar(255) DEFAULT NULL,
  `imagemAtual` bit(1) NOT NULL,
  `pathImagem` varchar(255) DEFAULT NULL,
  `media_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK57821037711A7E80` (`media_id`),
  CONSTRAINT `FK57821037711A7E80` FOREIGN KEY (`media_id`) REFERENCES `media` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `galeria`
--

/*!40000 ALTER TABLE `galeria` DISABLE KEYS */;
/*!40000 ALTER TABLE `galeria` ENABLE KEYS */;


--
-- Definition of table `genero`
--

DROP TABLE IF EXISTS `genero`;
CREATE TABLE `genero` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricaoCurta` varchar(255) DEFAULT NULL,
  `descricaoLonga` longtext,
  `prefixo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `prefixo` (`prefixo`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `genero`
--

/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
INSERT INTO `genero` (`id`,`descricaoCurta`,`descricaoLonga`,`prefixo`) VALUES 
 (1,'G.DESC.SHORT.ACAO','G.DESC.LONG.ACAO','G.TYPE.ACAO'),
 (2,'G.DESC.SHORT.ADULTO','G.DESC.LONG.ADULTO','G.TYPE.ADULTO'),
 (3,'G.DESC.SHORT.AVENTURA','G.DESC.LONG.AVENTURA','G.TYPE.AVENTURA'),
 (4,'G.DESC.SHORT.COMEDIA','G.DESC.LONG.COMEDIA','G.TYPE.COMEDIA'),
 (5,'G.DESC.SHORT.DRAMA','G.DESC.LONG.DRAMA','G.TYPE.DRAMA'),
 (6,'G.DESC.SHORT.DOC','G.DESC.LONG.DOC','G.TYPE.DOC'),
 (7,'G.DESC.SHORT.ECCHI','G.DESC.LONG.ECCHI','G.TYPE.ECCHI'),
 (8,'G.DESC.SHORT.FANTASIA','G.DESC.LONG.FANTASIA','G.TYPE.FANTASIA'),
 (9,'G.DESC.SHORT.FICC','G.DESC.LONG.FICC','G.TYPE.FICC'),
 (10,'G.DESC.SHORT.HEN','G.DESC.LONG.HEN','G.TYPE.HEN'),
 (11,'G.DESC.SHORT.HIS','G.DESC.LONG.HIS','G.TYPE.HIS'),
 (12,'G.DESC.SHORT.HOR','G.DESC.LONG.HOR','G.TYPE.HOR'),
 (13,'G.DESC.SHORT.MAGIA','G.DESC.LONG.MAGIA','G.TYPE.MAGIA'),
 (14,'G.DESC.SHORT.MECHA','G.DESC.LONG.MECHA','G.TYPE.MECHA'),
 (15,'G.DESC.SHORT.MILITAR','G.DESC.LONG.MILITAR','G.TYPE.MILITAR'),
 (16,'G.DESC.SHORT.MISTERIO','G.DESC.LONG.MISTERIO','G.TYPE.MISTERIO'),
 (17,'G.DESC.SHORT.MUSICA','G.DESC.LONG.MUSICA','G.TYPE.MUSICA'),
 (18,'G.DESC.SHORT.POLICE','G.DESC.LONG.POLICE','G.TYPE.POLICE'),
 (19,'G.DESC.SHORT.PSY','G.DESC.LONG.PSY','G.TYPE.PSY'),
 (20,'G.DESC.SHORT.ROMANCE','G.DESC.LONG.ROMANCE','G.TYPE.ROMANCE'),
 (21,'G.DESC.SHORT.SHOUJO','G.DESC.LONG.SHOUJO','G.TYPE.SHOUJO'),
 (22,'G.DESC.SHORT.SHOUJOAI','G.DESC.LONG.SHOUJOAI','G.TYPE.SHOUJOAI'),
 (23,'G.DESC.SHORT.SHOUNEN','G.DESC.LONG.SHOUNEN','G.TYPE.SHOUNEN'),
 (24,'G.DESC.SHORT.SHOUNENAI','G.DESC.LONG.SHOUNENAI','G.TYPE.SHOUNENAI'),
 (25,'G.DESC.SHORT.SLL','G.DESC.LONG.SLL','G.TYPE.SLL'),
 (26,'G.DESC.SHORT.ESPORTE','G.DESC.LONG.ESPORTE','G.TYPE.ESPORTE'),
 (27,'G.DESC.SHORT.SUPERN','G.DESC.LONG.SUPERN','G.TYPE.SUPERN'),
 (28,'G.DESC.SHORT.TORNEIO','G.DESC.LONG.TORNEIO','G.TYPE.TORNEIO'),
 (29,'G.DESC.SHORT.YAOI','G.DESC.LONG.YAOI','G.TYPE.YAOI'),
 (30,'G.DESC.SHORT.YURI','G.DESC.LONG.YURI','G.TYPE.YURI');
/*!40000 ALTER TABLE `genero` ENABLE KEYS */;


--
-- Definition of table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
CREATE TABLE `grupo` (
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
  CONSTRAINT `FK41E1C4997FDB552` FOREIGN KEY (`owner_id`) REFERENCES `login` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `grupo`
--

/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;


--
-- Definition of table `localidade`
--

DROP TABLE IF EXISTS `localidade`;
CREATE TABLE `localidade` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prefixo` varchar(255) NOT NULL,
  `tipoLocalidade` char(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `prefixo` (`prefixo`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `localidade`
--

/*!40000 ALTER TABLE `localidade` DISABLE KEYS */;
INSERT INTO `localidade` (`id`,`prefixo`,`tipoLocalidade`) VALUES 
 (1,'language.pt.br','I'),
 (2,'language.en.us','I'),
 (3,'language.jp.romaji','I'),
 (4,'language.jp','I'),
 (5,'language.ru','I'),
 (6,'language.ch','I'),
 (7,'language.ch.tai','I'),
 (8,'language.kr','I'),
 (9,'language.de','I'),
 (10,'language.default','P');
/*!40000 ALTER TABLE `localidade` ENABLE KEYS */;


--
-- Definition of table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
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
  CONSTRAINT `FK462FF49DABF34DA` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`),
  CONSTRAINT `FK462FF49DFD8BA3C` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `login`
--

/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` (`id`,`dataCadastro`,`email`,`senha`,`status`,`username`,`grupo_id`,`usuario_id`) VALUES 
 (1,NULL,'rsouza.ds@gmail.com','pm1ymo0b84ls7s9u753gt1usfdf4aow','ATIVO','rsouza',NULL,1);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;


--
-- Definition of table `loginrole`
--

DROP TABLE IF EXISTS `loginrole`;
CREATE TABLE `loginrole` (
  `login_fk` bigint(20) NOT NULL,
  `role_fk` bigint(20) NOT NULL,
  KEY `FK10F7275FE02BEDA2` (`role_fk`),
  KEY `FK10F7275FAD6CF486` (`login_fk`),
  CONSTRAINT `FK10F7275FAD6CF486` FOREIGN KEY (`login_fk`) REFERENCES `login` (`id`),
  CONSTRAINT `FK10F7275FE02BEDA2` FOREIGN KEY (`role_fk`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `loginrole`
--

/*!40000 ALTER TABLE `loginrole` DISABLE KEYS */;
INSERT INTO `loginrole` (`login_fk`,`role_fk`) VALUES 
 (1,1),
 (1,2),
 (1,9);
/*!40000 ALTER TABLE `loginrole` ENABLE KEYS */;


--
-- Definition of table `media`
--

DROP TABLE IF EXISTS `media`;
CREATE TABLE `media` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ano` int(11) NOT NULL,
  `capitulos` int(11) NOT NULL,
  `duracao` int(11) NOT NULL,
  `episodios` int(11) NOT NULL,
  `formatoAnime` varchar(255) DEFAULT NULL,
  `formatoDorama` varchar(255) DEFAULT NULL,
  `formatoMedia` varchar(255) DEFAULT NULL,
  `nomePrincipal` varchar(255) DEFAULT NULL,
  `origem` varchar(255) DEFAULT NULL,
  `pathImagem` varchar(255) DEFAULT NULL,
  `sinopse` longtext,
  `temporadas` int(11) NOT NULL,
  `volumes` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `media`
--

/*!40000 ALTER TABLE `media` DISABLE KEYS */;
/*!40000 ALTER TABLE `media` ENABLE KEYS */;


--
-- Definition of table `mediagenero`
--

DROP TABLE IF EXISTS `mediagenero`;
CREATE TABLE `mediagenero` (
  `media_fk` bigint(20) NOT NULL,
  `genero_fk` bigint(20) NOT NULL,
  PRIMARY KEY (`media_fk`,`genero_fk`),
  KEY `FK3A0180F6711A7E2A` (`media_fk`),
  KEY `FK3A0180F63983C19E` (`genero_fk`),
  CONSTRAINT `FK3A0180F63983C19E` FOREIGN KEY (`genero_fk`) REFERENCES `genero` (`id`),
  CONSTRAINT `FK3A0180F6711A7E2A` FOREIGN KEY (`media_fk`) REFERENCES `media` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `mediagenero`
--

/*!40000 ALTER TABLE `mediagenero` DISABLE KEYS */;
/*!40000 ALTER TABLE `mediagenero` ENABLE KEYS */;


--
-- Definition of table `mediatema`
--

DROP TABLE IF EXISTS `mediatema`;
CREATE TABLE `mediatema` (
  `media_fk` bigint(20) NOT NULL,
  `tema_fk` bigint(20) NOT NULL,
  PRIMARY KEY (`media_fk`,`tema_fk`),
  KEY `FKF706B5A9711A7E2A` (`media_fk`),
  KEY `FKF706B5A923DB573E` (`tema_fk`),
  CONSTRAINT `FKF706B5A923DB573E` FOREIGN KEY (`tema_fk`) REFERENCES `tema` (`id`),
  CONSTRAINT `FKF706B5A9711A7E2A` FOREIGN KEY (`media_fk`) REFERENCES `media` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `mediatema`
--

/*!40000 ALTER TABLE `mediatema` DISABLE KEYS */;
/*!40000 ALTER TABLE `mediatema` ENABLE KEYS */;


--
-- Definition of table `mediatitulo`
--

DROP TABLE IF EXISTS `mediatitulo`;
CREATE TABLE `mediatitulo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `principal` bit(1) NOT NULL,
  `localidade_id` bigint(20) DEFAULT NULL,
  `media_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK506BD49D6421946A` (`localidade_id`),
  KEY `FK506BD49D711A7E80` (`media_id`),
  CONSTRAINT `FK506BD49D711A7E80` FOREIGN KEY (`media_id`) REFERENCES `media` (`id`),
  CONSTRAINT `FK506BD49D6421946A` FOREIGN KEY (`localidade_id`) REFERENCES `localidade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `mediatitulo`
--

/*!40000 ALTER TABLE `mediatitulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `mediatitulo` ENABLE KEYS */;


--
-- Definition of table `origem`
--

DROP TABLE IF EXISTS `origem`;
CREATE TABLE `origem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `idOrigem` bigint(20) DEFAULT NULL,
  `siteOrigem` varchar(255) DEFAULT NULL,
  `ultimaAtualizacao` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `origem`
--

/*!40000 ALTER TABLE `origem` DISABLE KEYS */;
/*!40000 ALTER TABLE `origem` ENABLE KEYS */;


--
-- Definition of table `permissao`
--

DROP TABLE IF EXISTS `permissao`;
CREATE TABLE `permissao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `habilitado` bit(1) NOT NULL,
  `nivel` bigint(20) DEFAULT NULL,
  `prefixo` varchar(255) DEFAULT NULL,
  `tipoSeguranca` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `permissao`
--

/*!40000 ALTER TABLE `permissao` DISABLE KEYS */;
INSERT INTO `permissao` (`id`,`descricao`,`habilitado`,`nivel`,`prefixo`,`tipoSeguranca`) VALUES 
 (1,'Menu Administrador',0x01,NULL,'RM_ADMIN','SESSAO'),
 (2,'Submenu de Administrador - Midia',0x01,1,'RM_IM_ADMIN_MIDIA','SESSAO'),
 (3,'Submenu de Midia em Administrador - Inserir Midia',0x01,2,'RM_IMIM_INSERIR_MIDIA','SESSAO'),
 (4,'Submenu de Midia em Administrador - Midias Pendentes',0x01,2,'RM_IMIM_PENDENTE','SESSAO'),
 (5,'Menu SeguranÃ§a',0x01,NULL,'RM_SECURITY','SESSAO'),
 (6,'Submenu Detalhe de PermissÃ£o',0x01,5,'RM_IM_PERMISSAO','SESSAO'),
 (7,'Submenu Detalhe de Role',0x01,5,'RM_IM_ROLE','SESSAO'),
 (8,'Menu Media',0x01,NULL,'RM_MEDIA','SESSAO'),
 (9,'Submenu Consulta em Media',0x01,8,'RM_IM_MEDIA_CONSULTA','SESSAO'),
 (10,'Habilita botÃ£o para alterar filler do conteudo',0x01,NULL,'GRANT_ALTERAR_FILLER','SESSAO'),
 (11,'habilita botÃ£o para adicionar conteudo',0x01,NULL,'GRANT_ADD_CONTEUDO','SESSAO');
/*!40000 ALTER TABLE `permissao` ENABLE KEYS */;


--
-- Definition of table `projeto`
--

DROP TABLE IF EXISTS `projeto`;
CREATE TABLE `projeto` (
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
  CONSTRAINT `FK50C8E503711A7E80` FOREIGN KEY (`media_id`) REFERENCES `media` (`id`),
  CONSTRAINT `FK50C8E503C2D49EF7` FOREIGN KEY (`usuario_id`) REFERENCES `login` (`id`),
  CONSTRAINT `FK50C8E503DABF34DA` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `projeto`
--

/*!40000 ALTER TABLE `projeto` DISABLE KEYS */;
/*!40000 ALTER TABLE `projeto` ENABLE KEYS */;


--
-- Definition of table `projetoconteudo`
--

DROP TABLE IF EXISTS `projetoconteudo`;
CREATE TABLE `projetoconteudo` (
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
  CONSTRAINT `FKDEEB09901905BA89` FOREIGN KEY (`projeto_fk`) REFERENCES `projeto` (`id`),
  CONSTRAINT `FKDEEB0990234C93E` FOREIGN KEY (`conteudo_fk`) REFERENCES `conteudo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `projetoconteudo`
--

/*!40000 ALTER TABLE `projetoconteudo` DISABLE KEYS */;
/*!40000 ALTER TABLE `projetoconteudo` ENABLE KEYS */;


--
-- Definition of table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `prefixo` varchar(255) DEFAULT NULL,
  `tipoSeguranca` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`,`descricao`,`prefixo`,`tipoSeguranca`) VALUES 
 (1,'Administrador geral do sistema','ROLE_ROOT',NULL),
 (2,'Administrador das midias em geral','ROLE_ADMIN',NULL),
 (3,'Atende as solicitaÃ§Ãµes efetuadas','ROLE_ADMIN_LISTENER',NULL),
 (4,'Administrador de um grupo','ROLE_GROUP_ADMIN',NULL),
 (5,'Uploader de um grupo','ROLE_GROUP_UPLOADER',NULL),
 (6,'Membro de um grupo','ROLE_GROUP_MEMBER',NULL),
 (7,'Uploader de um grupo','ROLE_GROUP_MODERADOR',NULL),
 (8,'UsuÃ¡rio comum do sistema','ROLE_USER',NULL),
 (9,'Visitante do sistema','NO_ROLE',NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


--
-- Definition of table `rolepermissao`
--

DROP TABLE IF EXISTS `rolepermissao`;
CREATE TABLE `rolepermissao` (
  `role_fk` bigint(20) NOT NULL,
  `permissao_fk` bigint(20) NOT NULL,
  KEY `FK8D317D91E02BEDA2` (`role_fk`),
  KEY `FK8D317D916A9EA6C6` (`permissao_fk`),
  CONSTRAINT `FK8D317D916A9EA6C6` FOREIGN KEY (`permissao_fk`) REFERENCES `permissao` (`id`),
  CONSTRAINT `FK8D317D91E02BEDA2` FOREIGN KEY (`role_fk`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rolepermissao`
--

/*!40000 ALTER TABLE `rolepermissao` DISABLE KEYS */;
INSERT INTO `rolepermissao` (`role_fk`,`permissao_fk`) VALUES 
 (1,5),
 (1,6),
 (1,7),
 (2,1),
 (2,2),
 (2,3),
 (2,4),
 (2,10),
 (2,11),
 (9,8),
 (9,9);
/*!40000 ALTER TABLE `rolepermissao` ENABLE KEYS */;


--
-- Definition of table `session`
--

DROP TABLE IF EXISTS `session`;
CREATE TABLE `session` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataInicio` datetime DEFAULT NULL,
  `datatFim` datetime DEFAULT NULL,
  `numeroControle` varchar(255) DEFAULT NULL,
  `remoteAddress` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `login_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD9891A76AD6CF4DC` (`login_id`),
  CONSTRAINT `FKD9891A76AD6CF4DC` FOREIGN KEY (`login_id`) REFERENCES `login` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `session`
--

/*!40000 ALTER TABLE `session` DISABLE KEYS */;
/*!40000 ALTER TABLE `session` ENABLE KEYS */;


--
-- Definition of table `tema`
--

DROP TABLE IF EXISTS `tema`;
CREATE TABLE `tema` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `prefixo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `prefixo` (`prefixo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tema`
--

/*!40000 ALTER TABLE `tema` DISABLE KEYS */;
/*!40000 ALTER TABLE `tema` ENABLE KEYS */;


--
-- Definition of table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataNascimento` date DEFAULT NULL,
  `primeiroNome` varchar(255) DEFAULT NULL,
  `sexo` char(1) DEFAULT NULL,
  `ultimoNome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`,`dataNascimento`,`primeiroNome`,`sexo`,`ultimoNome`) VALUES 
 (1,NULL,'Rafael','M','Souza');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
