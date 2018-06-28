<%-- 
    Document   : cadastra-ingrediente
    Created on : 27/06/2018, 19:57:15
    Author     : leonam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Ingrediente</title>
    </head>
    <body>
        <h3> Cadastrando ingrediente </h3>
        <form action="${action}" method="post">
             <input type="hidden" id="${form.id}" name="ingredienteId" value="${form.id}">
            Nome:<input type="text" name="nome" size="8"
                   value="${form.nome}" /><br/>
            <button type="submit">Cadastrar</button>

        </form>
    </body>
</html>
