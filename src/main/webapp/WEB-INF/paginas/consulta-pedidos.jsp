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
        <h1>Consulta de produtos</h1>
        <form action="consulta-produtos" method="get">
            Nome: <input type="text" name="nome" value="${param.nome}" />
            <input type="submit" value="Consultar" />
        </form>
        <c:if test="${empty pedidos}">
            <strong>Nenhum produto encontrado.</strong>
        </c:if>

        <c:if test="${not empty pedidos}">
            <table e border="1">
                <tr>
                    <th>Data</th>
                    <th>Desconto</th>
                    <th>Hora</th>
                    <th>Taxa de Entrega</th>
                    <th>Troco</th>
                    <th>Valor</th>
                </tr>
                <c:forEach items="${pedidos}" var="pedido">
                    <tr>
                        <td>${pedido.produtoPedidos}</td>
                        <td><fmt:setLocale value="pt_BR" />
                            <fmt:formatNumber type="currency" value="${pedido.desconto}" />
                        </td>
                        <td>${pedido.hora}</td>
                        <td><fmt:setLocale value="pt_BR" />
                            <fmt:formatNumber type="currency" value="${pedido.taxa_entrega}" /></td>
                        <td><fmt:setLocale value="pt_BR" />
                            <fmt:formatNumber type="currency" value="${pedido.troco}" /></td>
                        <td>
                            <fmt:setLocale value="pt_BR" />
                            <fmt:formatNumber type="currency" value="${(pedido.valor + pedido.taxa_entrega) - pedido.desconto}" /></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if><br>
    <aw:pesquisa acao="consulta-produtos" nomeParametro="nome"
                 descricaoParametro="Nome" />
    <br/><br/>

</body>
</html>