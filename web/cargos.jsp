<%-- 
    Document   : clientes
    Created on : Sep 4, 2019, 12:58:58 AM
    Author     : ccezar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="Cargos"/>
</jsp:include>
<div class="container mt-4">
    <div class="container">
        <h3>Cargos</h3>
        <form method="post" action="cargos">
            <div class="input-group mb-3">
                <input type="text" name="inputTermo" class="form-control" placeholder="Código ou nome do cargo" aria-label="Código ou nome do cliente" aria-describedby="button-addon2">
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
                    <c:forEach items="${cargos}" var="cargo">
                        <tr>
                            <th scope="row"><c:out value="${cargo.codigo}"></c:out></th>
                            <td><c:out value="${cargo.nome}"></c:out></td>
                            <td><c:out value="${cargo.descricao}"></c:out></td>
                                <td>
                                    <a class="btn btn-warning" href="manterCargo?acao=prepararOperacao&operacao=alterar&cod=${cargo.codigo}">Alterar</a>
                                <a class="btn btn-danger" href="manterCargo?acao=prepararOperacao&operacao=excluir&cod=${cargo.codigo}">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>