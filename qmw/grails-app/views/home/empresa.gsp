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
        <td style="border-top:0">
            <p><strong><g:message code="empresa.titulo.label" /></strong></p>
            <p><img alt="Galleria Plaza" src="http://qconnect.com.br/wordpress/wp-content/uploads/2013/01/galleria.jpg" width="224" height="144" /></p>
        </td>
        <td style="border-top:0">
            <br>
            <p><g:message code="empresa.descricao1.label" /></p>
        </td>
        <td rowspan="2" width="320"  style="vertical-align: middle; padding-left: 3em; border-top:0">
                <p><strong><g:message code="empresa.vantagemTitulo.label" /></strong></p>
                <g:message code="empresa.vantagemTopicos.label" />
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <g:message code="empresa.descricao2.label" />
        </td>
    </tr>
</table>
</body>
</html>
