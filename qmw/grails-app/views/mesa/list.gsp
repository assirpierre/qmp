
<%@ page import="qmw.Mesa" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: session.estab.sistemaTrabalho + '.label', default: 'Mesa')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-mesa" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/estabelecimento/logout')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-mesa" class="content scaffold-list" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" />   <g:helpBalloon code="${session.estab.sistemaTrabalho}.label" mostra="true"/></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="numero" title="${message(code: 'mesa.numero.label', default: 'Numero')}" />
					
						<g:sortableColumn property="dataultsituacao" title="${message(code: 'mesa.dataultsituacao.label', default: 'dataultsituacao')}" />
										
					</tr>
				</thead>
				<tbody>
				<g:each in="${mesaInstanceList}" status="i" var="mesaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${mesaInstance.id}">${fieldValue(bean: mesaInstance, field: "numero")}</g:link></td>
					
						<td><g:formatDate date="${mesaInstance.dataultsituacao}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${mesaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
