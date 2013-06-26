			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>			
			<table class="ajax">
				<thead>
					<tr>
					
						<th>${message(code: 'pedido.' + session.estab.sistemaTrabalho + '.label', default: 'Mesa')} </th>
						<th>${message(code: 'pedido.usuario.label', default: 'Usuario')}</th>
						<th>${message(code: 'pedido.menu.label', default: 'Menu')}</th>
						<th>${message(code: 'pedido.item.label', default: 'Item')}</th>
						<th>${message(code: 'pedido.situacao.label', default: 'Situacao')}</th>
						<th style="text-align:right">${message(code: 'pedido.preco.label', default: 'Preco')}</th>
						<th style="text-align:right">${message(code: 'pedido.qtde.label', default: 'Qtde')}</th>
						<th style="text-align:right">${message(code: 'pedido.total.label', default: 'Total')}</th>		
						<th></th>			
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
						<td>
							<input onclick="excluir(${pedidoInstance?.id})" type="button" value="${message(code: 'pedido.excluir.button', default: 'Excluir')}"></input>							
						</td>						
					</tr>
				</g:each>
				<tr class="odd">
					<td colspan=6></td>
					<td style="text-align:right">${message(code: 'pedido.subTotal.label', default: 'subTotal')}:</td> 
					<td style="text-align:right"><g:formatNumber number="${pedidoInstanceList? pedidoInstanceList.first().pedidoCapa.subTotal:0}" type="currency" /> </td>
					<td></td>
				</tr>
				<tr class="odd">
					<td colspan=6></td>
					<td style="text-align:right">${message(code: 'pedido.servico.label', default: 'Servico')}:</td> 
					<td style="text-align:right">
					<g:formRemote name="myForm1" on404="alert('not found!')" update="target" url="[controller: 'pedido', action:'atualizaServico']" >
						<input type="text" style="text-align: right" size="10" name="servico" value="<g:formatNumber number="${pedidoInstanceList? pedidoInstanceList.first().pedidoCapa.servico:0}" format="0.00" />"/>
						<input type="hidden" name="pedido" value="${pedidoInstanceList? pedidoInstanceList.first().pedidoCapa.id:0}"/>
						<input type="hidden" name="mesa" value="${pedidoInstanceList? pedidoInstanceList.first().mesa:0}"/> 
					</g:formRemote>
					</td>
					<td></td>
				</tr>
				<tr class="odd">
					<td colspan=6></td>
					<td style="text-align:right">${message(code: 'pedido.total.label', default: 'Total')}:</td> 
					<td style="text-align:right"><g:formatNumber number="${pedidoInstanceList? pedidoInstanceList.first().pedidoCapa.total:0}" type="currency" /> </td>
					<td></td>
				</tr>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${pedidoInstanceTotal}" params="${[mesa:pedidoInstanceList? pedidoInstanceList.first().mesa:"", pedido:pedidoInstanceList? pedidoInstanceList.first().pedidoCapa.id:""]}"/>
			</div>
			
			