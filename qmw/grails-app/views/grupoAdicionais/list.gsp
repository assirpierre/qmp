
<%@ page import="qmw.GrupoAdicionais" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'grupoAdicionais.label', default: 'GrupoAdicionais')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-grupoAdicionais" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/estabelecimento/logout')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-grupoAdicionais" class="content scaffold-list" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" />   <g:helpBalloon code="grupoAdicionais.label" mostra="true"/></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="descricao" title="${message(code: 'grupoAdicionais.descricao.label', default: 'Descricao')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${grupoAdicionaisInstanceList}" status="i" var="grupoAdicionaisInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${grupoAdicionaisInstance.id}">${fieldValue(bean: grupoAdicionaisInstance, field: "descricao")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${grupoAdicionaisInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
