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
            <input type="number" value="${cliente.codigo}" class="form-control" id="inputCodigo" name="inputCodigo" placeholder="0" readonly="true">
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputNome">Nome</label>
                <input type="text" value="${cliente.nome}" class="form-control" id="inputNome" name="inputNome" placeholder="Nome" required="true">
            </div>
            <div class="form-group col-md-6">
                <label for="inputTelefone">Telefone</label>
                <input type="text" class="form-control" value="${cliente.telefone}" id="inputTelefone" name="inputTelefone" placeholder="(12) 1234-1234" required="true">
            </div>
        </div>
        <div class="form-group">
            <label for="inputCNPJ">CNPJ</label>
            <input type="text" class="form-control" value="${cliente.cnpj}" id="inputCnpj" name="inputCnpj" placeholder="15.853.198/0001-23" required="true">
        </div>
        <div class="form-row">
            <div class="form-group col-md-8">
                <label for="inputRua">Rua</label>
                <input type="text" class="form-control" value="${cliente.rua}" id="inputRua" name="inputRua" placeholder="Rua Principal">
            </div>
            <div class="form-group col-md-4">
                <label for="inputNumero">Número</label>
                <input type="number" class="form-control" value="${cliente.numero}" id="inputNumero" name="inputNumero" placeholder="1234">
            </div>
        </div>
        <div class="form-group">
            <label for="inputComplemento">Complemento</label>
            <input type="text" class="form-control" id="inputComplemento" value="${cliente.complemento}" name="inputComplemento" placeholder="Apartamento 123">
        </div>
        <div class="form-group">
            <label for="inputBairro">Bairro</label>
            <input type="text" class="form-control" id="inputTelefone" value="${cliente.bairro}" name="inputBairro" placeholder="Centro">
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