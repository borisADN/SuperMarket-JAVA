-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 06 nov. 2024 à 07:27
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `market`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`admin_id`, `username`, `password`) VALUES
(1, 'boris', '1999');

-- --------------------------------------------------------

--
-- Structure de la table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `brand` varchar(100) DEFAULT NULL,
  `productName` varchar(100) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `customer`
--

INSERT INTO `customer` (`id`, `customer_id`, `brand`, `productName`, `quantity`, `date`, `price`) VALUES
(12, 1, 'iphone', 'IPhone5', 1, '2024-05-10', 35000),
(15, 2, 'iphone', 'IPhone7', 1, '2024-05-10', 90000),
(16, 2, 'iphone', 'IPhone7', 2, '2024-05-10', 90000),
(17, 2, 'iphone', 'IPhone7', 3, '2024-05-10', 90000),
(18, 3, 'samsung', 'Samsung Galaxy S23', 1, '2024-05-10', 600000),
(19, 3, 'samsung', 'Samsung Galaxy S23', 4, '2024-05-10', 600000),
(20, 3, 'samsung', 'Samsung Galaxy S23', 4, '2024-05-10', 600000),
(21, 3, 'samsung', 'Samsung Galaxy S23', 4, '2024-05-10', 600000),
(22, 3, 'samsung', 'Samsung Galaxy S23', 4, '2024-05-10', 600000),
(23, 3, 'samsung', 'Samsung Galaxy S23', 4, '2024-05-10', 600000),
(24, 3, 'samsung', 'Samsung Galaxy S23', 4, '2024-05-10', 600000),
(25, 3, 'samsung', 'Samsung Galaxy S23', 4, '2024-05-10', 600000),
(26, 3, 'samsung', 'Samsung Galaxy S23', 4, '2024-05-10', 600000),
(27, 3, 'samsung', 'Samsung Galaxy S23', 4, '2024-05-10', 600000),
(28, 3, 'samsung', 'Samsung Galaxy S23', 4, '2024-05-10', 600000),
(29, 3, 'samsung', 'Samsung Galaxy S23', 4, '2024-05-10', 600000),
(30, 3, 'samsung', 'Samsung Galaxy S23', 4, '2024-05-10', 600000),
(31, 3, 'samsung', 'Samsung Galaxy S23', 4, '2024-05-10', 600000),
(32, 3, 'samsung', 'Samsung Galaxy S23', 4, '2024-05-10', 600000),
(33, 3, 'samsung', 'Samsung Galaxy S23', 4, '2024-05-10', 600000),
(34, 3, 'samsung', 'Samsung Galaxy S23', 4, '2024-05-10', 600000),
(35, 3, 'samsung', 'Samsung Galaxy S23', 4, '2024-05-10', 600000),
(36, 4, 'iphone', 'IPhone5', 1, '2024-05-10', 35000),
(37, 5, 'iphone', 'IPhone5', 1, '2024-05-11', 35000),
(38, 6, 'samsung', 'Samsung Galaxy S23', 3, '2024-05-11', 600000),
(39, 7, 'HUAWEI', 'Huawei nova 3i', 5, '2024-05-16', 90000),
(40, 8, 'huawei', 'Huawei nova 2+', 7, '2024-07-29', 60000);

-- --------------------------------------------------------

--
-- Structure de la table `customer_receipt`
--

CREATE TABLE `customer_receipt` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `customer_receipt`
--

INSERT INTO `customer_receipt` (`id`, `customer_id`, `total`, `date`) VALUES
(1, 1, 270000, '2024-05-10'),
(2, 2, 270000, '2024-05-10'),
(3, 3, 60000, '2024-05-10'),
(4, 4, 400000, '2024-05-11'),
(5, 2, 615000, '2024-05-10'),
(6, 5, 750000, '2024-05-12'),
(7, 1, 35000, '2024-05-10'),
(8, 2, 270000, '2024-05-10'),
(9, 3, 10800000, '2024-05-10'),
(10, 4, 35000, '2024-05-10'),
(11, 5, 35000, '2024-05-11'),
(12, 6, 600000, '2024-05-11'),
(13, 6, 600000, '2024-05-11'),
(14, 6, 600000, '2024-05-11'),
(15, 6, 600000, '2024-05-11'),
(16, 6, 600000, '2024-05-11'),
(17, 6, 600000, '2024-05-11'),
(18, 6, 600000, '2024-05-11'),
(19, 6, 600000, '2024-05-11'),
(20, 6, 600000, '2024-05-11'),
(21, 6, 600000, '2024-05-11'),
(22, 6, 600000, '2024-05-11'),
(23, 6, 600000, '2024-05-11'),
(24, 6, 600000, '2024-05-11'),
(25, 6, 600000, '2024-05-11'),
(26, 6, 600000, '2024-05-11'),
(27, 6, 600000, '2024-05-11'),
(28, 6, 600000, '2024-05-11'),
(29, 6, 600000, '2024-05-11'),
(30, 7, 90000, '2024-05-16'),
(31, 8, 60000, '2024-07-29');

-- --------------------------------------------------------

--
-- Structure de la table `employee`
--

CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `firstName` varchar(100) DEFAULT NULL,
  `lastName` varchar(100) DEFAULT NULL,
  `gender` varchar(100) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `employeeID` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `employee`
--

INSERT INTO `employee` (`employee_id`, `username`, `password`, `firstName`, `lastName`, `gender`, `date`, `employeeID`) VALUES
(1, NULL, '0000', 'SANDJISSON', 'GANGSTER', 'LGBT+', '2024-05-07', 'sandji'),
(2, NULL, '0000', 'Lazare', 'TOGBA', 'Masculin', '2024-05-07', 'lazarus'),
(3, NULL, 'e', 'essai', 'essai', 'Masculin', '2024-05-07', 'e'),
(4, NULL, '0000', 'CLAUDE ', 'ADJE', 'Masculin', '2024-05-07', 'adje'),
(5, NULL, '0000', 'syl', 'OSWALD', 'Masculin', '2024-05-07', 'oswald'),
(6, NULL, 'Ahmad', 'Ahmad', 'MAMAM MOROU', 'Masculin', '2024-05-08', 'mamam'),
(11, NULL, '1234', 'lazare', 'TOGBA BETAN', 'Masculin', '2024-05-14', 'laza12'),
(12, NULL, 'serre', 'elom', 'khatib', 'Masculin', '2024-05-16', 'elomfestus');

-- --------------------------------------------------------

--
-- Structure de la table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `brand` varchar(100) DEFAULT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `product_id` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `product`
--

INSERT INTO `product` (`id`, `brand`, `product_name`, `status`, `price`, `product_id`) VALUES
(20, 'IPHONE', 'IPhone4S', 'Indisponible', 5000, 'Prod1'),
(21, 'IPHONE', 'IPhone5', 'Disponible', 35000, 'Prod2'),
(22, 'IPHONE', 'IPhone6', 'Disponible', 45000, 'Prod3'),
(23, 'IPHONE', 'IPhone7', 'Disponible', 90000, 'Prod4'),
(24, 'IPHONE', 'IPhone11', 'Indisponible', 150000, 'Prod5'),
(25, 'IPHONE', 'IPhone6', 'Indisponible', 45000, 'Prod6'),
(26, 'HUAWEI', 'Huawei Mate 50 Pro', 'Disponible', 245000, 'Prod7'),
(27, 'HUAWEI', 'Huawei P60 Pro', 'Disponible', 345000, 'Prod8'),
(28, 'HUAWEI', 'Huawei nova 12s', 'Indisponible', 385000, 'Prod9'),
(29, 'HUAWEI', 'Huawei nova 2+', 'Disponible', 60000, 'Prod10'),
(30, 'HUAWEI', 'Huawei nova 3i', 'Disponible', 90000, 'Prod11'),
(31, 'SAMSUNG', 'Samsung Galaxy S21', 'Disponible', 400000, 'Prod12'),
(32, 'SAMSUNG', 'Samsung Galaxy S23', 'Indisponible', 600000, 'Prod13'),
(33, 'SAMSUNG', 'Samsung Galaxy S23', 'Disponible', 650000, 'Prod14'),
(34, 'SONY', 'Sony PlayStation 5', 'Disponible', 555000, 'Prod15'),
(35, 'SONY', 'Sony PlayStation 3', 'Indisponible', 558000, 'Prod16'),
(41, 'Tecno', 'Spark10', 'Disponible', 150000, 'prod_21'),
(44, 'gucci', 'guicci', 'Disponible', 4444444444, 'dysdgyugy');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`);

--
-- Index pour la table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `customer_receipt`
--
ALTER TABLE `customer_receipt`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`);

--
-- Index pour la table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT pour la table `customer_receipt`
--
ALTER TABLE `customer_receipt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT pour la table `employee`
--
ALTER TABLE `employee`
  MODIFY `employee_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
