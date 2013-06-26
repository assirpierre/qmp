
<%@ page import="qmw.GrupoAdicionais" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'grupoAdicionais.label', default: 'GrupoAdicionais')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-grupoAdicionais" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/estabelecimento/logout')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-grupoAdicionais" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" />   <g:helpBalloon code="grupoAdicionais.label"/></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list grupoAdicionais">
			
				<g:if test="${grupoAdicionaisInstance?.descricao}">
				<li class="fieldcontain">
					<span id="descricao-label" class="property-label"><g:message code="grupoAdicionais.descricao.label" default="Descricao" /></span>
					
						<span class="property-value" aria-labelledby="descricao-label"><g:fieldValue bean="${grupoAdicionaisInstance}" field="descricao"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${grupoAdicionaisInstance?.adicionais}">
				<li class="fieldcontain">
					<span id="usuario-label" class="property-label"><g:message code="menu.adicionais" default="Adicionais" /></span>
					
						<g:each in="${grupoAdicionaisInstance.adicionais}" var="u">
						<span class="property-value" aria-labelledby="adicionais-label"><g:link controller="adicionais" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
						</g:each>
				</li>
				</g:if>
			</ol>

			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${grupoAdicionaisInstance?.id}" />
					<g:link class="edit" action="edit" id="${grupoAdicionaisInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
