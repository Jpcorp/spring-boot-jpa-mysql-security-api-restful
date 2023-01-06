-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 05-01-2023 a las 01:35:22
-- Versión del servidor: 5.7.31
-- Versión de PHP: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `stram`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `token` varchar(500) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kfsp0s1tflm1cwlj8idhqsad0` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `created`, `email`, `is_active`, `last_login`, `modified`, `name`, `password`, `token`, `username`, `phone`) VALUES
(13, '2023-01-03 03:54:18', 'juan12@rodriguez.cl', b'1', '2023-01-03 03:54:18', '2023-01-03 03:54:18', 'Juan Rodriguez', 'Hunter23', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuMTJAcm9kcmlndWV6LmNsIiwiZXhwIjoxNjcyNzM2MDQyLCJpYXQiOjE2NzI3MTgwNDJ9.PJOq-3lGOpO53Eao8uv-wifeLcQv7gIzAMsUnMimzV2DB8onj-pm6ee1jPWtn6BPNrSRWLNS5kp7VoyW99CVGg', 'juan12@rodriguez.cl', '+57983790533'),
(19, '2021-10-08 21:00:23', 'ju.pablo@juan.org', b'1', '2021-11-08 22:00:00', '2021-11-08 22:00:00', 'Juan Pablo', 'hunter2', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios_roles`
--

DROP TABLE IF EXISTS `usuarios_roles`;
CREATE TABLE IF NOT EXISTS `usuarios_roles` (
  `usuarios_id` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL,
  KEY `FK6j2duw4ute390mj7kos3sffss` (`usuarios_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `usuarios_roles`
--
ALTER TABLE `usuarios_roles`
  ADD CONSTRAINT `FK6j2duw4ute390mj7kos3sffss` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
