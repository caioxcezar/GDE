<%-- 
    Document   : funcionarios
    Created on : Sep 4, 2019, 12:59:06 AM
    Author     : ccezar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="Funcionário"/>
</jsp:include>
<div class="container mt-4">
    <div class="container">
        <h3>Funcionário</h3>
        <form>
            <div class="input-group mb-3">
                <input type="text" name="inputTermo" class="form-control" placeholder="Código ou nome do funcionário" aria-label="Código ou nome do funcionário" aria-describedby="button-addon2">
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button" id="button-addon2">Buscar</button>
                </div>
            </div>
            <div id="tabelaRetorno">Tabela com os valores será carregada aqui</div>
        </form>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>