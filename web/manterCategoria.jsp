<%-- 
    Document   : cadastroCategoria
    Created on : Sep 4, 2019, 5:51:11 PM
    Author     : ccezar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="${operacao} Categoria"/>
</jsp:include>
<div class="container mt-4">
    <h3>${operacao.toUpperCase()} CATEGORIA</h3>
    <form action="manterCurso?acao=confirmarOperacao&operacao=${operacao}" method="post">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputCodigo">Código</label>
                <input type="number" class="form-control" name="inputCodigo" id="inputCodigo" placeholder="0" readonly="true">
            </div>
            <div class="form-group col-md-6">
                <label for="inputNome">Nome</label>
                <input type="text" class="form-control" name="inputNome" id="inputNome" placeholder="Nome" required="true">
            </div>
        </div>
        <div class="form-group">
            <label for="inputDescricao">Descrição</label>
            <textarea class="form-control" name="inputDescricao" id="inputDescricao" placeholder="Descrição sobre o categoria" required="true"></textarea>
        </div>
        <button type="submit" name="btnSalvar" class="btn btn-primary">Salvar</button>
        <button type="submit" name="btnExcluir" class="btn btn-danger">Excluir</button>
        <a type="submit" class="btn btn-warning" onclick="location.reload(true)">Cancelar</a>
</div>
</form>
</div>
<jsp:include page="templates/footer.jsp"/>