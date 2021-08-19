-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: course_selection_system
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `admin_username_uindex` (`username`),
  UNIQUE KEY `admin_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (4,'gregPerlinLi','e10adc3949ba59abbe56e057f20f883e'),(5,'TestAdmin','8206d629db9722aa6c56cda609cecdbd'),(6,'anotherTest','e10adc3949ba59abbe56e057f20f883e'),(11,'Test02','4d5e2a885578299e5a5902ad295447c6');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `classes_name` varchar(100) NOT NULL,
  `college` varchar(100) NOT NULL,
  `grade` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `class_class_name_uindex` (`classes_name`),
  UNIQUE KEY `class_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES (1,'20电子科学与技术1班','物理与光电工程学院','20级'),(2,'20电子科学与技术2班','物理与光电工程学院','20级'),(3,'20电子科学与技术3班','物理与光电工程学院','20级'),(4,'20电子科学与技术4班','物理与光电工程学院','20级'),(5,'21电子科学与技术1班','物理与光电工程学院','21级'),(6,'21电子科学与技术2班','物理与光电工程学院','21级'),(7,'21电子科学与技术3班','物理与光电工程学院','21级'),(8,'21电子科学与技术4班','物理与光电工程学院','21级'),(9,'20光电信息科学与技术1班','物理与光电工程学院','20级'),(10,'21微电子科学与工程1班','材料与能源学院','21级'),(11,'19自动化类2班','自动化学院','19级'),(12,'20光电信息科学技术2班','物理与光电工程学院','20级'),(13,'19电子科学与技术1班','物理与光电工程学院','19级'),(25,'19计算机科学与技术1班','计算机学院','19级'),(26,'21自动化类1班','自动化学院','21级');
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `college`
--

DROP TABLE IF EXISTS `college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `college` (
  `id` int NOT NULL AUTO_INCREMENT,
  `college_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `college_college_name_uindex` (`college_name`),
  UNIQUE KEY `college_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `college`
--

LOCK TABLES `college` WRITE;
/*!40000 ALTER TABLE `college` DISABLE KEYS */;
INSERT INTO `college` VALUES (2,'材料与能源学院'),(1,'物理与光电工程学院'),(3,'自动化学院'),(7,'计算机学院');
/*!40000 ALTER TABLE `college` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(100) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `max_stu` int NOT NULL,
  `current_stu` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `course_id_uindex` (`id`),
  UNIQUE KEY `course_course_name_uindex` (`course_name`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (2,'高等数学(2)','2021-08-15','12:30:00',150,10),(3,'大学英语(2)','2021-08-31','11:45:00',150,2),(4,'大学物理(1)','2021-09-03','00:30:00',100,0),(5,'电路','2021-08-30','12:00:00',80,0),(6,'模拟电子技术','2021-09-01','00:00:00',80,0),(7,'线性代数','2021-08-16','14:30:00',100,6),(8,'概率论与数理统计','2021-08-16','16:00:00',100,6),(9,'大学生心理健康教育','2021-08-16','22:31:40',100,6),(10,'体育','2021-08-29','14:30:00',200,0),(11,'时事与政策','2021-08-20','12:30:00',100,0),(12,'模拟电子技术实验','2021-08-30','14:30:00',100,0),(13,'工程制图','2021-08-30','12:30:00',100,0);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grade` (
  `id` int NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `grade_grade_name_uindex` (`grade_name`),
  UNIQUE KEY `grade_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` VALUES (2,'19级'),(1,'20级'),(3,'21级');
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `selected_course`
--

DROP TABLE IF EXISTS `selected_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `selected_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `stu_num` varchar(50) NOT NULL,
  `stu_name` varchar(100) NOT NULL,
  `course` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `selected_course_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `selected_course`
--

LOCK TABLES `selected_course` WRITE;
/*!40000 ALTER TABLE `selected_course` DISABLE KEYS */;
INSERT INTO `selected_course` VALUES (5,'3121005002','李华','高等数学(2)'),(6,'3121005002','李华','线性代数'),(7,'3121005002','李华','概率论与数理统计'),(8,'3121005002','李华','大学生心理健康教育'),(9,'3121008001','小明','线性代数'),(10,'3121008001','小明','高等数学(2)'),(11,'3121008001','小明','概率论与数理统计'),(12,'3119002001','小军','大学生心理健康教育'),(13,'3119002001','小军','线性代数'),(14,'3119002001','小军','高等数学(2)'),(15,'3221006001','小红','高等数学(2)'),(16,'3221006001','小红','概率论与数理统计'),(17,'3221006001','小红','大学生心理健康教育'),(18,'3119001001','小强','高等数学(2)'),(19,'3119001001','小强','线性代数'),(20,'3119001001','小强','概率论与数理统计'),(21,'3119001001','小强','大学生心理健康教育'),(22,'3121006020','小黄','高等数学(2)'),(23,'3121006020','小黄','概率论与数理统计'),(24,'3221007261','小青','高等数学(2)'),(25,'3221007261','小青','大学生心理健康教育'),(26,'3221007261','小青','线性代数'),(27,'3210001021','小刚','高等数学(2)'),(28,'3210001021','小刚','线性代数'),(29,'3210001021','小刚','概率论与数理统计'),(30,'3210001021','小刚','大学生心理健康教育');
/*!40000 ALTER TABLE `selected_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `stu_num` varchar(50) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `college` varchar(100) NOT NULL,
  `grade` varchar(20) NOT NULL,
  `stu_class` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_username_uindex` (`username`),
  UNIQUE KEY `user_id_uindex` (`id`),
  UNIQUE KEY `students_stu_num_uindex` (`stu_num`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (5,'3121008001','小明','e10adc3949ba59abbe56e057f20f883e','材料与能源学院','21级','21微电子科学与工程1班'),(6,'3119002001','小军','e10adc3949ba59abbe56e057f20f883e','自动化学院','19级','19自动化类2班'),(8,'3121005002','李华','135053df988912507fb5a8de4cd99881','材料与能源学院','21级','21微电子科学与工程1班'),(9,'3221006001','小红','e10adc3949ba59abbe56e057f20f883e','材料与能源学院','21级','21微电子科学与工程1班'),(10,'3119001001','小强','e10adc3949ba59abbe56e057f20f883e','自动化学院','19级','19自动化类2班'),(14,'3221007261','小青','e10adc3949ba59abbe56e057f20f883e','物理与光电工程学院','21级','21电子科学与技术4班'),(15,'3121006020','小黄','e10adc3949ba59abbe56e057f20f883e','材料与能源学院','21级','21微电子科学与工程1班'),(16,'3210001021','小刚','e10adc3949ba59abbe56e057f20f883e','自动化学院','21级','21自动化类1班');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-19 19:18:54
