# Host: localhost  (Version: 5.7.25)
# Date: 2020-03-11 21:07:37
# Generator: MySQL-Front 5.3  (Build 4.269)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "choose"
#

DROP TABLE IF EXISTS `choose`;
CREATE TABLE `choose` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `question` text,
  `responseA` varchar(255) DEFAULT NULL,
  `responseB` varchar(255) DEFAULT NULL,
  `responseC` varchar(255) DEFAULT NULL,
  `responseD` varchar(255) DEFAULT NULL,
  `response` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='选择题题库表';

#
# Data for table "choose"
#


#
# Structure for table "exam"
#

DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '考试名称',
  `time` int(11) DEFAULT '0' COMMENT '考试日期',
  `date` datetime DEFAULT NULL,
  `course` varchar(255) DEFAULT NULL COMMENT '考试科目',
  `pageId` int(11) DEFAULT NULL COMMENT '试卷ID',
  `status` int(11) DEFAULT NULL COMMENT '1未开始/2已结束/3进行中',
  `number` varchar(255) DEFAULT NULL COMMENT '考试编号',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='考试表';

#
# Data for table "exam"
#

INSERT INTO `exam` VALUES (1,'计算机基础',120,'2020-01-03 18:00:00','操作系统',1,1,'161000001'),(2,'语言基础',120,'2020-03-15 19:00:00','JAVA',2,1,'161000002'),(3,'大学基本课程',120,'2020-03-15 19:00:00','英语四级',3,1,'161000003');

#
# Structure for table "judge"
#

DROP TABLE IF EXISTS `judge`;
CREATE TABLE `judge` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `question` text,
  `response` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='判断题信息';

#
# Data for table "judge"
#


#
# Structure for table "page"
#

DROP TABLE IF EXISTS `page`;
CREATE TABLE `page` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `pageinfo` text,
  `pagename` varchar(255) DEFAULT NULL,
  `pagenum` varchar(255) DEFAULT '' COMMENT '试卷编号',
  `choosenum` int(11) DEFAULT NULL,
  `judgenum` int(11) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='试卷信息表';

#
# Data for table "page"
#


#
# Structure for table "pagedetail"
#

DROP TABLE IF EXISTS `pagedetail`;
CREATE TABLE `pagedetail` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `pagenum` varchar(255) DEFAULT '',
  `type` int(11) DEFAULT NULL,
  `questionId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "pagedetail"
#


#
# Structure for table "pageques"
#

DROP TABLE IF EXISTS `pageques`;
CREATE TABLE `pageques` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `pageId` int(11) NOT NULL DEFAULT '0',
  `questionId` int(11) NOT NULL DEFAULT '0',
  `type` int(11) DEFAULT NULL COMMENT '1选择题/2判断题',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='试卷选择题中间表';

#
# Data for table "pageques"
#


#
# Structure for table "specialty"
#

DROP TABLE IF EXISTS `specialty`;
CREATE TABLE `specialty` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `college` varchar(255) DEFAULT NULL COMMENT '学院',
  `major` varchar(255) DEFAULT NULL COMMENT '专业',
  `grade` varchar(255) DEFAULT NULL COMMENT '年级',
  `classname` varchar(255) DEFAULT NULL COMMENT '班级',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='专业信息表';

#
# Data for table "specialty"
#

INSERT INTO `specialty` VALUES (1,'计算机学院','软件工程','16级','软件3班'),(2,'计算机学院','软件工程','16级','软件3班'),(3,'计算机学院','软件工程','16级','软件3班'),(4,'计算机学院','软件工程','16级','软件3班'),(5,'计算机学院','软件工程','16级','软件3班'),(6,'计算机学院','软件工程','16级','软件3班'),(7,'计算机学院','软件工程','16级','软件3班'),(8,'计算机学院','软件工程','16级','软件3班'),(9,'计算机学院','软件工程','16级','软件3班'),(10,'计算机学院','软件工程','16级','软件3班'),(11,'计算机学院','软件工程','16级','软件3班'),(12,'计算机学院','软件工程','16级','软件3班'),(13,'计算机学院','软件工程','16级','软件3班'),(14,'计算机学院','软件工程','16级','软件3班'),(15,'计算机学院','软件工程','16级','软件3班'),(16,'计算机学院','软件工程','16级','软件1班');

#
# Structure for table "student"
#

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `specialtyId` int(11) DEFAULT NULL,
  `role` varchar(255) DEFAULT 'student',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "student"
#

INSERT INTO `student` VALUES (1,'1610300204','b7797cce01b4b131b433b6acf4add449','huang','男','1a2b3c',NULL,1,'student');

#
# Structure for table "teacher"
#

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(11) NOT NULL DEFAULT '0',
  `password` varchar(255) NOT NULL DEFAULT '',
  `name` varchar(20) NOT NULL DEFAULT '',
  `age` int(3) DEFAULT NULL,
  `college` varchar(255) DEFAULT NULL COMMENT '学院名称',
  `title` varchar(255) DEFAULT NULL,
  `salt` varchar(20) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT 'teacher',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='老师表';

#
# Data for table "teacher"
#

INSERT INTO `teacher` VALUES (1,'1610300204','b7797cce01b4b131b433b6acf4add449','tiao',NULL,'sss','sss','1a2b3c',NULL,'teacher');

#
# Structure for table "userexam"
#

DROP TABLE IF EXISTS `userexam`;
CREATE TABLE `userexam` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) NOT NULL DEFAULT '0',
  `exam` int(11) NOT NULL DEFAULT '0',
  `usertype` tinyint(3) DEFAULT NULL COMMENT '1学生/2老师',
  `score` int(11) DEFAULT NULL COMMENT '成绩',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='学生考试中间表';

#
# Data for table "userexam"
#

INSERT INTO `userexam` VALUES (1,1,1,1,18),(2,1,2,2,14),(3,1,3,1,54);
