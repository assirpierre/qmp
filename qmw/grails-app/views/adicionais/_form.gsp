<%@ page import="qmw.Adicionais" %>



<div class="fieldcontain ${hasErrors(bean: adicionaisInstance, field: 'grupoAdicionais', 'error')} required">
	<label for="grupoAdicionais">
		<g:message code="adicionais.grupoAdicionais.label" default="Grupo Adicionais" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="grupoAdicionais" name="grupoAdicionais.id" from="${qmw.GrupoAdicionais.findAllByEstab(session.estab)}" optionKey="id" required="" value="${adicionaisInstance?.grupoAdicionais?.id}" class="many-to-one" noSelection="['': '']"/>
    <g:helpBalloon code="adicionais.grupoAdicionais.label"/>
    <div class="linkadd">
        <g:link controller="grupoAdicionais" class="list" action="create"><g:message code="default.adicionar.label" /></g:link>
    </div>
</div>

<div class="fieldcontain ${hasErrors(bean: adicionaisInstance, field: 'nome', 'error')} ">
    <label for="nome">
        <g:message code="adicionais.nome.label" default="nome" />
    </label>
    <g:textField name="nome" size="30" maxlength="30" value="${adicionaisInstance?.nome}"/>
    <g:helpBalloon code="adicionais.nome.label"/>
</div>


<div class="fieldcontain ${hasErrors(bean: adicionaisInstance, field: 'descricao', 'error')} ">
	<label for="descricao">
		<g:message code="adicionais.descricao.label" default="descricao" />
		
	</label>
	<g:textField name="descricao" size="70" maxlength="300" value="${adicionaisInstance?.descricao}"/>
    <g:helpBalloon code="adicionais.descricao.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: adicionaisInstance, field: 'preco', 'error')} required">
	<label for="preco">
		<g:message code="adicionais.preco.label" default="Preco" />
		<span class="required-indicator">*</span>
	</label>	
	<g:field name="preco" value="${fieldValue(bean: adicionaisInstance, field: 'preco')}" required=""/>
    <g:helpBalloon code="adicionais.preco.label"/>
</div>

