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
        <!--<a class="list-group-item list-group-item-action" href="manterCliente?acao=prepararOperacao&operacao=incluir">Cadastrar Cliente</a>-->
        <a class="list-group-item list-group-item-action" href="index?caminho=manterCliente">Cadastrar Cliente</a>     
        <a class="list-group-item list-group-item-action" href="index?caminho=manterFuncionario">Cadastrar Funcionario</a>
        <a class="list-group-item list-group-item-action" href="index?caminho=manterProduto">Cadastrar Produto</a>
        <a class="list-group-item list-group-item-action" href="index?caminho=manterCategoria">Cadastrar Categoria</a>
        <a class="list-group-item list-group-item-action" href="index?caminho=manterCargo">Cadastrar Cargo</a>
    </div>
    <hr>
    <h3>Consultas</h3>
    <div class="list-group">
        <a class="list-group-item list-group-item-action" href="index?caminho=clientes">Consultar Cliente</a>
        <a class="list-group-item list-group-item-action" href="index?caminho=funcionarios">Consultar Funcionario</a>
        <a class="list-group-item list-group-item-action" href="index?caminho=produtos">Consultar Produto</a>
        <a class="list-group-item list-group-item-action" href="index?caminho=estoque">Consultar Estoque</a>
        <a class="list-group-item list-group-item-action" href="index?caminho=categorias">Consultar Categoria</a>
        <a class="list-group-item list-group-item-action" href="index?caminho=cargos">Consultar Cargo</a>
        <a class="list-group-item list-group-item-action" href="index?caminho=pedidos">Consultar Pedidos</a>
        <a class="list-group-item list-group-item-action" href="index?caminho=notasFiscais">Consultar Notas-Fiscais</a>
    </div>
    <hr>
    <h3>Transações</h3>
    <div class="list-group">
        <a class="list-group-item list-group-item-action" href="index?caminho=manterPedido">Emitir Pedido</a>
        <a class="list-group-item list-group-item-action" href="index?caminho=manterPagamento">Realizar Pagamento</a>
        <a class="list-group-item list-group-item-action" href="index?caminho=manterEstoque">Adicionar ao Estoque</a>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>