<%-- 
    Document   : venderProduto
    Created on : Sep 4, 2019, 12:53:18 AM
    Author     : ccezar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="Vender Produtos"/>
</jsp:include>
<div class="container mt-4">
    <h3>Vender Produtos</h3>
    <form>
        <div class="input-group mb-3">
            <input type="text" name="inputTermo" class="form-control" placeholder="0555010" aria-label="Código do Pedido" aria-describedby="button-addon2">
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="button" id="button-addon2">Buscar</button>
            </div>
        </div>
        <div class="form-group">
            <label for="inputCodigo">Código</label>
            <input type="number" class="form-control" id="inputCodigo" placeholder="0" readonly="true" required="true">
        </div>
        <div class="form-group">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="gridCheck">
                <label class="form-check-label" for="gridCheck">
                    Entregar no endereço: <span name="endereco">Rua ...</span>
                </label>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Vender</button>
</div>
</form>
</div>
<jsp:include page="templates/footer.jsp"/>