<%-- 
    Document   : cadastra-categoria
    Created on : Jun 27, 2018, 10:26:49 AM
    Author     : leonam
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Categoria</title>
    </head>
    <body>
        <h3> Cadastrando categoria </h3>
        <form action="${action}" method="post">
             <input type="hidden" id="${form.id}" name="categoriaId" value="${form.id}">
            Nome:<input type="text" name="nome" size="8"
                   value="${form.nome}" /><br/>
            <button type="submit">Cadastrar</button>

        </form>
    </body>
</html>