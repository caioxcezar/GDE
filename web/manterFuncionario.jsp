<%-- 
    Document   : cadastrarFuncionario
    Created on : Sep 4, 2019, 12:55:21 AM
    Author     : ccezar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="${operacao} funcionário"/>
</jsp:include>
<div class="container mt-4">
    <h3>${operacao.toUpperCase()} FUNCIONÁRIO</h3>
    <form action="manterFuncionario?acao=confirmarOperacao&operacao=${operacao}" method="post">
        <div class="form-group">
            <label for="inputCodigo">Código</label>
            <input type="number" class="form-control" value="${funcionario.codigo}" name="inputCodigo" id="inputCodigo" readonly="true">
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputNome">Nome</label>
                <input type="text" class="form-control" value="${funcionario.nome}" name="inputNome" id="inputNome" required="true">
            </div>
            <div class="form-group col-md-6">
                <label for="inputTelefone">Telefone</label>
                <input type="text" class="form-control" value="${funcionario.telefone}" name="inputTelefone" id="inputTelefone" required="true">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputCPF">CPF</label>
                <input type="text" class="form-control" value="${funcionario.cpf}" name="inputCPF" id="inputCPF" required="true">
            </div>
            <div class="form-group col-md-6">
                <label for="inputSalario">Salário</label>
                <input type="text" class="form-control" value="${funcionario.salario}" name="inputSalario" id="inputSalario" required="true">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-8">
                <label for="inputRua">Rua</label>
                <input type="text" class="form-control" value="${funcionario.rua}" name="inputRua" id="inputNome">
            </div>
            <div class="form-group col-md-4">
                <label for="inputNumero">Número</label>
                <input type="number" class="form-control" value="${funcionario.numero}" name="inputNumero" id="inputNumero">
            </div>
        </div>
        <div class="form-group">
            <label for="inputComplemento">Complemento</label>
            <input type="text" class="form-control" value="${funcionario.complemento}" name="inputComplemento" id="inputComplemento">
        </div>
        <div class="form-group">
            <label for="inputBairro">Bairro</label>
            <input type="text" class="form-control" value="${funcionario.bairro}" name="inputBairro" id="inputBairro" >
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputCidade">Cidade</label>
                <input type="text" class="form-control" value="${funcionario.cidade}" name="inputCidade" id="inputCidade">
            </div>
            <div class="form-group col-md-4">
                <label for="inputEstado">Estado</label>
                <select id="inputEstado" name="inputEstado" class="form-control" required="true">
                    <option value="">Escolha...</option>
                    <c:forEach var="estado" items="${estados}">
                        <option value="${estado.sigla}" 
                                <c:if test="${estado.sigla.equals(funcionario.estado.sigla)}">
                                    selected
                                </c:if>
                                >${estado.nome}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-2">
                <label for="inputCep">CEP</label>
                <input name="inputCep" type="text" value="${funcionario.cep}" class="form-control" id="inputCep">
            </div>
            <div class="form-group">
                <label for="inputCargo">Cargo</label>
                <select name="inputCargo" id="inputCargo" value="${funcionario.telefone}" class="form-control" required="true">
                    <option value="">Escolha...</option>
                    <c:forEach var="cargo" items="${cargos}">
                        <option value="${cargo.codigo}" 
                                <c:if test="${cargo.codigo == funcionario.cargo.codigo}">
                                    selected
                                </c:if>
                                >${cargo.nome}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">${operacao}</button>
        <a class="btn btn-warning" onclick="location.reload(true)">cancelar</a>
    </form>
</div>
<jsp:include page="templates/footer.jsp"/>
