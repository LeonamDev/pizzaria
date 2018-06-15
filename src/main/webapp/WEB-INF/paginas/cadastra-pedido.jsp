<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%-- 
    Document   : consulta-pedidos
    Created on : 13/06/2018, 15:26:33
    Author     : leonam
--%>

<html>
    <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Realizando Pedido</h1>



        <fieldset>
            <div>
                <c:forEach items="${produtos}" var="produto">
                    <input type="checkbox" id="${produto.id}" name="produto" value="${produto.nome}">${produto.nome}
                    <br>
                </c:forEach>
                    
                    <form actoin="cadastra-pedido" method="post">
                        
                        <button type="submit">Pedir</button>
                    </form>
            </div>
            <div>

        </fieldset>



    </body>
</html>