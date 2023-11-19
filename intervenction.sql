-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 19 nov. 2023 à 10:46
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `intervenction`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id`, `nom`) VALUES
(255, 'imprime');

-- --------------------------------------------------------

--
-- Structure de la table `demandes`
--

CREATE TABLE `demandes` (
  `etudiant_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `personnel_id` bigint(20) DEFAULT 0,
  `sous_categorie_id` bigint(20) DEFAULT NULL,
  `description` text NOT NULL,
  `statut` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `demandes`
--

INSERT INTO `demandes` (`etudiant_id`, `id`, `personnel_id`, `sous_categorie_id`, `description`, `statut`) VALUES
(101, 1, NULL, 7, '', 'En attente'),
(102, 2, NULL, 7, '', NULL),
(103, 3, NULL, 7, '', NULL),
(101, 4, NULL, 7, '', NULL),
(102, 5, NULL, 7, '', NULL),
(103, 6, NULL, 7, '', NULL),
(102, 7, NULL, 7, 'bonjour monsieur', NULL),
(101, 8, NULL, 7, 'boj', NULL),
(101, 9, NULL, 7, 'aaa', NULL),
(103, 10, NULL, 7, 'nooo', NULL),
(102, 11, NULL, 7, 'vkvkjbkjb', NULL),
(103, 12, NULL, 7, 'jgdjgchvc,vn', NULL),
(103, 13, NULL, 7, 'prombleme de matricule', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `departements`
--

CREATE TABLE `departements` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `departements`
--

INSERT INTO `departements` (`id`, `nom`) VALUES
(77, 'Departement fictif');

-- --------------------------------------------------------

--
-- Structure de la table `etudiants`
--

CREATE TABLE `etudiants` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(200) NOT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `matricule` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `etudiants`
--

INSERT INTO `etudiants` (`id`, `nom`, `prenom`, `matricule`) VALUES
(101, 'Alice', 'Dupont', '2021001'),
(102, 'Bob', 'Martin', '2021002'),
(103, 'Claire', 'Leclerc', '2021003');

-- --------------------------------------------------------

--
-- Structure de la table `mails`
--

CREATE TABLE `mails` (
  `demande_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `objet` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `personnels`
--

CREATE TABLE `personnels` (
  `departement_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `poste` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `pieces_jointes`
--

CREATE TABLE `pieces_jointes` (
  `demande_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `pieces_jointes`
--

INSERT INTO `pieces_jointes` (`demande_id`, `id`, `nom`, `path`) VALUES
(NULL, 1, 'Piece1.pdf', NULL),
(NULL, 2, 'Image1.jpg', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `sous_categorie`
--

CREATE TABLE `sous_categorie` (
  `categorie_id` bigint(20) DEFAULT NULL,
  `departement_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `sous_categorie`
--

INSERT INTO `sous_categorie` (`categorie_id`, `departement_id`, `id`, `nom`) VALUES
(255, 77, 7, 'dev');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `statut` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  `mot_de_passe` varchar(255) DEFAULT NULL,
  `nom_utilisateur` varchar(255) DEFAULT NULL,
  `etudiant_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `email`, `login`, `nom`, `password`, `statut`, `telephone`, `role`, `mot_de_passe`, `nom_utilisateur`, `etudiant_id`) VALUES
(101, 'john.smith@example.com', 'john_smith', 'John Smith', 'mot101', 'Actif', '555-1234', 'Etudiant', 'mot101', 'john101', NULL),
(102, 'mary.jones@example.com', 'mary_jones', 'Mary Jones', 'hashed_password_102', 'Actif', '555-5678', 'Personnel', 'mot102', 'mary102', NULL),
(103, 'peter.doe@example.com', 'peter_doe', 'Peter Doe', 'hashed_password_103', 'Inactif', '555-9876', '', NULL, NULL, NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `demandes`
--
ALTER TABLE `demandes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6ck20b2s0q1v82xnrf4clft81` (`etudiant_id`),
  ADD KEY `FK53468hpg7f2r31uu0cjbnfhyj` (`personnel_id`),
  ADD KEY `FKbfbqjxx0k799w88vasl803k1p` (`sous_categorie_id`);

--
-- Index pour la table `departements`
--
ALTER TABLE `departements`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `etudiants`
--
ALTER TABLE `etudiants`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `mails`
--
ALTER TABLE `mails`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmihfrgo4a6mnbkyj4uyd7ekfa` (`demande_id`);

--
-- Index pour la table `personnels`
--
ALTER TABLE `personnels`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1x0wgvksf3xq3odwale8vbr0v` (`departement_id`);

--
-- Index pour la table `pieces_jointes`
--
ALTER TABLE `pieces_jointes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKj3ai6i6vep04j8xeir87im5x` (`demande_id`);

--
-- Index pour la table `sous_categorie`
--
ALTER TABLE `sous_categorie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrqdn0l7bb88g42fs81pbhevag` (`categorie_id`),
  ADD KEY `FKqe7roa7v4wesf3g8xcxdi6mkr` (`departement_id`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=256;

--
-- AUTO_INCREMENT pour la table `demandes`
--
ALTER TABLE `demandes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `departements`
--
ALTER TABLE `departements`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- AUTO_INCREMENT pour la table `mails`
--
ALTER TABLE `mails`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `pieces_jointes`
--
ALTER TABLE `pieces_jointes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `sous_categorie`
--
ALTER TABLE `sous_categorie`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=104;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `demandes`
--
ALTER TABLE `demandes`
  ADD CONSTRAINT `FK53468hpg7f2r31uu0cjbnfhyj` FOREIGN KEY (`personnel_id`) REFERENCES `personnels` (`id`),
  ADD CONSTRAINT `FK6ck20b2s0q1v82xnrf4clft81` FOREIGN KEY (`etudiant_id`) REFERENCES `etudiants` (`id`),
  ADD CONSTRAINT `FKbfbqjxx0k799w88vasl803k1p` FOREIGN KEY (`sous_categorie_id`) REFERENCES `sous_categorie` (`id`);

--
-- Contraintes pour la table `etudiants`
--
ALTER TABLE `etudiants`
  ADD CONSTRAINT `FKchwx9fgql30st7ymsvt2fg7wk` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `mails`
--
ALTER TABLE `mails`
  ADD CONSTRAINT `FKmihfrgo4a6mnbkyj4uyd7ekfa` FOREIGN KEY (`demande_id`) REFERENCES `demandes` (`id`);

--
-- Contraintes pour la table `personnels`
--
ALTER TABLE `personnels`
  ADD CONSTRAINT `FK1x0wgvksf3xq3odwale8vbr0v` FOREIGN KEY (`departement_id`) REFERENCES `departements` (`id`),
  ADD CONSTRAINT `FKbc2dusf0hcj3erca6jdfdjvi6` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `pieces_jointes`
--
ALTER TABLE `pieces_jointes`
  ADD CONSTRAINT `FKj3ai6i6vep04j8xeir87im5x` FOREIGN KEY (`demande_id`) REFERENCES `demandes` (`id`);

--
-- Contraintes pour la table `sous_categorie`
--
ALTER TABLE `sous_categorie`
  ADD CONSTRAINT `FKqe7roa7v4wesf3g8xcxdi6mkr` FOREIGN KEY (`departement_id`) REFERENCES `departements` (`id`),
  ADD CONSTRAINT `FKrqdn0l7bb88g42fs81pbhevag` FOREIGN KEY (`categorie_id`) REFERENCES `categorie` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
