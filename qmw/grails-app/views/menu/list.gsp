
<%@ page import="qmw.Menu" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'menu.label', default: 'Menu')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-menu" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/estabelecimento/logout')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-menu" class="content scaffold-list" role="main">
			<table class="tablecustomizada" border="0">
				<tr>
					<td style="vertical-align: bottom;">
						<label for="menuPrincipal">
							<g:message code="menu.menuPrincipal.label" default="menuPrincipal" />
						</label>
						<g:select name="menuPrincipal" from="${qmw.MenuPrincipal.findAllByEstab(session.estab)}" optionKey="id" value="${menuInstanceList? menuInstanceList.first().menuPrincipal.id:""}"
						onchange="${remoteFunction(controller:'menu', action:'list',update:'target', params:'\'menuPrincipal=\' + this.value' )}" />
                    </td>
					<td>
						<h1 style="margin: 0 0 0 0; border:none;"><g:message code="default.list.label" args="[entityName]" />   <g:helpBalloon code="menu.label" mostra="true"/></h1>
					</td>
				</tr>
			</table>	
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div id="target">
			    <g:render template="list" model="model"/>
			</div>
		</div>
	</body>
</html>
