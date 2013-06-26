
<%@ page import="qmw.Menu" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'menu.label', default: 'Menu')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-menu" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/estabelecimento/logout')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-menu" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" />   <g:helpBalloon code="menu.label"/></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list menu">
			
				<g:if test="${menuInstance?.menuPrincipal}">
				<li class="fieldcontain">
					<span id="menuPrincipal-label" class="property-label"><g:message code="menu.menuPrincipal.label" default="Menu Principal" /></span>
					
						<span class="property-value" aria-labelledby="menuPrincipal-label"><g:link controller="menuPrincipal" action="show" id="${menuInstance?.menuPrincipal?.id}">${menuInstance?.menuPrincipal?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>

                <g:if test="${menuInstance?.nome}">
                    <li class="fieldcontain">
                        <span id="nome-label" class="property-label"><g:message code="menu.nome.label" default="nome" /></span>

                        <span class="property-value" aria-labelledby="nome-label"><g:fieldValue bean="${menuInstance}" field="nome"/></span>

                    </li>
                </g:if>

				<g:if test="${menuInstance?.descricao}">
				<li class="fieldcontain">
					<span id="descricao-label" class="property-label"><g:message code="menu.descricao.label" default="descricao" /></span>

						<span class="property-value" aria-labelledby="descricao-label"><g:fieldValue bean="${menuInstance}" field="descricao"/></span>

				</li>
				</g:if>

				<g:if test="${menuInstance?.preco}">
				<li class="fieldcontain">
					<span id="preco-label" class="property-label"><g:message code="menu.preco.label" default="Preco" /></span>
					
						<span class="property-value" aria-labelledby="preco-label"><g:formatNumber number="${menuInstance.preco}" type="currency" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${menuInstance?.grupoAdicionais}">
				<li class="fieldcontain">
					<span id="grupoAdicionais-label" class="property-label"><g:message code="menu.grupoAdicionais.label" default="Grupo Adicionais" /></span>
					
						<span class="property-value" aria-labelledby="grupoAdicionais-label"><g:link controller="grupoAdicionais" action="show" id="${menuInstance?.grupoAdicionais?.id}">${menuInstance?.grupoAdicionais?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
						
				<g:if test="${menuInstance?.sequencia}">
				<li class="fieldcontain">
					<span id="sequencia-label" class="property-label"><g:message code="menu.sequencia.label" default="Sequencia" /></span>
					
						<span class="property-value" aria-labelledby="sequencia-label"><g:fieldValue bean="${menuInstance}" field="sequencia"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${menuInstance?.id}" />
					<g:link class="edit" action="edit" id="${menuInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
