-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 22-08-2021 a las 19:46:36
-- Versión del servidor: 5.7.26
-- Versión de PHP: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db-gestionpedidos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_pedido`
--

DROP TABLE IF EXISTS `detalle_pedido`;
CREATE TABLE IF NOT EXISTS `detalle_pedido` (
  `ID_DETALLE_PEDIDO` int(11) NOT NULL AUTO_INCREMENT,
  `CANT_SOLICITADA` int(11) NOT NULL,
  `FECHA_PEDIDO` date NOT NULL,
  `ID_PRODUCTO` int(11) NOT NULL,
  `ID_PROVEEDOR` int(11) NOT NULL,
  PRIMARY KEY (`ID_DETALLE_PEDIDO`),
  KEY `ID_PRODUCTO` (`ID_PRODUCTO`),
  KEY `ID_PROVEEDOR` (`ID_PROVEEDOR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario`
--

DROP TABLE IF EXISTS `inventario`;
CREATE TABLE IF NOT EXISTS `inventario` (
  `ID_INVENTARIO` int(11) NOT NULL AUTO_INCREMENT,
  `STOCK` int(11) NOT NULL,
  `HISTORIAL_STOCK` int(11) NOT NULL,
  `ID_PRODUCTO` int(11) NOT NULL,
  PRIMARY KEY (`ID_INVENTARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

DROP TABLE IF EXISTS `pedido`;
CREATE TABLE IF NOT EXISTS `pedido` (
  `ID_PEDIDO` int(11) NOT NULL AUTO_INCREMENT,
  `NUM_PEDIDO` int(11) DEFAULT NULL,
  `CANT_SOLICITADA` int(11) NOT NULL,
  `ID_PRODUCTO` int(11) NOT NULL,
  `ID_PROVEEDOR` int(11) NOT NULL,
  PRIMARY KEY (`ID_PEDIDO`),
  KEY `ID_PRODUCTO` (`ID_PRODUCTO`),
  KEY `ID_PROVEEDOR` (`ID_PROVEEDOR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

DROP TABLE IF EXISTS `productos`;
CREATE TABLE IF NOT EXISTS `productos` (
  `ID_PRODUCTO` int(11) NOT NULL AUTO_INCREMENT,
  `DESC_PRODUCTO` varchar(150) NOT NULL,
  `STOCK` int(11) DEFAULT NULL,
  `RANKING` int(11) DEFAULT '0',
  `ID_PROVEEDOR` int(11) NOT NULL,
  PRIMARY KEY (`ID_PRODUCTO`),
  UNIQUE KEY `DESC_PRODUCTO` (`DESC_PRODUCTO`),
  KEY `ID_PROVEEDOR` (`ID_PROVEEDOR`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`ID_PRODUCTO`, `DESC_PRODUCTO`, `STOCK`, `RANKING`, `ID_PROVEEDOR`) VALUES
(55, 'Leche descremada 1L', 35, 0, 1),
(56, 'Coca cola 3Lts', 20, 0, 17),
(57, 'Galletitas surtidas 500gr', 35, 0, 18),
(58, 'Mayonesa 350gr', 15, 0, 19),
(59, 'Sonrisas 150gr', 4, 0, 24),
(60, 'Fanta 1L', 8, 0, 17),
(61, 'Manteca 250gr', 7, 0, 1),
(62, 'Casancrem light 250gr', 20, 0, 1),
(63, 'Ketchup 350g', 20, 0, 19),
(64, 'Sprite 1,5L', 3, 0, 17),
(65, 'Yerba 1kg', 25, 0, 25);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE IF NOT EXISTS `proveedor` (
  `ID_PROVEEDOR` int(11) NOT NULL AUTO_INCREMENT,
  `NOM_PROVEEDOR` varchar(40) NOT NULL,
  `DIR_PROVEEDOR` varchar(30) DEFAULT NULL,
  `TEL_PROVEEDOR` varchar(30) DEFAULT NULL,
  `EMAIL_PROVEEDOR` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`ID_PROVEEDOR`),
  UNIQUE KEY `NOM_PROVEEDOR` (`NOM_PROVEEDOR`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`ID_PROVEEDOR`, `NOM_PROVEEDOR`, `DIR_PROVEEDOR`, `TEL_PROVEEDOR`, `EMAIL_PROVEEDOR`) VALUES
(1, 'La serenisima SRL', 'Av 3 numero 570', '02255473950', 'laserenisima@hotmail.com'),
(17, 'Coca cola', 'Boulevard y paseo 122', '2255473344', 'cocacolaarg@hotmail.com'),
(18, 'Bagley', 'Paseo 121 entre 2 y 3', '2255473456', 'bagley@hotmail.com'),
(19, 'Hellmans', 'Paseo 115 n 1280', '0224535643', 'hellmans@hotmail.com'),
(24, 'Arcor SA', 'paseo 109 n 1020', '02255478743', 'arcor@hotmail.com'),
(25, 'Amanda', 'paseo 108 n 4455', '2255664433', 'amanda@hotmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_usuario`
--

DROP TABLE IF EXISTS `tipo_usuario`;
CREATE TABLE IF NOT EXISTS `tipo_usuario` (
  `ID_TIPO` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(40) NOT NULL,
  PRIMARY KEY (`ID_TIPO`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipo_usuario`
--

INSERT INTO `tipo_usuario` (`ID_TIPO`, `NOMBRE`) VALUES
(1, 'admin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_`
--

DROP TABLE IF EXISTS `usuario_`;
CREATE TABLE IF NOT EXISTS `usuario_` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USUARIO` varchar(40) NOT NULL,
  `PASSWORD` varchar(40) NOT NULL,
  `NOMBRE` varchar(40) NOT NULL,
  `ID_TIPO` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_TIPO` (`ID_TIPO`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario_`
--

INSERT INTO `usuario_` (`ID`, `USUARIO`, `PASSWORD`, `NOMBRE`, `ID_TIPO`) VALUES
(2, 'admin', '7b902e6ff1db9f560443f2048974fd7d386975b0', 'admin', 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle_pedido`
--
ALTER TABLE `detalle_pedido`
  ADD CONSTRAINT `detalle_pedido_ibfk_1` FOREIGN KEY (`ID_PRODUCTO`) REFERENCES `productos` (`ID_PRODUCTO`),
  ADD CONSTRAINT `detalle_pedido_ibfk_2` FOREIGN KEY (`ID_PROVEEDOR`) REFERENCES `proveedor` (`ID_PROVEEDOR`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`ID_PRODUCTO`) REFERENCES `productos` (`ID_PRODUCTO`),
  ADD CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`ID_PROVEEDOR`) REFERENCES `proveedor` (`ID_PROVEEDOR`);

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`ID_PROVEEDOR`) REFERENCES `proveedor` (`ID_PROVEEDOR`);

--
-- Filtros para la tabla `usuario_`
--
ALTER TABLE `usuario_`
  ADD CONSTRAINT `usuario__ibfk_1` FOREIGN KEY (`ID_TIPO`) REFERENCES `tipo_usuario` (`ID_TIPO`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
