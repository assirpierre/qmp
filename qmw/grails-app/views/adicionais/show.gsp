
<%@ page import="qmw.Adicionais" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'adicionais.label', default: 'Adicionais')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-adicionais" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/estabelecimento/logout')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-adicionais" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" />   <g:helpBalloon code="adicionais.label"/></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list adicionais">
			
				<g:if test="${adicionaisInstance?.grupoAdicionais}">
				<li class="fieldcontain">
					<span id="grupoAdicionais-label" class="property-label"><g:message code="adicionais.grupoAdicionais.label" default="Grupo Adicionais" /></span>
					
						<span class="property-value" aria-labelledby="grupoAdicionais-label"><g:link controller="grupoAdicionais" action="show" id="${adicionaisInstance?.grupoAdicionais?.id}">${adicionaisInstance?.grupoAdicionais?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>

                <g:if test="${adicionaisInstance?.nome}">
                    <li class="fieldcontain">
                        <span id="nome-label" class="property-label"><g:message code="adicionais.nome.label" default="nome" /></span>

                        <span class="property-value" aria-labelledby="nome-label"><g:fieldValue bean="${adicionaisInstance}" field="nome"/></span>

                    </li>
                </g:if>
			
				<g:if test="${adicionaisInstance?.descricao}">
				<li class="fieldcontain">
					<span id="descricao-label" class="property-label"><g:message code="adicionais.descricao.label" default="descricao" /></span>
					
						<span class="property-value" aria-labelledby="descricao-label"><g:fieldValue bean="${adicionaisInstance}" field="descricao"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${adicionaisInstance?.preco}">
				<li class="fieldcontain">
					<span id="preco-label" class="property-label"><g:message code="adicionais.preco.label" default="Preco" /></span>
					
						<span class="property-value" aria-labelledby="preco-label"><g:formatNumber number="${adicionaisInstance.preco}" type="currency" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${adicionaisInstance?.id}" />
					<g:link class="edit" action="edit" id="${adicionaisInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
