-- phpMyAdmin SQL Dump
-- version 4.4.7
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2015-08-30 13:45:55
-- 服务器版本： 5.6.22
-- PHP Version: 5.6.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tacdm`
--

-- --------------------------------------------------------

--
-- 表的结构 `borrowrecord`
--

CREATE TABLE IF NOT EXISTS `borrowrecord` (
  `recordId` int(11) NOT NULL,
  `borrowerName` varchar(32) NOT NULL,
  `tele` varchar(32) NOT NULL,
  `itemId` int(11) NOT NULL,
  `ItemName` varchar(32) NOT NULL,
  `itemInfo` varchar(128) NOT NULL,
  `borrowDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `returnDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `iteminfo`
--

CREATE TABLE IF NOT EXISTS `iteminfo` (
  `id` int(11) NOT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `count` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `borrowrecord`
--
ALTER TABLE `borrowrecord`
  ADD PRIMARY KEY (`recordId`);

--
-- Indexes for table `iteminfo`
--
ALTER TABLE `iteminfo`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `borrowrecord`
--
ALTER TABLE `borrowrecord`
  MODIFY `recordId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `iteminfo`
--
ALTER TABLE `iteminfo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
