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
    <h3>${operacao.toUpperCase()} CARGO</h3>
    <form action="manterCargo?acao=confirmarOperacao&operacao=${operacao}" method="post">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputCodigo">Código</label>
                <input type="number" value="${cargo.codigo}" class="form-control" name="inputCodigo" id="inputCodigo" readonly="true">
            </div>
            <div class="form-group col-md-6">
                <label for="inputNome">Nome</label>
                <input type="text" value="${cargo.nome}" class="form-control" name="inputNome" id="inputNome" required="true">
            </div>
        </div>
        <div class="form-group">
            <label for="inputDescricao">Descrição</label>
            <textarea class="form-control" maxlength="150" name="inputDescricao" id="inputDescricao" required="true">${cargo.descricao}</textarea>
        </div>
        <button type="submit" class="btn btn-primary">${operacao}</button>
        <a type="submit" class="btn btn-warning" onclick="location.reload(true)">cancelar</a>
    </form>
</div>
<jsp:include page="templates/footer.jsp"/>
