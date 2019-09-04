<%-- 
    Document   : emitirPedido
    Created on : Sep 4, 2019, 12:58:31 AM
    Author     : ccezar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="Emitir Pedido"/>
</jsp:include>
<div class="container mt-4">
    <h3>Emitir Pedido</h3>
    <form>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputCodigo">Código</label>
                <input type="text" class="form-control" id="inputCodigo" placeholder="100053" readonly="true" required="true">
            </div>
            <div class="form-group col-md-6">
                <label for="inputcodCli">Código do Cliente</label>
                <input type="text" class="form-control" id="inputcodCli" placeholder="200037" required="true">
            </div>
        </div>
            <div class="input-group mb-3">
                <input type="text" name="inputTermo" class="form-control" placeholder="300025" aria-label="Código ou nome do cliente" aria-describedby="button-addon2">
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button" id="button-addon2">Adicionar</button>
                </div>
            </div>
        <button type="submit" class="btn btn-primary">Emitir</button>
        <button type="submit" class="btn btn-danger">Excluir</button>
    </form>
</div>
<jsp:include page="templates/footer.jsp"/>