<%-- 
    Document   : pedidos
    Created on : 27/10/2019, 16:47:02
    Author     : caioc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="Pedidos"/>
</jsp:include>
<div class="container mt-4">
    <div class="container">
        <h3>Pedidos</h3>
        <form>
            <div class="input-group mb-3">
                <input type="text" name="inputTermo" class="form-control" placeholder="Código do Pedido" aria-label="Código do Pedido" aria-describedby="button-addon2">
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button" id="button-addon2">Buscar</button>
                </div>
            </div>
            <div id="tabelaRetorno">
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Cliente</th>
                            <th scope="col">Estado</th>
                            <th scope="col">Operações</th>
                        </tr>
                    </thead>
                    <c:forEach items="${pedidos}" var="pedido">
                        <tr>
                            <th scope="row"><c:out value="${pedido.codigo}"></c:out></th>
                            <td><c:out value="${pedido.cliente.nome}"></c:out></td>
                            <td><c:out value="${pedido.estado}"></c:out></td>
                            <td>
                                <a class="btn btn-warning" href="manterPedido?acao=prepararOperacao&operacao=alterar&cod=${pedido.codigo}">Alterar</a>
                                <a class="btn btn-danger" href="manterPedido?acao=prepararOperacao&operacao=excluir&cod=${pedido.codigo}">Excluir</a>
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