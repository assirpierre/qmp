
<%@ page import="qmw.Estabelecimento" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'estabelecimento.label', default: 'Estabelecimento')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-estabelecimento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/estabelecimento/logout')}"><g:message code="default.home.label"/></a></li>
				<g:if test="${session.estab && session.estab.documento.equals('101010')}">
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				</g:if>
			</ul>
		</div>
		<div id="show-estabelecimento" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" />  <g:helpBalloon code="estabelecimento.label"/></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list estabelecimento">
			
				<g:if test="${estabelecimentoInstance?.documento}">
				<li class="fieldcontain">
					<span id="documento-label" class="property-label"><g:message code="estabelecimento.documento.label" default="Documento" /></span>
					
						<span class="property-value" aria-labelledby="documento-label"><g:fieldValue bean="${estabelecimentoInstance}" field="documento"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${estabelecimentoInstance?.nomefantasia}">
				<li class="fieldcontain">
					<span id="nomefantasia-label" class="property-label"><g:message code="estabelecimento.nomefantasia.label" default="Nomefantasia" /></span>
					
						<span class="property-value" aria-labelledby="nomefantasia-label"><g:fieldValue bean="${estabelecimentoInstance}" field="nomefantasia"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${estabelecimentoInstance?.razaosocial}">
				<li class="fieldcontain">
					<span id="razaosocial-label" class="property-label"><g:message code="estabelecimento.razaosocial.label" default="Razaosocial" /></span>
					
						<span class="property-value" aria-labelledby="razaosocial-label"><g:fieldValue bean="${estabelecimentoInstance}" field="razaosocial"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${estabelecimentoInstance?.responsavel}">
				<li class="fieldcontain">
					<span id="responsavel-label" class="property-label"><g:message code="estabelecimento.responsavel.label" default="Responsavel" /></span>
					
						<span class="property-value" aria-labelledby="responsavel-label"><g:fieldValue bean="${estabelecimentoInstance}" field="responsavel"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${estabelecimentoInstance?.telefone}">
				<li class="fieldcontain">
					<span id="telefone-label" class="property-label"><g:message code="estabelecimento.telefone.label" default="Telefone" /></span>
					
						<span class="property-value" aria-labelledby="telefone-label"><g:fieldValue bean="${estabelecimentoInstance}" field="telefone"/></span>
					
				</li>
				</g:if>
						
				<g:if test="${estabelecimentoInstance?.cep}">
				<li class="fieldcontain">
					<span id="cep-label" class="property-label"><g:message code="estabelecimento.cep.label" default="Cep" /></span>
					
						<span class="property-value" aria-labelledby="cep-label"><g:fieldValue bean="${estabelecimentoInstance}" field="cep"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${estabelecimentoInstance?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="estabelecimento.estado.label" default="Estado" /></span>
					
						<span class="property-value" aria-labelledby="estado-label"><g:fieldValue bean="${estabelecimentoInstance}" field="estado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${estabelecimentoInstance?.cidade}">
				<li class="fieldcontain">
					<span id="cidade-label" class="property-label"><g:message code="estabelecimento.cidade.label" default="Cidade" /></span>
					
						<span class="property-value" aria-labelledby="cidade-label"><g:fieldValue bean="${estabelecimentoInstance}" field="cidade"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${estabelecimentoInstance?.endereco}">
				<li class="fieldcontain">
					<span id="endereco-label" class="property-label"><g:message code="estabelecimento.endereco.label" default="Endereco" /></span>
					
						<span class="property-value" aria-labelledby="endereco-label"><g:fieldValue bean="${estabelecimentoInstance}" field="endereco"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${estabelecimentoInstance?.numero}">
				<li class="fieldcontain">
					<span id="numero-label" class="property-label"><g:message code="estabelecimento.numero.label" default="Numero" /></span>
					
						<span class="property-value" aria-labelledby="numero-label"><g:fieldValue bean="${estabelecimentoInstance}" field="numero"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${estabelecimentoInstance?.complemento}">
				<li class="fieldcontain">
					<span id="complemento-label" class="property-label"><g:message code="estabelecimento.complemento.label" default="Complemento" /></span>
					
						<span class="property-value" aria-labelledby="complemento-label"><g:fieldValue bean="${estabelecimentoInstance}" field="complemento"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${estabelecimentoInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="estabelecimento.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${estabelecimentoInstance}" field="email"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${estabelecimentoInstance?.nolocal}">
				<li class="fieldcontain">
					<span id="nolocal-label" class="property-label"><g:message code="estabelecimento.nolocal.label" default="nolocal" /></span>
					
						<span class="property-value" aria-labelledby="nolocal-label"><g:formatBoolean boolean="${estabelecimentoInstance?.nolocal}" /></span>
					
				</li>
				</g:if>				
			
				<g:if test="${estabelecimentoInstance?.viagem}">
				<li class="fieldcontain">
					<span id="viagem-label" class="property-label"><g:message code="estabelecimento.viagem.label" default="Viagem" /></span>
					
						<span class="property-value" aria-labelledby="viagem-label"><g:formatBoolean boolean="${estabelecimentoInstance?.viagem}" /></span>
					
				</li>
				</g:if>				
			
				<g:if test="${estabelecimentoInstance?.delivery}">
				<li class="fieldcontain">
					<span id="delivery-label" class="property-label"><g:message code="estabelecimento.delivery.label" default="delivery" /></span>
					
						<span class="property-value" aria-labelledby="delivery-label"><g:formatBoolean boolean="${estabelecimentoInstance?.delivery}" /></span>
					
				</li>
				</g:if>				

				<g:if test="${estabelecimentoInstance?.localFechamento}">
				<li class="fieldcontain">
					<span id="localFechamento-label" class="property-label"><g:message code="estabelecimento.localFechamento.label" default="localFechamento" /></span>
					
						<span class="property-value" aria-labelledby="localFechamento-label"><g:fieldValue bean="${estabelecimentoInstance}" field="localFechamento"/></span>
					
				</li>
				</g:if>				

				<g:if test="${estabelecimentoInstance?.taxaServico}">
				<li class="fieldcontain">
					<span id="taxaServico-label" class="property-label"><g:message code="estabelecimento.taxaServico.label" default="taxaServico" /></span>
					
						<span class="property-value" aria-labelledby="taxaServico-label"><g:fieldValue bean="${estabelecimentoInstance}" field="taxaServico"/></span>
					
				</li>
				</g:if>

                <g:if test="${estabelecimentoInstance?.sistemaTrabalho}">
                    <li class="fieldcontain">
                        <span id="sistemaTrabalho-label" class="property-label"><g:message code="estabelecimento.sistemaTrabalho.label" default="sistemaTrabalho" /></span>

                        <span class="property-value" aria-labelledby="sistemaTrabalho-label"><g:fieldValue bean="${estabelecimentoInstance}" field="sistemaTrabalho"/></span>

                    </li>
                </g:if>

                <g:if test="${estabelecimentoInstance?.usuario}">
				<li class="fieldcontain">
					<span id="usuario-label" class="property-label"><g:message code="usuario.label" default="Usuario" /></span>
					
						<g:each in="${estabelecimentoInstance.usuario}" var="u">
						<span class="property-value" aria-labelledby="usuario-label"><g:link controller="usuario" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
						</g:each>
				</li>
				</g:if>
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${estabelecimentoInstance?.id}" />
					<g:link class="edit" action="edit" id="${estabelecimentoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:link class="edit" action="editParam" id="${estabelecimentoInstance?.id}"><g:message code="estabelecimento.editarParam.label" default="Edit Param" /></g:link>
					<g:if test="${session.estab && session.estab.documento.equals('101010')}">
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</g:if>
					<g:link class="create" controller="usuario" action="create" params="['estabelecimento.id': estabelecimentoInstance?.id]">
						${message(code: 'default.proximopasso.label', args: [message(code: 'usuario.label', default: 'Proximo passo')])}
						${message(code: 'default.add.label', args: [message(code: 'usuario.label', default: 'Usuario')])}</g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
