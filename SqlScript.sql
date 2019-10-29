CREATE DATABASE gde;
USE gde;
CREATE TABLE funcionarios_tb (
	cod_func int PRIMARY KEY,
	nome_func nvarchar(150) NOT NULL, 
	telefone_func nvarchar(20) NOT NULL, 
	cpf_func nvarchar(14) NOT NULL, 
	rua_func nvarchar(150), 
	numero_func int, 
	complemento_func nvarchar(50), 
	bairro_func nvarchar(50), 
	cep_func nvarchar(9), 
	estado_func char(2),
	cargo_func int,
    cidade_func nvarchar(100)
);
CREATE TABLE clientes_tb (
	cod_cli int PRIMARY KEY,
	nome_cli nvarchar(150) NOT NULL,
	telefone_cli nvarchar(20) NOT NULL,
	cnpj_cli nvarchar(18) NOT NULL,
	rua_cli nvarchar(150),
	complemento_cli nvarchar(100),
	numero_cli int,
	bairro_cli nvarchar(100),
	cidade_cli nvarchar(100),
	estado_cli char(2),
	cep_cli nvarchar(9)
);
CREATE TABLE pedidos_tb(
	cod_pedido int PRIMARY KEY,
	cliente_pedido int NOT NULL,
	funcionario_pedido int NOT NULL,
	estado_pedido nvarchar(50) NOT NULL,
	tipo_pedido nvarchar(50) NOT NULL,
	data_pedido date NOT NULL
);
CREATE TABLE pedido_produtos_tb (
	cod_pprod int PRIMARY KEY AUTO_INCREMENT,
	pedido_pprod int NULL,
	produto_pprod int NOT NULL,
	qtd_pprod int NOT NULL
);
CREATE TABLE estoque_tb (
	cod_estoque int PRIMARY KEY,
	produto_estoque int NOT NULL,
	quantidade_estoque int NOT NULL
);
CREATE TABLE notas_fiscais_tb (
	cod_nota int PRIMARY KEY,
	data_nota date NOT NULL,
	pedido_nota int NULL
);
CREATE TABLE cargos_tb (
	cod_cargo int PRIMARY KEY,
	nome_cargo nvarchar(75) NOT NULL,
	descricao_cargo nvarchar(150) NOT NULL
);
CREATE TABLE produtos_tb (
	cod_prod int PRIMARY KEY,
	nome_prod nvarchar(150) NOT NULL,
	descricao_prod nvarchar(255) NOT NULL, 
	categoria_prod int NULL,
	valor_produto float NOT NULL
);
CREATE TABLE categorias_tb (
	cod_cat int PRIMARY KEY,
	nome_cat nvarchar(150) NOT NULL,
	descricao_cat nvarchar(255) NOT NULL
);
CREATE TABLE estados_tb (
	sigla_estado CHAR(2) PRIMARY KEY,
	nome_estado nvarchar(45)
);
ALTER TABLE funcionarios_tb
	ADD CONSTRAINT fk_func_cargo FOREIGN KEY (cargo_func) REFERENCES cargos_tb(cod_cargo);
ALTER TABLE funcionarios_tb
	ADD CONSTRAINT fk_func_estado FOREIGN KEY (estado_func) REFERENCES estados_tb(sigla_estado);
ALTER TABLE clientes_tb
	ADD CONSTRAINT fk_cli_estado FOREIGN KEY (estado_cli) REFERENCES estados_tb(sigla_estado);
ALTER TABLE produtos_tb
	ADD CONSTRAINT fk_prod_cat FOREIGN KEY (categoria_prod) REFERENCES categorias_tb(cod_cat);
ALTER TABLE notas_fiscais_tb
	ADD CONSTRAINT fk_nota_pedido FOREIGN KEY (pedido_nota) REFERENCES pedidos_tb(cod_pedido);
ALTER TABLE pedido_produtos_tb
	ADD CONSTRAINT fk_pedido_ppdrod FOREIGN KEY (pedido_pprod) REFERENCES pedidos_tb(cod_pedido);
ALTER TABLE pedido_produtos_tb
	ADD CONSTRAINT fk_ppdrod_prod FOREIGN KEY (produto_ppdrod) REFERENCES produtos_tb(cod_prod);
ALTER TABLE estoque_tb
	ADD CONSTRAINT fk_estoque_produto FOREIGN KEY (produto_estoque) REFERENCES produtos_tb(cod_prod);
ALTER TABLE pedidos_tb
	ADD CONSTRAINT fk_pedido_cli FOREIGN KEY (cliente_pedido) REFERENCES clientes_tb(cod_cli);
ALTER TABLE pedidos_tb
	ADD CONSTRAINT fk_pedido_func FOREIGN KEY (funcionario_pedido) REFERENCES funcionarios_tb(cod_func);

CREATE USER 'gde'@'%';
GRANT Alter ON gde.* TO 'gde'@'%';
GRANT Create ON gde.* TO 'gde'@'%';
GRANT Create view ON gde.* TO 'gde'@'%';
GRANT Delete ON gde.* TO 'gde'@'%';
GRANT Delete history ON gde.* TO 'gde'@'%';
GRANT Drop ON gde.* TO 'gde'@'%';
GRANT Grant option ON gde.* TO 'gde'@'%';
GRANT Index ON gde.* TO 'gde'@'%';
GRANT Insert ON gde.* TO 'gde'@'%';
GRANT References ON gde.* TO 'gde'@'%';
GRANT Select ON gde.* TO 'gde'@'%';
GRANT Show view ON gde.* TO 'gde'@'%';
GRANT Trigger ON gde.* TO 'gde'@'%';
GRANT Update ON gde.* TO 'gde'@'%';
GRANT Alter routine ON gde.* TO 'gde'@'%';
GRANT Create routine ON gde.* TO 'gde'@'%';
GRANT Create temporary tables ON gde.* TO 'gde'@'%';
GRANT Execute ON gde.* TO 'gde'@'%';
GRANT Lock tables ON gde.* TO 'gde'@'%';
FLUSH PRIVILEGES;

insert into estados_tb (nome_estado, sigla_estado) values ('Acre', 'AC');
insert into estados_tb (nome_estado, sigla_estado) values ('Alagoas', 'AL');
insert into estados_tb (nome_estado, sigla_estado) values ('Amapá', 'AP');
insert into estados_tb (nome_estado, sigla_estado) values ('Amazonas', 'AM');
insert into estados_tb (nome_estado, sigla_estado) values ('Bahia ', 'BA');
insert into estados_tb (nome_estado, sigla_estado) values ('Ceará', 'CE');
insert into estados_tb (nome_estado, sigla_estado) values ('Distrito Federal ', 'DF');
insert into estados_tb (nome_estado, sigla_estado) values ('Espírito Santo', 'ES');
insert into estados_tb (nome_estado, sigla_estado) values ('Goiás', 'GO');
insert into estados_tb (nome_estado, sigla_estado) values ('Maranhão', 'MA');
insert into estados_tb (nome_estado, sigla_estado) values ('Mato Grosso', 'MT');
insert into estados_tb (nome_estado, sigla_estado) values ('Mato Grosso do Sul', 'MS');
insert into estados_tb (nome_estado, sigla_estado) values ('Minas Gerais', 'MG');
insert into estados_tb (nome_estado, sigla_estado) values ('Pará', 'PA');
insert into estados_tb (nome_estado, sigla_estado) values ('Paraíba', 'PB');
insert into estados_tb (nome_estado, sigla_estado) values ('Paraná', 'PR');
insert into estados_tb (nome_estado, sigla_estado) values ('Pernambuco', 'PE');
insert into estados_tb (nome_estado, sigla_estado) values ('Piauí', 'PI');
insert into estados_tb (nome_estado, sigla_estado) values ('Rio de Janeiro', 'RJ');
insert into estados_tb (nome_estado, sigla_estado) values ('Rio Grande do Norte', 'RN');
insert into estados_tb (nome_estado, sigla_estado) values ('Rio Grande do Sul', 'RS');
insert into estados_tb (nome_estado, sigla_estado) values ('Rondônia', 'RO');
insert into estados_tb (nome_estado, sigla_estado) values ('Roraima', 'RR');
insert into estados_tb (nome_estado, sigla_estado) values ('Santa Catarina', 'SC');
insert into estados_tb (nome_estado, sigla_estado) values ('São Paulo', 'SP');
insert into estados_tb (nome_estado, sigla_estado) values ('Sergipe', 'SE');
insert into estados_tb (nome_estado, sigla_estado) values ('Tocantins', 'TO');