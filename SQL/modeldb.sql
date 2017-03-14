-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema agriotes2017
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `agriotes2017` ;

-- -----------------------------------------------------
-- Schema agriotes2017
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `agriotes2017` DEFAULT CHARACTER SET utf8 ;
USE `agriotes2017` ;

-- -----------------------------------------------------
-- Table `personne`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `personne` (
  `id_personne` INT(11) NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NOT NULL,
  `prenom` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `no_rue` VARCHAR(9) NOT NULL,
  `rue` VARCHAR(45) NOT NULL,
  `code_postal` VARCHAR(5) NOT NULL,
  `ville` VARCHAR(45) NOT NULL,
  `pays` VARCHAR(45) NOT NULL,
  `mot_de_passe` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_personne`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `formation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `formation` (
  `id_formation` INT(11) NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NOT NULL,
  `description` TEXT NOT NULL,
  PRIMARY KEY (`id_formation`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `session_formation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `session_formation` (
  `id_session` INT(11) NOT NULL AUTO_INCREMENT,
  `id_formation` INT(11) NOT NULL,
  `date_debut` DATE NOT NULL,
  `date_fin` DATE NOT NULL,
  `lieu` VARCHAR(45) NOT NULL,
  `est_ouverte` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id_session`),
  INDEX `fk_Session_Formation_idx` (`id_formation` ASC),
  CONSTRAINT `fk_Session_Formation`
    FOREIGN KEY (`id_formation`)
    REFERENCES `formation` (`id_formation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `etat_candidature`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `etat_candidature` (
  `id_etat_candidature` INT(11) NOT NULL AUTO_INCREMENT,
  `libelle` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_etat_candidature`),
  UNIQUE INDEX `etat_candidaturecol_UNIQUE` (`libelle` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `candidature`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `candidature` (
  `id_personne` INT(11) NOT NULL,
  `id_session` INT(11) NOT NULL,
  `id_etat_candidature` INT(11) NOT NULL,
  PRIMARY KEY (`id_personne`, `id_session`),
  INDEX `fk_Personne_has_Session_Session1_idx` (`id_session` ASC),
  INDEX `fk_Personne_has_Session_Personne1_idx` (`id_personne` ASC),
  INDEX `fk_candidature_etat_candidature1_idx` (`id_etat_candidature` ASC),
  CONSTRAINT `fk_Personne_has_Session_Personne1`
    FOREIGN KEY (`id_personne`)
    REFERENCES `personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Personne_has_Session_Session1`
    FOREIGN KEY (`id_session`)
    REFERENCES `session_formation` (`id_session`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_candidature_etat_candidature1`
    FOREIGN KEY (`id_etat_candidature`)
    REFERENCES `etat_candidature` (`id_etat_candidature`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `agriotes2017` ;

-- -----------------------------------------------------
-- procedure agriotes2017_reset
-- -----------------------------------------------------

DELIMITER $$
USE `agriotes2017`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `agriotes2017_reset`()
BEGIN
  -- Désactiver contraintes de clé étrangère
  SET FOREIGN_KEY_CHECKS = 0;
  -- Vider les tables en repositionnant à 1 leur auto-incrément
  TRUNCATE TABLE candidature;
  TRUNCATE TABLE etat_candidature;
  TRUNCATE TABLE formation;
  TRUNCATE TABLE personne;
  TRUNCATE TABLE session_formation;
  -- Réasactiver contraintes de clé étrangère
  SET FOREIGN_KEY_CHECKS = 1;

  BEGIN
    -- Recuperation en cas d'exception
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
      -- Annuler la transaction
      ROLLBACK;
      -- Afficher la cause de l'échec
      SHOW ERRORS;
    END;  
    START TRANSACTION;
    -- Insérer les données dans l'ordre des contraintes
    -- d'intégrité référentielle
    INSERT INTO personne VALUES
    (1, 'Leto','Elias','leto@yahoo.com',12,'rue du chat',75001,'Paris','France','Jesuisunchat123!'),
    (2, 'Yuuki', 'Gertrude', 'gyuuki@wanadoo.fr', 16, 'avenue des papillons', 69000, 'Lyon', 'France', 'Jaimelaneige345?'),
    (3, 'Scout', 'Luke', 'vader@lycos.fr', 29, 'boulevard du tank', 89000, 'Auxerre', 'France', 'Jesuistonpère99/'),
    (4, 'Bond', 'Julie', 'pasjamesbond@free.fr', 5, 'chemin de la voiture', 89100, 'Sens', 'France', 'Jesuis1espion!'),
    (5, 'Loganlapointe', 'Bob', 'bob@viril.com', 26,'rue des chats', 75029, 'Meow', 'Irlande', 'Jaimeleschats777-'),
    (6, 'Titteh', 'Serena', 'des@tits.com', 8, 'chemin des voisins', 59000, 'Lille', 'France', 'adri1cestpourTOI?'),
    (7, 'Luc', 'Lana', 'blblblbl@essef.com', 18,'avenue du derrière', 14360, 'Trouville', 'Thailande', 'Jaimelestomates888!'),
    (8, 'Dicoco', 'Margeriti', 'bgpenne@caramail.fr', 69, 'boulevard du chemin', 40100, 'Bologne', 'Italie', '+grandde14cm');

    INSERT INTO etat_candidature (id_etat_candidature, libelle) VALUES
    (1, 'reçu'),
    (2, 'convoqué'),
    (3, 'accepté'),
    (4, 'refusé'),
    (5, 'liste d''attente'),
    (6, 'inscrit');

    INSERT INTO formation (id_formation, nom, description) VALUES 
    (1, 'BTS SIO', 'le bts de l''amour'),
    (2, 'BTS IRIS', 'la formation qu''il te faut !');

    INSERT INTO session_formation (id_session, id_formation, date_debut, date_fin, lieu, est_ouverte) VALUES 
    (1, 1, '2017-05-01', '2018-04-15', 'MASSY', 1),
    (2, 1, '2016-06-01', '2017-05-08', 'MASSY', 0),
    (3, 2, '2017-04-03', '2018-04-02', 'MASSY', 1);
    
    INSERT INTO candidature (id_personne, id_session, id_etat_candidature) VALUES 
    (1, 1, 1), 
    (2, 1, 2), 
    (3, 1, 3),
    (4, 1, 4),
    (5, 1, 5),
    (6, 1, 6),
    (1, 2, 4);
    COMMIT;
  END;
END$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
