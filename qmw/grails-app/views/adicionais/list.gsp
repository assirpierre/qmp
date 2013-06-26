
<%@ page import="qmw.Adicionais" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'adicionais.label', default: 'Adicionais')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-adicionais" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/estabelecimento/logout')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-adicionais" class="content scaffold-list" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" />   <g:helpBalloon code="adicionais.label" mostra="true"/></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="adicionais.grupoAdicionais.label" default="Grupo Adicionais" /></th>
					
						<th><g:message code="adicionais.nome.label" default="nome" /></th>
					
						<g:sortableColumn property="descricao" title="${message(code: 'adicionais.descricao.label', default: 'descricao')}" />
					
						<g:sortableColumn property="preco" title="${message(code: 'adicionais.preco.label', default: 'Preco')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${adicionaisInstanceList}" status="i" var="adicionaisInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${adicionaisInstance.id}">${fieldValue(bean: adicionaisInstance, field: "grupoAdicionais")}</g:link></td>
					
						<td>${fieldValue(bean: adicionaisInstance, field: "nome")}</td>
					
						<td>${fieldValue(bean: adicionaisInstance, field: "descricao")}</td>
					
						<td><g:formatNumber number="${adicionaisInstance.preco}" type="currency" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${adicionaisInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
