-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ctvista_dhrd_dev
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ctvista_dhrd_dev
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ctvista_dhrd_dev` DEFAULT CHARACTER SET utf8 ;
USE `ctvista_dhrd_dev` ;

-- -----------------------------------------------------
-- Table `ctvista_dhrd_dev`.`asset_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ctvista_dhrd_dev`.`asset_type` (
  `id` CHAR(10) NOT NULL,
  `name` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ctvista_dhrd_dev`.`tenant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ctvista_dhrd_dev`.`tenant` (
  `id` CHAR(20) NOT NULL,
  `account_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `account_id_UNIQUE` (`account_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ctvista_dhrd_dev`.`asset`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ctvista_dhrd_dev`.`asset` (
  `id` CHAR(10) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `asset_type_id` CHAR(20) NOT NULL,
  `tenant_id` CHAR(20) NOT NULL,
  `status` TINYINT NULL,
  `calculation_master_id` CHAR(20) NOT NULL,
  `parent_asset_id` CHAR(20) NOT NULL,
  INDEX `fk_asset_asset_type_idx` (`asset_type_id` ASC) VISIBLE,
  INDEX `fk_asset_tenant1_idx` (`tenant_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  INDEX `fk_asset_asset1_idx` (`parent_asset_id` ASC) VISIBLE,
  CONSTRAINT `fk_asset_asset_type`
    FOREIGN KEY (`asset_type_id`)
    REFERENCES `ctvista_dhrd_dev`.`asset_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_asset_tenant1`
    FOREIGN KEY (`tenant_id`)
    REFERENCES `ctvista_dhrd_dev`.`tenant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_asset_asset1`
    FOREIGN KEY (`parent_asset_id`)
    REFERENCES `ctvista_dhrd_dev`.`asset` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ctvista_dhrd_dev`.`asset_params`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ctvista_dhrd_dev`.`asset_params` (
  `id` CHAR(20) NOT NULL,
  `name` VARCHAR(200) NOT NULL,
  `asset_id` CHAR(20) NOT NULL,
  `data_type` VARCHAR(100) NULL,
  `status` TINYINT NULL,
  `unit_of_measure_id` CHAR(20) NOT NULL,
  `value` DOUBLE NULL,
  `customer_tag_name` VARCHAR(100) NULL,
  `descriptor` VARCHAR(400) NULL,
  `type` VARCHAR(45) NULL,
  `calculation_master_id` CHAR(20) NOT NULL,
  PRIMARY KEY (`name`),
  INDEX `fk_asset_params_asset1_idx` (`asset_id` ASC) VISIBLE,
  CONSTRAINT `fk_asset_params_asset1`
    FOREIGN KEY (`asset_id`)
    REFERENCES `ctvista_dhrd_dev`.`asset` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ctvista_dhrd_dev`.`asset_type_params`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ctvista_dhrd_dev`.`asset_type_params` (
  `id` CHAR(10) NOT NULL,
  `name` VARCHAR(200) NULL,
  `asset_type_id` CHAR(20) NOT NULL,
  `data_type` VARCHAR(100) NULL,
  `status` TINYINT NULL,
  `unit_of_measure_id` CHAR(20) NOT NULL,
  `value` DOUBLE NULL,
  `calculation_master_id` CHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `fk_asset_type_params_asset_type1_idx` (`asset_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_asset_type_params_asset_type1`
    FOREIGN KEY (`asset_type_id`)
    REFERENCES `ctvista_dhrd_dev`.`asset_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
