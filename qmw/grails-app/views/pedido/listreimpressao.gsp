
<%@ page import="qmw.Pedido" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pedido.label', default: 'Pedido')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<script type="text/javascript">
            function reImprimePedido(){
                if(jQuery('input')[4].value != '')
           			jQuery('#relatorio')[0].src= '../ImpressaoCupom/pedido?cupomId=' + jQuery('input')[4].value + '&sequencia=' + jQuery('input')[7].value;
            }            
            function reImprimeFechamento(){
            	if(jQuery('input')[4].value != ''){
            		if(jQuery('input')[9].value == '')
                		alert('<g:message code="pedido.reimpressaoMsgPedAberto${session.estab.sistemaTrabalho}.label"/>');
            		else
           				jQuery('#relatorio')[0].src= '../ImpressaoCupom/fechamento?cupomId=' + jQuery('input')[4].value;
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
			<form>
			<table class="tablecustomizada" border="0">
				<tr>
					<td style="vertical-align: bottom;">
						<label for="mesa">
							<g:message code="pedido.${session.estab.sistemaTrabalho}.label" default="Mesa" />:
						</label>
						<input onclick="${remoteFunction(controller:'pedido', action:'listreimpressao',update:'target', params:'\'acao=mesaanterior&mesa=\' + jQuery(\'input\')[1].value')}" type="button" value="<g:message code="default.anterior.label" />"></input>
						<input type="text" size="4" name="mesa" value="${pedidoInstanceList? pedidoInstanceList.first().mesa:""}"
						onblur="${remoteFunction(controller:'pedido', action:'listreimpressao',update:'target', params:'\'mesa=\' + this.value')}" />
						<input onclick="${remoteFunction(controller:'pedido', action:'listreimpressao',update:'target', params:'\'acao=mesaproximo&mesa=\' + jQuery(\'input\')[1].value')}" type="button" value="<g:message code="default.proximo.label" />"></input>
						
						<label for="pedido">
							<g:message code="pedido.label" default="Pedido" />:
						</label>
						<input onclick="${remoteFunction(controller:'pedido', action:'listreimpressao',update:'target', params:'\'acao=pedidoanterior&mesa=\' + jQuery(\'input\')[1].value + \'&pedido=\' + jQuery(\'input\')[4].value')}" type="button" value="<g:message code="default.anterior.label" />"></input>
						<input type="text" size="3" name="pedido" value="${pedidoInstanceList? pedidoInstanceList.first().pedidoCapa.id:""}"  disabled />
						<input onclick="${remoteFunction(controller:'pedido', action:'listreimpressao',update:'target', params:'\'acao=pedidoproximo&mesa=\' + jQuery(\'input\')[1].value + \'&pedido=\' + jQuery(\'input\')[4].value')}" type="button" value="<g:message code="default.proximo.label" />"></input>
						
						<label for="sequencia">
							<g:message code="pedido.codigo.label" default="Sequencia" />:
						</label>
						<input onclick="${remoteFunction(controller:'pedido', action:'listreimpressao',update:'target', params:'\'acao=sequenciaanterior&mesa=\' + jQuery(\'input\')[1].value + \'&pedido=\' + jQuery(\'input\')[4].value+ \'&sequencia=\' + jQuery(\'input\')[7].value')}" type="button" value="<g:message code="default.anterior.label" />"></input>
						<input type="text" size="1" name="sequencia" value="${pedidoInstanceList? pedidoInstanceList.first().sequencia:""}" disabled" />
						<input onclick="${remoteFunction(controller:'pedido', action:'listreimpressao',update:'target', params:'\'acao=sequenciaproximo&mesa=\' + jQuery(\'input\')[1].value + \'&pedido=\' + jQuery(\'input\')[4].value+ \'&sequencia=\' + jQuery(\'input\')[7].value')}" type="button" value="<g:message code="default.proximo.label" />"></input>
						<input type="hidden" size="1" name="finalizado" value="${pedidoInstanceList? pedidoInstanceList.first().pedidoCapa.dataFim:""}" disabled" />
					</td>
					<td>
						<h1 style="margin: 0 0 0 0; border:none;"><g:message code="reimpressao.label" />   <g:helpBalloon code="reimpressao.label" mostra="true"/></h1>
					</td>
				</tr>
			</table>					
			</form>
			<div id="target">
			    <g:render template="reimpressao" model="model"/>
			</div>
			<IFRAME style="border:0px none;" src="" id="relatorio" src="" width="0" height="0" ></IFRAME>
		</div>
	</body>

</html>
