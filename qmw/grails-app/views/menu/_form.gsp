<%@ page import="qmw.Menu" %>



<div class="fieldcontain ${hasErrors(bean: menuInstance, field: 'menuPrincipal', 'error')} required">
	<label for="menuPrincipal">
		<g:message code="menu.menuPrincipal.label" default="Menu Principal" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="menuPrincipal" name="menuPrincipal.id" from="${qmw.MenuPrincipal.findAllByEstab(session.estab)}" optionKey="id" required="" value="${menuInstance?.menuPrincipal?.id}" class="many-to-one" noSelection="['': '']"/>
    <g:helpBalloon code="menu.menuPrincipal.label"/>
    <g:set var="entityName" value="${message(code: 'menuPrincipal.label', default: 'menuPrincipal')}" />
    <div class="linkadd">
        <g:link controller="menuPrincipal" class="list" action="create"><g:message code="default.adicionar.label" /></g:link>
    </div>
</div>

<div class="fieldcontain ${hasErrors(bean: menuInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="menu.nome.label" default="nome" />
	</label>
	<g:textField name="nome" size="30" maxlength="30" value="${menuInstance?.nome}"/>
    <g:helpBalloon code="menu.nome.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: menuInstance, field: 'descricao', 'error')} ">
	<label for="descricao">
		<g:message code="menu.descricao.label" default="descricao" />
	</label>
	<g:textField name="descricao" size="70" maxlength="300" value="${menuInstance?.descricao}"/>
    <g:helpBalloon code="menu.descricao.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: menuInstance, field: 'preco', 'error')} required">
	<label for="preco">
		<g:message code="menu.preco.label" default="Preco" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="preco" value="${fieldValue(bean: menuInstance, field: 'preco')}"></g:field>
    <g:helpBalloon code="menu.preco.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: menuInstance, field: 'grupoAdicionais', 'error')}">
	<label for="grupoAdicionais">
		<g:message code="menu.grupoAdicionais.label" default="Grupo Adicionais" />

	</label>
	<g:select id="grupoAdicionais" name="grupoAdicionais.id" from="${qmw.GrupoAdicionais.findAllByEstab(session.estab)}" optionKey="id" value="${menuInstance?.grupoAdicionais?.id}" noSelection="['': '']"/>
    <g:helpBalloon code="menu.grupoAdicionais.label"/>
    <div class="linkadd">
    <g:link controller="adicionais" class="list" action="create"><g:message code="default.adicionar.label" /></g:link>
    </div>
</div>

<div class="fieldcontain ${hasErrors(bean: menuInstance, field: 'sequencia', 'error')} required">
	<label for="sequencia">
		<g:message code="menu.sequencia.label" default="Sequencia" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="sequencia" type="number" value="${menuInstance.sequencia}" required=""/>
    <g:helpBalloon code="menu.sequencia.label"/>
</div>

