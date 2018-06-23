<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 
    Document   : consulta-clientes
    Created on : 21/06/2018, 22:54:08
    Author     : leonam
--%>

<html>
    <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Lista de Clientes</h1>
        <c:if test="${empty clientes}">
            <strong>Nenhum cliente encontrado.</strong>
        </c:if>

        <c:if test="${not empty clientes}">
            <table e border="1">
                <tr>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>Telefone</th>
                    <th>Endereço(s)</th>
                    <th>Ações</th>

                </tr>
                <c:forEach items="${clientes}" var="cliente">
                    <tr>
                        <td>${cliente.id}
                        <td>${cliente.nome}</td>
                        <td>${cliente.telefone}</td>
                        <td>
                            <c:forEach items="${cliente.enderecos}" var="endereco">
                                <form action="cadastra-endereco" method="post">
                                    <input type="hidden" name="enderecoId" value="${endereco.id}" />
                                    <a>Rua: ${endereco.rua}.</a>
                                    <a>Nº: ${endereco.numero}.</a>
                                    <a>Referência.: ${endereco.referencia} <input type="submit" value="Remover" />
                                </form>
                                <form action="edita-endereco" method="get">
                                    <input type="hidden" name="enderecoId" value="${endereco.id}" />
                                    <input type="submit" value="Editar" />

                                </form>

                                </a><br>


                            </c:forEach>
                            <form action="cadastra-endereco" method="get">
                                <input type="hidden" id="${clienteId}" name="clienteId" value="${cliente.id}">
                                <input type="submit" value="Novo Endereco" />
                            </form>
                        </td>


                        <td><form action="edita-cliente" method="get">
                                <input type="hidden" name="clienteId" value="${cliente.id}" />
                                <a><button>Editar</button></a><br>
                            </form>

                            <form action="consulta-clientes" method="post">
                                <input type="hidden" name="clienteId" value="${cliente.id}" />
                                <a><button type="submit">Deletar</button></a>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if><br>
        <form action="cadastra-cliente" method="get">
            <input type="submit" value="Novo Cliente" />
        </form>
        <br/><br/>

    </body>
</html>