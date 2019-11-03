<%-- 
    Document   : header
    Created on : Sep 4, 2019, 12:20:13 AM
    Author     : ccezar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="index"/>
</jsp:include>
<div class="container">
    <h3>Cadastros</h3>
    <div class="list-group">
        <a class="list-group-item list-group-item-action" href="manterCliente?acao=prepararOperacao&operacao=incluir">Cadastrar Cliente</a>
        <a class="list-group-item list-group-item-action" href="manterFuncionario?acao=prepararOperacao&operacao=incluir">Cadastrar Funcionario</a>
        <a class="list-group-item list-group-item-action" href="manterProduto?acao=prepararOperacao&operacao=incluir">Cadastrar Produto</a>
        <a class="list-group-item list-group-item-action" href="manterCategoria?acao=prepararOperacao&operacao=incluir">Cadastrar Categoria</a>
        <a class="list-group-item list-group-item-action" href="manterCargo?acao=prepararOperacao&operacao=incluir">Cadastrar Cargo</a>
    </div>
    <hr>
    <h3>Consultas</h3>
    <div class="list-group">
        <a class="list-group-item list-group-item-action" href="clientes">Consultar Cliente</a>
        <a class="list-group-item list-group-item-action" href="funcionarios">Consultar Funcionario</a>
        <a class="list-group-item list-group-item-action" href="produtos">Consultar Produto</a>
        <a class="list-group-item list-group-item-action" href="estoque">Consultar Estoque</a>
        <a class="list-group-item list-group-item-action" href="categorias">Consultar Categoria</a>
        <a class="list-group-item list-group-item-action" href="cargos">Consultar Cargo</a>
        <a class="list-group-item list-group-item-action" href="pedidos">Consultar Pedidos</a>
        <a class="list-group-item list-group-item-action" href="notasFiscais">Consultar Notas-Fiscais</a>
    </div>
    <hr>
    <h3>Transações</h3>
    <div class="list-group">
        <a class="list-group-item list-group-item-action" href="manterPedido?acao=prepararOperacao&operacao=incluir">Emitir Pedido</a>
        <a class="list-group-item list-group-item-action" href="manterPagamento?acao=prepararOperacao&operacao=incluir">Realizar Pagamento</a>
        <a class="list-group-item list-group-item-action" href="manterEstoque?acao=prepararOperacao&operacao=incluir">Adicionar ao Estoque</a>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>