
<%@ page import="qmw.MenuPrincipal" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'menuPrincipal.label', default: 'menuPrincipal')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-menuPrincipal" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/estabelecimento/logout')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-menuPrincipal" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" />   <g:helpBalloon code="menuPrincipal.label"/></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list menuPrincipal">
			
				<g:if test="${menuPrincipalInstance?.nome}">
				<li class="fieldcontain">
					<span id="item-label" class="property-label"><g:message code="menuPrincipal.nome.label" default="Tipo" /></span>
					
						<span class="property-value" aria-labelledby="nome-label"><g:fieldValue bean="${menuPrincipalInstance}" field="nome"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${menuPrincipalInstance?.qtdeitem}">
				<li class="fieldcontain">
					<span id="qtdeitem-label" class="property-label"><g:message code="menuPrincipal.qtdeitem.label" default="Qtdeitem" /></span>
					
						<span class="property-value" aria-labelledby="qtdeitem-label"><g:fieldValue bean="${menuPrincipalInstance}" field="qtdeitem"/></span>
					
				</li>
				</g:if>
				<g:if test="${menuPrincipalInstance?.tipoCobranca}">
				<li class="fieldcontain">
					<span id="tipoCobranca-label" class="property-label"><g:message code="menuPrincipal.tipoCobranca.label" default="tipoCobranca" /></span>
					
						<span class="property-value" aria-labelledby="tipoCobranca-label"><g:fieldValue bean="${menuPrincipalInstance}" field="tipoCobranca"/></span>
					
				</li>
				</g:if>
				<g:if test="${menuPrincipalInstance?.localAtendimento}">
				<li class="fieldcontain">
					<span id="localAtendimento-label" class="property-label"><g:message code="menuPrincipal.localAtendimento.label" default="localAtendimento" /></span>
					
						<span class="property-value" aria-labelledby="localAtendimento-label"><g:fieldValue bean="${menuPrincipalInstance}" field="localAtendimento"/></span>
					
				</li>
				</g:if>
				<g:if test="${menuPrincipalInstance?.sequencia}">
				<li class="fieldcontain">
					<span id="sequencia-label" class="property-label"><g:message code="menuPrincipal.sequencia.label" default="sequencia" /></span>
					
						<span class="property-value" aria-labelledby="sequencia-label"><g:fieldValue bean="${menuPrincipalInstance}" field="sequencia"/></span>
					
				</li>
				</g:if>
				<g:if test="${menuPrincipalInstance?.imprimeCupom}">
				<li class="fieldcontain">
					<span id="imprimeCupom-label" class="property-label"><g:message code="menuPrincipal.imprimeCupom.label" default="imprimeCupom" /></span>
					
						<span class="property-value" aria-labelledby="imprimeCupom-label"><g:formatBoolean boolean="${menuPrincipalInstance?.imprimeCupom}"/></span>
					
				</li>
				</g:if>
				<g:if test="${menuPrincipalInstance?.menu}">
				<li class="fieldcontain">
					<span id="menu-label" class="property-label"><g:message code="menu.label" default="Menu" /></span>

						<g:each in="${menuPrincipalInstance.menu}" var="u">
						<span class="property-value" aria-labelledby="menu-label"><g:link controller="menu" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
						</g:each>
				</li>
				</g:if>
				<li class="fieldcontain">			
				<g:link class="property-value" controller="menu" action="create" params="['estabelecimento.id': menuPrincipalInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'menu.label', default: 'Menu')])}</g:link>
				</li>			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${menuPrincipalInstance?.id}" />
					<g:link class="edit" action="edit" id="${menuPrincipalInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
