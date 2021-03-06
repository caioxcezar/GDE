DROP DATABASE IF EXISTS gde;
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
	salario_func decimal(15,2) not NULL,
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
	data_estoque date NOT NULL,
	quantidade_estoque int NOT NULL,
	pedido_estoque int NOT NULL,
	data_alteracao date NULL
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
	descricao_cat nvarchar(255) NOT NULL,
	data_cat Date 
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
	ADD CONSTRAINT fk_pprod_prod FOREIGN KEY (produto_pprod) REFERENCES produtos_tb(cod_prod);
ALTER TABLE estoque_tb
	ADD CONSTRAINT fk_estoque_produto FOREIGN KEY (produto_estoque) REFERENCES produtos_tb(cod_prod);
ALTER TABLE estoque_tb
	ADD CONSTRAINT fk_estoque_pedido FOREIGN KEY (pedido_estoque) REFERENCES pedidos_tb(cod_pedido);
ALTER TABLE pedidos_tb
	ADD CONSTRAINT fk_pedido_cli FOREIGN KEY (cliente_pedido) REFERENCES clientes_tb(cod_cli);
ALTER TABLE pedidos_tb
	ADD CONSTRAINT fk_pedido_func FOREIGN KEY (funcionario_pedido) REFERENCES funcionarios_tb(cod_func);

DROP USER IF EXISTS "gde"@"%";
CREATE USER "gde"@"%";
GRANT Alter ON gde.* TO "gde"@"%";
GRANT Create ON gde.* TO "gde"@"%";
GRANT Create view ON gde.* TO "gde"@"%";
GRANT Delete ON gde.* TO "gde"@"%";
GRANT Drop ON gde.* TO "gde"@"%";
GRANT Grant option ON gde.* TO "gde"@"%";
GRANT Index ON gde.* TO "gde"@"%";
GRANT Insert ON gde.* TO "gde"@"%";
GRANT References ON gde.* TO "gde"@"%";
GRANT Select ON gde.* TO "gde"@"%";
GRANT Show view ON gde.* TO "gde"@"%";
GRANT Trigger ON gde.* TO "gde"@"%";
GRANT Update ON gde.* TO "gde"@"%";
GRANT Alter routine ON gde.* TO "gde"@"%";
GRANT Create routine ON gde.* TO "gde"@"%";
GRANT Create temporary tables ON gde.* TO "gde"@"%";
GRANT Execute ON gde.* TO "gde"@"%";
GRANT Lock tables ON gde.* TO "gde"@"%";
FLUSH PRIVILEGES;

INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Acre", "AC");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Alagoas", "AL");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Amapá", "AP");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Amazonas", "AM");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Bahia ", "BA");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Ceará", "CE");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Distrito Federal ", "DF");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Espírito Santo", "ES");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Goias", "GO");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Maranhão", "MA");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Mato Grosso", "MT");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Mato Grosso do Sul", "MS");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Minas Gerais", "MG");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Pará", "PA");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Paraíba", "PB");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Paraná", "PR");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Pernambuco", "PE");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Piauí", "PI");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Rio de Janeiro", "RJ");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Rio Grande do Norte", "RN");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Rio Grande do Sul", "RS");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Rondônia", "RO");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Roraima", "RR");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Santa Catarina", "SC");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("São Paulo", "SP");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Sergipe", "SE");
INSERT INTO estados_tb (nome_estado, sigla_estado) VALUES ("Tocantins", "TO");


INSERT INTO cargos_tb (cod_cargo, nome_cargo, descricao_cargo) VALUES (1, "Vendedor", "Funcionario responsavel por fazer os pedidos.");
INSERT INTO cargos_tb (cod_cargo, nome_cargo, descricao_cargo) VALUES (2, "Caixa", "Funcionario responsavel por receber/pagar os pedidos.");

INSERT INTO clientes_tb
(cod_cli, nome_cli, telefone_cli, cnpj_cli, rua_cli, complemento_cli, numero_cli, bairro_cli, cidade_cli, estado_cli, cep_cli)
VALUES (1, "GDE", "(32) 3261-9856", "77.197.110/0001-10","Rua Izabel Correa de Souza", "Ap. 145", 2375,"São Pedro", "Juiz de Fora", "MG", "36037-050");

INSERT INTO clientes_tb
(cod_cli, nome_cli, telefone_cli, cnpj_cli, rua_cli, complemento_cli, numero_cli, bairro_cli, cidade_cli, estado_cli, cep_cli)
VALUES (2, "Luiz Design", "(32) 3261-9856", "77.197.110/0001-10","Rua Izabel Correa de Souza", "Ap. 145", 2375,"São Pedro", "Juiz de Fora", "MG", "36037-050");

INSERT INTO funcionarios_tb
(cod_func, nome_func, telefone_func, cpf_func, rua_func, complemento_func, numero_func, bairro_func, cidade_func, estado_func, cep_func, cargo_func, salario_func )
VALUES (1, "Helio", "(32) 3261-9856", "145.325.362-45","Rua Izabel Correa de Souza", "Ap. 145", 2375,"S�o Pedro", "Juiz de Fora", "MG", "36037-050", 1, 800.00);

INSERT INTO funcionarios_tb
(cod_func, nome_func, telefone_func, cpf_func, rua_func, complemento_func, numero_func, bairro_func, cidade_func, estado_func, cep_func, cargo_func, salario_func )
VALUES (2, "Phillipe", "(32) 3261-9856", "145.325.362-45","Rua Izabel Correa de Souza", "Ap. 145", 2375,"S�o Pedro", "Juiz de Fora", "MG", "36037-050", 2, 3000.00);

insert into categorias_tb (cod_cat,nome_cat,descricao_cat) 
values (1, "Eletronicos", "Descri��o dos eletronicos");

insert into produtos_tb (cod_prod, nome_prod, descricao_prod, categoria_prod, valor_produto) 
values (1, "HD 2TB", "Disco Rigido", 1, "234.71");

insert into produtos_tb (cod_prod, nome_prod, descricao_prod, categoria_prod, valor_produto) 
values (2, "HD 1TB", "Disco Rigido", 1, "201.35");
/*Para testes da classe pedido*/
INSERT INTO gde.pedidos_tb (cod_pedido,cliente_pedido,funcionario_pedido,estado_pedido,tipo_pedido,data_pedido) 
VALUES (1,1,1,'Pago','Interno','2020-04-25');

INSERT INTO gde.pedido_produtos_tb (pedido_pprod,produto_pprod,qtd_pprod) 
VALUES (1,1,2);

INSERT INTO gde.estoque_tb (cod_estoque,produto_estoque,data_estoque,quantidade_estoque,pedido_estoque,data_alteracao) 
VALUES (1,1,'2020-04-25',2,1,NULL);
/*Para testes da classe estoque*/
INSERT INTO gde.pedidos_tb (cod_pedido,cliente_pedido,funcionario_pedido,estado_pedido,tipo_pedido,data_pedido) 
VALUES (2,1,1,'Pendente','Interno','2020-04-25');

INSERT INTO gde.pedido_produtos_tb (pedido_pprod,produto_pprod,qtd_pprod) 
VALUES (2,2,2);

INSERT INTO gde.pedidos_tb (cod_pedido,cliente_pedido,funcionario_pedido,estado_pedido,tipo_pedido,data_pedido) 
VALUES (4,1,1,'Pendente','Interno','2020-04-25');

INSERT INTO gde.pedido_produtos_tb (pedido_pprod,produto_pprod,qtd_pprod) 
VALUES (4,2,2);
/*Para testes da classe pagamento*/
INSERT INTO gde.pedidos_tb (cod_pedido,cliente_pedido,funcionario_pedido,estado_pedido,tipo_pedido,data_pedido) 
VALUES (3,2,1,'Pendente','Externo','2020-04-25');

INSERT INTO gde.pedido_produtos_tb (pedido_pprod,produto_pprod,qtd_pprod) 
VALUES (3,1,2);