-- MySQL Workbench Synchronization
-- Generated: 2018-09-12 00:08
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: FHLC

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `banco_de_renato` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `banco_de_renato`.`relatorio_original` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_relatorio` VARCHAR(750) NOT NULL,
  `arquivo` LONGBLOB NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `banco_de_renato`.`relatorio_enviado` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_arquivo` LONGTEXT NOT NULL,
  `arquivo` LONGBLOB NOT NULL,
  `id_usuario` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_usuario_relatorio_enviado_idx` (`id_usuario` ASC),
  CONSTRAINT `fk_usuario_relatorio_enviado`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `banco_de_renato`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `banco_de_renato`.`usuario` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `usuario` LONGTEXT NOT NULL,
  `senha` LONGTEXT NOT NULL,
  `tipo` LONGTEXT NOT NULL,
  `matricula` LONGTEXT NOT NULL,
  `nome_completo` LONGTEXT NOT NULL,
  `email` LONGTEXT NULL DEFAULT NULL,
  `fone` LONGTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
