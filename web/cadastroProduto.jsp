<%-- 
    Document   : cadastarProduto
    Created on : Sep 4, 2019, 12:53:33 AM
    Author     : ccezar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="Cadastro de Cliente"/>
</jsp:include>
<div class="container mt-4">
    <h3>Cadastro de Produto</h3>
    <form>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputCodigo">Código</label>
                <input type="number" class="form-control" id="inputCodigo" placeholder="0" readonly="true">
            </div>
            <div class="form-group col-md-6">
                <label for="inputNome">Nome</label>
                <input type="text" class="form-control" id="inputNome" placeholder="Nome" required="true">
            </div>
        </div>
        <div class="form-group">
            <label for="inputDescricao">Descrição</label>
            <textarea class="form-control" name="inputTermo" id="inputDescricao" placeholder="Descrição sobre o produto" required="true"></textarea>
        </div>
        <div class="form-group">
            <label for="inputCategoria">Categoria</label>
            <select id="inputCategoria" class="form-control" required="true">
                <option selected>Escolha...</option>
                <option>...</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Salvar</button>
        <button type="submit" class="btn btn-danger">Excluir</button>
        <a type="submit" class="btn btn-warning" onclick="location.reload(true)">Cancelar</a>
</div>
</form>
</div>
<jsp:include page="templates/footer.jsp"/>