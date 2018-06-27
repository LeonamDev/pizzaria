<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : consulta-categoria
    Created on : 26/06/2018, 21:24:55
    Author     : leonam
--%>

<html>
    <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Lista de Categorias</h1>
        <c:if test="${empty categorias}">
            <strong>Nenhuma categoria encontrada.</strong>
        </c:if>

        <c:if test="${not empty categorias}">
            <table e border="1">
                <tr>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>Ações</th>
                    
                </tr>
                <c:forEach items="${categorias}" var="categoria">
                    <tr>
                        <td>${categoria.id}</td>
                        <td>${categoria.nome}</td>                     
                        <td><form action="edita-categoria" method="get">
                                <input type="hidden" name="categoriaId" value="${categoria.id}" />
                                <a><button>Editar</button></a><br>
                            </form>

                            <form action="consulta-categorias" method="post">
                                <input type="hidden" name="categoriaId" value="${categoria.id}" />
                                <a><button type="submit">Deletar</button></a>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if><br>
        <form action="cadastra-categoria" method="get">
            <input type="submit" value="Nova Categoria" />
        </form>
        <br/><br/>

    </body>
</html>
