create table `exam` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(20) NOT NULL COMMENT '姓名',
 `happenTime` varchar(20) NOT NULL COMMENT '考试进行时间',
 `del` tinyint(1) DEFAULT 0 COMMENT '是否删除',
 `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
 `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`),
 KEY `idx_name` (`name`),
 KEY `idx_del` (`del`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT '考试表';

create table `grade` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `examId` int(11) NOT NULL COMMENT '考试id',
 `subjectId` int(11) NOT NULL COMMENT '科目id',
 `studentId` int(11) NOT NULL COMMENT '学生id',
 `classId` int(11) NOT NULL COMMENT '班级id',
 `grade` double NOT NULL COMMENT '分数',
 PRIMARY KEY (`id`),
 KEY `idx_examId` (`examId`),
 KEY `idx_subjectId` (`subjectId`),
 KEY `idx_classId` (`classId`),
 KEY `idx_grade` (`grade`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT '学生表';
