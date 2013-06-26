
<%@ page import="qmw.MenuPrincipal" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'menuPrincipal.label', default: 'menuPrincipal')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>	
		<a href="#list-menuPrincipal" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/estabelecimento/logout')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>		
 
		<div id="list-menuPrincipal" class="content scaffold-list" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" />   <g:helpBalloon code="menuPrincipal.label" mostra="true"/></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="sequencia" title="${message(code: 'menuPrincipal.sequencia.label', default: 'Sequencia')}" />
						<g:sortableColumn property="nome" title="${message(code: 'menuPrincipal.nome.label', default: 'Nome')}" />
						<g:sortableColumn property="qtdeitem" title="${message(code: 'menuPrincipal.qtdeitem.label', default: 'Qtdeitem')}" />
						<g:sortableColumn property="tipoCobranca" title="${message(code: 'menuPrincipal.tipoCobranca.label', default: 'Tipo Cobranca')}" />
						<g:sortableColumn property="localAtendimento" title="${message(code: 'menuPrincipal.localAtendimento.label', default: 'localAtendimento')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${menuPrincipalInstanceList}" status="i" var="menuPrincipalInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link action="show" id="${menuPrincipalInstance.id}">${fieldValue(bean: menuPrincipalInstance, field: "sequencia")}</g:link></td>
						<td><g:link action="show" id="${menuPrincipalInstance.id}">${fieldValue(bean: menuPrincipalInstance, field: "nome")}</g:link></td>
						<td>${fieldValue(bean: menuPrincipalInstance, field: "qtdeitem")}</td>
						<td>${fieldValue(bean: menuPrincipalInstance, field: "tipoCobranca")}</td>
						<td>${fieldValue(bean: menuPrincipalInstance, field: "localAtendimento")}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${menuPrincipalInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
