-- CREACION DE LA BD
-- eliminamos la base de datos si existe
DROP DATABASE IF EXISTS bdrapidosyfuriosos20262;
-- creamos la base de datos 
CREATE DATABASE bdrapidosyfuriosos20262 CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
-- seleccionamos la base de datos
USE bdrapidosyfuriosos20262;

-- tablas simples
CREATE TABLE distrito(
    coddis INT PRIMARY KEY AUTO_INCREMENT,
    nomdis VARCHAR(50) NOT NULL UNIQUE,
    estdis BIT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sexo(
    codsex INT PRIMARY KEY AUTO_INCREMENT,
    nomsex VARCHAR(20) NOT NULL UNIQUE,
    estsex BIT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE tipodocumento(
    codtipd INT PRIMARY KEY AUTO_INCREMENT,
    nomtipd VARCHAR(30) NOT NULL UNIQUE,
    esttipd BIT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE estadocivil(
    codestc INT PRIMARY KEY AUTO_INCREMENT,
    nomestc VARCHAR(30) NOT NULL UNIQUE,
    estestc BIT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE marca (
    codmar INT AUTO_INCREMENT PRIMARY KEY,
    nommar VARCHAR(50) NOT NULL UNIQUE,
    estmar BIT NOT NULL DEFAULT 1
) ENGINE=InnoDB;

CREATE TABLE color(
    codcol INT AUTO_INCREMENT PRIMARY KEY,
    nomcol VARCHAR(30) NOT NULL UNIQUE,
    estcol BIT NOT NULL DEFAULT 1
) ENGINE=InnoDB;

CREATE TABLE rol(
    codrol INT PRIMARY KEY AUTO_INCREMENT,
    nomrol VARCHAR(40) NOT NULL UNIQUE,
    estrol BIT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE servicio (
    codser INT AUTO_INCREMENT PRIMARY KEY,
    nomser VARCHAR(50) NOT NULL UNIQUE,
    desser VARCHAR(300) NOT NULL,
    preser DECIMAL(10,2) NOT NULL,
    estser BIT NOT NULL DEFAULT 1
) ENGINE=InnoDB;

-- tablas cruzadas
CREATE TABLE cliente(
    codcli INT PRIMARY KEY AUTO_INCREMENT,
    nomcli VARCHAR(40) NOT NULL,
    apepcli VARCHAR(40) NOT NULL,
    apemcli VARCHAR(40) NOT NULL,
    doccli VARCHAR(20) NOT NULL UNIQUE,
    feccli DATE NOT NULL,
    naccli varchar(50) NOT NULL,
    dircli VARCHAR(300) NOT NULL,
    telcli VARCHAR(15) NULL,
    celcli VARCHAR(15) NOT NULL UNIQUE,
    corcli VARCHAR(100) NULL UNIQUE,
    estcli BIT NOT NULL,
    coddis INT NOT NULL,
    codsex INT NOT NULL,
    codtipd INT NOT NULL,
    FOREIGN KEY (coddis) REFERENCES distrito(coddis),
    FOREIGN KEY (codsex) REFERENCES sexo(codsex),
    FOREIGN KEY (codtipd) REFERENCES tipodocumento(codtipd)
) ENGINE=InnoDB;

CREATE TABLE empleado(
    codemp INT PRIMARY KEY AUTO_INCREMENT,
    nomemp VARCHAR(40) NOT NULL,
    apepemp VARCHAR(40) NOT NULL,
    apememp VARCHAR(40) NOT NULL,
    docemp VARCHAR(20) NOT NULL UNIQUE,
    fecemp DATE NOT NULL,
    nacemp VARCHAR(50) NOT NULL,
    diremp VARCHAR(300) NOT NULL,
    telemp VARCHAR(15) NOT NULL,
    celemp VARCHAR(15) NOT NULL UNIQUE,
    coremp VARCHAR(100) NOT NULL UNIQUE,
    usuemp VARCHAR(40) NOT NULL UNIQUE,
    claemp VARCHAR(255) NOT NULL,
    sueemp DECIMAL(10,2) NOT NULL,
    estemp BIT NOT NULL,
    coddis INT NOT NULL,
    codrol INT NOT NULL,
    codsex INT NOT NULL,
    codtipd INT NOT NULL,
    codestc INT NOT NULL,
    FOREIGN KEY (coddis) REFERENCES distrito(coddis),
    FOREIGN KEY (codrol) REFERENCES rol(codrol),
    FOREIGN KEY (codsex) REFERENCES sexo(codsex),
    FOREIGN KEY (codtipd) REFERENCES tipodocumento(codtipd),
    FOREIGN KEY (codestc) REFERENCES estadocivil(codestc)
) ENGINE=InnoDB;

CREATE TABLE vehiculo (
    codveh INT AUTO_INCREMENT PRIMARY KEY,
    plaveh VARCHAR(20) NOT NULL UNIQUE,
    modveh VARCHAR(50) NOT NULL,
    anioveh INT NOT NULL,
    codmar INT NOT NULL,
    codcol INT NOT NULL,
    codcli INT NOT NULL,
    estveh BIT NOT NULL,
    FOREIGN KEY (codmar) REFERENCES marca(codmar),
    FOREIGN KEY (codcol) REFERENCES color(codcol),
    FOREIGN KEY (codcli) REFERENCES cliente(codcli)
) ENGINE=InnoDB;

create table log_sistema(
  idlog int primary key auto_increment,
  codemp int not null,
  accion varchar(100) not null,
  tabla_afectada varchar(40) not null,
  fecha datetime not null,
  foreign key (codemp) references empleado(codemp)
)engine=InnoDB;

-- Maestro - Detalle
CREATE TABLE historialvehiculo (
    numhis INT AUTO_INCREMENT PRIMARY KEY,
    codveh INT NOT NULL,
    fecing DATE NOT NULL,
    obshis VARCHAR(300) NOT NULL,
    codemp INT NOT NULL,
    esthis BIT NOT NULL DEFAULT 1,
    FOREIGN KEY (codveh) REFERENCES vehiculo(codveh),
    FOREIGN KEY (codemp) REFERENCES empleado(codemp)
) ENGINE=InnoDB;

CREATE TABLE detallehistorial (
    numdethis INT AUTO_INCREMENT PRIMARY KEY,
    numhis INT NOT NULL,
    codser INT NOT NULL,
    predet DECIMAL(10,2) NOT NULL,
    candet INT NOT NULL,
    FOREIGN KEY (numhis) REFERENCES historialvehiculo(numhis),
    FOREIGN KEY (codser) REFERENCES servicio(codser)
) ENGINE=InnoDB;
