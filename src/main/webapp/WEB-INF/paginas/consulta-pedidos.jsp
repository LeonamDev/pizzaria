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
        <h1>Lista de Pedidos</h1>
        <c:if test="${empty pedidos}">
            <strong>Nenhum pedido encontrado.</strong>
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
                    <th>Entrega</th>
                    <th>Status</th>
                    <th>Produto(s)</th>
                    <th>Ações</th>
                </tr>
                <c:forEach items="${pedidos}" var="pedido">
                    <tr>
                        <td><fmt:formatDate value="${pedido.data}"
                                        pattern="dd/MM/yyyy"/></td>
                        <td><fmt:setLocale value="pt_BR" />
                            <fmt:formatNumber type="currency" value="${pedido.desconto}" />
                        </td>
                        <td><fmt:formatDate value="${pedido.hora}"
                                        pattern="HH:mm:ss"/></td>
                        <td><fmt:setLocale value="pt_BR" />
                            <fmt:formatNumber type="currency" value="${pedido.taxa_entrega}" /></td>
                        <td><fmt:setLocale value="pt_BR" />
                            <fmt:formatNumber type="currency" value="${pedido.troco}" /></td>
                        <td>
                            <fmt:setLocale value="pt_BR" />
                            <fmt:formatNumber type="currency" value="${(pedido.valor + pedido.taxa_entrega) - pedido.desconto}" /></td>
                        <td>${pedido.entrega}</td>
                        <td>${pedido.status}</td>
                        <td>
                            <c:forEach items="${pedido.produtoPedidos}" var="produtoPedido">
                                <a>${produtoPedido.produto.nome}.</a>
                                <a>Qtd: ${produtoPedido.quantidade}.</a>
                                <a>Obs.: ${produtoPedido.obs}</a><br>
                            </c:forEach>
                        </td>

                        <td>
                            <a><button>Editar</button></a><br>
                            <form action="consulta-pedidos" method="post">
                                <input type="hidden" name="pedido_id" value="${pedido.id}" />
                                <a><button type="submit">Deletar</button></a>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if><br>
        <form action="cadastra-pedido" method="get">
            <input type="submit" value="Novo Pedido" />
        </form>
        <br/><br/>

    </body>
</html>