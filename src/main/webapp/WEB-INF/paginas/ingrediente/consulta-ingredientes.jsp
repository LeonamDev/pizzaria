<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : consulta-ingredientes
    Created on : Jun 27, 2018, 11:28:37 AM
    Author     : leonam
--%>


<html>
    <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Lista de Ingredientes</h1>
        <c:if test="${empty ingredientes}">
            <strong>Nenhum ingrediente encontrado.</strong>
        </c:if>

        <c:if test="${not empty ingredientes}">
            <table e border="1">
                <tr>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>Ações</th>

                </tr>
                <c:forEach items="${ingredientes}" var="ingrediente">
                    <tr>
                        <td>${ingrediente.id}</td>
                        <td>${ingrediente.nome}</td>                     
                        <td><form action="edita-ingrediente" method="get">
                                <input type="hidden" name="ingredienteId" value="${ingrediente.id}" />
                                <a><button>Editar</button></a><br>
                            </form>

                            <form action="consulta-ingredientes" method="post">
                                <input type="hidden" name="ingredienteId" value="${ingrediente.id}" />
                                <a><button type="submit">Deletar</button></a>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if><br>
        <form action="cadastra-ingrediente" method="get">
            <input type="submit" value="Novo Ingrediente" />
        </form>
        <br/><br/>

    </body>
</html>
