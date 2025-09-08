create sql

CREATE TABLE carriers (
	id BIGINT IDENTITY(1,1) PRIMARY KEY,
	carrier_code varchar(50) NOT NULL,
	reserved BIT DEFAULT 0 NULL,
	error BIT DEFAULT 0 NULL,
	distance int DEFAULT 0 NULL,
	created_at DATETIME2 DEFAULT GETDATE(),
);

CREATE TABLE equipments (
	id BIGINT IDENTITY(1,1) PRIMARY KEY,
	equipment_code varchar(50) NOT NULL,
	"location" varchar(50) NULL,
	status varchar(20) NULL,
	created_at DATETIME2 DEFAULT GETDATE(),
);