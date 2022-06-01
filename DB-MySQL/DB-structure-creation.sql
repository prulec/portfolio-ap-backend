-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema portfolio_test
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema portfolio_test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `portfolio_test` ;
USE `portfolio_test` ;

-- -----------------------------------------------------
-- Table `portfolio_test`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio_test`.`User` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(200) NOT NULL,
  `first_names` VARCHAR(80) NOT NULL,
  `last_names` VARCHAR(80) NOT NULL,
  `mail` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio_test`.`Portfolio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio_test`.`Portfolio` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `visible` TINYINT NOT NULL,
  `banner_url` VARCHAR(255) NULL,
  `photo_url` VARCHAR(255) NULL,
  `job_title` VARCHAR(150) NULL,
  `p_statement` VARCHAR(800) NULL,
  `User_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Portfolio_User_idx` (`User_id` ASC) VISIBLE,
  CONSTRAINT `fk_Portfolio_User`
    FOREIGN KEY (`User_id`)
    REFERENCES `portfolio_test`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio_test`.`SocialType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio_test`.`SocialType` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `icon_url` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio_test`.`Social`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio_test`.`Social` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order` INT UNSIGNED NOT NULL,
  `url` VARCHAR(255) NULL,
  `SocialType_id` INT NOT NULL,
  `Portfolio_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Social_SocialType1_idx` (`SocialType_id` ASC) VISIBLE,
  INDEX `fk_Social_Portfolio1_idx` (`Portfolio_id` ASC) VISIBLE,
  CONSTRAINT `fk_Social_SocialType1`
    FOREIGN KEY (`SocialType_id`)
    REFERENCES `portfolio_test`.`SocialType` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Social_Portfolio1`
    FOREIGN KEY (`Portfolio_id`)
    REFERENCES `portfolio_test`.`Portfolio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio_test`.`Experience`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio_test`.`Experience` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order` INT UNSIGNED NOT NULL,
  `logo_url` VARCHAR(255) NULL,
  `enterprise` VARCHAR(120) NULL,
  `time` VARCHAR(100) NULL,
  `position` VARCHAR(100) NULL,
  `tasks` VARCHAR(255) NULL,
  `Portfolio_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Experience_Portfolio1_idx` (`Portfolio_id` ASC) VISIBLE,
  CONSTRAINT `fk_Experience_Portfolio1`
    FOREIGN KEY (`Portfolio_id`)
    REFERENCES `portfolio_test`.`Portfolio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio_test`.`Education`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio_test`.`Education` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order` INT UNSIGNED NOT NULL,
  `logo_url` VARCHAR(255) NULL,
  `institution` VARCHAR(120) NULL,
  `time` VARCHAR(100) NULL,
  `title` VARCHAR(150) NULL,
  `Portfolio_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Education_Portfolio1_idx` (`Portfolio_id` ASC) VISIBLE,
  CONSTRAINT `fk_Education_Portfolio1`
    FOREIGN KEY (`Portfolio_id`)
    REFERENCES `portfolio_test`.`Portfolio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio_test`.`Skill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio_test`.`Skill` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order` INT UNSIGNED NOT NULL,
  `name` VARCHAR(30) NOT NULL,
  `level` INT UNSIGNED NULL,
  `level_tag` VARCHAR(100) NULL,
  `Portfolio_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Skill_Portfolio1_idx` (`Portfolio_id` ASC) VISIBLE,
  CONSTRAINT `fk_Skill_Portfolio1`
    FOREIGN KEY (`Portfolio_id`)
    REFERENCES `portfolio_test`.`Portfolio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio_test`.`Project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio_test`.`Project` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order` INT UNSIGNED NOT NULL,
  `name` VARCHAR(150) NULL,
  `time` VARCHAR(100) NULL,
  `link` VARCHAR(255) NULL,
  `description` VARCHAR(500) NULL,
  `Portfolio_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Project_Portfolio1_idx` (`Portfolio_id` ASC) VISIBLE,
  CONSTRAINT `fk_Project_Portfolio1`
    FOREIGN KEY (`Portfolio_id`)
    REFERENCES `portfolio_test`.`Portfolio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio_test`.`ProjectImage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio_test`.`ProjectImage` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order` INT UNSIGNED NOT NULL,
  `title` VARCHAR(45) NULL,
  `image_url` VARCHAR(255) NULL,
  `Project_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ProjectImage_Project1_idx` (`Project_id` ASC) VISIBLE,
  CONSTRAINT `fk_ProjectImage_Project1`
    FOREIGN KEY (`Project_id`)
    REFERENCES `portfolio_test`.`Project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
