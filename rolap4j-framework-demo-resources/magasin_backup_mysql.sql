-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.41-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema magasin
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ magasin;
USE magasin;

--
-- Table structure for table `magasin`.`achats`
--

DROP TABLE IF EXISTS `achats`;
CREATE TABLE `achats` (
  `fournisseur_id` int(11) NOT NULL,
  `produit_id` int(11) NOT NULL,
  `lieu_id` int(11) NOT NULL,
  `temps_id` int(11) NOT NULL,
  `quantite` int(11) NOT NULL,
  `prix_unitaire` int(11) NOT NULL,
  PRIMARY KEY  (`fournisseur_id`,`produit_id`,`lieu_id`,`temps_id`),
  KEY `produit_id` (`produit_id`),
  KEY `lieu_id` (`lieu_id`),
  KEY `temps_id` (`temps_id`),
  CONSTRAINT `achats_ibfk_1` FOREIGN KEY (`fournisseur_id`) REFERENCES `fournisseur` (`fournisseur_id`),
  CONSTRAINT `achats_ibfk_2` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`produit_id`),
  CONSTRAINT `achats_ibfk_3` FOREIGN KEY (`lieu_id`) REFERENCES `lieu` (`lieu_id`),
  CONSTRAINT `achats_ibfk_4` FOREIGN KEY (`temps_id`) REFERENCES `temps` (`temps_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `magasin`.`achats`
--

/*!40000 ALTER TABLE `achats` DISABLE KEYS */;
INSERT INTO `achats` (`fournisseur_id`,`produit_id`,`lieu_id`,`temps_id`,`quantite`,`prix_unitaire`) VALUES 
 (1,1,22,271,1,700),
 (1,1,22,301,9,700),
 (1,1,22,361,42,700),
 (1,2,22,181,1,700),
 (1,2,22,301,7,700),
 (1,3,22,31,8,700),
 (1,3,22,151,19,700),
 (1,3,22,211,10,700),
 (1,3,22,271,24,700),
 (1,4,22,1,46,700),
 (1,5,22,61,19,600),
 (1,5,22,151,18,600),
 (1,5,22,211,18,600),
 (1,6,22,61,20,600),
 (1,6,22,151,20,600),
 (1,6,22,331,5,600),
 (1,6,22,361,43,600),
 (1,7,22,151,28,600),
 (1,8,22,301,20,150),
 (1,9,22,91,21,150),
 (1,9,22,121,16,150),
 (1,9,22,271,14,150),
 (1,9,22,331,17,150),
 (1,10,22,91,5,150),
 (1,10,22,241,47,150),
 (1,10,22,301,5,150),
 (1,11,22,61,35,150),
 (1,11,22,91,6,150),
 (1,11,22,151,6,150),
 (1,11,22,181,30,150),
 (2,1,22,61,23,700),
 (2,1,22,91,14,700),
 (2,1,22,331,45,700),
 (2,2,22,61,44,700),
 (2,2,22,151,44,700),
 (2,2,22,331,38,700),
 (2,3,22,181,43,700),
 (2,4,22,61,23,700),
 (2,4,22,91,29,700),
 (2,4,22,181,34,700),
 (2,4,22,361,9,700),
 (2,5,22,31,40,600),
 (2,5,22,91,37,600);
INSERT INTO `achats` (`fournisseur_id`,`produit_id`,`lieu_id`,`temps_id`,`quantite`,`prix_unitaire`) VALUES 
 (2,5,22,271,47,600),
 (2,5,22,301,22,600),
 (2,5,22,331,46,600),
 (2,6,22,181,44,600),
 (2,7,22,31,39,600),
 (2,7,22,61,24,600),
 (2,7,22,181,41,600),
 (2,7,22,211,46,600),
 (2,7,22,271,22,600),
 (2,7,22,331,25,600),
 (2,7,22,361,14,600),
 (2,8,22,181,6,150),
 (2,8,22,241,25,150),
 (2,8,22,361,15,150),
 (2,9,22,1,26,150),
 (2,9,22,31,7,150),
 (2,10,22,361,34,150),
 (2,11,22,1,10,150),
 (2,11,22,121,49,150),
 (2,11,22,301,32,150),
 (2,11,22,361,19,150),
 (3,1,22,121,37,700),
 (3,1,22,181,14,700),
 (3,2,22,1,8,700),
 (3,2,22,121,15,700),
 (3,2,22,211,34,700),
 (3,2,22,241,19,700),
 (3,4,22,31,46,700),
 (3,5,22,181,1,600),
 (3,6,22,31,46,600),
 (3,6,22,121,22,600),
 (3,6,22,211,3,600),
 (3,7,22,241,29,600),
 (3,8,22,1,34,150),
 (3,8,22,31,11,150),
 (3,8,22,121,24,150),
 (3,8,22,271,8,150),
 (3,9,22,241,10,150),
 (3,9,22,301,18,150),
 (3,9,22,361,35,150),
 (3,10,22,31,18,150),
 (3,10,22,151,49,150),
 (3,10,22,181,42,150);
INSERT INTO `achats` (`fournisseur_id`,`produit_id`,`lieu_id`,`temps_id`,`quantite`,`prix_unitaire`) VALUES 
 (3,10,22,331,30,150),
 (3,11,22,271,33,150),
 (4,1,22,1,0,700),
 (4,1,22,211,21,700),
 (4,1,22,241,41,700),
 (4,2,22,31,17,700),
 (4,3,22,61,1,700),
 (4,3,22,121,37,700),
 (4,3,22,301,28,700),
 (4,4,22,151,11,700),
 (4,4,22,241,42,700),
 (4,4,22,301,4,700),
 (4,4,22,331,45,700),
 (4,5,22,121,28,600),
 (4,5,22,241,47,600),
 (4,6,22,91,10,600),
 (4,7,22,91,29,600),
 (4,9,22,211,20,150),
 (4,10,22,211,31,150),
 (4,11,22,31,35,150),
 (4,11,22,241,0,150);
/*!40000 ALTER TABLE `achats` ENABLE KEYS */;


--
-- Table structure for table `magasin`.`client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `client_id` int(11) NOT NULL,
  `client_type` varchar(20) NOT NULL,
  `client_nom` varchar(20) NOT NULL,
  PRIMARY KEY  (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `magasin`.`client`
--

/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` (`client_id`,`client_type`,`client_nom`) VALUES 
 (1,'Société','Microsoft'),
 (2,'Société','Google'),
 (3,'Société','Linkedin'),
 (4,'Société','Tweeter'),
 (5,'Société','Facebook'),
 (6,'Société','Oracle'),
 (7,'Particulier','Will Smith'),
 (8,'Particulier','Brad Pitt'),
 (9,'Particulier','Angelina Jolie'),
 (10,'Particulier','Nicole Kidman'),
 (11,'Particulier','Denzel Washington'),
 (12,'Particulier','Lionel Messi'),
 (13,'Particulier','Celine Dion'),
 (14,'Particulier','Édith Piaf');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;


--
-- Table structure for table `magasin`.`fournisseur`
--

DROP TABLE IF EXISTS `fournisseur`;
CREATE TABLE `fournisseur` (
  `fournisseur_id` int(11) NOT NULL,
  `fournisseur_type` varchar(20) NOT NULL,
  `fournisseur_nom` varchar(20) NOT NULL,
  `fournisseur_pays` varchar(20) NOT NULL,
  `fournisseur_region` varchar(20) NOT NULL,
  `fournisseur_ville` varchar(20) NOT NULL,
  PRIMARY KEY  (`fournisseur_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `magasin`.`fournisseur`
--

/*!40000 ALTER TABLE `fournisseur` DISABLE KEYS */;
INSERT INTO `fournisseur` (`fournisseur_id`,`fournisseur_type`,`fournisseur_nom`,`fournisseur_pays`,`fournisseur_region`,`fournisseur_ville`) VALUES 
 (1,'Local','Zitouna Service','Tunisie','Nord','Kef'),
 (2,'Local','Carthage I-Tech','Tunisie','Centre','Siliana'),
 (3,'Local','Tun Industriel','Tunisie','Sud','Gafsa'),
 (4,'Etranger','Creative IT','France','Nord','Strasbourg'),
 (5,'Etranger','Electronic Systems','France','Centre','Lyon'),
 (6,'Etranger','TechniPrint S','France','Sud','Toulouse');
/*!40000 ALTER TABLE `fournisseur` ENABLE KEYS */;


--
-- Table structure for table `magasin`.`lieu`
--

DROP TABLE IF EXISTS `lieu`;
CREATE TABLE `lieu` (
  `lieu_id` int(11) NOT NULL,
  `lieu_pays` varchar(20) NOT NULL,
  `lieu_region` varchar(20) NOT NULL,
  `lieu_ville` varchar(20) NOT NULL,
  PRIMARY KEY  (`lieu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `magasin`.`lieu`
--

/*!40000 ALTER TABLE `lieu` DISABLE KEYS */;
INSERT INTO `lieu` (`lieu_id`,`lieu_pays`,`lieu_region`,`lieu_ville`) VALUES 
 (1,'Tunisie','Nord','Béja'),
 (2,'Tunisie','Nord','Bizerte'),
 (3,'Tunisie','Nord','Jendouba'),
 (4,'Tunisie','Nord','Kef'),
 (5,'Tunisie','Nord','Nabeul'),
 (6,'Tunisie','Nord','Tabarka'),
 (7,'Tunisie','Nord','Zaghouan'),
 (8,'Tunisie','Centre','Kairouan'),
 (9,'Tunisie','Centre','Kasserine'),
 (10,'Tunisie','Centre','Mahdia'),
 (11,'Tunisie','Centre','Monastir'),
 (12,'Tunisie','Centre','Sfax'),
 (13,'Tunisie','Centre','Sidi Bouzid'),
 (14,'Tunisie','Centre','Siliana'),
 (15,'Tunisie','Centre','Sousse'),
 (16,'Tunisie','Sud','Gabès'),
 (17,'Tunisie','Sud','Gafsa'),
 (18,'Tunisie','Sud','Kebili'),
 (19,'Tunisie','Sud','Medenine'),
 (20,'Tunisie','Sud','Tataouine'),
 (21,'Tunisie','Sud','Tozeur'),
 (22,'Tunisie','Nord','Tunis');
/*!40000 ALTER TABLE `lieu` ENABLE KEYS */;


--
-- Table structure for table `magasin`.`produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE `produit` (
  `produit_id` int(11) NOT NULL,
  `produit_category` varchar(20) NOT NULL,
  `produit_marque` varchar(20) NOT NULL,
  `produit_couleur` varchar(20) NOT NULL,
  `produit_nom` varchar(20) NOT NULL,
  PRIMARY KEY  (`produit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `magasin`.`produit`
--

/*!40000 ALTER TABLE `produit` DISABLE KEYS */;
INSERT INTO `produit` (`produit_id`,`produit_category`,`produit_marque`,`produit_couleur`,`produit_nom`) VALUES 
 (1,'Ordinateur','Lenovo','Bleu','PC-Lenovo-Bleu'),
 (2,'Ordinateur','Samsung','Blanc','PC-Samsung-Blanc'),
 (3,'Ordinateur','Sony','Blanc','PC-Sony-Blanc'),
 (4,'Ordinateur','Toshiba','Noir','PC-Toshiba-Noir'),
 (5,'Tablette','Evertech','Blanc','Evertech-Blanc'),
 (6,'Tablette','Samsung','Bleu','Samsung-Bleu'),
 (7,'Tablette','Ipad','Noir','Ipad-Noir'),
 (8,'Téléphone','Iphone','Bleu','Iphone-Bleu'),
 (9,'Téléphone','Samsung','Noir','Samsung-Noir'),
 (10,'Téléphone','Nokia','Noir','Nokia-Noir'),
 (11,'Téléphone','LG','Bleu','LG-Bleu');
/*!40000 ALTER TABLE `produit` ENABLE KEYS */;


--
-- Table structure for table `magasin`.`temps`
--

DROP TABLE IF EXISTS `temps`;
CREATE TABLE `temps` (
  `temps_id` int(11) NOT NULL,
  `temps_annee` int(11) NOT NULL,
  `temps_trimestre` int(11) NOT NULL,
  `temps_mois` int(11) NOT NULL,
  `temps_jour_mois` int(11) NOT NULL,
  `temps_jour_semaine` int(11) NOT NULL,
  `temps_date` date NOT NULL,
  PRIMARY KEY  (`temps_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `magasin`.`temps`
--

/*!40000 ALTER TABLE `temps` DISABLE KEYS */;
INSERT INTO `temps` (`temps_id`,`temps_annee`,`temps_trimestre`,`temps_mois`,`temps_jour_mois`,`temps_jour_semaine`,`temps_date`) VALUES 
 (1,2013,1,1,1,2,'2013-02-01'),
 (2,2013,1,1,2,3,'2013-02-02'),
 (3,2013,1,1,3,4,'2013-02-03'),
 (4,2013,1,1,4,5,'2013-02-04'),
 (5,2013,1,1,5,6,'2013-02-05'),
 (6,2013,1,1,6,7,'2013-02-06'),
 (7,2013,1,1,7,1,'2013-02-07'),
 (8,2013,1,1,8,2,'2013-02-08'),
 (9,2013,1,1,9,3,'2013-02-09'),
 (10,2013,1,1,10,4,'2013-02-10'),
 (11,2013,1,1,11,5,'2013-02-11'),
 (12,2013,1,1,12,6,'2013-02-12'),
 (13,2013,1,1,13,7,'2013-02-13'),
 (14,2013,1,1,14,1,'2013-02-14'),
 (15,2013,1,1,15,2,'2013-02-15'),
 (16,2013,1,1,16,3,'2013-02-16'),
 (17,2013,1,1,17,4,'2013-02-17'),
 (18,2013,1,1,18,5,'2013-02-18'),
 (19,2013,1,1,19,6,'2013-02-19'),
 (20,2013,1,1,20,7,'2013-02-20'),
 (21,2013,1,1,21,1,'2013-02-21'),
 (22,2013,1,1,22,2,'2013-02-22'),
 (23,2013,1,1,23,3,'2013-02-23'),
 (24,2013,1,1,24,4,'2013-02-24'),
 (25,2013,1,1,25,5,'2013-02-25'),
 (26,2013,1,1,26,6,'2013-02-26'),
 (27,2013,1,1,27,7,'2013-02-27');
INSERT INTO `temps` (`temps_id`,`temps_annee`,`temps_trimestre`,`temps_mois`,`temps_jour_mois`,`temps_jour_semaine`,`temps_date`) VALUES 
 (28,2013,1,1,28,1,'2013-02-28'),
 (29,2013,1,1,29,2,'2013-03-01'),
 (30,2013,1,1,30,3,'2013-03-02'),
 (31,2013,1,1,31,4,'2013-03-03'),
 (32,2013,1,2,1,5,'2013-03-01'),
 (33,2013,1,2,2,6,'2013-03-02'),
 (34,2013,1,2,3,7,'2013-03-03'),
 (35,2013,1,2,4,1,'2013-03-04'),
 (36,2013,1,2,5,2,'2013-03-05'),
 (37,2013,1,2,6,3,'2013-03-06'),
 (38,2013,1,2,7,4,'2013-03-07'),
 (39,2013,1,2,8,5,'2013-03-08'),
 (40,2013,1,2,9,6,'2013-03-09'),
 (41,2013,1,2,10,7,'2013-03-10'),
 (42,2013,1,2,11,1,'2013-03-11'),
 (43,2013,1,2,12,2,'2013-03-12'),
 (44,2013,1,2,13,3,'2013-03-13'),
 (45,2013,1,2,14,4,'2013-03-14'),
 (46,2013,1,2,15,5,'2013-03-15'),
 (47,2013,1,2,16,6,'2013-03-16'),
 (48,2013,1,2,17,7,'2013-03-17'),
 (49,2013,1,2,18,1,'2013-03-18'),
 (50,2013,1,2,19,2,'2013-03-19'),
 (51,2013,1,2,20,3,'2013-03-20'),
 (52,2013,1,2,21,4,'2013-03-21'),
 (53,2013,1,2,22,5,'2013-03-22'),
 (54,2013,1,2,23,6,'2013-03-23');
INSERT INTO `temps` (`temps_id`,`temps_annee`,`temps_trimestre`,`temps_mois`,`temps_jour_mois`,`temps_jour_semaine`,`temps_date`) VALUES 
 (55,2013,1,2,24,7,'2013-03-24'),
 (56,2013,1,2,25,1,'2013-03-25'),
 (57,2013,1,2,26,2,'2013-03-26'),
 (58,2013,1,2,27,3,'2013-03-27'),
 (59,2013,1,2,28,4,'2013-03-28'),
 (60,2013,1,3,1,5,'2013-04-01'),
 (61,2013,1,3,2,6,'2013-04-02'),
 (62,2013,1,3,3,7,'2013-04-03'),
 (63,2013,1,3,4,1,'2013-04-04'),
 (64,2013,1,3,5,2,'2013-04-05'),
 (65,2013,1,3,6,3,'2013-04-06'),
 (66,2013,1,3,7,4,'2013-04-07'),
 (67,2013,1,3,8,5,'2013-04-08'),
 (68,2013,1,3,9,6,'2013-04-09'),
 (69,2013,1,3,10,7,'2013-04-10'),
 (70,2013,1,3,11,1,'2013-04-11'),
 (71,2013,1,3,12,2,'2013-04-12'),
 (72,2013,1,3,13,3,'2013-04-13'),
 (73,2013,1,3,14,4,'2013-04-14'),
 (74,2013,1,3,15,5,'2013-04-15'),
 (75,2013,1,3,16,6,'2013-04-16'),
 (76,2013,1,3,17,7,'2013-04-17'),
 (77,2013,1,3,18,1,'2013-04-18'),
 (78,2013,1,3,19,2,'2013-04-19'),
 (79,2013,1,3,20,3,'2013-04-20'),
 (80,2013,1,3,21,4,'2013-04-21'),
 (81,2013,1,3,22,5,'2013-04-22');
INSERT INTO `temps` (`temps_id`,`temps_annee`,`temps_trimestre`,`temps_mois`,`temps_jour_mois`,`temps_jour_semaine`,`temps_date`) VALUES 
 (82,2013,1,3,23,6,'2013-04-23'),
 (83,2013,1,3,24,7,'2013-04-24'),
 (84,2013,1,3,25,1,'2013-04-25'),
 (85,2013,1,3,26,2,'2013-04-26'),
 (86,2013,1,3,27,3,'2013-04-27'),
 (87,2013,1,3,28,4,'2013-04-28'),
 (88,2013,1,3,29,5,'2013-04-29'),
 (89,2013,1,3,30,6,'2013-04-30'),
 (90,2013,1,3,31,7,'2013-05-01'),
 (91,2013,2,4,1,1,'2013-05-01'),
 (92,2013,2,4,2,2,'2013-05-02'),
 (93,2013,2,4,3,3,'2013-05-03'),
 (94,2013,2,4,4,4,'2013-05-04'),
 (95,2013,2,4,5,5,'2013-05-05'),
 (96,2013,2,4,6,6,'2013-05-06'),
 (97,2013,2,4,7,7,'2013-05-07'),
 (98,2013,2,4,8,1,'2013-05-08'),
 (99,2013,2,4,9,2,'2013-05-09'),
 (100,2013,2,4,10,3,'2013-05-10'),
 (101,2013,2,4,11,4,'2013-05-11'),
 (102,2013,2,4,12,5,'2013-05-12'),
 (103,2013,2,4,13,6,'2013-05-13'),
 (104,2013,2,4,14,7,'2013-05-14'),
 (105,2013,2,4,15,1,'2013-05-15'),
 (106,2013,2,4,16,2,'2013-05-16'),
 (107,2013,2,4,17,3,'2013-05-17'),
 (108,2013,2,4,18,4,'2013-05-18');
INSERT INTO `temps` (`temps_id`,`temps_annee`,`temps_trimestre`,`temps_mois`,`temps_jour_mois`,`temps_jour_semaine`,`temps_date`) VALUES 
 (109,2013,2,4,19,5,'2013-05-19'),
 (110,2013,2,4,20,6,'2013-05-20'),
 (111,2013,2,4,21,7,'2013-05-21'),
 (112,2013,2,4,22,1,'2013-05-22'),
 (113,2013,2,4,23,2,'2013-05-23'),
 (114,2013,2,4,24,3,'2013-05-24'),
 (115,2013,2,4,25,4,'2013-05-25'),
 (116,2013,2,4,26,5,'2013-05-26'),
 (117,2013,2,4,27,6,'2013-05-27'),
 (118,2013,2,4,28,7,'2013-05-28'),
 (119,2013,2,4,29,1,'2013-05-29'),
 (120,2013,2,4,30,2,'2013-05-30'),
 (121,2013,2,5,1,3,'2013-06-01'),
 (122,2013,2,5,2,4,'2013-06-02'),
 (123,2013,2,5,3,5,'2013-06-03'),
 (124,2013,2,5,4,6,'2013-06-04'),
 (125,2013,2,5,5,7,'2013-06-05'),
 (126,2013,2,5,6,1,'2013-06-06'),
 (127,2013,2,5,7,2,'2013-06-07'),
 (128,2013,2,5,8,3,'2013-06-08'),
 (129,2013,2,5,9,4,'2013-06-09'),
 (130,2013,2,5,10,5,'2013-06-10'),
 (131,2013,2,5,11,6,'2013-06-11'),
 (132,2013,2,5,12,7,'2013-06-12'),
 (133,2013,2,5,13,1,'2013-06-13'),
 (134,2013,2,5,14,2,'2013-06-14');
INSERT INTO `temps` (`temps_id`,`temps_annee`,`temps_trimestre`,`temps_mois`,`temps_jour_mois`,`temps_jour_semaine`,`temps_date`) VALUES 
 (135,2013,2,5,15,3,'2013-06-15'),
 (136,2013,2,5,16,4,'2013-06-16'),
 (137,2013,2,5,17,5,'2013-06-17'),
 (138,2013,2,5,18,6,'2013-06-18'),
 (139,2013,2,5,19,7,'2013-06-19'),
 (140,2013,2,5,20,1,'2013-06-20'),
 (141,2013,2,5,21,2,'2013-06-21'),
 (142,2013,2,5,22,3,'2013-06-22'),
 (143,2013,2,5,23,4,'2013-06-23'),
 (144,2013,2,5,24,5,'2013-06-24'),
 (145,2013,2,5,25,6,'2013-06-25'),
 (146,2013,2,5,26,7,'2013-06-26'),
 (147,2013,2,5,27,1,'2013-06-27'),
 (148,2013,2,5,28,2,'2013-06-28'),
 (149,2013,2,5,29,3,'2013-06-29'),
 (150,2013,2,5,30,4,'2013-06-30'),
 (151,2013,2,5,31,5,'2013-07-01'),
 (152,2013,2,6,1,6,'2013-07-01'),
 (153,2013,2,6,2,7,'2013-07-02'),
 (154,2013,2,6,3,1,'2013-07-03'),
 (155,2013,2,6,4,2,'2013-07-04'),
 (156,2013,2,6,5,3,'2013-07-05'),
 (157,2013,2,6,6,4,'2013-07-06'),
 (158,2013,2,6,7,5,'2013-07-07'),
 (159,2013,2,6,8,6,'2013-07-08'),
 (160,2013,2,6,9,7,'2013-07-09');
INSERT INTO `temps` (`temps_id`,`temps_annee`,`temps_trimestre`,`temps_mois`,`temps_jour_mois`,`temps_jour_semaine`,`temps_date`) VALUES 
 (161,2013,2,6,10,1,'2013-07-10'),
 (162,2013,2,6,11,2,'2013-07-11'),
 (163,2013,2,6,12,3,'2013-07-12'),
 (164,2013,2,6,13,4,'2013-07-13'),
 (165,2013,2,6,14,5,'2013-07-14'),
 (166,2013,2,6,15,6,'2013-07-15'),
 (167,2013,2,6,16,7,'2013-07-16'),
 (168,2013,2,6,17,1,'2013-07-17'),
 (169,2013,2,6,18,2,'2013-07-18'),
 (170,2013,2,6,19,3,'2013-07-19'),
 (171,2013,2,6,20,4,'2013-07-20'),
 (172,2013,2,6,21,5,'2013-07-21'),
 (173,2013,2,6,22,6,'2013-07-22'),
 (174,2013,2,6,23,7,'2013-07-23'),
 (175,2013,2,6,24,1,'2013-07-24'),
 (176,2013,2,6,25,2,'2013-07-25'),
 (177,2013,2,6,26,3,'2013-07-26'),
 (178,2013,2,6,27,4,'2013-07-27'),
 (179,2013,2,6,28,5,'2013-07-28'),
 (180,2013,2,6,29,6,'2013-07-29'),
 (181,2013,2,6,30,7,'2013-07-30'),
 (182,2013,3,7,1,1,'2013-08-01'),
 (183,2013,3,7,2,2,'2013-08-02'),
 (184,2013,3,7,3,3,'2013-08-03'),
 (185,2013,3,7,4,4,'2013-08-04'),
 (186,2013,3,7,5,5,'2013-08-05');
INSERT INTO `temps` (`temps_id`,`temps_annee`,`temps_trimestre`,`temps_mois`,`temps_jour_mois`,`temps_jour_semaine`,`temps_date`) VALUES 
 (187,2013,3,7,6,6,'2013-08-06'),
 (188,2013,3,7,7,7,'2013-08-07'),
 (189,2013,3,7,8,1,'2013-08-08'),
 (190,2013,3,7,9,2,'2013-08-09'),
 (191,2013,3,7,10,3,'2013-08-10'),
 (192,2013,3,7,11,4,'2013-08-11'),
 (193,2013,3,7,12,5,'2013-08-12'),
 (194,2013,3,7,13,6,'2013-08-13'),
 (195,2013,3,7,14,7,'2013-08-14'),
 (196,2013,3,7,15,1,'2013-08-15'),
 (197,2013,3,7,16,2,'2013-08-16'),
 (198,2013,3,7,17,3,'2013-08-17'),
 (199,2013,3,7,18,4,'2013-08-18'),
 (200,2013,3,7,19,5,'2013-08-19'),
 (201,2013,3,7,20,6,'2013-08-20'),
 (202,2013,3,7,21,7,'2013-08-21'),
 (203,2013,3,7,22,1,'2013-08-22'),
 (204,2013,3,7,23,2,'2013-08-23'),
 (205,2013,3,7,24,3,'2013-08-24'),
 (206,2013,3,7,25,4,'2013-08-25'),
 (207,2013,3,7,26,5,'2013-08-26'),
 (208,2013,3,7,27,6,'2013-08-27'),
 (209,2013,3,7,28,7,'2013-08-28'),
 (210,2013,3,7,29,1,'2013-08-29'),
 (211,2013,3,7,30,2,'2013-08-30'),
 (212,2013,3,7,31,3,'2013-08-31');
INSERT INTO `temps` (`temps_id`,`temps_annee`,`temps_trimestre`,`temps_mois`,`temps_jour_mois`,`temps_jour_semaine`,`temps_date`) VALUES 
 (213,2013,3,8,1,4,'2013-09-01'),
 (214,2013,3,8,2,5,'2013-09-02'),
 (215,2013,3,8,3,6,'2013-09-03'),
 (216,2013,3,8,4,7,'2013-09-04'),
 (217,2013,3,8,5,1,'2013-09-05'),
 (218,2013,3,8,6,2,'2013-09-06'),
 (219,2013,3,8,7,3,'2013-09-07'),
 (220,2013,3,8,8,4,'2013-09-08'),
 (221,2013,3,8,9,5,'2013-09-09'),
 (222,2013,3,8,10,6,'2013-09-10'),
 (223,2013,3,8,11,7,'2013-09-11'),
 (224,2013,3,8,12,1,'2013-09-12'),
 (225,2013,3,8,13,2,'2013-09-13'),
 (226,2013,3,8,14,3,'2013-09-14'),
 (227,2013,3,8,15,4,'2013-09-15'),
 (228,2013,3,8,16,5,'2013-09-16'),
 (229,2013,3,8,17,6,'2013-09-17'),
 (230,2013,3,8,18,7,'2013-09-18'),
 (231,2013,3,8,19,1,'2013-09-19'),
 (232,2013,3,8,20,2,'2013-09-20'),
 (233,2013,3,8,21,3,'2013-09-21'),
 (234,2013,3,8,22,4,'2013-09-22'),
 (235,2013,3,8,23,5,'2013-09-23'),
 (236,2013,3,8,24,6,'2013-09-24'),
 (237,2013,3,8,25,7,'2013-09-25'),
 (238,2013,3,8,26,1,'2013-09-26');
INSERT INTO `temps` (`temps_id`,`temps_annee`,`temps_trimestre`,`temps_mois`,`temps_jour_mois`,`temps_jour_semaine`,`temps_date`) VALUES 
 (239,2013,3,8,27,2,'2013-09-27'),
 (240,2013,3,8,28,3,'2013-09-28'),
 (241,2013,3,8,29,4,'2013-09-29'),
 (242,2013,3,8,30,5,'2013-09-30'),
 (243,2013,3,8,31,6,'2013-10-01'),
 (244,2013,3,9,1,7,'2013-10-01'),
 (245,2013,3,9,2,1,'2013-10-02'),
 (246,2013,3,9,3,2,'2013-10-03'),
 (247,2013,3,9,4,3,'2013-10-04'),
 (248,2013,3,9,5,4,'2013-10-05'),
 (249,2013,3,9,6,5,'2013-10-06'),
 (250,2013,3,9,7,6,'2013-10-07'),
 (251,2013,3,9,8,7,'2013-10-08'),
 (252,2013,3,9,9,1,'2013-10-09'),
 (253,2013,3,9,10,2,'2013-10-10'),
 (254,2013,3,9,11,3,'2013-10-11'),
 (255,2013,3,9,12,4,'2013-10-12'),
 (256,2013,3,9,13,5,'2013-10-13'),
 (257,2013,3,9,14,6,'2013-10-14'),
 (258,2013,3,9,15,7,'2013-10-15'),
 (259,2013,3,9,16,1,'2013-10-16'),
 (260,2013,3,9,17,2,'2013-10-17'),
 (261,2013,3,9,18,3,'2013-10-18'),
 (262,2013,3,9,19,4,'2013-10-19'),
 (263,2013,3,9,20,5,'2013-10-20'),
 (264,2013,3,9,21,6,'2013-10-21');
INSERT INTO `temps` (`temps_id`,`temps_annee`,`temps_trimestre`,`temps_mois`,`temps_jour_mois`,`temps_jour_semaine`,`temps_date`) VALUES 
 (265,2013,3,9,22,7,'2013-10-22'),
 (266,2013,3,9,23,1,'2013-10-23'),
 (267,2013,3,9,24,2,'2013-10-24'),
 (268,2013,3,9,25,3,'2013-10-25'),
 (269,2013,3,9,26,4,'2013-10-26'),
 (270,2013,3,9,27,5,'2013-10-27'),
 (271,2013,3,9,28,6,'2013-10-28'),
 (272,2013,3,9,29,7,'2013-10-29'),
 (273,2013,3,9,30,1,'2013-10-30'),
 (274,2013,4,10,1,2,'2013-11-01'),
 (275,2013,4,10,2,3,'2013-11-02'),
 (276,2013,4,10,3,4,'2013-11-03'),
 (277,2013,4,10,4,5,'2013-11-04'),
 (278,2013,4,10,5,6,'2013-11-05'),
 (279,2013,4,10,6,7,'2013-11-06'),
 (280,2013,4,10,7,1,'2013-11-07'),
 (281,2013,4,10,8,2,'2013-11-08'),
 (282,2013,4,10,9,3,'2013-11-09'),
 (283,2013,4,10,10,4,'2013-11-10'),
 (284,2013,4,10,11,5,'2013-11-11'),
 (285,2013,4,10,12,6,'2013-11-12'),
 (286,2013,4,10,13,7,'2013-11-13'),
 (287,2013,4,10,14,1,'2013-11-14'),
 (288,2013,4,10,15,2,'2013-11-15'),
 (289,2013,4,10,16,3,'2013-11-16'),
 (290,2013,4,10,17,4,'2013-11-17');
INSERT INTO `temps` (`temps_id`,`temps_annee`,`temps_trimestre`,`temps_mois`,`temps_jour_mois`,`temps_jour_semaine`,`temps_date`) VALUES 
 (291,2013,4,10,18,5,'2013-11-18'),
 (292,2013,4,10,19,6,'2013-11-19'),
 (293,2013,4,10,20,7,'2013-11-20'),
 (294,2013,4,10,21,1,'2013-11-21'),
 (295,2013,4,10,22,2,'2013-11-22'),
 (296,2013,4,10,23,3,'2013-11-23'),
 (297,2013,4,10,24,4,'2013-11-24'),
 (298,2013,4,10,25,5,'2013-11-25'),
 (299,2013,4,10,26,6,'2013-11-26'),
 (300,2013,4,10,27,7,'2013-11-27'),
 (301,2013,4,10,28,1,'2013-11-28'),
 (302,2013,4,10,29,2,'2013-11-29'),
 (303,2013,4,10,30,3,'2013-11-30'),
 (304,2013,4,10,31,4,'2013-12-01'),
 (305,2013,4,11,1,5,'2013-12-01'),
 (306,2013,4,11,2,6,'2013-12-02'),
 (307,2013,4,11,3,7,'2013-12-03'),
 (308,2013,4,11,4,1,'2013-12-04'),
 (309,2013,4,11,5,2,'2013-12-05'),
 (310,2013,4,11,6,3,'2013-12-06'),
 (311,2013,4,11,7,4,'2013-12-07'),
 (312,2013,4,11,8,5,'2013-12-08'),
 (313,2013,4,11,9,6,'2013-12-09'),
 (314,2013,4,11,10,7,'2013-12-10'),
 (315,2013,4,11,11,1,'2013-12-11'),
 (316,2013,4,11,12,2,'2013-12-12');
INSERT INTO `temps` (`temps_id`,`temps_annee`,`temps_trimestre`,`temps_mois`,`temps_jour_mois`,`temps_jour_semaine`,`temps_date`) VALUES 
 (317,2013,4,11,13,3,'2013-12-13'),
 (318,2013,4,11,14,4,'2013-12-14'),
 (319,2013,4,11,15,5,'2013-12-15'),
 (320,2013,4,11,16,6,'2013-12-16'),
 (321,2013,4,11,17,7,'2013-12-17'),
 (322,2013,4,11,18,1,'2013-12-18'),
 (323,2013,4,11,19,2,'2013-12-19'),
 (324,2013,4,11,20,3,'2013-12-20'),
 (325,2013,4,11,21,4,'2013-12-21'),
 (326,2013,4,11,22,5,'2013-12-22'),
 (327,2013,4,11,23,6,'2013-12-23'),
 (328,2013,4,11,24,7,'2013-12-24'),
 (329,2013,4,11,25,1,'2013-12-25'),
 (330,2013,4,11,26,2,'2013-12-26'),
 (331,2013,4,11,27,3,'2013-12-27'),
 (332,2013,4,11,28,4,'2013-12-28'),
 (333,2013,4,11,29,5,'2013-12-29'),
 (334,2013,4,11,30,6,'2013-12-30'),
 (335,2013,4,12,1,7,'2014-01-01'),
 (336,2013,4,12,2,1,'2014-01-02'),
 (337,2013,4,12,3,2,'2014-01-03'),
 (338,2013,4,12,4,3,'2014-01-04'),
 (339,2013,4,12,5,4,'2014-01-05'),
 (340,2013,4,12,6,5,'2014-01-06'),
 (341,2013,4,12,7,6,'2014-01-07'),
 (342,2013,4,12,8,7,'2014-01-08');
INSERT INTO `temps` (`temps_id`,`temps_annee`,`temps_trimestre`,`temps_mois`,`temps_jour_mois`,`temps_jour_semaine`,`temps_date`) VALUES 
 (343,2013,4,12,9,1,'2014-01-09'),
 (344,2013,4,12,10,2,'2014-01-10'),
 (345,2013,4,12,11,3,'2014-01-11'),
 (346,2013,4,12,12,4,'2014-01-12'),
 (347,2013,4,12,13,5,'2014-01-13'),
 (348,2013,4,12,14,6,'2014-01-14'),
 (349,2013,4,12,15,7,'2014-01-15'),
 (350,2013,4,12,16,1,'2014-01-16'),
 (351,2013,4,12,17,2,'2014-01-17'),
 (352,2013,4,12,18,3,'2014-01-18'),
 (353,2013,4,12,19,4,'2014-01-19'),
 (354,2013,4,12,20,5,'2014-01-20'),
 (355,2013,4,12,21,6,'2014-01-21'),
 (356,2013,4,12,22,7,'2014-01-22'),
 (357,2013,4,12,23,1,'2014-01-23'),
 (358,2013,4,12,24,2,'2014-01-24'),
 (359,2013,4,12,25,3,'2014-01-25'),
 (360,2013,4,12,26,4,'2014-01-26'),
 (361,2013,4,12,27,5,'2014-01-27'),
 (362,2013,4,12,28,6,'2014-01-28'),
 (363,2013,4,12,29,7,'2014-01-29'),
 (364,2013,4,12,30,1,'2014-01-30'),
 (365,2013,4,12,31,2,'2014-01-31');
/*!40000 ALTER TABLE `temps` ENABLE KEYS */;


--
-- Table structure for table `magasin`.`ventes`
--

DROP TABLE IF EXISTS `ventes`;
CREATE TABLE `ventes` (
  `client_id` int(11) NOT NULL,
  `produit_id` int(11) NOT NULL,
  `lieu_id` int(11) NOT NULL,
  `temps_id` int(11) NOT NULL,
  `type_payement` varchar(20) NOT NULL,
  `quantite` int(11) NOT NULL,
  `prix_unitaire` int(11) NOT NULL,
  PRIMARY KEY  (`client_id`,`produit_id`,`lieu_id`,`temps_id`),
  KEY `produit_id` (`produit_id`),
  KEY `lieu_id` (`lieu_id`),
  KEY `temps_id` (`temps_id`),
  CONSTRAINT `ventes_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`),
  CONSTRAINT `ventes_ibfk_2` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`produit_id`),
  CONSTRAINT `ventes_ibfk_3` FOREIGN KEY (`lieu_id`) REFERENCES `lieu` (`lieu_id`),
  CONSTRAINT `ventes_ibfk_4` FOREIGN KEY (`temps_id`) REFERENCES `temps` (`temps_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `magasin`.`ventes`
--

/*!40000 ALTER TABLE `ventes` DISABLE KEYS */;
INSERT INTO `ventes` (`client_id`,`produit_id`,`lieu_id`,`temps_id`,`type_payement`,`quantite`,`prix_unitaire`) VALUES 
 (1,1,2,1,'Virement bancaire',5,1000),
 (1,1,8,207,'Chèque',15,1000),
 (1,1,16,37,'Virement bancaire',31,1000),
 (1,2,4,283,'Virement bancaire',38,1000),
 (1,3,3,60,'Chèque',0,1000),
 (1,3,11,24,'Chèque',23,1000),
 (1,3,12,83,'Chèque',17,1000),
 (1,3,18,48,'Chèque',15,1000),
 (1,5,6,200,'Virement bancaire',27,800),
 (1,5,14,197,'Virement bancaire',42,800),
 (1,5,16,208,'Virement bancaire',22,800),
 (1,6,1,342,'Virement bancaire',41,800),
 (1,6,9,74,'Virement bancaire',2,800),
 (1,6,11,169,'Virement bancaire',34,800),
 (1,6,17,275,'Virement bancaire',45,800),
 (1,7,1,104,'Virement bancaire',45,800),
 (1,7,9,10,'Chèque',12,800),
 (1,8,1,39,'Chèque',39,300),
 (1,9,3,145,'Chèque',40,300),
 (1,9,9,82,'Virement bancaire',9,300),
 (2,1,12,228,'Virement bancaire',46,1000),
 (2,1,18,6,'Chèque',4,1000),
 (2,2,5,323,'Chèque',4,1000),
 (2,3,6,164,'Chèque',6,1000),
 (2,3,9,324,'Chèque',27,1000),
 (2,3,11,227,'Virement bancaire',46,1000);
INSERT INTO `ventes` (`client_id`,`produit_id`,`lieu_id`,`temps_id`,`type_payement`,`quantite`,`prix_unitaire`) VALUES 
 (2,4,4,244,'Virement bancaire',33,1000),
 (2,4,13,354,'Virement bancaire',5,1000),
 (2,4,15,100,'Chèque',7,1000),
 (2,5,15,144,'Virement bancaire',1,800),
 (2,5,18,213,'Chèque',4,800),
 (2,7,17,184,'Virement bancaire',7,800),
 (2,8,13,43,'Virement bancaire',36,300),
 (2,9,2,96,'Virement bancaire',44,300),
 (2,9,5,274,'Chèque',16,300),
 (3,1,18,265,'Virement bancaire',11,1000),
 (3,2,1,58,'Chèque',7,1000),
 (3,2,6,14,'Virement bancaire',42,1000),
 (3,2,15,240,'Virement bancaire',42,1000),
 (3,2,19,256,'Virement bancaire',11,1000),
 (3,3,3,201,'Virement bancaire',22,1000),
 (3,3,13,292,'Virement bancaire',46,1000),
 (3,3,18,261,'Chèque',21,1000),
 (3,4,5,91,'Chèque',43,1000),
 (3,4,7,185,'Virement bancaire',1,1000),
 (3,4,11,222,'Virement bancaire',1,1000),
 (3,6,4,183,'Virement bancaire',34,800),
 (3,7,3,118,'Chèque',36,800),
 (3,7,12,159,'Virement bancaire',37,800),
 (3,7,14,65,'Virement bancaire',24,800);
INSERT INTO `ventes` (`client_id`,`produit_id`,`lieu_id`,`temps_id`,`type_payement`,`quantite`,`prix_unitaire`) VALUES 
 (3,7,14,72,'Chèque',27,800),
 (3,7,18,243,'Virement bancaire',36,800),
 (3,8,4,290,'Chèque',42,300),
 (3,9,12,229,'Virement bancaire',34,300),
 (3,9,15,328,'Virement bancaire',37,300),
 (3,9,19,347,'Virement bancaire',40,300),
 (4,1,1,68,'Virement bancaire',17,1000),
 (4,1,9,296,'Chèque',5,1000),
 (4,1,12,277,'Chèque',40,1000),
 (4,1,18,166,'Virement bancaire',3,1000),
 (4,2,4,102,'Chèque',24,1000),
 (4,2,4,343,'Chèque',28,1000),
 (4,2,7,284,'Chèque',13,1000),
 (4,2,18,110,'Chèque',45,1000),
 (4,3,9,120,'Chèque',27,1000),
 (4,4,18,124,'Virement bancaire',46,1000),
 (4,5,10,273,'Chèque',9,800),
 (4,6,14,364,'Virement bancaire',23,800),
 (4,7,7,331,'Virement bancaire',38,800),
 (4,7,10,234,'Chèque',41,800),
 (4,7,15,117,'Chèque',27,800),
 (4,8,16,75,'Virement bancaire',34,300),
 (4,9,5,51,'Virement bancaire',31,300),
 (4,9,12,365,'Chèque',15,300),
 (4,9,13,355,'Chèque',38,300),
 (5,1,1,363,'Virement bancaire',26,1000);
INSERT INTO `ventes` (`client_id`,`produit_id`,`lieu_id`,`temps_id`,`type_payement`,`quantite`,`prix_unitaire`) VALUES 
 (5,1,2,333,'Virement bancaire',35,1000),
 (5,1,17,271,'Virement bancaire',31,1000),
 (5,3,15,4,'Virement bancaire',9,1000),
 (5,4,7,258,'Chèque',7,1000),
 (5,6,6,304,'Virement bancaire',4,800),
 (5,7,13,249,'Chèque',38,800),
 (5,9,3,129,'Virement bancaire',26,300),
 (5,9,15,140,'Chèque',28,300),
 (5,9,16,194,'Chèque',15,300),
 (5,9,19,241,'Chèque',20,300),
 (6,1,7,99,'Virement bancaire',5,1000),
 (6,2,15,182,'Virement bancaire',45,1000),
 (6,2,19,98,'Virement bancaire',21,1000),
 (6,3,2,282,'Virement bancaire',28,1000),
 (6,3,10,9,'Virement bancaire',22,1000),
 (6,4,2,50,'Chèque',31,1000),
 (6,4,6,263,'Virement bancaire',43,1000),
 (6,5,7,352,'Virement bancaire',7,800),
 (6,5,17,224,'Chèque',40,800),
 (6,6,13,165,'Virement bancaire',10,800),
 (6,6,18,206,'Chèque',31,800),
 (6,6,19,156,'Virement bancaire',15,800),
 (6,7,2,101,'Virement bancaire',19,800),
 (6,7,3,303,'Virement bancaire',0,800),
 (6,7,19,172,'Chèque',24,800);
INSERT INTO `ventes` (`client_id`,`produit_id`,`lieu_id`,`temps_id`,`type_payement`,`quantite`,`prix_unitaire`) VALUES 
 (6,8,3,77,'Virement bancaire',17,300),
 (6,9,13,105,'Chèque',4,300),
 (7,1,2,198,'Chèque',28,1000),
 (7,1,12,321,'Virement bancaire',8,1000),
 (7,2,3,2,'Chèque',39,1000),
 (7,2,5,95,'Virement bancaire',28,1000),
 (7,2,6,41,'Virement bancaire',37,1000),
 (7,3,12,318,'Chèque',19,1000),
 (7,3,14,287,'Chèque',28,1000),
 (7,3,16,93,'Virement bancaire',1,1000),
 (7,5,4,294,'Chèque',33,800),
 (7,6,9,25,'Chèque',4,800),
 (7,6,19,49,'Virement bancaire',48,800),
 (7,6,19,322,'Chèque',45,800),
 (7,8,1,202,'Chèque',44,300),
 (7,8,7,62,'Chèque',8,300),
 (7,9,2,123,'Chèque',31,300),
 (7,9,2,338,'Chèque',7,300),
 (7,9,14,270,'Virement bancaire',30,300),
 (7,9,16,264,'Virement bancaire',30,300),
 (8,1,10,362,'Virement bancaire',33,1000),
 (8,1,13,31,'Chèque',45,1000),
 (8,4,16,276,'Chèque',4,1000),
 (8,4,16,351,'Chèque',38,1000),
 (8,5,6,112,'Virement bancaire',10,800),
 (8,5,7,54,'Chèque',27,800);
INSERT INTO `ventes` (`client_id`,`produit_id`,`lieu_id`,`temps_id`,`type_payement`,`quantite`,`prix_unitaire`) VALUES 
 (8,5,19,293,'Chèque',23,800),
 (8,6,19,357,'Chèque',21,800),
 (8,7,1,286,'Chèque',10,800),
 (8,7,5,259,'Virement bancaire',42,800),
 (8,8,19,204,'Chèque',18,300),
 (8,9,6,67,'Chèque',39,300),
 (8,9,8,46,'Chèque',32,300),
 (9,1,15,193,'Chèque',39,1000),
 (9,2,2,84,'Chèque',45,1000),
 (9,2,13,11,'Chèque',33,1000),
 (9,3,1,308,'Virement bancaire',30,1000),
 (9,3,8,27,'Virement bancaire',36,1000),
 (9,3,18,214,'Chèque',23,1000),
 (9,4,9,345,'Chèque',43,1000),
 (9,4,14,350,'Virement bancaire',7,1000),
 (9,4,17,47,'Chèque',25,1000),
 (9,5,2,233,'Chèque',5,800),
 (9,6,1,262,'Virement bancaire',24,800),
 (9,6,10,248,'Chèque',13,800),
 (9,7,4,230,'Virement bancaire',15,800),
 (9,7,5,171,'Chèque',29,800),
 (9,8,6,152,'Chèque',13,300),
 (9,9,7,150,'Chèque',9,300),
 (9,9,9,103,'Chèque',41,300),
 (9,9,17,137,'Virement bancaire',23,300),
 (9,9,18,174,'Virement bancaire',43,300),
 (10,1,7,121,'Virement bancaire',26,1000);
INSERT INTO `ventes` (`client_id`,`produit_id`,`lieu_id`,`temps_id`,`type_payement`,`quantite`,`prix_unitaire`) VALUES 
 (10,1,7,313,'Virement bancaire',31,1000),
 (10,1,12,358,'Virement bancaire',33,1000),
 (10,1,15,23,'Chèque',40,1000),
 (10,2,11,176,'Virement bancaire',6,1000),
 (10,2,19,107,'Chèque',4,1000),
 (10,4,15,272,'Chèque',31,1000),
 (10,5,2,218,'Chèque',10,800),
 (10,7,19,18,'Virement bancaire',0,800),
 (10,7,19,40,'Virement bancaire',2,800),
 (10,8,2,205,'Virement bancaire',33,300),
 (10,8,12,15,'Chèque',6,300),
 (10,9,10,167,'Chèque',11,300),
 (10,9,10,175,'Chèque',31,300),
 (11,1,3,42,'Chèque',28,1000),
 (11,1,11,251,'Chèque',14,1000),
 (11,4,1,215,'Virement bancaire',36,1000),
 (11,5,1,177,'Chèque',2,800),
 (11,6,19,280,'Virement bancaire',34,800),
 (11,7,5,266,'Chèque',43,800),
 (11,7,5,349,'Chèque',34,800),
 (11,8,5,154,'Chèque',0,300),
 (11,9,4,119,'Virement bancaire',11,300),
 (11,9,17,20,'Chèque',43,300),
 (12,1,3,134,'Virement bancaire',29,1000),
 (12,1,6,114,'Virement bancaire',6,1000),
 (12,1,8,220,'Virement bancaire',12,1000);
INSERT INTO `ventes` (`client_id`,`produit_id`,`lieu_id`,`temps_id`,`type_payement`,`quantite`,`prix_unitaire`) VALUES 
 (12,1,16,196,'Virement bancaire',6,1000),
 (12,2,1,285,'Chèque',26,1000),
 (12,2,3,16,'Virement bancaire',31,1000),
 (12,3,1,8,'Chèque',12,1000),
 (12,3,2,335,'Chèque',26,1000),
 (12,3,10,12,'Virement bancaire',28,1000),
 (12,3,14,314,'Virement bancaire',36,1000),
 (12,3,16,199,'Virement bancaire',33,1000),
 (12,5,19,221,'Virement bancaire',48,800),
 (12,7,14,356,'Virement bancaire',38,800),
 (12,8,11,70,'Virement bancaire',2,300),
 (12,9,14,66,'Chèque',40,300),
 (12,9,17,260,'Virement bancaire',36,300),
 (12,9,19,146,'Chèque',21,300);
/*!40000 ALTER TABLE `ventes` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
