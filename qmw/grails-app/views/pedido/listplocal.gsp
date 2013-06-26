
<%@ page import="qmw.Pedido" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pedido.label', default: 'Pedido')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<script type="text/javascript">
            jQuery(document).ready(setTimeout("refresh()",1000));
            function atender(idPedido){
            	if (confirm('${message(code: 'pedido.atender.confirma', default: 'Confirma?')}')) {
            		jQuery.ajax({type:'POST',
            					 data: {id:idPedido, localAtendimento: jQuery('select')[0].value}, 
                				 url:'/qmw/pedido/atender',
                				 success:function(data,textStatus){jQuery('#target').html(data);},
                				 error:function(XMLHttpRequest,textStatus,errorThrown){}});
                	return false; 
                }
            }
                
            function refresh(){
            	setTimeout("${remoteFunction(controller:'pedido', action:'listplocal',update:'target', params:'\'localAtendimento=\' + jQuery(\'select\')[0].value',onSuccess: 'refresh()' )}",10000);
            }
		</script>
	</head>
	<body>
		<a href="#list-pedido" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="listplocal"><g:message code="pedidoLocal.label" /></g:link></li>
				<li><g:link class="list" action="listpmesa"><g:message code="pedido${session.estab.sistemaTrabalho}.label" /></g:link></li>
				<li><g:link class="list" action="listreimpressao"><g:message code="reimpressao.label" /></g:link></li>
			</ul>
		</div>
		<div id="list-pedido" class="content scaffold-list" role="main">
			<table class="tablecustomizada" border="0">
				<tr>
					<td style="vertical-align: bottom;">
						<label for="localAtendimento">
							<g:message code="menuPrincipal.localAtendimento.label" default="localAtendimento" />
						</label>
						<g:select name="localAtendimento" from="${qmw.MenuPrincipal.constraints.localAtendimento.inList}" value="1" valueMessagePrefix="menuPrincipal.localAtendimento" 
						onchange="${remoteFunction(controller:'pedido', action:'listplocal',update: 'target', params:'\'localAtendimento=\' + this.value', onError:'alert(\'error\')' )}" />
					</td>
					<td>
						<h1 style="margin: 0 0 0 0; border:none;"><g:message code="pedidoLocal.label" />   <g:helpBalloon code="pedidoLocal.label" mostra="true"/></h1>
					</td>
				</tr>
			</table>					
			<div id="target">
			    <g:render template="listplocal" model="model"/>
			</div>
			<IFRAME style="border:0px none;" src="" id="relatorio" src="" width="0" height="0" ></IFRAME>		
			<IFRAME style="border:0px none;" src="" id="relatoriofechamento" src="" width="0" height="0" ></IFRAME>		
		</div>
	</body>

</html>
