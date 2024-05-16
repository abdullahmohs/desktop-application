-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 16, 2024 at 04:30 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `am`
--

-- --------------------------------------------------------

--
-- Table structure for table `pone_1`
--

CREATE TABLE `pone_1` (
  `name_products` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `quntity` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `total` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pone_1`
--

INSERT INTO `pone_1` (`name_products`, `price`, `quntity`, `code`, `total`) VALUES
('بيض بلدى ', '6.5', '1', '1', '6.5'),
('خلطة ماجى ', '14', '1', '2', '14');

-- --------------------------------------------------------

--
-- Table structure for table `pone_2`
--

CREATE TABLE `pone_2` (
  `name_products` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `quntity` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `total` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pone_2`
--

INSERT INTO `pone_2` (`name_products`, `price`, `quntity`, `code`, `total`) VALUES
('بيض بلدى ', '6.5', '1', '1', '6.5'),
('خلطة ماجى ', '14', '1', '2', '14'),
('بيض بلدى ', '6.5', '1', '1', '6.5'),
('سفن اب 1.5 لتر ', '30', '11', '3', '330.0'),
('خلطة ماجى ', '14', '1', '2', '14');

-- --------------------------------------------------------

--
-- Table structure for table `pone_3`
--

CREATE TABLE `pone_3` (
  `name_products` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `quntity` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `total` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pone_3`
--

INSERT INTO `pone_3` (`name_products`, `price`, `quntity`, `code`, `total`) VALUES
('بيض بلدى ', '6.5', '1', '1', '6.5');

-- --------------------------------------------------------

--
-- Table structure for table `pone_4`
--

CREATE TABLE `pone_4` (
  `name_products` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `quntity` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `total` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pone_4`
--

INSERT INTO `pone_4` (`name_products`, `price`, `quntity`, `code`, `total`) VALUES
('بيض بلدى ', '6.5', '1', '1', '6.5'),
('بيض بلدى ', '6.5', '1', '1', '6.5'),
('سفن اب 1.5 لتر ', '30', '1', '3', '30'),
('براميلى مزبدة ', '140', '1', '0', '140'),
('براميلى مزبدة ', '140', '2', '0', '280.0'),
('بيض بلدى ', '6.5', '2', '1', '13.0'),
('خلطة ماجى ', '14', '1', '2', '14'),
('بيض بلدى ', '6.5', '1', '1', '6.5'),
('خلطة ماجى ', '14', '2', '2', '28.0'),
('سفن اب 1.5 لتر ', '30', '2', '3', '60.0'),
('بيض بلدى ', '6.5', '1', '1', '6.5'),
('خلطة ماجى ', '14', '1', '2', '14'),
('سفن اب 1.5 لتر ', '30', '1', '3', '30'),
('براميلى مزبدة ', '140', '1', '0', '140');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
