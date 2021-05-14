-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 14, 2021 at 06:29 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `payment1`
--

-- --------------------------------------------------------

--
-- Table structure for table `payment1`
--

CREATE TABLE `payment1` (
  `paymentID` varchar(10) NOT NULL,
  `amount` varchar(10) NOT NULL,
  `date` varchar(10) NOT NULL,
  `paymentType` varchar(10) NOT NULL,
  `accountNumber` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment1`
--

INSERT INTO `payment1` (`paymentID`, `amount`, `date`, `paymentType`, `accountNumber`) VALUES
('65', '1000', '05/05/2021', 'cash', '201325'),
('452', '2000', '02/05/2021', 'card', '32165'),
('152', '1500', '03/04/2021', 'cash', '23654'),
('78945', '2500', '04/02/2021', 'card', '42366'),
('45612', '5000', '25/08/2021', 'card', '45213');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
