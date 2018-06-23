<%-- 
    Document   : cadastra-endereco
    Created on : 22/06/2018, 17:14:14
    Author     : leonam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Endereco</title>
    </head>
    <body>
        <h3> Cadastrando Endereco </h3>
        <form action="${action}" method="post">
             <input type="hidden" id="${clienteId}" name="clienteId" value="${clienteId}">
             <input type="hidden" name="enderecoId" value="${form.id}" />
            rua:<input type="text" name="rua" size="8"
                        value="${form.rua}" /><br/>
            Nº: <input type="text" name="numero" size="8"
                             value="${form.numero}" /><br/>
            Referência: <input type="text" name="referencia" size="8"
                             value="${form.referencia}" /><br/><br>
            <button type="submit">Cadastrar</button>

        </form>
    </body>
</html>
