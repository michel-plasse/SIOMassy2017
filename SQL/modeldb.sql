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

CREATE VIEW membre AS
SELECT
	p.id_personne , nom , prenom , email , no_rue , rue , code_postal , ville , pays , mot_de_passe , no_tel , photo , token , est_valide, COUNT(f.id_personne) AS est_formateur
FROM
    personne p
        LEFT OUTER JOIN
    formateur f ON p.id_personne = f.id_personne
WHERE
    est_valide = TRUE
GROUP BY p.id_personne , nom , prenom , email , no_rue , rue , code_postal , ville , pays , mot_de_passe , no_tel , photo , token , est_valide;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
