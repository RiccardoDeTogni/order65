-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 22, 2016 alle 17:34
-- Versione del server: 5.6.21
-- PHP Version: 5.5.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `playtoday`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `amici`
--

CREATE TABLE IF NOT EXISTS `amici` (
`ID` int(11) NOT NULL,
  `id_user1` int(11) NOT NULL,
  `id_user2` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Struttura della tabella `campo`
--

CREATE TABLE IF NOT EXISTS `campo` (
`ID` int(11) NOT NULL,
  `ID_struttura` int(11) NOT NULL,
  `name` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Struttura della tabella `prenotazione`
--

CREATE TABLE IF NOT EXISTS `prenotazione` (
`ID` int(11) NOT NULL,
  `data` date DEFAULT NULL,
  `ora_inizio` time DEFAULT NULL,
  `ora_fine` time DEFAULT NULL,
  `id_campo` int(11) NOT NULL,
  `aperta` tinyint(1) DEFAULT NULL,
  `num_partecipanti` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Struttura della tabella `sport`
--

CREATE TABLE IF NOT EXISTS `sport` (
`ID` int(11) NOT NULL,
  `Name` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Struttura della tabella `struttura`
--

CREATE TABLE IF NOT EXISTS `struttura` (
`ID` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `name` text COLLATE utf8_bin,
  `city` text COLLATE utf8_bin,
  `active` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dump dei dati per la tabella `struttura`
--

INSERT INTO `struttura` (`ID`, `id_user`, `name`, `city`, `active`) VALUES
(1, 1, 'Str1', 'Ferrara', 1),
(2, 1, 'Str2', 'Ferrara', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`ID` int(11) NOT NULL,
  `username` varchar(20) COLLATE utf8_bin NOT NULL,
  `passwd` text COLLATE utf8_bin NOT NULL,
  `first_name` text COLLATE utf8_bin NOT NULL,
  `surname` text COLLATE utf8_bin NOT NULL,
  `email` text COLLATE utf8_bin NOT NULL,
  `reg_date` date NOT NULL,
  `type` int(11) NOT NULL,
  `city` text COLLATE utf8_bin,
  `telephone` text COLLATE utf8_bin,
  `session_token` text COLLATE utf8_bin,
  `fl_active` tinyint(1) NOT NULL,
  `last_mod` date NOT NULL,
  `sport` int(11) DEFAULT NULL,
  `struttura` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dump dei dati per la tabella `user`
--

INSERT INTO `user` (`ID`, `username`, `passwd`, `first_name`, `surname`, `email`, `reg_date`, `type`, `city`, `telephone`, `session_token`, `fl_active`, `last_mod`, `sport`, `struttura`) VALUES
(1, 'admin', 'e9b35379a4a2155324153569bea58a99de746e9e1603da9721bdd06271bebb2512358b3a65dd5631f5251796f444cc7047d1aacd49d65e2928343c6b8aa79052', 'admin', 'admin', 'admin@admin.com', '2016-04-18', 1, 'Ferrara', '34543553', '532fea0aacb8de3718a82b19090222206f3469d562d99a4b24fc523c6e6f028e', 0, '2016-04-18', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `amici`
--
ALTER TABLE `amici`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `campo`
--
ALTER TABLE `campo`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `prenotazione`
--
ALTER TABLE `prenotazione`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `sport`
--
ALTER TABLE `sport`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `struttura`
--
ALTER TABLE `struttura`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`ID`), ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `amici`
--
ALTER TABLE `amici`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `campo`
--
ALTER TABLE `campo`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `prenotazione`
--
ALTER TABLE `prenotazione`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `sport`
--
ALTER TABLE `sport`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `struttura`
--
ALTER TABLE `struttura`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
