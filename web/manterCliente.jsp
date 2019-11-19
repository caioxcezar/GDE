<%-- 
    Document   : cadastroCliente
    Created on : Sep 4, 2019, 12:45:28 AM
    Author     : ccezar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="${operacao} cliente"/>
</jsp:include>
<div class="container mt-4">
    <h3>${operacao.toUpperCase()} CLIENTE</h3>
    <form action="manterCliente?acao=confirmarOperacao&operacao=${operacao}" method="post">
        <div class="form-group">
            <label for="inputCodigo">Código</label>
            <input type="number" value="${cliente.codigo}" class="form-control" id="inputCodigo" name="inputCodigo" readonly="true">
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputNome">Nome</label>
                <input type="text" value="${cliente.nome}" class="form-control" id="inputNome" name="inputNome" required="true">
            </div>
            <div class="form-group col-md-6">
                <label for="inputTelefone">Telefone</label>
                <input type="text" class="form-control" value="${cliente.telefone}" id="inputTelefone" name="inputTelefone" required="true">
            </div>
        </div>
        <div class="form-group">
            <label for="inputCNPJ">CNPJ</label>
            <input type="text" class="form-control" value="${cliente.cnpj}" id="inputCnpj" name="inputCnpj" required="true">
        </div>
        <div class="form-row">
            <div class="form-group col-md-8">
                <label for="inputRua">Rua</label>
                <input type="text" class="form-control" value="${cliente.rua}" id="inputRua" name="inputRua">
            </div>
            <div class="form-group col-md-4">
                <label for="inputNumero">Número</label>
                <input type="number" class="form-control" value="${cliente.numero}" id="inputNumero" name="inputNumero">
            </div>
        </div>
        <div class="form-group">
            <label for="inputComplemento">Complemento</label>
            <input type="text" class="form-control" id="inputComplemento" value="${cliente.complemento}" name="inputComplemento">
        </div>
        <div class="form-group">
            <label for="inputBairro">Bairro</label>
            <input type="text" class="form-control" id="inputTelefone" value="${cliente.bairro}" name="inputBairro">
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputCidade">Cidade</label>
                <input type="text" class="form-control" name="inputCidade" value="${cliente.cidade}" id="inputCidade">
            </div>
            <div class="form-group col-md-4">
                <label for="inputEstado">Estado</label>
                <select id="inputEstado" name="inputEstado" class="form-control">
                    <option value="">Escolha...</option>
                    <c:forEach var="estado" items="${estados}">
                        <option value="${estado.sigla}" 
                                <c:if test="${estado.sigla.equals(cliente.estado.sigla)}">
                                    selected
                                </c:if>
                                >${estado.nome}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-2">
                <label for="inputCep">CEP</label>
                <input type="text" class="form-control" value="${cliente.cep}" name="inputCep" id="inputCep">
            </div>
        </div>
        <button type="submit" class="btn btn-primary">${operacao}</button>
        <a class="btn btn-warning" onclick="location.reload(true)">Cancelar</a>
    </form>
</div>
<jsp:include page="templates/footer.jsp"/>
