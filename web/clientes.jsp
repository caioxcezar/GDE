<%-- 
    Document   : clientes
    Created on : Sep 4, 2019, 12:58:58 AM
    Author     : ccezar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="Clientes"/>
</jsp:include>
<div class="container mt-4">
    <div class="container">
        <h3>Clientes</h3>
        <form>
            <div class="input-group mb-3">
                <input type="text" name="inputTermo" class="form-control" placeholder="Código ou nome do cliente" aria-label="Código ou nome do cliente" aria-describedby="button-addon2">
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button" id="button-addon2">Buscar</button>
                </div>
            </div>
            <div id="tabelaRetorno">Tabela com os valores será carregada aqui</div>
        </form>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>