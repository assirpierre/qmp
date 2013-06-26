			<table class="ajax">
				<thead>
					<tr>

                        <th>${message(code: 'pedido.' + session.estab.sistemaTrabalho + '.label', default: 'Mesa')} </th>
						<th>${message(code: 'pedido.usuario.label', default: 'Usuario')}</th>
						<th>${message(code: 'pedido.menuPrincipal.label', default: 'Menu')}</th>
						<th>${message(code: 'pedido.item.label', default: 'Item')}</th>
						<th>${message(code: 'pedido.qtde.label', default: 'Qtde')}</th>
						<th>${message(code: 'pedido.adicionais.label', default: 'Adicionais')}</th>					
						<th>${message(code: 'pedido.observacao.label', default: 'Observacao')}</th>
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
						<td>${fieldValue(bean: pedidoInstance, field: "qtde")}</td>
						<td>${fieldValue(bean: pedidoInstance, field: "itemAdicional")}</td>
						<td>${fieldValue(bean: pedidoInstance, field: "observacao")}</td>
						<td>
							<input onclick="atender(${pedidoInstance?.id})" type="button" value="${message(code: 'pedido.atender.button', default: 'Atender')}">
						</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${pedidoInstanceTotal}" />
			</div>
			
			