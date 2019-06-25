-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Erstellungszeit: 25. Jun 2019 um 09:06
-- Server-Version: 5.7.26
-- PHP-Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `benjamin_java_core_project`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `assigngradestudent`
--

CREATE TABLE `assigngradestudent` (
  `fk_student_id` int(11) NOT NULL,
  `fk_teacher_id` int(11) NOT NULL,
  `fk_grade_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `assignspecialgradestudent`
--

CREATE TABLE `assignspecialgradestudent` (
  `fk_student_id` int(11) NOT NULL,
  `fk_specialSubject_id` int(11) NOT NULL,
  `fk_specialGrade_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `classes`
--

CREATE TABLE `classes` (
  `class_id` int(11) NOT NULL,
  `className` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Daten für Tabelle `classes`
--

INSERT INTO `classes` (`class_id`, `className`) VALUES
(1, '1a'),
(2, '1b'),
(3, '2a'),
(4, '2b'),
(5, '3a'),
(6, '3b'),
(7, '4a'),
(8, '4b');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `grades`
--

CREATE TABLE `grades` (
  `grade_id` int(11) NOT NULL,
  `grade` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Daten für Tabelle `grades`
--

INSERT INTO `grades` (`grade_id`, `grade`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `specialgrades`
--

CREATE TABLE `specialgrades` (
  `specialGrade_id` int(11) NOT NULL,
  `specialGradeGrade` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Daten für Tabelle `specialgrades`
--

INSERT INTO `specialgrades` (`specialGrade_id`, `specialGradeGrade`) VALUES
(1, 'Very good'),
(2, 'Well done'),
(3, 'Successful'),
(4, 'Not successful');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `specialsubjects`
--

CREATE TABLE `specialsubjects` (
  `specialSubject_id` int(11) NOT NULL,
  `specialSubjectName` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Daten für Tabelle `specialsubjects`
--

INSERT INTO `specialsubjects` (`specialSubject_id`, `specialSubjectName`) VALUES
(1, 'Drawing'),
(2, 'Physical Education');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `students`
--

CREATE TABLE `students` (
  `student_id` int(11) NOT NULL,
  `studentName` varchar(30) NOT NULL,
  `studentSurname` varchar(30) NOT NULL,
  `studentAddress` varchar(50) NOT NULL,
  `studentContactPerson` varchar(50) NOT NULL,
  `fk_class_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Daten für Tabelle `students`
--

INSERT INTO `students` (`student_id`, `studentName`, `studentSurname`, `studentAddress`, `studentContactPerson`, `fk_class_id`) VALUES
(1, 'Tony', 'Stark', 'Homeway 01', 'Howard Stark', 1),
(2, 'Steve', 'Rogers', 'Homeway 02', 'Rogers Dad', 1),
(3, 'Bruce', 'Banner', 'Homeway 03', 'Bruce Shark', 1),
(4, 'Peggy', 'Carter', 'Homeyway 04', 'Mrs Puff', 1),
(5, 'James', 'Barnes', 'Homeway 05', 'Paul Summers', 1),
(6, 'Homer', 'Simpsons', 'Homeway 06', 'Al Neal', 2),
(7, 'Marge', 'Simpson', 'Homeway 07', 'Mr Krabs', 2),
(8, 'Lisa', 'Simpson', 'Homeway 08', 'James Norrington', 2),
(9, 'Bart', 'Simpson', 'Homeway 09', 'Doctor Strange', 2),
(10, 'Maggie', 'Simpson', 'Homeway 10', 'Charles Xavier', 2),
(11, 'Harry', 'Potter', 'Homeway 11', 'Albus Dumbledore', 3),
(12, 'Ronald', 'Weasley', 'Homeway 12', 'Donald Duck', 3),
(13, 'Hermione', 'Granger', 'Homeway 13', 'Brad Hole', 3),
(14, 'Luna', 'Lovegood', 'Homeway 14', 'Kent Brockman', 3),
(15, 'Severus', 'Snape', 'Homeway 15', 'Page Name', 3),
(16, 'Micheal', 'Townley', 'Homeway 16', 'Patrick Star', 4),
(17, 'Trevor', 'Philips', 'Homeway 17', 'Mark Layer', 4),
(18, 'Franklin', 'Clinton', 'Homeway 18', 'Gerri Starr', 4),
(19, 'Lester', 'Crest', 'Homeway 19', 'Eric Block', 4),
(20, 'Tonya', 'Grove', 'Homeway 20', 'Pinhead Larry', 4),
(21, 'Jack', 'Sparrow', 'Homeway 21', 'Krabby Patty', 5),
(22, 'William', 'Turner', 'Homeway 22', 'Medusa', 5),
(23, 'Elizabeth', 'Swan', 'Homeway 23', 'Chiara Kane', 5),
(24, 'Hector', 'Barbossa', 'Homeway 24', 'Lisa Mahdude', 5),
(25, 'Davy', 'Jones', 'Homeway 25', 'Rare Pepe', 5),
(26, 'Frodo', 'Baggins', 'Homeway 26', 'Chester Benington', 6),
(27, 'Samwise', 'Gamgee', 'Homeway 27', 'Mike Shinoda', 6),
(28, 'Meriadoc', 'Brandybuck', 'Homeway 28', 'Corey Taylor', 6),
(29, 'Peregrin', 'Took', 'Homeway 29', 'Till Lindemann', 6),
(30, 'Bilbo', 'Baggins', 'Homeway 30', 'Deryck Whimbley', 6),
(31, 'Spongebob', 'Squarepants', 'Homeway 31', 'Carl Johnson', 7),
(32, 'Patrick', 'Star', 'Homeway 32', 'Peter Parker', 7),
(33, 'Squidward', 'Tentacles', 'Homeway 33', 'Mike Tyson', 7),
(34, 'Sandy', 'Cheeks', 'Homeway 34', 'Scooby Doo', 7),
(35, 'Sheldon', 'Plankton', 'Homeway 35', 'Ted Mosby', 7),
(36, 'Sheldon', 'Cooper', 'Homeway 36', 'Tom Turbo', 8),
(37, 'Howard', 'Wolowitz', 'Homeway 37', 'Robbie Rotton', 8),
(38, 'Rajesh', 'Koothrappali', 'Homeway 38', 'Neil Armstrong', 8),
(39, 'Leonard', 'Hofstadter', 'Homeway 39', 'Obelix', 8),
(40, 'Barry', 'Kripke', 'Homeway 40', 'Asterix', 8);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `subjectclass`
--

CREATE TABLE `subjectclass` (
  `fk_class_id` int(11) NOT NULL,
  `fk_subject_id` int(11) NOT NULL,
  `fk_specialSubject_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `subjects`
--

CREATE TABLE `subjects` (
  `subject_id` int(11) NOT NULL,
  `subjectName` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Daten für Tabelle `subjects`
--

INSERT INTO `subjects` (`subject_id`, `subjectName`) VALUES
(1, 'English'),
(2, 'Maths'),
(3, 'Physics'),
(4, 'Chemistry'),
(5, 'German'),
(6, 'Biology'),
(7, 'Spanish'),
(8, 'IT'),
(9, 'History');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `teacherclass`
--

CREATE TABLE `teacherclass` (
  `fk_teacher_id` int(11) NOT NULL,
  `fk_classes_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `teachers`
--

CREATE TABLE `teachers` (
  `teacher_id` int(11) NOT NULL,
  `teacherName` varchar(30) NOT NULL,
  `teacherSurname` varchar(30) NOT NULL,
  `teacherAddress` varchar(30) NOT NULL,
  `teacherEmail` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Daten für Tabelle `teachers`
--

INSERT INTO `teachers` (`teacher_id`, `teacherName`, `teacherSurname`, `teacherAddress`, `teacherEmail`) VALUES
(1, 'Ronald', 'McDonald', 'Houseway 01', 'rm@domain.com'),
(2, 'Dirty', 'Bubble', 'Houseway 02', 'db@domain.com'),
(3, 'Tom', 'Riddle', 'Houseway 03', 'tr@domain.com'),
(4, 'Mrs', 'Puff', 'Houseway 04', 'mp@domain.com'),
(5, 'Donald', 'Duck', 'Houseway 05', 'dd@domain.com'),
(6, 'Mickey', 'Mouse', 'Houseway 06', 'mm@domain.com'),
(7, 'Bill', 'Gates', 'Houseway 07', 'bg@domain.com'),
(8, 'Steve', 'Jobs', 'Houseway 08', 'sj@domain.com');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `teachersubject`
--

CREATE TABLE `teachersubject` (
  `fk_teacher_id` int(11) NOT NULL,
  `fk_subject_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `assigngradestudent`
--
ALTER TABLE `assigngradestudent`
  ADD KEY `studentId` (`fk_student_id`,`fk_teacher_id`,`fk_grade_id`),
  ADD KEY `fk_teacher_id` (`fk_teacher_id`),
  ADD KEY `fk_grade_id` (`fk_grade_id`);

--
-- Indizes für die Tabelle `assignspecialgradestudent`
--
ALTER TABLE `assignspecialgradestudent`
  ADD KEY `studentId` (`fk_student_id`,`fk_specialSubject_id`,`fk_specialGrade_id`),
  ADD KEY `fk_specialGrade_id` (`fk_specialGrade_id`),
  ADD KEY `fk_specialSubject_id` (`fk_specialSubject_id`);

--
-- Indizes für die Tabelle `classes`
--
ALTER TABLE `classes`
  ADD PRIMARY KEY (`class_id`);

--
-- Indizes für die Tabelle `grades`
--
ALTER TABLE `grades`
  ADD PRIMARY KEY (`grade_id`),
  ADD KEY `grade` (`grade`);

--
-- Indizes für die Tabelle `specialgrades`
--
ALTER TABLE `specialgrades`
  ADD PRIMARY KEY (`specialGrade_id`);

--
-- Indizes für die Tabelle `specialsubjects`
--
ALTER TABLE `specialsubjects`
  ADD PRIMARY KEY (`specialSubject_id`);

--
-- Indizes für die Tabelle `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`student_id`),
  ADD KEY `classId` (`fk_class_id`);

--
-- Indizes für die Tabelle `subjectclass`
--
ALTER TABLE `subjectclass`
  ADD KEY `classId` (`fk_class_id`,`fk_subject_id`,`fk_specialSubject_id`),
  ADD KEY `fk_subject_id` (`fk_subject_id`),
  ADD KEY `fk_specialSubject_id` (`fk_specialSubject_id`);

--
-- Indizes für die Tabelle `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`subject_id`);

--
-- Indizes für die Tabelle `teacherclass`
--
ALTER TABLE `teacherclass`
  ADD KEY `teacherId` (`fk_teacher_id`,`fk_classes_id`),
  ADD KEY `fk_classes_id` (`fk_classes_id`);

--
-- Indizes für die Tabelle `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`teacher_id`);

--
-- Indizes für die Tabelle `teachersubject`
--
ALTER TABLE `teachersubject`
  ADD PRIMARY KEY (`fk_teacher_id`),
  ADD KEY `subjectId` (`fk_subject_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `classes`
--
ALTER TABLE `classes`
  MODIFY `class_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT für Tabelle `grades`
--
ALTER TABLE `grades`
  MODIFY `grade_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT für Tabelle `specialgrades`
--
ALTER TABLE `specialgrades`
  MODIFY `specialGrade_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT für Tabelle `specialsubjects`
--
ALTER TABLE `specialsubjects`
  MODIFY `specialSubject_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT für Tabelle `students`
--
ALTER TABLE `students`
  MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT für Tabelle `subjects`
--
ALTER TABLE `subjects`
  MODIFY `subject_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT für Tabelle `teachers`
--
ALTER TABLE `teachers`
  MODIFY `teacher_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `assigngradestudent`
--
ALTER TABLE `assigngradestudent`
  ADD CONSTRAINT `assigngradestudent_ibfk_2` FOREIGN KEY (`fk_teacher_id`) REFERENCES `teachers` (`teacher_id`),
  ADD CONSTRAINT `assigngradestudent_ibfk_4` FOREIGN KEY (`fk_student_id`) REFERENCES `students` (`student_id`),
  ADD CONSTRAINT `assigngradestudent_ibfk_5` FOREIGN KEY (`fk_grade_id`) REFERENCES `grades` (`grade_id`);

--
-- Constraints der Tabelle `assignspecialgradestudent`
--
ALTER TABLE `assignspecialgradestudent`
  ADD CONSTRAINT `assignspecialgradestudent_ibfk_3` FOREIGN KEY (`fk_specialGrade_id`) REFERENCES `specialgrades` (`specialGrade_id`),
  ADD CONSTRAINT `assignspecialgradestudent_ibfk_4` FOREIGN KEY (`fk_student_id`) REFERENCES `students` (`student_id`),
  ADD CONSTRAINT `assignspecialgradestudent_ibfk_5` FOREIGN KEY (`fk_specialSubject_id`) REFERENCES `specialsubjects` (`specialSubject_id`);

--
-- Constraints der Tabelle `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `students_ibfk_1` FOREIGN KEY (`fk_class_id`) REFERENCES `classes` (`class_id`);

--
-- Constraints der Tabelle `subjectclass`
--
ALTER TABLE `subjectclass`
  ADD CONSTRAINT `subjectclass_ibfk_1` FOREIGN KEY (`fk_class_id`) REFERENCES `classes` (`class_id`),
  ADD CONSTRAINT `subjectclass_ibfk_3` FOREIGN KEY (`fk_class_id`) REFERENCES `classes` (`class_id`),
  ADD CONSTRAINT `subjectclass_ibfk_6` FOREIGN KEY (`fk_subject_id`) REFERENCES `subjects` (`subject_id`),
  ADD CONSTRAINT `subjectclass_ibfk_7` FOREIGN KEY (`fk_specialSubject_id`) REFERENCES `specialsubjects` (`specialSubject_id`);

--
-- Constraints der Tabelle `teacherclass`
--
ALTER TABLE `teacherclass`
  ADD CONSTRAINT `teacherclass_ibfk_1` FOREIGN KEY (`fk_teacher_id`) REFERENCES `teachers` (`teacher_id`),
  ADD CONSTRAINT `teacherclass_ibfk_2` FOREIGN KEY (`fk_classes_id`) REFERENCES `classes` (`class_id`);

--
-- Constraints der Tabelle `teachersubject`
--
ALTER TABLE `teachersubject`
  ADD CONSTRAINT `teachersubject_ibfk_1` FOREIGN KEY (`fk_teacher_id`) REFERENCES `teachers` (`teacher_id`),
  ADD CONSTRAINT `teachersubject_ibfk_2` FOREIGN KEY (`fk_subject_id`) REFERENCES `subjects` (`subject_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
