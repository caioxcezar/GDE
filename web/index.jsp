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
        <a class="list-group-item list-group-item-action" href="cadastroCliente.jsp">Cadastrar Cliente</a>
        <a class="list-group-item list-group-item-action" href="cadastroFuncionario.jsp">Cadastrar Funcionario</a>
        <a class="list-group-item list-group-item-action" href="cadastroProduto.jsp">Cadastrar Produto</a>
        <a class="list-group-item list-group-item-action" href="cadastroCategoria.jsp">Cadastrar Categoria</a>
        <a class="list-group-item list-group-item-action" href="adicionarEstoque.jsp">Adicionar ao Estoque</a>
    </div>
    <hr>
    <h3>Consultas</h3>
    <div class="list-group">
        <a class="list-group-item list-group-item-action" href="clientes.jsp">Consultar Cliente</a>
        <a class="list-group-item list-group-item-action" href="funcionarios.jsp">Consultar Funcionario</a>
        <a class="list-group-item list-group-item-action" href="produtos.jsp">Consultar Produto</a>
        <a class="list-group-item list-group-item-action" href="estoque.jsp">Consultar Estoque</a>
        <a class="list-group-item list-group-item-action" href="categoria.jsp">Consultar Categoria</a>
    </div>
    <hr>
    <h3>Transações</h3>
    <div class="list-group">
        <a class="list-group-item list-group-item-action" href="emitirPedido.jsp">Emitir Pedido</a>
        <a class="list-group-item list-group-item-action" href="venderProduto.jsp">Vender Produto</a>
        <a class="list-group-item list-group-item-action" href="RealizarPagamento.jsp">Realizar Pagamento</a>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>