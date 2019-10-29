<%-- 
    Document   : notasFiscais
    Created on : 29/10/2019, 00:41:01
    Author     : caioc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="Notas Ficais"/>
</jsp:include>
<div class="container mt-4">
    <div class="container">
        <h3>Pedidos</h3>
        <form action="notasFiscais" method="post">
            <div class="input-group mb-3">
                <input type="number" name="inputTermo" class="form-control" placeholder="Código da nota-fiscal" aria-label="Código do Pedido" aria-describedby="button-addon2">
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Buscar</button>
                </div>
            </div>
            <div id="tabelaRetorno">
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Data</th>
                            <th scope="col">Cliente</th>
                            <th scope="col">Funcionario</th>
                            <th scope="col">Pedido</th>
                            <th scope="col">Operações</th>
                        </tr>
                    </thead>
                    <c:forEach items="${notasFiscais}" var="nota">
                        <tr>
                            <th scope="row"><c:out value="${nota.codigo}"></c:out></th>
                            <td><c:out value="${nota.data}"></c:out></td>
                            <td><c:out value="${nota.pedido.cliente.nome}"></c:out></td>
                            <td><c:out value="${nota.pedido.funcionario.nome}"></c:out></td>
                            <td><c:out value="${nota.pedido.codigo}"></c:out></td>
                            <td>
                                <a class="btn btn-success" href="manterPagamento?acao=prepararOperacao&operacao=visualizar&cod=${nota.codigo}">Visualizar</a>
                                <a class="btn btn-danger" href="manterPagamento?acao=prepararOperacao&operacao=excluir&cod=${nota.codigo}">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </div>
</div>
<script>
    $(".estoque-valor").text(parseFloat($(".estoque-valor").text()).toFixed(2));
</script>
<jsp:include page="templates/footer.jsp"/>