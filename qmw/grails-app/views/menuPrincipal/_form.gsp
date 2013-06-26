<%@ page import="qmw.MenuPrincipal" %>



<div class="fieldcontain ${hasErrors(bean: menuPrincipalInstance, field: 'nome', 'error')} ">
    <label for="nome">
        <g:message code="menuPrincipal.nome.label" default="nome" />
    </label>
    <g:textField name="nome" size="30" maxlength="30" value="${menuPrincipalInstance?.nome}"/>
    <g:helpBalloon code="menuPrincipal.nome.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: menuPrincipalInstance, field: 'qtdeitem', 'error')} required">
	<label for="qtdeitem">
		<g:message code="menuPrincipal.qtdeitem.label" default="Qtdeitem" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="qtdeitem" type="number" min="1" value="${menuPrincipalInstance.qtdeitem}" required=""/>
    <g:helpBalloon code="menuPrincipal.qtdeitem.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: menuPrincipalInstance, field: 'tipoCobranca', 'error')} required">
	<label for="tipoCobranca">
		<g:message code="menuPrincipal.tipoCobranca.label" default="Tipo Cobranca" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="tipoCobranca" from="${menuPrincipalInstance.constraints.tipoCobranca.inList}" value="${menuPrincipalInstance?.tipoCobranca}" valueMessagePrefix="menuPrincipal.tipoCobranca" noSelection="['': '']"/>
    <g:helpBalloon code="menuPrincipal.tipoCobranca.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: menuPrincipalInstance, field: 'localAtendimento', 'error')} required">
	<label for="localAtendimento">
		<g:message code="menuPrincipal.localAtendimento.label" default="localAtendimento" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="localAtendimento" from="${menuPrincipalInstance.constraints.localAtendimento.inList}" value="${menuPrincipalInstance?.localAtendimento}" valueMessagePrefix="menuPrincipal.localAtendimento" noSelection="['': '']"/>
    <g:helpBalloon code="menuPrincipal.localAtendimento.label"/>
</div>


<div class="fieldcontain ${hasErrors(bean: menuPrincipalInstance, field: 'sequencia', 'error')} required">
	<label for="sequencia">
		<g:message code="menuPrincipal.sequencia.label" default="Sequencia" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="sequencia" type="number" value="${menuPrincipalInstance.sequencia}" required=""/>
    <g:helpBalloon code="menuPrincipal.sequencia.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: menuPrincipalInstance, field: 'imprimeCupom', 'error')} ">
	<label for="imprimeCupom">
		<g:message code="menuPrincipal.imprimeCupom.label" default="imprimeCupom" />
		
	</label>
	<g:checkBox name="imprimeCupom" value="${estabelecimentoInstance?.delivery}" />
    <g:helpBalloon code="menuPrincipal.imprimeCupom.label"/>
</div>
