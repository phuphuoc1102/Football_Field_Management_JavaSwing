-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.32 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for projectjava
CREATE DATABASE IF NOT EXISTS `projectjava` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `projectjava`;

-- Dumping structure for table projectjava.soccerfieldbooking
CREATE TABLE IF NOT EXISTS `soccerfieldbooking` (
  `dateOfBooking` date DEFAULT NULL,
  `id` int NOT NULL,
  `customerName` varchar(50) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `customerPhoneNumber` char(10) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `fieldBooking` char(50) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `starttime` char(50) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `price` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='soccerFieldBooking';

-- Dumping data for table projectjava.soccerfieldbooking: ~5 rows (approximately)
INSERT INTO `soccerfieldbooking` (`dateOfBooking`, `id`, `customerName`, `customerPhoneNumber`, `fieldBooking`, `starttime`, `price`) VALUES
	('2023-06-01', 1, 'Phú Phước', '0377382067', 'A', '10:00', 250000),
	('2023-06-02', 2, 'Hoàng Phi', '012482324', 'B', '20:30', 300000),
	('2023-06-02', 3, 'Trọng Hiếu', '013582385', 'A', '08:00', 200000),
	('2023-04-29', 5, 'phi', '035768', 'A', '19:30', 200000),
	('2023-04-29', 6, 'dfdf', '3242', 'B', '19:30', 100000);

-- Dumping structure for table projectjava.soccer_field
CREATE TABLE IF NOT EXISTS `soccer_field` (
  `soccerField` char(10) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table projectjava.soccer_field: ~6 rows (approximately)
INSERT INTO `soccer_field` (`soccerField`) VALUES
	('A'),
	('B'),
	('C'),
	('D'),
	('E'),
	('F');

-- Dumping structure for table projectjava.time_soccer
CREATE TABLE IF NOT EXISTS `time_soccer` (
  `shift` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `startTime` char(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `price` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table projectjava.time_soccer: ~14 rows (approximately)
INSERT INTO `time_soccer` (`shift`, `startTime`, `price`) VALUES
	('sang', '07:00', 100000),
	('sang', '08:00', 100000),
	('sang', '09:00', 100000),
	('sang', '10:00', 100000),
	('chieu-toi', '13:30', 100000),
	('chieu-toi', '14:30', 100000),
	('chieu-toi', '15:30', 150000),
	('chieu-toi', '16:30', 200000),
	('chieu-toi', '17:30', 250000),
	('chieu-toi', '18:30', 250000),
	('chieu-toi', '19:30', 250000),
	('chieu-toi', '20:30', 250000),
	('chieu-toi', '21:30', 250000),
	('sang', '06:00', 100000);

-- Dumping structure for table projectjava.user
CREATE TABLE IF NOT EXISTS `user` (
  `username` char(50) COLLATE utf8mb4_general_ci NOT NULL,
  `password` char(50) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table projectjava.user: ~0 rows (approximately)
INSERT INTO `user` (`username`, `password`) VALUES
	('admin', 'admin');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
