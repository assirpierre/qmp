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
<table style="border-top:0">
    <tr>
        <td style="width:25%; border-top:0">
            <img src="${resource(dir: 'images', file: 'celular.png')}" />
        </td>
        <td style="border-top:0">
            <br>
            <p><strong><g:message code="diferenciais.titulo.label" /></strong></p>
            <g:message code="diferenciais.descricao.label" />
        </td>
        <td style="width:15%; border-top:0;vertical-align: middle">
            <div class="acesso"><g:message code="home.acessovia.label"/></div>
            <img style="margin: 0em 0em 0em;" src="${resource(dir: 'images', file: 'acesso.png')}" />
        </td>
    </tr>
</table>


</body>
</html>
