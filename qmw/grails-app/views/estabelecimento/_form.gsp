<%@ page import="qmw.Estabelecimento" %>



		
<div class="fieldcontain ${hasErrors(bean: estabelecimentoInstance, field: 'documento', 'error')} required">
	<label for="documento">
		<g:message code="estabelecimento.documento.label" default="Documento" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="documento" required="" value="${estabelecimentoInstance?.documento}" size="17"/>
	<g:helpBalloon code="estabelecimento.documento.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estabelecimentoInstance, field: 'nomefantasia', 'error')} ">
	<label for="nomefantasia">
		<g:message code="estabelecimento.nomefantasia.label" default="Nomefantasia" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nomefantasia" required="" value="${estabelecimentoInstance?.nomefantasia}" size="25"/>
	<g:helpBalloon code="estabelecimento.nomefantasia.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estabelecimentoInstance, field: 'razaosocial', 'error')} ">
	<label for="razaosocial">
		<g:message code="estabelecimento.razaosocial.label" default="Razaosocial" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="razaosocial" required="" value="${estabelecimentoInstance?.razaosocial}" size="50"/>
	<g:helpBalloon code="estabelecimento.razaosocial.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estabelecimentoInstance, field: 'responsavel', 'error')} ">
	<label for="responsavel">
		<g:message code="estabelecimento.responsavel.label" default="Responsavel" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="responsavel"  required=""  value="${estabelecimentoInstance?.responsavel}" size="30"/>
	<g:helpBalloon code="estabelecimento.responsavel.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estabelecimentoInstance, field: 'telefone', 'error')} ">
	<label for="telefone">
		<g:message code="estabelecimento.telefone.label" default="Telefone" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="telefone"  required=""  value="${estabelecimentoInstance?.telefone}" size="15"/>
	<g:helpBalloon code="estabelecimento.telefone.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estabelecimentoInstance, field: 'cep', 'error')} ">
	<label for="cep">
		<g:message code="estabelecimento.cep.label" default="Cep" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="cep"  required="" value="${estabelecimentoInstance?.cep}" size="10"/>
	<g:helpBalloon code="estabelecimento.cep.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estabelecimentoInstance, field: 'estado', 'error')} ">
	<label for="estado">
		<g:message code="estabelecimento.estado.label" default="Estado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="estado" required=""  from="${estabelecimentoInstance.constraints.estado.inList}" value="${estabelecimentoInstance?.estado}" valueMessagePrefix="estabelecimento.estado" noSelection="['': '']"/>
	<g:helpBalloon code="estabelecimento.estado.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estabelecimentoInstance, field: 'cidade', 'error')} ">
	<label for="cidade">
		<g:message code="estabelecimento.cidade.label" default="Cidade" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="cidade" required=""  value="${estabelecimentoInstance?.cidade}" size="25"/>
	<g:helpBalloon code="estabelecimento.cidade.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estabelecimentoInstance, field: 'endereco', 'error')} ">
	<label for="endereco">
		<g:message code="estabelecimento.endereco.label" default="Endereco" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="endereco" required=""  value="${estabelecimentoInstance?.endereco}" size="50"/>
	<g:helpBalloon code="estabelecimento.endereco.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estabelecimentoInstance, field: 'numero', 'error')} ">
	<label for="numero">
		<g:message code="estabelecimento.numero.label" default="Numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="numero" required=""  value="${estabelecimentoInstance?.numero}" size="5"/>
	<g:helpBalloon code="estabelecimento.numero.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estabelecimentoInstance, field: 'complemento', 'error')} ">
	<label for="complemento">
		<g:message code="estabelecimento.complemento.label" default="Complemento" />
		
	</label>
	<g:textField name="complemento"  value="${estabelecimentoInstance?.complemento}" size="25"/>
	<g:helpBalloon code="estabelecimento.complemento.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estabelecimentoInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="estabelecimento.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required=""  value="${estabelecimentoInstance?.email}" size="30"/>
	<g:helpBalloon code="estabelecimento.email.label"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estabelecimentoInstance, field: 'senha', 'error')} ">
	<label for="senha">
		<g:message code="estabelecimento.senha.label" default="Senha" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="password" name="senha" required=""  value="${estabelecimentoInstance?.senha}" size="10"/>
	<g:helpBalloon code="estabelecimento.senha.label"/>
</div>

<g:if test="${!session.estab}">
    <div class="fieldcontain">
        <label for="geraCadastro">
            <g:message code="estabelecimento.geraCadastro.label" default="geraCadastro" />
        </label>
        <g:checkBox name="geraCadastro" value="true" />
        <g:helpBalloon code="estabelecimento.geraCadastro.label"/>
    </div>
</g:if>

