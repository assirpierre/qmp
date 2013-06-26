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
					<div class="acesso"><g:message code="home.recursos.label"/></div>
                        <g:message code="home.recursos1.label"/><br>
                        <g:message code="home.recursos2.label"/><br>
                        <g:message code="home.recursos3.label"/>
					<div class="acesso"><g:message code="home.acessovia.label"/></div>
					<img style="margin: 0em 5em 0em;" src="${resource(dir: 'images', file: 'acesso.png')}" />
				</td>
                <td style="border-top:0">
					<div id="status" role="complementary">
						<h1 class=tableindex><g:message code="home.estabcadastrados.label"/></h1>
						<form action="estabelecimento/authenticate" method="post">
							<table class="tableindex">
								<tr>
									<td style="text-align:right" width="100" >
                                        <g:message code="home.documento.label"/>
									</td>
									<td> 
										<input type="text" name="userName" value="" id="userName" size="15"/>
									</td>
								</tr>
								<tr>
									<td style="text-align:right">
                                        <g:message code="home.senha.label"/>
									</td>
									<td>
										 <input type="password" name="password" value="" id="password" size="15" />
									</td>
								</tr>
								<tr>
									<td>
									</td>
									<td>
										<input type="submit" name="login" class="save" value="<g:message code="home.login.label"/>" id="login" />
									</td>
								</tr>
							</table>
						</form>
				        <g:if test="${flash.message}">
				        <div class="message" role="status">${flash.message}</div>
				        </g:if>
						<h1 class=tableindex><g:message code="home.novosestab.label"/></h1>
						<form action="estabelecimento/create" method="post">
							<table class="tableindex">
								<tr>
									<td width="100">
									</td>
									<td>						
										<input type="submit" name="login" class="save" value="<g:message code="home.cadastrar.label"/>" id="login" />
									</td>
								</tr>
							</table>
						</form>						
					</div>					
				</td>				
			</tr>
		</table>
	</body>
</html>
