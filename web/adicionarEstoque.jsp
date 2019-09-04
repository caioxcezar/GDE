<%-- 
    Document   : adicionarEstoque
    Created on : Sep 4, 2019, 12:54:47 AM
    Author     : ccezar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="Adicionar ao Estoque"/>
</jsp:include>




<div class="container mt-4">
    <h3>Adicionar ao Estoque</h3>
    <form>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputCodigo">Código</label>
                <input type="number" class="form-control" id="inputCodigo" placeholder="0">
            </div>
            <div class="form-group col-md-6">
                <label for="inputNome">Nome</label>
                <input type="text" class="form-control" id="inputNome" placeholder="Nome" readonly="true">
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Verificar</button>
        <div class="form-group">
            <label for="inputQuantidade">Quantidade</label>
            <input type="number" class="form-control" id="inputQuantidade" placeholder="1" required="true" />
        </div>
        <div class="form-group">
            <label for="inputCodSolicitacao">Código da Solicitação</label>
            <input type="number" class="form-control" id="inputCodSolicitacao" placeholder="100058" required="true" />
        </div>
        <button type="submit" class="btn btn-primary">Salvar</button>
</div>
</form>
</div>
<jsp:include page="templates/footer.jsp"/>