-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 20, 2018 at 10:56 PM
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
(8, 'Cambodja', 3),
(2, 'India', 2),
(3, 'Savanne', 2),
(4, 'Jungle', 2),
(5, 'Mexico', 2),
(6, 'Far West', 2),
(7, 'Kidspark', 2),
(9, 'Kenia', 3),
(10, 'Belgie', 3),
(11, 'Italie', 3),
(12, 'Amerika', 3),
(13, 'Europe', 5),
(14, 'Africa', 5),
(15, 'America', 5),
(16, 'Asia', 5),
(17, 'Oceania', 5);

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
('Owner', 1, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Albert Florizoone', 2),
('Keeper', 9, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Lucas', NULL),
('Keeper', 10, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Lucas', NULL),
('Keeper', 11, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Lucas', NULL),
('Keeper', 12, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Lucas', NULL),
('Keeper', 13, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Lucas', NULL),
('Keeper', 14, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Lucas', NULL),
('Keeper', 15, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Lucas', NULL),
('Keeper', 16, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Elise', NULL),
('Keeper', 17, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Elise', NULL),
('Keeper', 18, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Elise', NULL),
('Keeper', 19, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Elise', NULL),
('Keeper', 20, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Elise', NULL),
('Keeper', 21, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Elise', NULL),
('Keeper', 22, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Elise', NULL),
('Keeper', 23, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Noah', NULL),
('Keeper', 24, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Noah', NULL),
('Keeper', 25, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Noah', NULL),
('Keeper', 26, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Noah', NULL),
('Keeper', 27, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Noah', NULL),
('Keeper', 28, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Noah', NULL),
('Keeper', 29, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Noah', NULL),
('Keeper', 30, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Arthur', NULL),
('Keeper', 31, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Arthur', NULL),
('Keeper', 32, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Arthur', NULL),
('Keeper', 33, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Arthur', NULL),
('Keeper', 34, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Arthur', NULL),
('Keeper', 35, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Arthur', NULL),
('Keeper', 36, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Arthur', NULL),
('Keeper', 37, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Sofie', NULL),
('Keeper', 38, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Sofie', NULL),
('Keeper', 39, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Sofie', NULL),
('Keeper', 40, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Sofie', NULL),
('Keeper', 41, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Sofie', NULL),
('Keeper', 42, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Sofie', NULL),
('Keeper', 43, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Sofie', NULL),
('Keeper', 44, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Bob', NULL),
('Keeper', 45, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Bob', NULL),
('Keeper', 46, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Bob', NULL),
('Keeper', 47, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Bob', NULL),
('Keeper', 48, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Bob', NULL),
('Keeper', 49, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Bob', NULL),
('Keeper', 50, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Bob', NULL);

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
(2, 9),
(2, 10),
(2, 11),
(2, 12),
(2, 13),
(2, 14),
(2, 15),
(3, 16),
(3, 17),
(3, 18),
(3, 19),
(3, 20),
(3, 21),
(3, 22),
(4, 23),
(4, 24),
(4, 25),
(4, 26),
(4, 27),
(4, 28),
(4, 29),
(5, 30),
(5, 31),
(5, 32),
(5, 33),
(5, 34),
(5, 35),
(5, 36),
(6, 37),
(6, 38),
(6, 39),
(6, 40),
(6, 41),
(6, 42),
(6, 43),
(7, 44),
(7, 45),
(7, 46),
(7, 47),
(7, 48),
(7, 49),
(7, 50);

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
(2, 'Ieper', 'België', 497, 'Meenseweg', 8900, 'Bellewaerde', '057 46 86 86'),
(3, 'Brugelette', 'België', 1, 'Domaine de Cambron', 7940, 'Pairi Daiza', '068 25 08 50'),
(4, 'Han-sur-Lesse', 'België', 2, 'Rue Joseph Lamotte', 5580, 'De grotten van Han', '084 37 72 13'),
(5, 'Mechelen', 'België', 582, 'Leuvensesteenweg', 2812, 'Planckendael', '015 41 49 21');

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
  MODIFY `worker_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;
--
-- AUTO_INCREMENT for table `zoos`
--
ALTER TABLE `zoos`
  MODIFY `zoo_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
