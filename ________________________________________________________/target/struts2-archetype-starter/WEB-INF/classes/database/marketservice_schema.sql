-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema marketservice
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `marketservice` ;

-- -----------------------------------------------------
-- Schema marketservice
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `marketservice` DEFAULT CHARACTER SET utf8 ;
USE `marketservice` ;

-- -----------------------------------------------------
-- Table `marketservice`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketservice`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marketservice`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketservice`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(100) NOT NULL,
  `password` VARCHAR(250) NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_user_role_idx` (`role_id` ASC),
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `marketservice`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marketservice`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketservice`.`customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(100) NOT NULL,
  `lastname` VARCHAR(100) NOT NULL,
  `middlename` VARCHAR(100) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_customer_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_customer_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `marketservice`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marketservice`.`supplier`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketservice`.`supplier` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `company_name` VARCHAR(200) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_supplier_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_supplier_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `marketservice`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marketservice`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketservice`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marketservice`.`market`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketservice`.`market` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `address` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marketservice`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketservice`.`item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NOT NULL,
  `price` INT NOT NULL,
  `category_id` INT NOT NULL,
  `market_id` INT NOT NULL,
  `supplier_id` INT NOT NULL,
  `count_in_market` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_detail_car1_idx` (`category_id` ASC),
  INDEX `fk_detail_warhouse1_idx` (`market_id` ASC),
  INDEX `fk_detail_supplier1_idx` (`supplier_id` ASC),
  CONSTRAINT `fk_detail_car1`
    FOREIGN KEY (`category_id`)
    REFERENCES `marketservice`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_detail_warhouse1`
    FOREIGN KEY (`market_id`)
    REFERENCES `marketservice`.`market` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_detail_supplier1`
    FOREIGN KEY (`supplier_id`)
    REFERENCES `marketservice`.`supplier` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marketservice`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketservice`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_order_customer1_idx` (`customer_id` ASC),
  CONSTRAINT `fk_order_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `marketservice`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marketservice`.`order_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketservice`.`order_item` (
  `order_id` INT NOT NULL,
  `item_id` INT NOT NULL,
  `count` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  INDEX `fk_order_item_order1_idx` (`order_id` ASC),
  INDEX `fk_order_item_detail1_idx` (`item_id` ASC),
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  CONSTRAINT `fk_order_item_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `marketservice`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_item_detail1`
    FOREIGN KEY (`item_id`)
    REFERENCES `marketservice`.`item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
