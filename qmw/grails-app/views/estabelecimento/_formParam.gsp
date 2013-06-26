<%@ page import="qmw.Estabelecimento" %>




<div class="fieldcontain ${hasErrors(bean: estabelecimentoInstance, field: 'nolocal', 'error')} ">
	<label for="nolocal">
		<g:message code="estabelecimento.nolocal.label" default="nolocal" />
		
	</label>
	<g:checkBox name="nolocal" value="${estabelecimentoInstance?.nolocal}" />
	<g:helpBalloon code="estabelecimento.nolocal.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estabelecimentoInstance, field: 'viagem', 'error')} ">
	<label for="viagem">
		<g:message code="estabelecimento.viagem.label" default="Viagem" />
		
	</label>
	<g:checkBox name="viagem" value="${estabelecimentoInstance?.viagem}" />
	<g:helpBalloon code="estabelecimento.viagem.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estabelecimentoInstance, field: 'delivery', 'error')} ">
	<label for="delivery">
		<g:message code="estabelecimento.delivery.label" default="delivery" />
		
	</label>
	<g:checkBox name="delivery" value="${estabelecimentoInstance?.delivery}" />
	<g:helpBalloon code="estabelecimento.delivery.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estabelecimentoInstance, field: 'localFechamento', 'error')} ">
	<label for="localFechamento">
		<g:message code="estabelecimento.localFechamento.label" default="localFechamento" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="localFechamento" required=""  from="${estabelecimentoInstance.constraints.localFechamento.inList}" value="${estabelecimentoInstance?.localFechamento}" valueMessagePrefix="estabelecimento.localFechamento" noSelection="['': '']"/>
	<g:helpBalloon code="estabelecimento.localFechamento.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estabelecimentoInstance, field: 'taxaServico', 'error')} ">
	<label for="taxaServico">
		<g:message code="estabelecimento.taxaServico.label" default="taxaServico" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="taxaServico" required=""  value="${estabelecimentoInstance?.taxaServico}" size="5"/>
	<g:helpBalloon code="estabelecimento.taxaServico.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estabelecimentoInstance, field: 'sistemaTrabalho', 'error')} ">
    <label for="sistemaTrabalho">
        <g:message code="estabelecimento.sistemaTrabalho.label" default="sistemaTrabalho" />
        <span class="required-indicator">*</span>
    </label>
    <g:select name="sistemaTrabalho" required=""  from="${estabelecimentoInstance.constraints.sistemaTrabalho.inList}" value="${estabelecimentoInstance?.sistemaTrabalho}" valueMessagePrefix="estabelecimento.sistemaTrabalho" noSelection="['': '']"/>
    <g:helpBalloon code="estabelecimento.sistemaTrabalho.label"/>
</div>