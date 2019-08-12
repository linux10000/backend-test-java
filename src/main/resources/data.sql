DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
  usonid INT AUTO_INCREMENT  PRIMARY KEY,
  usocnome VARCHAR(250) NOT NULL,
  usoclogin VARCHAR(100) NOT NULL,
  usocsenha VARCHAR(50) NOT NULL,
);

DROP TABLE IF EXISTS empresa;

CREATE TABLE empresa (
  empnid INT AUTO_INCREMENT  PRIMARY KEY,
  empcnome VARCHAR(250) NOT NULL,
  empccnpj VARCHAR(20) NOT NULL,
  empcendereco VARCHAR(500) NOT NULL,
  empctelefone VARCHAR(20) not null,
  empnvagacarros int not null default 0,
  empnvagamotos int not null default 0
);

DROP TABLE IF EXISTS veiculo;

CREATE TABLE veiculo (
  veinid INT AUTO_INCREMENT  PRIMARY KEY,
  veicmarca VARCHAR(100) NOT NULL,
  veicmodelo VARCHAR(100) NOT NULL,
  veiccor VARCHAR(20) not null,
  veicplaca VARCHAR(20) not null,
  veintipo int not null
);

DROP TABLE IF EXISTS mov_veiculo;

CREATE TABLE mov_veiculo (
  movnid INT AUTO_INCREMENT  PRIMARY KEY,
  movnvei int NOT NULL,
  movnemp int NOT NULL,
  movdentrada timestamp not null,
  movdsaida timestamp,
  foreign key (movnvei) references veiculo(veinid),
  foreign key (movnemp) references empresa(empnid),
);

insert into usuario (usocnome, usoclogin, usocsenha) values ('Maria da Silva', 'xuxa', '123');
