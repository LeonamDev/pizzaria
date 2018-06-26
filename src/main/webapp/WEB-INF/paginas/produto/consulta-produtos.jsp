<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 
    Document   : consulta-produtos
    Created on : 25/06/2018, 20:41:33
    Author     : leonam
--%>

<html>
    <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Lista de Produtos</h1>
        <c:if test="${empty produtos}">
            <strong>Nenhum produto encontrado.</strong>
        </c:if>

        <c:if test="${not empty produtos}">
            <table e border="1">
                <tr>
                    <th>Id</th>
                    <th>Código</th>
                    <th>Nome</th>
                    <th>Preço</th>
                    <th>Categoria</th>
                    <th>Ingredientes</th>
                </tr>
                <c:forEach items="${produtos}" var="produto">
                    <tr>
                        <td>${produto.id}</td>
                        <td>${produto.codigo}</td>
                        <td>${produto.nome}</td>
                        <td><fmt:setLocale value="pt_BR" />
                            <fmt:formatNumber type="currency" value="${produto.preco}" /></td>
                        <td>${produto.categoria.nome}</td>
                        <td>
                            <c:forEach items="${produto.ingredientes}" var="produtoIngrediente">
                                <a>${produtoIngrediente.nome}.</a><br>
 
                            </c:forEach>
                        </td>

                        <td><form action="edita-produto" method="get">
                                <input type="hidden" name="produto_id" value="${produto.id}" />
                                <a><button>Editar</button></a><br>
                            </form>

                            <form action="consulta-produtos" method="post">
                                <input type="hidden" name="produto_id" value="${produto.id}" />
                                <a><button type="submit">Deletar</button></a>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if><br>
        <form action="cadastra-produto" method="get">
            <input type="submit" value="Novo Produto" />
        </form>
        <br/><br/>

    </body>
</html>