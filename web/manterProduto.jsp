<%-- 
    Document   : cadastarProduto
    Created on : Sep 4, 2019, 12:53:33 AM
    Author     : ccezar
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="${operacao} produto"/>
</jsp:include>
<div class="container mt-4">
    <h3>${operacao.toUpperCase()} PRODUTO</h3>
    <form action="manterProduto?acao=confirmarOperacao&operacao=${operacao}" method="post">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputCodigo">Código</label>
                <input type="number" value="${produto.codigo}" class="form-control" name="inputCodigo" id="inputCodigo" placeholder="0" readonly="true">
            </div>
            <div class="form-group col-md-6">
                <label for="inputNome">Nome</label>
                <input type="text" value="${produto.nome}" class="form-control" name="inputNome" id="inputNome" placeholder="Nome" required="true">
            </div>
        </div>
        <div class="form-group">
            <label for="inputDescricao">Descrição</label>
            <textarea class="form-control" name="inputDescricao" id="inputDescricao" placeholder="Descrição sobre o produto" required="true">${produto.descricao}</textarea>
        </div>
        <div class="form-group">
            <label for="inputValor">Valor</label>
            <input type="text" class="form-control" value="${produto.valor}" name="inputValor" id="inputValor" placeholder="10.52" required="true">
        </div>
        <div class="form-group">
            <label for="inputCategoria">Categoria</label>
            <select id="inputCategoria" name="inputCategoria" class="form-control" required="true">
                <option value="">Escolha...</option>
                <c:forEach items="${categorias}" var="categoria">
                    <option value="${categoria.codigo}" <c:if test="${categoria.codigo == produto.categoria.codigo}">selected</c:if>
                            >${categoria.nome}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">${operacao}</button>
        <a type="submit" class="btn btn-warning" onclick="location.reload(true)">cancelar</a>
</div>
</form>
</div>
<jsp:include page="templates/footer.jsp"/>