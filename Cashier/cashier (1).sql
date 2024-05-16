-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 16, 2024 at 04:29 AM
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
-- Database: `cashier`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `user_name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `num_user` int(11) NOT NULL,
  `kind` varchar(50) NOT NULL,
  `per` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`user_name`, `password`, `num_user`, `kind`, `per`) VALUES
('abdullah', '010', 1, 'user', 'AM');

-- --------------------------------------------------------

--
-- Table structure for table `pone`
--

CREATE TABLE `pone` (
  `num_pone` int(11) NOT NULL,
  `total` varchar(50) DEFAULT NULL,
  `cash` varchar(50) DEFAULT NULL,
  `mins` varchar(50) DEFAULT NULL,
  `number` varchar(50) DEFAULT NULL,
  `history` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pone`
--

INSERT INTO `pone` (`num_pone`, `total`, `cash`, `mins`, `number`, `history`) VALUES
(1, '20.5', '200', '179.5', '2', '2024-05-16'),
(2, '350.5', '', '', '3', '2024-05-16'),
(3, '6.5', '', '', '1', '2024-05-16'),
(4, '190.5', '200', '9.5', '4', '2024-05-16');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `parcode` int(11) NOT NULL,
  `products` varchar(50) NOT NULL,
  `price` varchar(11) NOT NULL,
  `quntity` varchar(11) NOT NULL,
  `code` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`parcode`, `products`, `price`, `quntity`, `code`) VALUES
(112233, 'خلطة ماجى ', '14', '14', 2),
(223344, 'سفن اب 1.5 لتر ', '30', '78', 3),
(1100911922, 'براميلى مزبدة ', '140', '14', 0),
(1114531437, 'بيض بلدى ', '6.5', '99', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pone`
--
ALTER TABLE `pone`
  ADD PRIMARY KEY (`num_pone`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`parcode`),
  ADD UNIQUE KEY `unique` (`code`),
  ADD KEY `code` (`code`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
