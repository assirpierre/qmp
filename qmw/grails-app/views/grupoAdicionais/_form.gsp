<%@ page import="qmw.GrupoAdicionais" %>



<div class="fieldcontain ${hasErrors(bean: grupoAdicionaisInstance, field: 'descricao', 'error')} ">
	<label for="descricao">
		<g:message code="grupoAdicionais.descricao.label" default="Descricao" />
		
	</label>
	<g:textField name="descricao" maxlength="30" size="30" value="${grupoAdicionaisInstance?.descricao}"/>
    <g:helpBalloon code="grupoAdicionais.descricao.label"/>
</div>

