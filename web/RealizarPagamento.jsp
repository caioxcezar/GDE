<%-- 
    Document   : pagamentos
    Created on : Sep 4, 2019, 1:32:24 PM
    Author     : ccezar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="Realizar Pagamento"/>
</jsp:include>
<div class="container mt-4">
    <h3>Realizar Pagamento</h3>
    <form>
        <div class="input-group mb-3">
            <input type="text" name="inputTermo" class="form-control" placeholder="0555010" aria-label="Código da Venda" aria-describedby="button-addon2">
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="button" id="button-addon2">Buscar</button>
            </div>
        </div>
        <div class="form-group">
            <label for="inputCodigo">Código</label>
            <input type="number" class="form-control" id="inputCodigo" placeholder="0" readonly="true" required="true">
        </div>
        <div class="form-group">
            <label for="listaProdutos">Produtos</label>
            <div class="tabela"></div>
        </div>
        <div class="form-group">
            <label for="inputTotal">Total</label>
            <input type="number" class="form-control" id="inputTotal" placeholder="0" readonly="true" required="true">
        </div>
        <div class="form-group">
            <label for="inputForPagamento">Forma de Pagamento</label>
            <select id="inputForPagamento" class="form-control" required="true">
                <option selected>Escolha...</option>
                <option>...</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Pagar</button>
</div>
</form>
</div>
<jsp:include page="templates/footer.jsp"/>