-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 24, 2019 at 01:08 PM
-- Server version: 10.3.15-MariaDB
-- PHP Version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `springbootproject`
--

-- --------------------------------------------------------

--
-- Table structure for table `assigngradestudent`
--

CREATE TABLE `assigngradestudent` (
  `studentId` int(11) NOT NULL,
  `subjectId` int(11) NOT NULL,
  `gradeId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Table structure for table `assignspecialgradestudent`
--

CREATE TABLE `assignspecialgradestudent` (
  `studentId` int(11) NOT NULL,
  `specialSubjectId` int(11) NOT NULL,
  `specialGradeId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Table structure for table `classes`
--

CREATE TABLE `classes` (
  `classesId` int(11) NOT NULL,
  `classesName` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Table structure for table `grades`
--

CREATE TABLE `grades` (
  `gradeId` int(11) NOT NULL,
  `grade` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Table structure for table `specialgrades`
--

CREATE TABLE `specialgrades` (
  `specialGradesId` int(11) NOT NULL,
  `specialGradesGrade` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Table structure for table `specialsubjects`
--

CREATE TABLE `specialsubjects` (
  `specialSubjectId` int(11) NOT NULL,
  `specialSubjectName` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `studentId` int(11) NOT NULL,
  `studentName` varchar(30) NOT NULL,
  `studentSurname` varchar(30) NOT NULL,
  `studentAdress` varchar(50) NOT NULL,
  `studentContactPerson` varchar(50) NOT NULL,
  `classId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Table structure for table `subjectclass`
--

CREATE TABLE `subjectclass` (
  `classId` int(11) NOT NULL,
  `subjectId` int(11) NOT NULL,
  `specialSubjectId` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE `subjects` (
  `subjectId` int(11) NOT NULL,
  `subjectName` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Table structure for table `teacherclass`
--

CREATE TABLE `teacherclass` (
  `teacherId` int(11) NOT NULL,
  `classId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Table structure for table `teachers`
--

CREATE TABLE `teachers` (
  `teacherId` int(11) NOT NULL,
  `teacherName` varchar(30) NOT NULL,
  `teacherSurname` varchar(30) NOT NULL,
  `teacherAdress` varchar(30) NOT NULL,
  `teacherEmail` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Table structure for table `teachersubject`
--

CREATE TABLE `teachersubject` (
  `teacherId` int(11) NOT NULL,
  `subjectId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assigngradestudent`
--
ALTER TABLE `assigngradestudent`
  ADD KEY `studentId` (`studentId`,`subjectId`,`gradeId`);

--
-- Indexes for table `assignspecialgradestudent`
--
ALTER TABLE `assignspecialgradestudent`
  ADD KEY `studentId` (`studentId`,`specialSubjectId`,`specialGradeId`);

--
-- Indexes for table `classes`
--
ALTER TABLE `classes`
  ADD PRIMARY KEY (`classesId`);

--
-- Indexes for table `grades`
--
ALTER TABLE `grades`
  ADD PRIMARY KEY (`gradeId`),
  ADD KEY `grade` (`grade`);

--
-- Indexes for table `specialgrades`
--
ALTER TABLE `specialgrades`
  ADD PRIMARY KEY (`specialGradesId`);

--
-- Indexes for table `specialsubjects`
--
ALTER TABLE `specialsubjects`
  ADD PRIMARY KEY (`specialSubjectId`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`studentId`),
  ADD KEY `classId` (`classId`);

--
-- Indexes for table `subjectclass`
--
ALTER TABLE `subjectclass`
  ADD KEY `classId` (`classId`,`subjectId`,`specialSubjectId`);

--
-- Indexes for table `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`subjectId`);

--
-- Indexes for table `teacherclass`
--
ALTER TABLE `teacherclass`
  ADD KEY `teacherId` (`teacherId`,`classId`);

--
-- Indexes for table `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`teacherId`);

--
-- Indexes for table `teachersubject`
--
ALTER TABLE `teachersubject`
  ADD PRIMARY KEY (`teacherId`),
  ADD KEY `subjectId` (`subjectId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `classes`
--
ALTER TABLE `classes`
  MODIFY `classesId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `specialgrades`
--
ALTER TABLE `specialgrades`
  MODIFY `specialGradesId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `teachers`
--
ALTER TABLE `teachers`
  MODIFY `teacherId` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
