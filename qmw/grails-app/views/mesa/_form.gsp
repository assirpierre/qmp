<%@ page import="qmw.Mesa" %>



<div class="fieldcontain ${hasErrors(bean: mesaInstance, field: 'numero', 'error')} required">
	<label for="numero">
		<g:message code="${session.estab.sistemaTrabalho}.numero.label" default="Numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numero" type="number" value="${mesaInstance.numero}" required=""/>
	<g:helpBalloon code="${session.estab.sistemaTrabalho}.numero.label"/>
</div>

