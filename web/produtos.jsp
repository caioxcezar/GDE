<%-- 
    Document   : produtos
    Created on : Sep 4, 2019, 12:59:43 AM
    Author     : ccezar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="Produtos"/>
</jsp:include>
<div class="container mt-4">
    <div class="container">
        <h3>Produtos</h3>
        <form action="produtos" method="post">
            <div class="input-group mb-3">
                <input type="text" name="inputTermo" class="form-control" placeholder="Código ou nome do produto" aria-label="Código ou nome do produto" aria-describedby="button-addon2">
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Buscar</button>
                </div>
            </div>
            <div id="tabelaRetorno">
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Nome</th>
                            <th scope="col">Descrição</th>
                            <th scope="col">Operações</th>
                        </tr>
                    </thead>
                    <c:forEach items="${produtos}" var="produto">
                        <tr>
                            <th scope="row"><c:out value="${produto.codigo}"></c:out></th>
                            <td><c:out value="${produto.nome}"></c:out></td>
                            <td><c:out value="${produto.descricao}"></c:out></td>
                            <td>
                                <a class="btn btn-warning" href="manterProduto?acao=prepararOperacao&operacao=alterar&cod=${produto.codigo}">Alterar</a>
                                <a class="btn btn-danger" href="manterProduto?acao=prepararOperacao&operacao=excluir&cod=${produto.codigo}">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>