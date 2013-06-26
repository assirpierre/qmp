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
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'index.css')}" type="text/css"> 
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<script type="text/javascript" src="${resource(dir: 'js/jquery', file: 'jquery-1.9.1.js')}"></script>  				
		<g:layoutHead/>		
		<r:layoutResources />
	</head>
	<body>
        <img src="${resource(dir: 'images', file: 'back' + (session.back?session.back:1) + '.jpg')}" id="bg" />
        <table class="tablebody">
            <tr>
                <td height="84" bgcolor="#FFFFFF">
                    <table class="tableconteudo" >
                        <tr>
                            <td style="padding: 0" height="25" bgcolor="#FFFFFF">
            					<a style="float: left" href="http://www.qmenu.com.br"><img src="${resource(dir: 'images', file: 'qmenu.png')}" /></a>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td style="padding: 0">
                    <table class="tableconteudo" >
                        <tr>
                            <td style="text-align:center; border-top:0" height="25">
                                <div class="titulo">
                                    <g:message code="home.titulo.label"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align:center" height="30">
                                <ul class="navmenu" style="padding-left: 13em; padding-top: 0.1em; border-color: transparent" >
                                    <li><a href="${createLink(uri: '#')}"><g:message code="home.home.label"/></a></li>
                                    <li><a href="${createLink(uri: '/home/empresa')}"><g:message code="home.empresa.label"/></a></li>
                                    <li><a href="${createLink(uri: '/home/diferenciais')}"><g:message code="home.diferenciais.label"/></a></li>
                                    <li><a href="${createLink(uri: '/home/funcionamento')}"><g:message code="home.funcionamento.label"/></a></li>
                                    <li><a href="${createLink(uri: '/home/tutoriais')}"><g:message code="home.tutoriais.label"/></a></li>
                                    <li><a href="${createLink(uri: '/home/contato')}"><g:message code="home.contato.label"/></a></li>
                                    <br>
                                </ul>
                            </td>
                        </tr>
                        <tr>
                            <td>
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