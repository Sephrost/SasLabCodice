-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 30, 2023 at 02:06 PM
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
-- Database: `catering`
--

-- --------------------------------------------------------

--
-- Table structure for table `Events`
--

CREATE TABLE `Events` (
  `id` int(11) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `date_start` date DEFAULT NULL,
  `date_end` date DEFAULT NULL,
  `expected_participants` int(11) DEFAULT NULL,
  `organizer_id` int(11) NOT NULL,
  `chef_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `Events`
--

INSERT INTO `Events` (`id`, `name`, `date_start`, `date_end`, `expected_participants`, `organizer_id`, `chef_id`) VALUES
(1, 'Convegno Agile Community', '2020-09-25', '2020-09-25', 100, 2, 0),
(2, 'Compleanno di Manuela', '2020-08-13', '2020-08-13', 25, 2, 0),
(3, 'Fiera del Sedano Rapa', '2020-10-02', '2020-10-04', 400, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `KitchenJobs`
--

CREATE TABLE `KitchenJobs` (
  `id` int(11) NOT NULL,
  `name` tinytext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `KitchenJobs`
--

INSERT INTO `KitchenJobs` (`id`, `name`) VALUES
(1, 'Vitello tonnato'),
(2, 'Carpaccio di spada'),
(3, 'Alici marinate'),
(4, 'Insalata di riso'),
(5, 'Penne al sugo di baccalà'),
(6, 'Pappa al pomodoro'),
(7, 'Hamburger con bacon e cipolla caramellata'),
(8, 'Salmone al forno'),
(9, 'Croissant'),
(10, 'Pane al cioccolato'),
(11, 'Girelle all\'uvetta'),
(12, 'Panini al latte'),
(13, 'Biscotti di pasta frolla'),
(14, 'Lingue di gatto'),
(15, 'Bigné farciti'),
(16, 'Pizzette'),
(17, 'Tramezzini'),
(18, 'Sorbetto al limone'),
(19, 'Torta Saint Honoré'),
(20, 'Risotto alla zucca');

-- --------------------------------------------------------

--
-- Table structure for table `MenuFeatures`
--

CREATE TABLE `MenuFeatures` (
  `menu_id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL DEFAULT '',
  `value` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `MenuFeatures`
--

INSERT INTO `MenuFeatures` (`menu_id`, `name`, `value`) VALUES
(80, 'Richiede cuoco', 0),
(80, 'Buffet', 0),
(80, 'Richiede cucina', 0),
(80, 'Finger food', 0),
(80, 'Piatti caldi', 0),
(82, 'Richiede cuoco', 0),
(82, 'Buffet', 0),
(82, 'Richiede cucina', 0),
(82, 'Finger food', 0),
(82, 'Piatti caldi', 0),
(86, 'Richiede cuoco', 0),
(86, 'Buffet', 0),
(86, 'Richiede cucina', 0),
(86, 'Finger food', 0),
(86, 'Piatti caldi', 0),
(89, 'Richiede cuoco', 1),
(89, 'Buffet', 1),
(89, 'Richiede cucina', 1),
(89, 'Finger food', 1),
(89, 'Piatti caldi', 1),
(90, 'Richiede cuoco', 1),
(90, 'Buffet', 1),
(90, 'Richiede cucina', 1),
(90, 'Finger food', 1),
(90, 'Piatti caldi', 1);

-- --------------------------------------------------------

--
-- Table structure for table `MenuItems`
--

CREATE TABLE `MenuItems` (
  `id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `section_id` int(11) DEFAULT NULL,
  `description` tinytext DEFAULT NULL,
  `kitchenjobs_id` int(11) NOT NULL,
  `position` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `MenuItems`
--

INSERT INTO `MenuItems` (`id`, `menu_id`, `section_id`, `description`, `kitchenjobs_id`, `position`) VALUES
(96, 80, 0, 'Croissant vuoti', 9, 0),
(97, 80, 0, 'Croissant alla marmellata', 9, 1),
(98, 80, 0, 'Pane al cioccolato mignon', 10, 2),
(99, 80, 0, 'Panini al latte con prosciutto crudo', 12, 4),
(100, 80, 0, 'Panini al latte con prosciutto cotto', 12, 5),
(101, 80, 0, 'Panini al latte con formaggio spalmabile alle erbe', 12, 6),
(102, 80, 0, 'Girelle all\'uvetta mignon', 11, 3),
(103, 82, 0, 'Biscotti', 13, 1),
(104, 82, 0, 'Lingue di gatto', 14, 2),
(105, 82, 0, 'Bigné alla crema', 15, 3),
(106, 82, 0, 'Bigné al caffè', 15, 4),
(107, 82, 0, 'Pizzette', 16, 5),
(108, 82, 0, 'Croissant al prosciutto crudo mignon', 9, 6),
(109, 82, 0, 'Tramezzini tonno e carciofini mignon', 17, 7),
(112, 86, 41, 'Vitello tonnato', 1, 0),
(113, 86, 41, 'Carpaccio di spada', 2, 1),
(114, 86, 41, 'Alici marinate', 3, 2),
(115, 86, 42, 'Penne alla messinese', 5, 0),
(116, 86, 42, 'Risotto alla zucca', 20, 1),
(117, 86, 43, 'Salmone al forno', 8, 0),
(118, 86, 44, 'Sorbetto al limone', 18, 0),
(119, 86, 44, 'Torta Saint Honoré', 19, 1),
(121, 89, 47, 'Vitello tonnato', 1, 0),
(122, 89, 47, 'Carpaccio di spada', 2, 1),
(123, 89, 47, 'Alici marinate', 3, 2),
(124, 89, 49, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(125, 89, 49, 'Salmone al forno', 8, 1),
(126, 89, 0, 'Insalata di riso', 4, 0),
(127, 89, 0, 'Penne al sugo di baccalà', 5, 1),
(128, 90, 50, 'Vitello tonnato', 1, 0),
(129, 90, 50, 'Carpaccio di spada', 2, 1),
(130, 90, 50, 'Alici marinate', 3, 2),
(131, 90, 52, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(132, 90, 52, 'Salmone al forno', 8, 1),
(133, 90, 0, 'Insalata di riso', 4, 0),
(134, 90, 0, 'Penne al sugo di baccalà', 5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `Menus`
--

CREATE TABLE `Menus` (
  `id` int(11) NOT NULL,
  `title` tinytext DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  `published` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `Menus`
--

INSERT INTO `Menus` (`id`, `title`, `owner_id`, `published`) VALUES
(80, 'Coffee break mattutino', 2, 1),
(82, 'Coffee break pomeridiano', 2, 1),
(86, 'Cena di compleanno pesce', 3, 1),
(89, 'Titolo Nuovo', 2, 1),
(90, 'Titolo Nuovo', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `MenuSections`
--

CREATE TABLE `MenuSections` (
  `id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `name` tinytext DEFAULT NULL,
  `position` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `MenuSections`
--

INSERT INTO `MenuSections` (`id`, `menu_id`, `name`, `position`) VALUES
(41, 86, 'Antipasti', 0),
(42, 86, 'Primi', 1),
(43, 86, 'Secondi', 2),
(44, 86, 'Dessert', 3),
(45, 87, 'Antipasti', 0),
(47, 89, 'Antipasti', 0),
(48, 89, 'Primi', 1),
(49, 89, 'Secondi', 2),
(50, 90, 'Antipasti', 0),
(51, 90, 'Primi', 1),
(52, 90, 'Secondi', 2);

-- --------------------------------------------------------

--
-- Table structure for table `Roles`
--

CREATE TABLE `Roles` (
  `id` char(1) NOT NULL,
  `role` varchar(128) NOT NULL DEFAULT 'servizio'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `Roles`
--

INSERT INTO `Roles` (`id`, `role`) VALUES
('c', 'cuoco'),
('h', 'chef'),
('o', 'organizzatore'),
('s', 'servizio');

-- --------------------------------------------------------

--
-- Table structure for table `Services`
--

CREATE TABLE `Services` (
  `id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `notes` blob DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `state` enum('NOT_STARTED','IN_PROGRESS','COMPLETED','CANCELLED') NOT NULL,
  `proposed_menu_id` int(11) NOT NULL DEFAULT 0,
  `approved_menu_id` int(11) DEFAULT 0,
  `service_date` date DEFAULT NULL,
  `time_start` time DEFAULT NULL,
  `time_end` time DEFAULT NULL,
  `expected_participants` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `Services`
--

INSERT INTO `Services` (`id`, `event_id`, `name`, `notes`, `location`, `state`, `proposed_menu_id`, `approved_menu_id`, `service_date`, `time_start`, `time_end`, `expected_participants`) VALUES
(1, 2, 'Cena', NULL, NULL, 'NOT_STARTED', 86, 0, '2020-08-13', '20:00:00', '23:30:00', 25),
(2, 1, 'Coffee break mattino', NULL, NULL, 'NOT_STARTED', 0, 80, '2020-09-25', '10:30:00', '11:30:00', 100),
(3, 1, 'Colazione di lavoro', NULL, NULL, 'NOT_STARTED', 0, 0, '2020-09-25', '13:00:00', '14:00:00', 80),
(4, 1, 'Coffee break pomeriggio', NULL, NULL, 'NOT_STARTED', 0, 82, '2020-09-25', '16:00:00', '16:30:00', 100),
(5, 1, 'Cena sociale', NULL, NULL, 'NOT_STARTED', 0, 0, '2020-09-25', '20:00:00', '22:30:00', 40),
(6, 3, 'Pranzo giorno 1', NULL, NULL, 'NOT_STARTED', 0, 0, '2020-10-02', '12:00:00', '15:00:00', 200),
(7, 3, 'Pranzo giorno 2', NULL, NULL, 'NOT_STARTED', 0, 0, '2020-10-03', '12:00:00', '15:00:00', 300),
(8, 3, 'Pranzo giorno 3', NULL, NULL, 'NOT_STARTED', 0, 0, '2020-10-04', '12:00:00', '15:00:00', 400);

-- --------------------------------------------------------

--
-- Table structure for table `Shifts`
--

CREATE TABLE `Shifts` (
  `id` int(11) NOT NULL,
  `data` date NOT NULL,
  `starttime` time NOT NULL,
  `endtime` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Shifts`
--

INSERT INTO `Shifts` (`id`, `data`, `starttime`, `endtime`) VALUES
(1, '2020-04-13', '10:00:00', '11:00:00'),
(2, '2020-04-13', '11:00:00', '12:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `SummarySheets`
--

CREATE TABLE `SummarySheets` (
  `service_id` int(11) NOT NULL,
  `task_id` int(11) NOT NULL,
  `chef_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `SummarySheets`
--

INSERT INTO `SummarySheets` (`service_id`, `task_id`, `chef_id`) VALUES
(1, 1, 3),
(1, 2, 3);

-- --------------------------------------------------------

--
-- Table structure for table `Task`
--

CREATE TABLE `Task` (
  `id` int(11) NOT NULL,
  `completed` tinyint(1) NOT NULL,
  `quantity` varchar(30) NOT NULL,
  `kitchenjob_id` int(11) NOT NULL,
  `cook_id` int(11) NOT NULL,
  `estimatedtime` double NOT NULL,
  `shift_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Task`
--

INSERT INTO `Task` (`id`, `completed`, `quantity`, `kitchenjob_id`, `cook_id`, `estimatedtime`, `shift_id`) VALUES
(1, 0, 'Tanta tanta', 1, 5, 30.6, 1),
(2, 0, 'abbastanza per un elefante', 2, 6, 35, 2);

-- --------------------------------------------------------

--
-- Table structure for table `UserRoles`
--

CREATE TABLE `UserRoles` (
  `user_id` int(11) NOT NULL,
  `role_id` char(1) NOT NULL DEFAULT 's'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `UserRoles`
--

INSERT INTO `UserRoles` (`user_id`, `role_id`) VALUES
(1, 'o'),
(2, 'o'),
(2, 'h'),
(3, 'h'),
(4, 'h'),
(4, 'c'),
(5, 'c'),
(6, 'c'),
(7, 'c'),
(8, 's'),
(9, 's'),
(10, 's'),
(7, 's');

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `id` int(11) NOT NULL,
  `username` varchar(128) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`id`, `username`) VALUES
(1, 'Carlin'),
(2, 'Lidia'),
(3, 'Tony'),
(4, 'Marinella'),
(5, 'Guido'),
(6, 'Antonietta'),
(7, 'Paola'),
(8, 'Silvia'),
(9, 'Marco'),
(10, 'Piergiorgio');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Events`
--
ALTER TABLE `Events`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `MenuItems`
--
ALTER TABLE `MenuItems`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Menus`
--
ALTER TABLE `Menus`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `MenuSections`
--
ALTER TABLE `MenuSections`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Shifts`
--
ALTER TABLE `Shifts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `SummarySheets`
--
ALTER TABLE `SummarySheets`
  ADD PRIMARY KEY (`service_id`,`task_id`);

--
-- Indexes for table `Task`
--
ALTER TABLE `Task`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Shifts`
--
ALTER TABLE `Shifts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `Task`
--
ALTER TABLE `Task`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
