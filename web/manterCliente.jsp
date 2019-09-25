<%-- 
    Document   : cadastroCliente
    Created on : Sep 4, 2019, 12:45:28 AM
    Author     : ccezar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="${operacao} cliente"/>
</jsp:include>
<div class="container mt-4">
    <h3>${operacao.toUpperCase()} CLIENTE</h3>
    <form action="manterCliente?acao=confirmarOperacao&operacao=${operacao}" method="post">
        <div class="form-group">
            <label for="inputCodigo">Código</label>
            <input type="number" class="form-control" id="inputCodigo" placeholder="0" readonly="true">
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputNome">Nome</label>
                <input type="text" class="form-control" id="inputNome" placeholder="Nome" required="true">
            </div>
            <div class="form-group col-md-6">
                <label for="inputTelefone">Telefone</label>
                <input type="text" class="form-control" id="inputTelefone" placeholder="(12) 1234-1234" required="true">
            </div>
        </div>
        <div class="form-group">
            <label for="inputCNPJ">CNPJ</label>
            <input type="text" class="form-control" id="inputCNPJ" placeholder="15.853.198/0001-23" required="true">
        </div>
        <div class="form-row">
            <div class="form-group col-md-8">
                <label for="inputRua">Rua</label>
                <input type="text" class="form-control" id="inputNome" placeholder="Rua Principal">
            </div>
            <div class="form-group col-md-4">
                <label for="inputNumero">Número</label>
                <input type="number" class="form-control" id="inputNumero" placeholder="1234">
            </div>
        </div>
        <div class="form-group">
            <label for="inputEndereco">Complemento</label>
            <input type="text" class="form-control" id="inputEndereco" placeholder="Apartamento 123">
        </div>
        <div class="form-group">
            <label for="inputTelefone">Telefone</label>
            <input type="text" class="form-control" id="inputTelefone" placeholder="(32) 1234-1234">
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputCidade">Cidade</label>
                <input type="text" class="form-control" id="inputCidade">
            </div>
            <div class="form-group col-md-4">
                <label for="inputEstado">Estado</label>
                <select id="inputEstado" class="form-control">
                    <option selected>Escolha...</option>
                    <option>...</option>
                </select>
            </div>
            <div class="form-group col-md-2">
                <label for="inputCep">CEP</label>
                <input type="text" class="form-control" id="inputCep">
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Salvar</button>
        <button type="submit" class="btn btn-danger">Excluir</button>
        <a type="submit" class="btn btn-warning" onclick="location.reload(true)">Cancelar</a>
    </form>
</div>
<jsp:include page="templates/footer.jsp"/>