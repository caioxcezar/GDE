<%-- 
    Document   : manterPedido
    Created on : 27/10/2019, 17:07:05
    Author     : caioc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="${operacao} pedido"/>
</jsp:include>
<div class="container mt-4">
    <h3>${operacao.toUpperCase()} PEDIDO</h3>
    <form action="manterPedido?acao=confirmarOperacao&operacao=${operacao}" method="post">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputCodigo">Codigo</label>
                <input type="text" value="${pedido.codigo}" class="form-control" id="inputCodigo" name="inputCodigo" placeholder="1" readonly="true">
            </div>
            <div class="form-group col-md-6">
                <label for="inputData">Data</label>
                <input type="date" class="form-control" value="${pedido.data}" id="inputData" name="inputData" placeholder="10/05/2019" readonly="true">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputTipo">Tipo</label>
                <select name="inputTipo" id="inputTipo" class="form-control" required="true">
                    <option value="">Escolha...</option>
                    <option value="Interno">Interno</option>
                    <option value="Externo">Externo</option>
                </select>
            </div> 
            <div class="form-group col-md-6">
                <label for="inputEstado">Estado</label>
                <input type="text" class="form-control" value="${pedido.estado}" name="inputEstado" id="inputEstado" placeholder="Pendente" readonly="true">
            </div>
        </div>
        <div class="form-group">
            <label for="inputProduto">Produto</label>
            <div class="input-group">
                <select id="inputProduto" name="inputProduto" class="form-control">
                    <option value="">Escolha...</option>
                    <c:forEach items="${produtos}" var="produto">
                        <option value="${produto.codigo}">${produto.nome}</option>
                    </c:forEach>
                </select>
                <input id="inputQuantidade" name="inputQuantidade" type="number" aria-label="Quantidade" placeholder="1" min="1" class="form-control" />
                <div class="input-group-append">
                    <a class="btn btn-outline-secondary" onclick="adicionarProduto()" id="btnAdicionar">Adicionar</a>
                </div>
            </div>
        </div>
        <div class="form-group" id="listaProd"></div>
        <div class="form-group">
            <label for="inputFuncionario">Funcionario</label>
            <select id="inputFuncionario" name="inputFuncionario" class="form-control" required="true">
                <option value="">Escolha...</option>
                <c:forEach items="${funcionarios}" var="funcionario">
                    <option value="${funcionario.codigo}" <c:if test="${funcionario.codigo == pedido.funcionario.codigo}">selected</c:if>
                            >${funcionario.nome}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="inputCliente">Cliente</label>
            <select id="inputCliente" name="inputCliente" class="form-control" required="true">
                <option value="">Escolha...</option>
                <c:forEach items="${clientes}" var="cliente">
                    <option value="${cliente.codigo}" <c:if test="${cliente.codigo == pedido.cliente.codigo}">selected</c:if>
                            >${cliente.nome}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">${operacao}</button>
        <a type="submit" class="btn btn-warning" onclick="location.reload(true)">cancelar</a>
        <input type="hidden" name="hiddenProdutos" id="hiddenProdutos" value="${hiddenProdutos}"/>
    </form>
</div>
<jsp:include page="templates/footer.jsp"/>