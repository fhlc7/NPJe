-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Máquina: localhost
-- Data de Criação: 12-Set-2018 às 03:11
-- Versão do servidor: 5.6.12-log
-- versão do PHP: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de Dados: `banco_de_renato`
--
CREATE DATABASE IF NOT EXISTS `banco_de_renato` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `banco_de_renato`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `relatorio_enviado`
--

CREATE TABLE IF NOT EXISTS `relatorio_enviado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_arquivo` longtext NOT NULL,
  `arquivo` longblob NOT NULL,
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usuario_relatorio_enviado_idx` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `relatorio_original`
--

CREATE TABLE IF NOT EXISTS `relatorio_original` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_relatorio` varchar(750) NOT NULL,
  `arquivo` longblob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` longtext NOT NULL,
  `senha` longtext NOT NULL,
  `tipo` longtext NOT NULL,
  `matricula` longtext NOT NULL,
  `nome_completo` longtext NOT NULL,
  `email` longtext,
  `fone` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `usuario`, `senha`, `tipo`, `matricula`, `nome_completo`, `email`, `fone`) VALUES
(1, 'admin', '123', 'Coordenador', '1234567890', 'Maria de Assís', 'maria@gmail.com', '(99) 98877-6655');

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `relatorio_enviado`
--
ALTER TABLE `relatorio_enviado`
  ADD CONSTRAINT `fk_usuario_relatorio_enviado` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
