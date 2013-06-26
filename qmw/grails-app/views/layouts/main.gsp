
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'qmenulogo.png')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'qmenulogo.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'qmenulogo.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css"> 
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
        <script type="text/javascript" src="${resource(dir: 'js/jquery', file: 'jquery-1.9.1.js')}"></script>
		<g:helpBalloons/>
		<g:layoutHead/>
        <r:layoutResources />
	</head>
	<body>
		<img src="${resource(dir: 'images', file: 'back' + (session.back?session.back:1) + '.jpg')}" id="bg" />
        <table class="tablebody" >
            <tr>
                <td height="84" bgcolor="#FFFFFF">
                    <table class="tableconteudo">
                        <tr>
                            <td style="padding: 0"  bgcolor="#FFFFFF">
                                <a style="float: left" href="http://www.qmenu.com.br"><img src="${resource(dir: 'images', file: 'qmenu.png')}" /></a>
                            </td>
                            <td bgcolor="#FFFFFF" id="logo">
                                ${session.estab?.nomefantasia }
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td style="padding: 0">
                    <table class="tableconteudo" >
                        <tr>
                            <td style="border-top:0">
                                <ul class="navmenu" style="height: 26px; padding-left: 16em;">
                                    <li><a href="${createLink(uri: '/estabelecimento')}"><g:message code="estabelecimento.label"/></a></li>
                                    <li><a href="${createLink(uri: '/usuario')}"><g:message code="usuario.label"/></a></li>
                                    <li><a href="${createLink(uri: '/mesa')}"><g:message code="${session.estab?session.estab.sistemaTrabalho:"mesa"}.label"/></a></li>
                                    <li><a href="${createLink(uri: '/menu')}"><g:message code="menu.label"/></a></li>
                                    <li><a href="${createLink(uri: '/pedido/listplocal')}"><g:message code="pedido.label"/></a></li>
                                </ul>
                    			<g:layoutBody/>
                                <div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
                                <g:javascript library="application"/>
                                <r:layoutResources />
                             </td>
                        </tr>
                    </table>
                </td>
            </tr>
		</table>
	</body>
</html>