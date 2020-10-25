create table `user` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `account` varchar(20) NOT NULL COMMENT '账号',
 `name` varchar(20) NOT NULL COMMENT '姓名',
 `avatar`   varchar(255) NULL COMMENT '头像',
 `gender`   tinyint(1) DEFAULT 0 COMMENT '性别',
 `phone`    varchar(20) DEFAULT NULL COMMENT '手机',
 `password` varchar(255) NOT NULL COMMENT '加密后的密码',
 `del` tinyint(1) DEFAULT 0 COMMENT '是否删除',
 `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
 `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_account` (`account`),
  KEY `idx_del` (`del`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT '用户';

create table `role` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `role` varchar(20) NOT NULL COMMENT '角色名',
 PRIMARY KEY (`id`),
 KEY `idx_role` (`role`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT '角色';

create table `user_role` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `userId` int(11) NOT NULL COMMENT '用户id',
 `roleId` int(11) NOT NULL COMMENT '角色id',
 PRIMARY KEY (`id`),
 KEY `idx_userId` (`userId`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT '用户角色';

create table `school` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(50) NOT NULL COMMENT '校名',
 `city` varchar(20) NOT NULL COMMENT '城市',
 `del` tinyint(1) DEFAULT 0 COMMENT '是否删除',
 `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
 `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`),
 KEY `idx_name` (`name`),
 KEY `idx_del` (`del`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT '学校';

create table `class` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(20) NOT NULL COMMENT '班级名',
 `del` tinyint(1) DEFAULT 0 COMMENT '是否删除',
 `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
 `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`),
 KEY `idx_name` (`name`),
 KEY `idx_del` (`del`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT '班级';

create table `subject` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(20) NOT NULL COMMENT '科目名',
 `del` tinyint(1) DEFAULT 0 COMMENT '是否删除',
 `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
 `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`),
 KEY `idx_name` (`name`),
 KEY `idx_del` (`del`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT '科目';

create table `teacher` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `schoolId` int(11) NOT NULL COMMENT '学校id',
 `userId` int(11) NULL COMMENT '用户id, if is a user',
 `name` varchar(20) NOT NULL COMMENT '姓名',
 `gender` tinyint(1) DEFAULT 0 COMMENT '性别',
 `subjectId` int(11) NULL COMMENT '科目id',
 `del` tinyint(1) DEFAULT 0 COMMENT '是否删除',
 `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
 `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`),
 KEY `idx_name` (`name`),
 KEY `idx_del` (`del`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT '老师';

create table `teacher_class_map` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `teacherId` int(11) NOT NULL COMMENT '老师id',
 `classId` int(11) NOT NULL COMMENT '班级id',
 PRIMARY KEY (`id`),
 KEY `idx_teacherId` (`teacherId`),
 KEY `idx_classId` (`classId`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT '老师班级对应表';

create table `student` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(20) NOT NULL COMMENT '姓名',
 `gender` tinyint(1) DEFAULT 0 COMMENT '性别',
 `code`    varchar(20) DEFAULT NULL COMMENT '学号/编号',
 `classId` int(11) NOT NULL COMMENT '班级id',
 `del` tinyint(1) DEFAULT 0 COMMENT '是否删除',
 `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
 `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`),
 KEY `idx_name` (`name`),
 KEY `idx_classId` (`classId`),
 KEY `idx_del` (`del`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT '学生表';
