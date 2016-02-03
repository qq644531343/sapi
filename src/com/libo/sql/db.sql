CREATE DATABASE IF NOT EXISTS infodb DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

CREATE TABLE `user` (
  `userId` varchar(24) DEFAULT "" primary key,
  `username` varchar(24) DEFAULT "" not NULL unique,
  `password` varchar(24) DEFAULT "" not NULL,
  `platform` varchar(8) DEFAULT "",
  `userNick` varchar(24) DEFAULT "",
  `userIcon` varchar(512) DEFAULT "",
  `userEmail` varchar(128) DEFAULT "",
  `userPhone` varchar(24) DEFAULT "",
  `userSex` varchar(4) DEFAULT ""
)