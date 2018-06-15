<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Realizando Pedido</h1>
        <c:if test="${not empty mensagem}">
            <strong>${mensagem}</strong><br/><br/>
        </c:if>
        <form action="cadastra-pedido" method="post">
            Entrega:
            <select>
                <option name="entrega" value="true">Sim</option>
                <option name="entrega" value="false">Não</option>
            </select><br>
            Valor do Desconto:
            <input type="text" name="desconto" size="8"
                   value="${form.desconto}" /><br/>
            Taxa de Entrega:
            <input type="text" name="taxa_entrega" size="8"
                   value="${form.Taxa_entrega}" /><br/>
            Preco do Pedido:
            <input type="text" name="valor" size="8"
                   value="${form.valor}" /><br/>
            Troco:
            <input type="text" name="troco" size="8"
                   value="${form.troco}" /><br/>
            Estatus do Pedido:
            <input type="text" name="status" size="8"
                   value="${form.status}" /><br/><br>
            <h3>Escolha o(s) produto(s):</h3>
            <c:forEach items="${produtos}" var="produto">
                <input type="checkbox" name="produto" value="${produto.id}">${produto.nome}.
                Qtd: <input id="number" type="number" name="qtd" value="0" min="0" max="5"><br>

            </c:forEach><br>
            <input type="submit" value="Pedir">


        </form>

    </body>
</html>