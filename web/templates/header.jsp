<%-- 
    Document   : header
    Created on : Sep 4, 2019, 12:24:33 AM
    Author     : ccezar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= request.getParameter("title")%></title>
        <link rel="stylesheet" type="text/css" media="screen" href="resources/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="resources/css/gde.css" />
        <script src="resources/js/jquery-3.4.1.min.js" ></script>
        <script src="resources/js/bootstrap.min.js" ></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark" id="headerBar">
            <a class="navbar-brand" href="index.jsp">GDE</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Cadastros
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="manterCliente?acao=prepararOperacao&operacao=incluir">Cadastrar Cliente</a>
                            <a class="dropdown-item" href="manterFuncionario?acao=prepararOperacao&operacao=incluir">Cadastrar Funcionario</a>
                            <a class="dropdown-item" href="manterProduto?acao=prepararOperacao&operacao=incluir">Cadastrar Produto</a>
                            <a class="dropdown-item" href="manterCategoria?acao=prepararOperacao&operacao=incluir">Cadastrar Categoria</a>
                            <a class="dropdown-item" href="manterCargo?acao=prepararOperacao&operacao=incluir">Cadastrar Cargo</a>
                        </div>
                    </li><li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Consultas
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="clientes">Consultar Cliente</a>
                            <a class="dropdown-item" href="funcionarios">Consultar Funcionario</a>
                            <a class="dropdown-item" href="produtos">Consultar Produto</a>
                            <a class="dropdown-item" href="estoque">Consultar Estoque</a>
                            <a class="dropdown-item" href="categorias">Consultar Categoria</a>
                            <a class="dropdown-item" href="cargos">Consultar Cargo</a>
                            <a class="dropdown-item" href="pedidos">Consultar Pedidos</a>
                            <a class="dropdown-item" href="notasFiscais">Consultar Notas-Fiscais</a>
                        </div>
                    </li><li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Pagamentos
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="manterPedido?acao=prepararOperacao&operacao=incluir">Emitir Pedido</a>
                            <a class="dropdown-item" href="manterPagamento?acao=prepararOperacao&operacao=incluir">Realizar Pagamento</a>
                            <a class="dropdown-item" href="manterEstoque?acao=prepararOperacao&operacao=incluir">Adicionar ao Estoque</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="conteudo">