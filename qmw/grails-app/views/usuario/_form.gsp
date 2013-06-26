<%@ page import="qmw.Usuario" %>



<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'sequencia', 'error')} ">
	<label for="codigo">
		<g:message code="usuario.codigo.label" default="Codigo" />
		
	</label>
	<g:textField name="codigo" value="${usuarioInstance?.codigo}"/>
	<g:helpBalloon code="usuario.codigo.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'senha', 'error')} ">
	<label for="senha">
		<g:message code="usuario.senha.label" default="Senha" />
		
	</label>
	<g:field type="password" name="senha" value="${usuarioInstance?.senha}"/>
	<g:helpBalloon code="usuario.senha.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="usuario.nome.label" default="Nome" />
		
	</label>
	<g:textField name="nome" value="${usuarioInstance?.nome}"/>
	<g:helpBalloon code="usuario.nome.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="usuario.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" value="${usuarioInstance?.email}" size="50"/>
	<g:helpBalloon code="usuario.email.label"/>
</div>

<g:hiddenField  name="estab.id" value="${session?.estab.id}" />