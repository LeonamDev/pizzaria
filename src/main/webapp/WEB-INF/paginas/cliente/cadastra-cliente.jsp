<%-- 
    Document   : cadastra-cliente
    Created on : 22/06/2018, 15:03:32
    Author     : leonam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Cliente</title>
    </head>
    <body>
        <h3> Cadastrando Cliente </h3>
        <form action="${action}" method="post">
             <input type="hidden" id="${form.id}" name="clienteId" value="${form.id}">
            Nome:<input type="text" name="nome" size="8"
                   value="${form.nome}" /><br/>
            Telefone: <input type="text" name="telefone" size="8"
                             value="${form.telefone}" /><br/><br>
            <button type="submit">Cadastrar</button>

        </form>
    </body>
</html>
