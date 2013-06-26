<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="mainindex"/>
    <title>QMenu</title>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#userName").focus();
        });
    </script>
</head>
<body>
    <a href="#create-usuario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <div id="create-usuario" class="content scaffold-create" role="main">
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <g:else>
            <br><br>
        </g:else>
        <g:hasErrors bean="${usuarioInstance}">
            <ul class="errors" role="alert">
                <g:eachError bean="${usuarioInstance}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
        </g:hasErrors>
        <g:form action="save" >
            <div class="fieldcontain required">
                <label for="tipoContato">
                    <g:message code="contato.tipoContato.label" />
                    <span class="required-indicator">*</span>
                </label>
                <g:select name="tipoContato" from="${['D', 'S', 'P', 'O']}"
                          valueMessagePrefix="contato.tipoContato" required=""  class="many-to-one" noSelection="['': '']"/>
            </div>
            <div class="fieldcontain required">
                <label for="nome">
                    <g:message code="contato.nome.label" />
                    <span class="required-indicator">*</span>
                </label>
                <g:textField name="nome" required="" size="30"/>
            </div>
            <div class="fieldcontain required">
                <label for="email">
                    <g:message code="contato.email.label" />
                    <span class="required-indicator">*</span>
                </label>
                <g:textField name="email" required="" size="50"/>
            </div>
            <div class="fieldcontain required">
                <label for="mensagem">
                    <g:message code="contato.mensagem.label" />
                    <span class="required-indicator">*</span>
                </label>
                <g:textArea name="mensagem" required="" />
            </div>
            <br>
            <fieldset class="buttons">
                <g:submitButton name="create" class="save" value="${message(code: 'default.enviar.label', default: 'Enviar')}" />
            </fieldset>
            <p align="right"><a href='mailto:qconnect@qconnect.com.br'>qconnect@qconnect.com.br</a>  <span class='phone'> </span> +55 (11) 3407.0906</p>
        </g:form>
    </div>
</body>
</html>