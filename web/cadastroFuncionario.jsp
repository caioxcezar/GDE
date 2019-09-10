<%-- 
    Document   : cadastrarFuncionario
    Created on : Sep 4, 2019, 12:55:21 AM
    Author     : ccezar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="Cadastro de Funcionário"/>
</jsp:include>
<div class="container mt-4">
    <h3>Cadastro de Funcionário</h3>
    <form method="post" action="FuncionarioController">
        <div class="form-group">
            <label for="inputCodigo">Código</label>
            <input type="number" class="form-control" name="inputCodigo" id="inputCodigo" placeholder="0" readonly="true">
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputNome">Nome</label>
                <input type="text" class="form-control" name="inputNome" id="inputNome" placeholder="Nome" required="true">
            </div>
            <div class="form-group col-md-6">
                <label for="inputTelefone">Telefone</label>
                <input type="text" class="form-control" name="inputTelefone" id="inputTelefone" placeholder="(12) 1234-1234" required="true">
            </div>
        </div>
        <div class="form-group">
            <label for="inputCPF">CPF</label>
            <input type="text" class="form-control" name="inputCPF" id="inputCPF" placeholder="023.078.530-14" required="true">
        </div>
        <div class="form-row">
            <div class="form-group col-md-8">
                <label for="inputRua">Rua</label>
                <input type="text" class="form-control" name="inputNome" id="inputNome" placeholder="Rua Principal">
            </div>
            <div class="form-group col-md-4">
                <label for="inputNumero">Número</label>
                <input type="number" class="form-control" name="inputNumero" id="inputNumero" placeholder="1234">
            </div>
        </div>
        <div class="form-group">
            <label for="inputEndereco">Complemento</label>
            <input type="text" class="form-control" name="inputEndereco" id="inputEndereco" placeholder="Apartamento 123">
        </div>
        <div class="form-group">
            <label for="inputTelefone">Telefone</label>
            <input type="text" class="form-control" name="inputTelefone" id="inputTelefone" placeholder="(32) 1234-1234">
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputCidade">Cidade</label>
                <input type="text" class="form-control" name="inputCidade" id="inputCidade">
            </div>
            <div class="form-group col-md-4">
                <label for="inputEstado">Estado</label>
                <select name="inputEstado" id="inputEstado" class="form-control">
                    <option selected>Escolha...</option>
                    <option>...</option>
                </select>
            </div>
            <div class="form-group col-md-2">
                <label for="inputCep">CEP</label>
                <input type="text" class="form-control" id="inputCep">
            </div>
            <div class="form-group">
                <label for="inputCargo">Cargo</label>
                <select id="inputCargo" class="form-control" required="true">
                    <option selected>Escolha...</option>
                    <option>...</option>
                </select>
            </div>
        </div>
        <button type="submit" name="salvar" class="btn btn-primary">Salvar</button>
        <button type="submit" name="excluir" class="btn btn-danger">Excluir</button>
        <a type="submit" class="btn btn-warning" onclick="location.reload(true)">Cancelar</a>
    </form>
</div>
<jsp:include page="templates/footer.jsp"/>