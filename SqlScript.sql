create database gde;
use gde;
create table funcionarios_tb (
	cod_func int AUTO_INCREMENT PRIMARY KEY,
	nome_func nvarchar(150) not null, 
	telefone_func nvarchar(17) not null, 
	cpf_func int not null, 
	rua_func nvarchar(150), 
	numero_func int, 
	complemento_func nvarchar(50), 
	bairro_func nvarchar(50), 
	cep_func int, 
	cargo_func int
);
create table cliente_tb (
	cod_cli int AUTO_INCREMENT PRIMARY KEY,
	nome_cli nvarchar(150) not null,
	telefone_cli nvarchar(17) not null,
	cnpj_cli int not null,
	rua_cli nvarchar(150),
	complemento_cli nvarchar(100),
	numero_cli int,
	bairro_cli nvarchar(100),
	cidade_cli nvarchar(100),
	cep_cli int
);
CREATE TABLE vendas_tb(
	cod_venda int auto_increment primery KEY,
	data_venda date NOT NULL,
	cliente_venda int NOT NULL,
	funcionario_venda int NOT NULL,
	pago boolean NOT null
);
CREATE TABLE pedidos_tb(
	cod_pedido int auto_increment primery KEY,
);
CREATE TABLE notas_fiscais(
	
);
create table cargos_tb (
	cod_cargo int AUTO_INCREMENT PRIMARY KEY,
	nome_cargo nvarchar(75) not null,
	descricao_cargo nvarchar(150) not null
);
create table produtos_tb (
	cod_prod int AUTO_INCREMENT PRIMARY KEY,
	nome_prod nvarchar(150) not null,
	decricao_prod nvarchar(255) not null, 
	categoria_prod int not null
);
create table categorias_tb (
	cod_cat int AUTO_INCREMENT PRIMARY KEY,
	nome_cat nvarchar(150) NOT NULL,
	descricao_cat nvarchar(255) NOT NULL
);
ALTER TABLE funcionarios_tb
	ADD CONSTRAINT fk_func_cargo FOREIGN KEY (cod_cargo) REFERENCES cargos_tb(cod_cargo);
ALTER TABLE produtos_tb
	ADD CONSTRAINT fk_prod_cat FOREIGN KEY (categoria_prod) REFERENCES categorias_tb(cod_cat);
	

