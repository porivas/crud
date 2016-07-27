-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema tos
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tos
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tos` DEFAULT CHARACTER SET utf8 ;
USE `tos` ;

-- -----------------------------------------------------
-- Table `tos`.`companies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tos`.`companies` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(120) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tos`.`aircrafts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tos`.`aircrafts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `model` VARCHAR(120) NOT NULL,
  `count_1_class` INT NULL,
  `count_2_class` INT NULL,
  `company_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_aircraft_company_idx` (`company_id` ASC),
  CONSTRAINT `fk_aircraft_company`
    FOREIGN KEY (`company_id`)
    REFERENCES `tos`.`companies` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tos`.`countries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tos`.`countries` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tos`.`cities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tos`.`cities` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `country_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cities_country_idx` (`country_id` ASC),
  CONSTRAINT `fk_cities_country`
    FOREIGN KEY (`country_id`)
    REFERENCES `tos`.`countries` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tos`.`flights`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tos`.`flights` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date_departure` DATETIME NOT NULL,
  `date_arrival` DATETIME NOT NULL,
  `price_1_class` INT NULL,
  `price_2_class` INT NULL,
  `aircraft_id` INT NOT NULL,
  `id_departure_city` INT NOT NULL,
  `id_arrival_city` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_flight_aircraft_idx` (`aircraft_id` ASC),
  INDEX `fk_flight_departure_city_idx` (`id_departure_city` ASC),
  INDEX `fk_flight_arrival_citiy_idx` (`id_arrival_city` ASC),
  CONSTRAINT `fk_flight_aircraft`
    FOREIGN KEY (`aircraft_id`)
    REFERENCES `tos`.`aircrafts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_flight_departure_city`
    FOREIGN KEY (`id_departure_city`)
    REFERENCES `tos`.`cities` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_flight_arrival_citiy`
    FOREIGN KEY (`id_arrival_city`)
    REFERENCES `tos`.`cities` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tos`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tos`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `board_class` INT NOT NULL,
  `count` INT NOT NULL,
  `credit_card` VARCHAR(20) CHARACTER SET 'utf8mb4' NOT NULL,
  `flight_id` INT NOT NULL,
  `order_id` VARCHAR(36) NOT NULL,
  `order_date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_orders_flight_idx` (`flight_id` ASC),
  UNIQUE INDEX `code_UNIQUE` (`order_id` ASC),
  CONSTRAINT `fk_orders_flight`
    FOREIGN KEY (`flight_id`)
    REFERENCES `tos`.`flights` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
