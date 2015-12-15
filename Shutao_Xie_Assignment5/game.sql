-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 14, 2015 at 10:09 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `game`
--

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE IF NOT EXISTS `game` (
  `game_id` int(11) NOT NULL AUTO_INCREMENT,
  `game_title` varchar(30) NOT NULL,
  PRIMARY KEY (`game_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `game`
--

INSERT INTO `game` (`game_id`, `game_title`) VALUES
(1, 'Ladybug'),
(2, 'Sound2Play'),
(3, 'Bomb'),
(4, 'Sailing'),
(5, 'Treasure'),
(6, 'Monster');

-- --------------------------------------------------------

--
-- Table structure for table `player`
--

CREATE TABLE IF NOT EXISTS `player` (
  `player_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `address` varchar(100) NOT NULL,
  `postal_code` varchar(10) NOT NULL,
  `province` varchar(10) NOT NULL,
  `phone_number` int(10) NOT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `player`
--

INSERT INTO `player` (`player_id`, `first_name`, `last_name`, `address`, `postal_code`, `province`, `phone_number`) VALUES
(1, 'Shutao', 'Xie', '11 Burn', 'M2J3N2', 'on', 1478878196),
(2, 'Ashley', 'lin', '12 clipper', 'm2j3v5', 'on', 1478123456),
(3, 'Henry', 'Austin', '2008 finch', 'm2i9k7', 'on', 1479871234),
(4, 'cindy', 'william', '2356 foo', 'w2e4l9', 'Qe', 1133456899);

-- --------------------------------------------------------

--
-- Table structure for table `playerandgame`
--

CREATE TABLE IF NOT EXISTS `playerandgame` (
  `player_game_id` int(11) NOT NULL AUTO_INCREMENT,
  `game_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  `playing_date` date NOT NULL,
  `score` int(100) NOT NULL,
  PRIMARY KEY (`player_game_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `playerandgame`
--

INSERT INTO `playerandgame` (`player_game_id`, `game_id`, `player_id`, `playing_date`, `score`) VALUES
(1, 1, 2, '2015-11-02', 45),
(2, 2, 3, '2015-10-05', 20),
(3, 5, 4, '2015-09-08', 78),
(4, 5, 3, '2015-08-12', 32),
(5, 4, 2, '2015-05-18', 23);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
