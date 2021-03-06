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
        <form action="${action}" method="post">
            <h3>Clientes:</h3>
            <c:forEach items="${clientes}" var="cliente">
                <b><p>${cliente.nome}</p></b>
                <ol>
                    <c:forEach items="${cliente.enderecos}" var="endereco">
                        <li><input type="radio" id="${endereco.id}"
                                   name="enderecoId" value="${endereco.id}" ${endereco.id == form.endereco.id ? 'checked="checked"' : ''}>${endereco.rua} - ${endereco.numero}/${endereco.referencia}. Tel.: ${cliente.telefone}
                        </li>
                    </c:forEach>
                </ol>
            </c:forEach><br>
             <input type="hidden" id="${form.id}" name="pedidoId" value="${form.id}">

            Entrega:
            <select name="entrega">
                <option  value="true">Sim</option>
                <option  value="false">Não</option>
            </select><br>
            Valor do Desconto:
            <input type="text" name="desconto" size="8"
                   value="${form.desconto}" /><br/>
            Taxa de Entrega:
            <input type="text" name="taxa_entrega" size="8"
                   value="${form.taxa_entrega}" /><br/>
            Preco do Pedido:
            <input type="text" name="valor" size="8"
                   value="${form.valor}" /><br/>
            Troco:
            <input type="text" name="troco" size="8"
                   value="${form.troco}" /><br/>
            
            Estatus do Pedido:
            <select name="status">
                <option  value="PREPARANDO" ${form.status == "PREPARANDO" ? 'selected="selected"' : ''}>PREPARANDO</option>
                <option  value="SAIU PARA ENTREGA" ${form.status == "SAIU PARA ENTREGA" ? 'selected="selected"' : ''}>SAIU PARA ENTREGA</option>
                <option  value="ENTREGUE" ${form.status == "ENTREGUE" ? 'selected="selected"' : ''}>ENTREGUE</option>
            </select><br><br/><br>
            
            <h3>Escolha o(s) produto(s):</h3>
            <c:forEach items="${form.produtoPedidos}" var="produtoPedido">
                <input type="checkbox" name="produtoId" value="${produtoPedido.produto.id}" checked>${produtoPedido.produto.nome}.
                Qtd: <input id="${produtoPedido.produto.id}" type="number" name="qtd_${produtoPedido.produto.id}" value="${produtoPedido.quantidade}" min="0" max="5"><br>
                Obs: <input id="${produtoPedido.produto.id}" type="text" name="obs_${produtoPedido.produto.id}" value="${produtoPedido.obs}"><br><br>
            </c:forEach><br>
            <c:forEach items="${produtos}" var="produto">
                <input type="checkbox" name="produtoId" value="${produto.id}" >${produto.nome}.
                Qtd: <input id="${produto.id}" type="number" name="qtd_${produto.id}" value="" min="0" max="5"><br>
                Obs: <input id="${produto.id}" type="text" name="obs_${produto.id}" value=""><br><br>
            </c:forEach><br>
            
            <input type="submit" value="Pedir">
        </form>
    </body>
</html>