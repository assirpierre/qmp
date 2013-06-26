
<%@ page import="qmw.Mesa" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: session.estab.sistemaTrabalho + '.label', default: 'Mesa')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-mesa" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/estabelecimento/logout')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-mesa" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" />   <g:helpBalloon code="${session.estab.sistemaTrabalho}.label"/></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list mesa">
			
				<g:if test="${mesaInstance?.numero}">
				<li class="fieldcontain">
					<span id="numero-label" class="property-label"><g:message code="mesa.numero.label" default="Numero" /></span>
					
						<span class="property-value" aria-labelledby="numero-label"><g:fieldValue bean="${mesaInstance}" field="numero"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${mesaInstance?.dataultsituacao}">
				<li class="fieldcontain">
					<span id="dataultsituacao-label" class="property-label"><g:message code="mesa.dataultsituacao.label" default="dataultsituacao" /></span>
					
						<span class="property-value" aria-labelledby="dataultsituacao-label"><g:formatDate date="${mesaInstance?.dataultsituacao}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${mesaInstance?.id}" />
					<g:link class="edit" action="edit" id="${mesaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    <g:link class="create" controller="menu" action="create" params="['estabelecimento.id': estabelecimentoInstance?.id]">
                        ${message(code: 'default.proximopasso.label', args: [message(code: 'usuario.label', default: 'Proximo passo')])}
                        ${message(code: 'default.add.label', args: [message(code: 'menu.label', default: 'Menu')])}</g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
