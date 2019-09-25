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
	cargo_func int
);
CREATE TABLE estoque_tb (
	cod_estoque int PRIMARY KEY,
	produto_estoque int NOT NULL,
	quantidade_estoque int NOT NULL
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
	cep_cli nvarchar(9)
);
CREATE TABLE vendas_tb(
	cod_venda int PRIMARY KEY,
	data_venda date NOT NULL,
	cliente_venda int NOT NULL,
	pedido_venda int NOT NULL,
	funcionario_venda int NOT NULL,
	pago boolean NOT NULL,
	total_venda float NOT NULL
);
CREATE TABLE pedidos_tb(
	cod_pedido int PRIMARY KEY,
	cliente_pedido int NOT NULL,
	funcionario_pedido int NOT NULL,
	estado_pedido nvarchar(50) NOT NULL,
	tipo_pedido nvarchar(50) NOT NULL
);
CREATE TABLE pedidos_produtos_tb (
	cod_pprod int PRIMARY KEY,
	pedido_pprod int NOT NULL,
	produto_ppdrod int NOT NULL,
	qtd_pprod int NOT NULL
);
CREATE TABLE notas_fiscais_tb (
	cod_nota int PRIMARY KEY,
	data_nota date NOT NULL,
	venda_nota int NOT NULL
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
	categoria_prod int NOT NULL,
	valor_produto float NOT NULL
);
CREATE TABLE categorias_tb (
	cod_cat int PRIMARY KEY,
	nome_cat nvarchar(150) NOT NULL,
	descricao_cat nvarchar(255) NOT NULL
);
ALTER TABLE funcionarios_tb
	ADD CONSTRAINT fk_func_cargo FOREIGN KEY (cargo_func) REFERENCES cargos_tb(cod_cargo);
ALTER TABLE produtos_tb
	ADD CONSTRAINT fk_prod_cat FOREIGN KEY (categoria_prod) REFERENCES categorias_tb(cod_cat);
ALTER TABLE notas_fiscais_tb
	ADD CONSTRAINT fk_nota_venda FOREIGN KEY (venda_nota) REFERENCES vendas_tb(cod_venda);
ALTER TABLE vendas_tb 
	ADD CONSTRAINT fk_venda_pedido FOREIGN KEY (pedido_venda) REFERENCES pedidos_tb(cod_pedido);
ALTER TABLE vendas_tb 
	ADD CONSTRAINT fk_venda_cliente FOREIGN KEY (cliente_venda) REFERENCES clientes_tb(cod_cli);
ALTER TABLE vendas_tb 
	ADD CONSTRAINT fk_venda_funcionario FOREIGN KEY (funcionario_venda) REFERENCES funcionarios_tb(cod_func);
ALTER TABLE pedidos_produtos_tb
	ADD CONSTRAINT fk_pedido_ppdrod FOREIGN KEY (pedido_pprod) REFERENCES pedidos_tb(cod_pedido);

