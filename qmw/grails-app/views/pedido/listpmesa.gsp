
<%@ page import="qmw.Pedido" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pedido.label', default: 'Pedido')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<script type="text/javascript">
			var vvmesa = 1;
            //var $j = jQuery.noConflict();
            function excluir(idPedido){
            	if (confirm('${message(code: 'pedido.excluir.confirma', default: 'Confirma?')}')) {
            		jQuery.ajax({type:'POST',
                				 data: {id:idPedido, mesa: jQuery('input')[1].value},
                				 url:'/qmw/pedido/excluir',
                				 success:function(data,textStatus){jQuery('#target').html(data);},
                				 error:function(XMLHttpRequest,textStatus,errorThrown){}});
                	return false;
                }
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
            <g:formRemote name="myForm0" on404="alert('not found!')" update="target" url="[controller: 'pedido', action:'listpmesa']" >
			<table class="tablecustomizada" border="0">
				<tr>
					<td style="vertical-align: bottom;">
						<label for="mesa">
							<g:message code="pedido.${session.estab.sistemaTrabalho}.label" default="Mesa" />:
						</label>
						<input onclick="${remoteFunction(controller:'pedido', action:'listpmesa',update:'target', params:'\'acao=mesaanterior&mesa=\' + jQuery(\'input\')[1].value')}" type="button" value="<g:message code="default.anterior.label" />"></input>
						<input type="text" size="4" name="mesa" value="${pedidoInstanceList? pedidoInstanceList.first().mesa:""}"
						onblur="${remoteFunction(controller:'pedido', action:'listpmesa',update:'target', params:'\'mesa=\' + this.value')}" />
						<input onclick="${remoteFunction(controller:'pedido', action:'listpmesa',update:'target', params:'\'acao=mesaproximo&mesa=\' + jQuery(\'input\')[1].value')}" type="button" value="<g:message code="default.proximo.label" />"></input>

						<label for="pedido">
							<g:message code="pedido.label" default="Pedido" />:
						</label>
						<input onclick="${remoteFunction(controller:'pedido', action:'listpmesa',update:'target', params:'\'acao=pedidoanterior&mesa=\' + jQuery(\'input\')[1].value + \'&pedido=\' + jQuery(\'input\')[4].value')}" type="button" value="<g:message code="default.anterior.label" />"></input>
						<input type="text" size="3" name="pedido" value="${pedidoInstanceList? pedidoInstanceList.first().pedidoCapa.id:""}"  disabled />
						<input onclick="${remoteFunction(controller:'pedido', action:'listpmesa',update:'target', params:'\'acao=pedidoproximo&mesa=\' + jQuery(\'input\')[1].value + \'&pedido=\' + jQuery(\'input\')[4].value')}" type="button" value="<g:message code="default.proximo.label" />"></input>
					</td>
					<td>
						<h1 style="margin: 0 0 0 0; border:none;"><g:message code="pedido${session.estab.sistemaTrabalho}.label" />   <g:helpBalloon code="pedido${session.estab.sistemaTrabalho}.label" mostra="true"/></h1>
					</td>
				</tr>
			</table>
            </g:formRemote>
			<div id="target">
			    <g:render template="listpmesa" model="model"/>
			</div>
		</div>
	</body>

</html>
