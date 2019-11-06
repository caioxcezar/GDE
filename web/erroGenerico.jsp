<%-- 
    Document   : erro
    Created on : 06/11/2019, 19:09:29
    Author     : caioc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp">
    <jsp:param name="title" value="Ocorreu um erro"/>
</jsp:include>
<!DOCTYPE html>
<div class="cont">
    <div class="container mt-2">
        <div class="jumbotron">
            <h3>Erro Código ${errorCode}</h3>
            <h4>${mensagem}</h4>
        </div>
</div>