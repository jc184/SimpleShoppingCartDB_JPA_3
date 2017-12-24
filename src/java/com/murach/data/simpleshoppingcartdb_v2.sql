-- phpMyAdmin SQL Dump
-- version 4.5.0.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 24, 2017 at 05:28 PM
-- Server version: 10.0.17-MariaDB
-- PHP Version: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `simpleshoppingcartdb_v2`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `address1` varchar(45) NOT NULL,
  `address2` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `creditcardtype` varchar(45) NOT NULL,
  `creditcardnumber` varchar(45) NOT NULL,
  `ccexpirydate` date NOT NULL,
  `email` varchar(45) NOT NULL,
  `mobile` varchar(12) NOT NULL,
  `loginname` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `firstName`, `lastName`, `address1`, `address2`, `city`, `state`, `creditcardtype`, `creditcardnumber`, `ccexpirydate`, `email`, `mobile`, `loginname`, `password`) VALUES
(1, 'John', 'Doe', '37 Forteath Avenue', '', 'elgin', 'Morayshire', 'Visa', '1111222233334444', '2019-09-12', 'john.doe@gmail.com', '07552605450', 'jd123', '1Acheilidh1'),
(2, 'claire', 'smith', '37 Forteath Avenue', '', 'elgin', 'Morayshire', 'Visa', '1111222233334444', '2019-09-12', 'clairesmith321@gmail.com', '07552605450', 'cs123', 'password');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `code` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `price` decimal(4,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`code`, `description`, `price`) VALUES
(1, 'Mountain Bike', '95.00'),
(2, 'Road Racer', '89.95'),
(3, 'Nylon Cycle Shorts', '27.50'),
(4, 'RayBan Sun Glasses', '19.95'),
(5, 'Plastic Water Bottle', '17.50'),
(6, 'Cotton Wrist Bands', '0.50');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`code`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `code` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
