-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 20, 2023 at 02:51 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `apple_stock`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `emp_id` varchar(20) NOT NULL,
  `emp_name` varchar(20) NOT NULL,
  `emp_pass` varchar(20) NOT NULL,
  `isManager` tinyint(1) NOT NULL DEFAULT 0,
  `emp_address` varchar(100) NOT NULL,
  `emp_phone` varchar(20) NOT NULL,
  `emp_email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`emp_id`, `emp_name`, `emp_pass`, `isManager`, `emp_address`, `emp_phone`, `emp_email`) VALUES
('1', 'admin', 'admin', 1, 'home address', '0769146712', 'admin@gmail.com'),
('2', 'cashier', 'cashier', 0, 'home address', '0769146712', 'cashier@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `product_slug` varchar(20) NOT NULL,
  `product_name` varchar(20) NOT NULL,
  `product_price` double NOT NULL,
  `product_qty` int(10) NOT NULL,
  `product_desc` varchar(500) NOT NULL,
  `product_cat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_slug`, `product_name`, `product_price`, `product_qty`, `product_desc`, `product_cat`) VALUES
('AirPods | Pro', 'Pro', 35000, 190, 'BN', 'AirPods'),
('iPhone | 6', '6', 17000, 900, 'imported', 'iPhone'),
('iPhone | 6s', '6s', 25000, 90, 'imported', 'iPhone');

-- --------------------------------------------------------

--
-- Table structure for table `product_categories`
--

CREATE TABLE `product_categories` (
  `category` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product_categories`
--

INSERT INTO `product_categories` (`category`) VALUES
('AirPods'),
('AirTag'),
('Apple TV'),
('iPad'),
('iPhone'),
('iPod'),
('iWatch'),
('Mac');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`emp_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_slug`),
  ADD KEY `cat` (`product_cat`);

--
-- Indexes for table `product_categories`
--
ALTER TABLE `product_categories`
  ADD PRIMARY KEY (`category`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `cat` FOREIGN KEY (`product_cat`) REFERENCES `product_categories` (`category`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
