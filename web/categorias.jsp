<%-- 
    Document   : categorias
    Created on : Sep 4, 2019, 5:51:22 PM
    Author     : ccezar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="Cadastro de Cliente"/>
</jsp:include>
<div class="container mt-4">
    <div class="container">
        <h3>Categorias</h3>
        <form method="post" action="categorias">
            <div class="input-group mb-3">
                <input name="inputTermo" type="text" class="form-control" placeholder="Código ou nome da categoria" aria-label="Código ou nome da categoria" aria-describedby="button-addon2">
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
                    <c:forEach items="${categorias}" var="categoria">
                        <tr>
                            <th scope="row"><c:out value="${categoria.codigo}"></c:out></th>
                            <td><c:out value="${categoria.nome}"></c:out></td>
                            <td><c:out value="${categoria.descricao}"></c:out></td>
                            <td>
                                <a class="btn btn-warning" href="manterCategoria?acao=prepararOperacao&operacao=alterar&cod=${categoria.codigo}">Alterar</a>
                                <a class="btn btn-danger" href="manterCategoria?acao=prepararOperacao&operacao=excluir&cod=${categoria.codigo}">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>