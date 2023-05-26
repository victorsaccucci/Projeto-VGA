CREATE TABLE `item` (
  `iditem` int NOT NULL AUTO_INCREMENT,
  `tamanho` char(2) NOT NULL,
  `cor` varchar(45) NOT NULL,
  `quantidade` int NOT NULL,
  `preco_unitario` float NOT NULL,
  `idproduto` int NOT NULL,
  PRIMARY KEY (`iditem`),
  KEY `idproduto_idx` (`idproduto`),
  CONSTRAINT `idproduto` FOREIGN KEY (`idproduto`) REFERENCES `produto` (`idproduto`)
) 


CREATE TABLE `produto` (
  `idproduto` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) NOT NULL,
  `marca` varchar(45) NOT NULL,
  PRIMARY KEY (`idproduto`)
)


CREATE TABLE `usuario` (
  `idusuario` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `email` int NOT NULL,
  `cpf` varchar(11) NOT NULL,
  PRIMARY KEY (`idusuario`)
) 

CREATE TABLE `venda` (
  `idvenda` int NOT NULL,
  `data_venda` varchar(45) NOT NULL,
  `iditem` int NOT NULL,
  `idusuario` int NOT NULL,
  PRIMARY KEY (`idvenda`),
  KEY `iditem_idx` (`iditem`),
  KEY `idusuario_idx` (`idusuario`),
  CONSTRAINT `iditem` FOREIGN KEY (`iditem`) REFERENCES `item` (`iditem`),
  CONSTRAINT `idusuario` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`)
);