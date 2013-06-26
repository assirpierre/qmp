
			<table>
				<thead>
					<tr>
					
						<th><g:message code="menu.sequencia.label" default="Sequencia" /></th>
                        <th><g:message code="menu.nome.label" default="nome" /></th>
						<g:sortableColumn property="descricao" title="${message(code: 'menu.descricao.label', default: 'descricao')}" />
						<g:sortableColumn property="preco" title="${message(code: 'menu.preco.label', default: 'Preco')}" />
						<th><g:message code="menu.grupoAdicionais.label" default="Grupo Adicionais" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${menuInstanceList}" status="i" var="menuInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
                        <td><g:link action="show" id="${menuInstance.id}">${fieldValue(bean: menuInstance, field: "sequencia")}</g:link></td>
						<td><g:link action="show" id="${menuInstance.id}">${fieldValue(bean: menuInstance, field: "nome")}</g:link></td>
						<td>${fieldValue(bean: menuInstance, field: "descricao")}</td>
						<td><g:formatNumber number="${menuInstance.preco}" type="currency" /></td>
						<td>${fieldValue(bean: menuInstance, field: "grupoAdicionais")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate params="${[menuPrincipal:menuInstanceList? menuInstanceList.first().menuPrincipal.id:""]}" total="${menuInstanceTotal}" />
			</div>
		