﻿USE STUDENTMANAGER
GO

--CREATE DATABASE STUDENTMANAGER

CREATE TABLE STUDENTS
(
	Id   NVARCHAR (32)              NOT NULL,
	Name NVARCHAR (32)     NOT NULL,
	Gender NVARCHAR (10),
	Address  NVARCHAR (50) NOT NULL,
	ClassId NVARCHAR(32) NOT NULL,
	BirthYear NCHAR(4) NOT NULL,
	GPA FLOAT NOT NULL,
	PRIMARY KEY (id)
)
ALTER TABLE STUDENTS ADD FOREIGN KEY (ClassId) REFERENCES CLASSES(ClassId)

CREATE TABLE CLASSES
(
	ClassId   NVARCHAR(32) NOT NULL,
	ClassName NVARCHAR (50) NOT NULL,
	PRIMARY KEY (ClassId)
)

INSERT INTO CLASSES VALUES (N'CNTT', N'Công Nghệ Thông Tin')
INSERT INTO CLASSES VALUES (N'KTVT',N'Kỹ thuật viễn thông') 
INSERT INTO CLASSES VALUES (N'KTE',N'Kinh Tế')
INSERT INTO CLASSES VALUES (N'KET',N'Kế Toán')
INSERT INTO CLASSES VALUES (N'DIDITU',N'Điện - Điện Tử')
INSERT INTO CLASSES VALUES (N'CTXD',N'Công Trình Xây Dựng')
INSERT INTO CLASSES VALUES (N'KCXD',N'Kết Cấu Xây Dựng')
INSERT INTO CLASSES VALUES (N'CKOT',N'Cơ Khí Ôtô')
INSERT INTO CLASSES VALUES (N'GTCT',N'Giao Thông Công Trình')
INSERT INTO CLASSES VALUES (N'NDIEN',N'Nhiệt Điện')
INSERT INTO CLASSES VALUES (N'KYTD',N'Kỹ Thuật Điện')
INSERT INTO CLASSES VALUES (N'QTKD',N'Quản trị kinh doanh')
INSERT INTO CLASSES VALUES (N'KTTHGT',N'Kế toán TH và GT')
INSERT INTO CLASSES VALUES (N'KCXD',N'Kết cấu xây dựng')
INSERT INTO CLASSES VALUES (N'TDHOA',N'Tự động hoá')
INSERT INTO CLASSES VALUES (N'KTXDCTGT',N'Kinh tế XD công trình GT')
INSERT INTO CLASSES VALUES (N'KTVTDL',N'Kinh tế vận tải du lịch')
INSERT INTO CLASSES VALUES (N'DKTCTGT',N'Địa kỹ thuật công trình GT')
INSERT INTO CLASSES VALUES (N'KTXDCH',N'Kỹ thuật xây dựng Cầu hầm')
INSERT INTO CLASSES VALUES (N'CKGTCC',N'Cơ khí giao thông công chính')
INSERT INTO CLASSES VALUES (N'QTLOG',N'Quản trị Logistics')
INSERT INTO CLASSES VALUES (N'KTBCVT',N'Kinh tế bưu chính viễn thông')
INSERT INTO CLASSES VALUES (N'QTDNXD',N'Quản trị doanh nghiệp XD')

INSERT INTO STUDENTS VALUES ('111', N'to be deleted' , N'Nam', N'Hải Dương', 'CNTT', 2000, 3.9)
INSERT INTO STUDENTS VALUES ('112', N'Tôn Thất Học' , N'Nam', N'Huế', 'KET', 2000, 6.5)
INSERT INTO STUDENTS VALUES ('113', N'Tôn Thất Đức' , N'Nam', N'Huế', 'KET', 2000, 7.5)
INSERT INTO STUDENTS VALUES ('114', N'Tôn Thất Thủ' , N'Nam', N'Huế', 'KET', 2000, 8.5)
INSERT INTO STUDENTS VALUES ('5951071059', N'Lê Huỳnh Minh' , N'Nam', N'P.Hiệp Thành, Q.12, TPHCM', 'CNTT', 2000, 6.9)	

ALTER TABLE STUDENTS 
alter column Id nvarchar(20) NOT NULL
go

ALTER TABLE STUDENTS 
add primary key (Id)
go

INSERT INTO STUDENTS VALUES ('', N'' , N'', N'', 'CNTT', 0, 0)	