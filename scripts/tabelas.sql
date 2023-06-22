SET SQL_SAFE_UPDATES = 0;
drop schema projetoVga;
create schema projetoVga;
use projetoVga;

CREATE TABLE `item` (
  `iditem` int NOT NULL AUTO_INCREMENT,
  `tamanho` char(2) NOT NULL,
  `cor` varchar(45) NOT NULL,
  `quantidade` int NOT NULL,
  `precoUnitario` double NOT NULL,
  `idproduto` int NOT NULL,
  `imagem` blob,
  PRIMARY KEY (`iditem`),
  KEY `idproduto_idx` (`idproduto`),
  CONSTRAINT `idproduto` FOREIGN KEY (`idproduto`) REFERENCES `produto` (`idproduto`)
);

CREATE TABLE `produto` (
  `idproduto` int NOT NULL AUTO_INCREMENT,
  `modelo` varchar(45) NOT NULL,
  `marca` varchar(45) NOT NULL,
  PRIMARY KEY (`idproduto`)
);


CREATE TABLE `usuario` (
  `idusuario` Integer NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `email` varchar(255) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `adm` boolean,
  PRIMARY KEY (`idusuario`)
);