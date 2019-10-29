<%-- 
    Document   : manterPagamento
    Created on : 29/10/2019, 00:10:23
    Author     : caioc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="${operacao} pagamento"/>
</jsp:include>
<div class="container mt-4">
    <h3>${operacao.toUpperCase()} PAGAMENTO</h3>
    <form action="manterPagamento?acao=confirmarOperacao&operacao=${operacao}" method="post">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputCodigo">Codigo</label>
                <input type="text" value="${notaFiscal.codigo}" class="form-control" id="inputCodigo" name="inputCodigo" placeholder="1" readonly="true">
            </div>
            <div class="form-group col-md-6">
                <label for="inputData">Data</label>
                <input type="date" class="form-control" value="${notaFiscal.data}" id="inputData" name="inputData" placeholder="10/05/2019" readonly="true">
            </div>
        </div>
        <select id="inputPedido" name="inputPedido" class="form-control">
            <option value="">Escolha...</option>
            <c:forEach items="${pedidos}" var="pedido">
                <option value="${pedido.codigo}" <c:if test="${pedido.codigo == notaFiscal.pedido.codigo}">
                        selected
                </c:if>>${pedido.codigo}: ${pedido.cliente.nome}, ${pedido.funcionario.nome}</option>
            </c:forEach>
        </select>
        <button type="submit" class="btn btn-primary">${operacao}</button>
        <a type="submit" class="btn btn-warning" onclick="location.reload(true)">cancelar</a>
    </form>
</div>
<jsp:include page="templates/footer.jsp"/>