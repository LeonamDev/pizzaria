<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : cadastra-produto
    Created on : Jun 26, 2018, 11:34:05 AM
    Author     : leonam
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Produto</title>
    </head>
    <body>
        <h3> Cadastrando produto </h3>
        <form action="${action}" method="post">
            <input type="hidden" id="${form.id}" name="produtoId" value="${form.id}">

            Código:<input type="text" name="codigo" size="8"
                          value="${form.codigo}" /><br/>
            Nome: <input type="text" name="nome" size="8"
                         value="${form.nome}" /><br/>
            Preco: <input type="text" name="preco" size="8"
                          value="${form.preco}" /><br/><br>

            Categoria:
            <select  name="categoriaId">
                <c:if test="${categoria_id == null}">
                    <option disabled selected value> -- select an option -- </option>
                </c:if>
                <c:forEach items="${categorias}" var="categoria">
                    <option value="${categoria.id}" ${categoria_id == categoria.id ? 'selected="selected"' : ''}>${categoria.nome}</option>
                </c:forEach>
            </select>
            <br><br/><br>
            Ingredientes:

            <c:forEach items="${ingredientes}" var="ingrediente">
                <input type="checkbox" name="ingredienteId" value="${ingrediente.id}" ${ingrediente_ids.contains(ingrediente.id) ? "checked" : ''}>${ingrediente.nome}
            </c:forEach><br/><br>

            <button type="submit">Cadastrar</button>

        </form>
    </body>
</html>