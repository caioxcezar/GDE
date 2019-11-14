<%-- 
    Document   : adicionarEstoque
    Created on : Sep 4, 2019, 12:54:47 AM
    Author     : ccezar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="${operacao} estoque"/>
</jsp:include>
<div class="container mt-4">
    <h3>${operacao.toUpperCase()} ESTOQUE</h3>
    <form action="manterEstoque?acao=confirmarOperacao&operacao=${operacao}" method="post">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputCodigo">Codigo</label>
                <input type="text" value="${estoque.codigo}" class="form-control" id="inputCodigo" name="inputCodigo" placeholder="1" readonly="true">
            </div>
            <div class="form-group col-md-6">
                <label for="inputData">Data</label>
                <input type="date" class="form-control" value="${estoque.data}" id="inputData" name="inputData" placeholder="10/05/2019" readonly="true">
            </div>
        </div>
        <select id="inputPedido" name="inputPedido" class="form-control mb-2">
            <option value="">Escolha...</option>
            <c:forEach items="${pedidos}" var="pedido">
                <option value="${pedido.codigo}" <c:if test="${pedido.codigo == estoque.pedido.codigo}">
                        selected
                </c:if>>${pedido.codigo}: ${pedido.cliente.nome}, ${pedido.funcionario.nome}</option>
            </c:forEach>
        </select>
        <button type="submit" class="btn btn-primary">${operacao}</button>
        <a class="btn btn-warning" onclick="location.reload(true)">cancelar</a>
    </form>
</div>
<jsp:include page="templates/footer.jsp"/>