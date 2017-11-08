
-- -----------------------------------------------------
-- Schema superHeroSightings
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `superHeroSightings_test` ;

-- -----------------------------------------------------
-- Schema superHeroSightings
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `superHeroSightings_test`;
USE `superHeroSightings_test` ;

-- -----------------------------------------------------
-- Table `superHeroSightings`.`organization`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `organization` (
  `organizationId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(255) NULL,
  `address` VARCHAR(255) NOT NULL,
  `city` VARCHAR(45) NULL,
  `zip` INT(8) NULL,
  `phone` BIGINT(12) NULL,
  `email` VARCHAR(50) NULL,
  PRIMARY KEY (`organizationId`));


-- -----------------------------------------------------
-- Table `superHeroSightings`.`superPowers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `superPowers` (
  `superPowerId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`superPowerId`));


-- -----------------------------------------------------
-- Table `superHeroSightings`.`super`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `super` (
  `superId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(255) NULL,
  `superPowerId` INT NOT NULL,
  PRIMARY KEY (`superId`),
    FOREIGN KEY (`superPowerId`)
    REFERENCES `superPowers` (`superPowerId`));


-- -----------------------------------------------------
-- Table `superHeroSightings`.`location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `location` (
  `locationId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL,
  `description` VARCHAR(255) NULL,
  `address` VARCHAR(255) NULL,
  `latitude` DOUBLE NULL,
  `longitude` DOUBLE NULL,
  PRIMARY KEY (`locationId`));


-- -----------------------------------------------------
-- Table `superHeroSightings`.`sighting`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sighting` (
  `sightingId` INT NOT NULL AUTO_INCREMENT,
  `locationId` INT NOT NULL,
  `sightingDate` DATE NOT NULL,
  PRIMARY KEY (`sightingId`),
  INDEX `locationId_idx` (`locationId` ASC),
    FOREIGN KEY (`locationId`)
    REFERENCES `location` (`locationId`));


-- -----------------------------------------------------
-- Table `superHeroSightings`.`superSighting`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `superSighting` (
  `superId` INT NOT NULL,
  `sightingId` INT NOT NULL,
    FOREIGN KEY (`sightingId`)
    REFERENCES `sighting` (`sightingId`),
    FOREIGN KEY (`superId`)
    REFERENCES `super` (`superId`));


-- -----------------------------------------------------
-- Table `superHeroSightings`.`superOrganizations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `superOrganizations` (
  `organizationId` INT NOT NULL,
  `superId` INT NOT NULL,
    FOREIGN KEY (`organizationId`)
    REFERENCES `organization` (`organizationId`),
    FOREIGN KEY (`superId`)
    REFERENCES `super` (`superId`));
