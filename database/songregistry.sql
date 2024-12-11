-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Gegenereerd op: 11 dec 2024 om 13:08
-- Serverversie: 10.4.32-MariaDB
-- PHP-versie: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `songregistry`
--

--
-- Gegevens worden geëxporteerd voor tabel `albums`
--

INSERT INTO `albums` (`id`, `album_name`) VALUES
(1, 'IGOR'),
(2, 'CHROMAKOPIA'),
(3, 'Donda'),
(4, 'Blonde'),
(5, 'Watch The Throne'),
(6, 'Now, Not Yet');

INSERT INTO `artists` (`id`, `artist_name`) VALUES
                                                (1, 'Tyler The Creator'),
                                                (2, 'Kanye West'),
                                                (3, 'Jay-Z'),
                                                (4, 'Half Alive'),
                                                (5, 'Frank Ocean'),
                                                (6, 'Mac Miller'),
                                                (7, 'Sor'),
                                                (8, 'Declan McKenna');

--
-- Gegevens worden geëxporteerd voor tabel `album_artists`
--

INSERT INTO `album_artists` (`id`, `album_id`, `artist_id`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 2),
(4, 4, 5),
(5, 5, 2),
(6, 5, 3),
(7, 6, 4);

--
-- Gegevens worden geëxporteerd voor tabel `artists`
--

INSERT INTO `songs` (`id`, `song_name`, `song_genre`, `album_id`) VALUES
    (3, 'IGOR\'S THEME', 1, 1),
(4, 'EARFQUAKE', 1, 1);

--
-- Gegevens worden geëxporteerd voor tabel `genres`
--

INSERT INTO `genres` (`id`, `genre_name`) VALUES
(1, 'Rap');

--
-- Gegevens worden geëxporteerd voor tabel `reviews`
--

INSERT INTO `reviews` (`id`, `text`, `rating`, `song_id`, `user_id`) VALUES
(1, 'test', 4, 3, NULL),
(2, 'test', 3, 3, NULL),
(3, 'Prima plaat', 3.5, 3, NULL),
(4, 'backend test', 5, 3, NULL),
(6, 'Hemels plaatje', 5, 4, NULL),
(7, 'heerlijk plaatje', 4, 3, NULL);

--
-- Gegevens worden geëxporteerd voor tabel `roles`
--

INSERT INTO `roles` (`id`, `role_name`) VALUES
(1, 'Performer');

--
-- Gegevens worden geëxporteerd voor tabel `songs`
--



--
-- Gegevens worden geëxporteerd voor tabel `song_artist`
--

INSERT INTO `song_artist` (`id`, `song_id`, `artist_id`, `artist_role`) VALUES
(5, 3, 1, 1),
(6, 4, 1, 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
