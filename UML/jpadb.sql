-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 21, 2018 at 11:36 AM
-- Server version: 5.6.34-log
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jpadb`
--

-- --------------------------------------------------------

--
-- Table structure for table `animals`
--

CREATE TABLE `animals` (
  `specie` varchar(31) NOT NULL,
  `animal_id` bigint(20) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `flying` bit(1) DEFAULT NULL,
  `hibernation` bit(1) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `animals`
--

INSERT INTO `animals` (`specie`, `animal_id`, `gender`, `name`, `flying`, `hibernation`, `department_id`) VALUES
('otherAnimal', 1, 'male', 'Basil', NULL, NULL, 10),
('Mammal', 2, 'male', 'Martin', NULL, b'1', 9),
('otherAnimal', 3, 'female', 'Janice', NULL, NULL, 12),
('Bird', 4, 'female', 'Linda', b'1', NULL, 8),
('Mammal', 5, 'female', 'Eva', NULL, b'0', 11);

-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

CREATE TABLE `departments` (
  `department_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `zoo_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `departments`
--

INSERT INTO `departments` (`department_id`, `name`, `zoo_id`) VALUES
(8, 'Cambodja', 4),
(2, 'India', 3),
(3, 'Savanne', 3),
(4, 'Jungle', 3),
(5, 'Mexico', 3),
(6, 'Far West', 3),
(7, 'Kidspark', 3),
(9, 'Kenia', 4),
(10, 'Belgie', 4),
(11, 'Italie', 4),
(12, 'Amerika', 4);

-- --------------------------------------------------------

--
-- Table structure for table `workers`
--

CREATE TABLE `workers` (
  `worker_type` varchar(31) NOT NULL,
  `worker_id` bigint(20) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `number` int(11) NOT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zipcode` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `zoo_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `workers`
--

INSERT INTO `workers` (`worker_type`, `worker_id`, `city`, `country`, `number`, `street`, `zipcode`, `name`, `zoo_id`) VALUES
('Owner', 1, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Albert Florizoone', 3),
('Keeper', 2, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Emma', NULL),
('Keeper', 3, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Lucas', NULL),
('Keeper', 4, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Elise', NULL),
('Keeper', 5, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Noah', NULL),
('Keeper', 6, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Arthur', NULL),
('Keeper', 7, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Sofie', NULL),
('Keeper', 8, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Bob', NULL),
('Owner', 9, 'Han-sur-Lesse', 'België', 2, 'Rue Joseph Lamotte', 5580, 'Han Verschuure', 5);

-- --------------------------------------------------------

--
-- Table structure for table `zookeepers_per_departments`
--

CREATE TABLE `zookeepers_per_departments` (
  `department_id` bigint(20) NOT NULL,
  `worker_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `zookeepers_per_departments`
--

INSERT INTO `zookeepers_per_departments` (`department_id`, `worker_id`) VALUES
(2, 2),
(2, 3),
(2, 4),
(2, 5),
(2, 6),
(2, 7),
(2, 8),
(3, 2),
(3, 3),
(3, 4),
(3, 5),
(3, 6),
(3, 7),
(3, 8),
(4, 2),
(4, 3),
(4, 4),
(4, 5),
(4, 6),
(4, 7),
(4, 8),
(5, 2),
(5, 3),
(5, 4),
(5, 5),
(5, 6),
(5, 7),
(5, 8),
(6, 2),
(6, 3),
(6, 4),
(6, 5),
(6, 6),
(6, 7),
(6, 8),
(7, 2),
(7, 3),
(7, 4),
(7, 5),
(7, 6),
(7, 7),
(7, 8);

-- --------------------------------------------------------

--
-- Table structure for table `zoos`
--

CREATE TABLE `zoos` (
  `zoo_id` bigint(20) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `number` int(11) NOT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zipcode` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `zoos`
--

INSERT INTO `zoos` (`zoo_id`, `city`, `country`, `number`, `street`, `zipcode`, `name`, `phone_number`) VALUES
(1, 'Antwerpen', 'België', 20, 'Koningin Astridplein', 2018, 'ZOO Antwerpen', '032 24 89 10'),
(2, 'Balen', 'België', 45, 'Bukenberg', 2491, 'Olmense zoo', '014 30 98 82'),
(3, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Bellewaerde', '057 46 86 86'),
(4, 'Brugelette', 'België', 1, 'Domaine de Cambron', 7940, 'Pairi Daiza', '068 25 08 50'),
(5, 'Han-sur-Lesse', 'België', 2, 'Rue Joseph Lamotte', 5580, 'De grotten van Han', '084 37 72 13');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `animals`
--
ALTER TABLE `animals`
  ADD PRIMARY KEY (`animal_id`),
  ADD KEY `FK9xdwdxt309jgo1yjstdwlgb36` (`department_id`);

--
-- Indexes for table `departments`
--
ALTER TABLE `departments`
  ADD PRIMARY KEY (`department_id`),
  ADD KEY `FKm68329514ciuv57pl0pmrrd4o` (`zoo_id`);

--
-- Indexes for table `workers`
--
ALTER TABLE `workers`
  ADD PRIMARY KEY (`worker_id`),
  ADD KEY `FKrafoqo4iw3r0rw7fv9o1oq3ku` (`zoo_id`);

--
-- Indexes for table `zookeepers_per_departments`
--
ALTER TABLE `zookeepers_per_departments`
  ADD KEY `FKf4uv8byyrxb48tr6ff7nf2ith` (`worker_id`),
  ADD KEY `FKnh6tixphry6jotvr7l9qoirg2` (`department_id`);

--
-- Indexes for table `zoos`
--
ALTER TABLE `zoos`
  ADD PRIMARY KEY (`zoo_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `animals`
--
ALTER TABLE `animals`
  MODIFY `animal_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `departments`
--
ALTER TABLE `departments`
  MODIFY `department_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `workers`
--
ALTER TABLE `workers`
  MODIFY `worker_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `zoos`
--
ALTER TABLE `zoos`
  MODIFY `zoo_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
