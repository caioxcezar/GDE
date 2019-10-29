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
        <div class="form-group">
            <label for="inputCodigo">Codigo</label>
            <input type="text" value="${estoque.codigo}" class="form-control" id="inputCodigo" name="inputCodigo" placeholder="0" readonly="true">
        </div>
        <div class="form-group">
            <label for="inputQuantidade">Quantidade</label>
            <input type="number" value="${estoque.quantidade}" class="form-control" id="inputQuantidade" name="inputQuantidade" placeholder="0" required="true">
        </div>
        <div class="form-group">
            <label for="inputProduto">Produto</label>
            <select id="inputProduto" name="inputProduto" class="form-control" required="true">
                <option value="">Escolha...</option>
                <c:forEach var="produto" items="${produtos}">
                    <option value="${produto.codigo}" 
                            <c:if test="${produto.codigo == estoque.produto.codigo}">
                                selected
                            </c:if>
                            >${produto.nome}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">${operacao}</button>
        <a type="submit" class="btn btn-warning" onclick="location.reload(true)">cancelar</a>
    </form>
</div>
<jsp:include page="templates/footer.jsp"/>