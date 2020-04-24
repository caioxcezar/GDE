<%-- 
    Document   : header
    Created on : Sep 4, 2019, 12:24:33 AM
    Author     : ccezar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
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
                            <a class="dropdown-item" href="index?caminho=manterCliente">">Cadastrar Cliente</a>
                            <a class="dropdown-item" href="index?caminho=manterFuncionario">Cadastrar Funcionario</a>
                            <a class="dropdown-item" href="index?caminho=manterProduto">Cadastrar Produto</a>
                            <a class="dropdown-item" href="index?caminho=manterCategoriar">Cadastrar Categoria</a>
                            <a class="dropdown-item" href="index?caminho=manterCargo">Cadastrar Cargo</a>
                        </div>
                    </li><li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Consultas
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="index?caminho=clientes">Consultar Cliente</a>
                            <a class="dropdown-item" href="index?caminho=funcionarios">Consultar Funcionario</a>
                            <a class="dropdown-item" href="index?caminho=produtos">Consultar Produto</a>
                            <a class="dropdown-item" href="index?caminho=estoque">Consultar Estoque</a>
                            <a class="dropdown-item" href="index?caminho=categorias">Consultar Categoria</a>
                            <a class="dropdown-item" href="index?caminho=cargos">Consultar Cargo</a>
                            <a class="dropdown-item" href="index?caminho=pedidos">Consultar Pedidos</a>
                            <a class="dropdown-item" href="index?caminho=notasFiscais">Consultar Notas-Fiscais</a>
                        </div>
                    </li><li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Pagamentos
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="index?caminho=manterPedido">Emitir Pedido</a>
                            <a class="dropdown-item" href="index?caminho=manterPagamento">Realizar Pagamento</a>
                            <a class="dropdown-item" href="index?caminho=manterEstoque">Adicionar ao Estoque</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="conteudo">