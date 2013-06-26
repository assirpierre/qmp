package qmw

import groovy.time.*
import java.text.DateFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat

import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef

class ImpressaoCupomController {
	def jasperService
	
	def pedido() {
		def cumpomID = (long)Integer.parseInt(params['cupomId'])
		def sequencia = Integer.parseInt(params['sequencia'])
        def local = params['local']
		def pedidoInstance = Pedido.where {
			pedidoCapa.id == cumpomID
			and
			sequencia == sequencia
            and
            menuPrincipal.localAtendimento == local
		}
		HashMap<String,String> parameters = new HashMap<String,String>()
		parameters.put("IMAGE_DIR","${servletContext.getRealPath('/images')}/")
		parameters.put("mesa.label",message(code: 'pedido.' + session.estab.sistemaTrabalho + '.label'))
		parameters.put("qtde.label",message(code: 'pedido.qtde.label'))
		parameters.put("data.label",message(code: 'pedido.data.label'))
		parameters.put("pedido.label",message(code: 'pedido.label'))
		parameters.put("cabecCupom",message(code: 'pedido.cabecCupom'))
		DateFormat fDate = new SimpleDateFormat('E, dd MMM yyyy HH:mm')
		parameters.put("data",fDate.format(pedidoInstance.first().dataPedido).toString())
		pedidoInstance.each{
			it.setCupomImpresso(true);
			it.save(flush: true)
		}		
		def reportDef = new JasperReportDef(folder: 'reports', name:'CupomPedido.jrxml', fileFormat: JasperExportFormat.PDF_FORMAT, reportData: pedidoInstance.list(), parameters: parameters)
		response.outputStream << jasperService.generateReport(reportDef).toByteArray()
	}

	def fechamento() {
		def cumpomID = (long)Integer.parseInt(params['cupomId'])
		def pedidoInstance = Pedido.where {
			pedidoCapa.id == cumpomID
		}
		HashMap<String,String> parameters = new HashMap<String,String>()
		parameters.put("IMAGE_DIR","${servletContext.getRealPath('/images')}/")
        parameters.put("mesa.label",message(code: 'pedido.' + session.estab.sistemaTrabalho + '.label'))
		parameters.put("subTotal.label",message(code: 'pedido.subTotal.label'))
		parameters.put("servico.label",message(code: 'pedido.servico.label'))
		parameters.put("total.label",message(code: 'pedido.total.label'))
		parameters.put("qtde.label",message(code: 'pedido.qtde.label'))
		parameters.put("preco.label",message(code: 'pedido.preco.label'))
		parameters.put("data.label",message(code: 'pedido.data.label'))
		parameters.put("permanencia.label",message(code: 'pedido.permanencia.label'))
		parameters.put("pedido.label",message(code: 'pedido.label'))
		parameters.put("cabecCupom",message(code: 'pedido.cabecCupom'))
		parameters.put("rodapeCupom",message(code: 'pedido.rodapeCupom'))
		NumberFormat formatter = NumberFormat.getCurrencyInstance()
        DateFormat fDate = new SimpleDateFormat('E, dd MMM yyyy HH:mm')
		PedidoCapa u = pedidoInstance.first().pedidoCapa
		parameters.put("data",fDate.format(u.dataFim).toString())
		TimeDuration td = TimeCategory.minus( u.dataFim, u.dataInicio )
		parameters.put("tempoPermanencia",td.getHours() + ":" + String.format('%02d',td.getMinutes()))
		u.setCupomImpresso(true);
		u.save(flush: true)
		def reportDef = new JasperReportDef(folder: 'reports', name:'CupomFechamento.jrxml', fileFormat: JasperExportFormat.PDF_FORMAT, reportData: pedidoInstance.list(), parameters: parameters)		
		response.outputStream << jasperService.generateReport(reportDef).toByteArray()
	}
}