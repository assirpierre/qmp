			<table class="ajax">
				<thead>
					<tr>

                        <th>${message(code: 'pedido.' + session.estab.sistemaTrabalho + '.label', default: 'Mesa')} </th>
						<th>${message(code: 'pedido.usuario.label', default: 'Usuario')}</th>
						<th>${message(code: 'pedido.menuPrincipal.label', default: 'Menu')}</th>
						<th>${message(code: 'pedido.item.label', default: 'Item')}</th>
						<th>${message(code: 'pedido.situacao.label', default: 'Situacao')}</th>
						<th style="text-align:right">${message(code: 'pedido.preco.label', default: 'Preco')}</th>
						<th style="text-align:right">${message(code: 'pedido.qtde.label', default: 'Qtde')}</th>
						<th style="text-align:right">${message(code: 'pedido.total.label', default: 'Total')}</th>		
					</tr>
				</thead>
				<tbody>
				<g:each in="${pedidoInstanceList}" status="i" var="pedidoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>${fieldValue(bean: pedidoInstance, field: "mesa")}</td>
						<td>${fieldValue(bean: pedidoInstance, field: "usuario")}</td>
						<td>${fieldValue(bean: pedidoInstance, field: "menuPrincipal")}</td>
						<td>${fieldValue(bean: pedidoInstance, field: "item")}</td>
						<td>${message(code: 'pedido.status'+pedidoInstance?.situacao, default: 'Total')}</td>
						<td style="text-align:right"><g:formatNumber number="${pedidoInstance.preco}" type="currency" /></td>	
						<td style="text-align:right">${fieldValue(bean: pedidoInstance, field: "qtde")}</td>
						<td style="text-align:right"><g:formatNumber number="${pedidoInstance.total}" type="currency" /></td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${pedidoInstanceTotal}" params="${[mesa:pedidoInstanceList? pedidoInstanceList.first().mesa:"", pedido:pedidoInstanceList? pedidoInstanceList.first().pedidoCapa.id:"", sequencia: pedidoInstanceList? pedidoInstanceList.first().sequencia:""]}" />
				<g:if test="${pedidoInstanceTotal>0}">
				<a class="list" onclick="javascript: reImprimePedido()"><g:message code="pedido.reimprimecupom.label" /></a>
				<a class="list" onclick="javascript: reImprimeFechamento()"><g:message code="pedido.reimprimefechamento.label" /></a>
				</g:if>			
			</div>