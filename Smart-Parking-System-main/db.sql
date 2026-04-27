/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.5-10.4.24-MariaDB : Database - smart_parking
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`smart_parking` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `smart_parking`;

/*Table structure for table `bookings` */

DROP TABLE IF EXISTS `bookings`;

CREATE TABLE `bookings` (
  `BID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BookingDate` date DEFAULT NULL,
  `CustomerName` varchar(50) DEFAULT 'Customer',
  `VehicleNumber` varchar(15) DEFAULT NULL,
  `Mobile` varchar(10) DEFAULT '9999999999',
  `PID` bigint(20) DEFAULT 0,
  `SlotNumber` int(11) DEFAULT 0,
  PRIMARY KEY (`BID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `bookings` */

insert  into `bookings`(`BID`,`BookingDate`,`CustomerName`,`VehicleNumber`,`Mobile`,`PID`,`SlotNumber`) values (1,'2023-01-21','Mohit','UP32AB1234','8888888888',3,1),(2,'2023-01-21','Mohit','UP32AB1234','8888888888',3,1),(3,'2023-01-21','Mohit','UP32AB1234','8888888888',3,1),(4,'2023-01-27','Rashmi','UP99XX1122','7777777777',1,1),(5,'2023-01-22','Rashmi','UP99XX1122','9696622933',1,1),(6,'2023-01-22','Rashmi','UP99XX1122','9696622933',1,2);

/*Table structure for table `parkings` */

DROP TABLE IF EXISTS `parkings`;

CREATE TABLE `parkings` (
  `PID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ParkingName` varchar(100) DEFAULT 'Royal Parking',
  `TotalSlots` int(11) DEFAULT 10,
  `City` varchar(25) DEFAULT 'Lucknow',
  `Charge` int(11) DEFAULT 10,
  `Address` varchar(100) DEFAULT 'Address Address Address Address Address Address',
  PRIMARY KEY (`PID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `parkings` */

insert  into `parkings`(`PID`,`ParkingName`,`TotalSlots`,`City`,`Charge`,`Address`) values (1,'Royal Parking',10,'Lucknow',100,'Gomti nagar near Taj hotel'),(2,'Gunj Parking',15,'Lucknow',50,'Hajratgunj maine market moti'),(3,'ABC Parking',10,'Delhi',100,'knaught Place delhi main bazar Under ground');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
