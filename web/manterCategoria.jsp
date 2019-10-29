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
    <form action="manterCategoria?acao=confirmarOperacao&operacao=${operacao}" method="post">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputCodigo">Código</label>
                <input type="number" value="${categoria.codigo}" class="form-control" name="inputCodigo" id="inputCodigo" placeholder="0" readonly="true">
            </div>
            <div class="form-group col-md-6">
                <label for="inputNome">Nome</label>
                <input type="text" value="${categoria.nome}" class="form-control" name="inputNome" id="inputNome" placeholder="Nome" required="true">
            </div>
        </div>
        <div class="form-group">
            <label for="inputDescricao">Descrição</label>
            <textarea class="form-control" maxlength="150" name="inputDescricao" id="inputDescricao" placeholder="Descrição sobre o categoria" required="true">${categoria.descricao}</textarea>
        </div>
        <button type="submit" class="btn btn-primary">${operacao}</button>
        <a type="submit" class="btn btn-warning" onclick="location.reload(true)">cancelar</a>
    </form>
</div>
<jsp:include page="templates/footer.jsp"/>