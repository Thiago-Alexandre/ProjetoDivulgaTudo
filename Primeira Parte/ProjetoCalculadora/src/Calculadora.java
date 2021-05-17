public class Calculadora {
	
	private Double totalVisualizacoes = 0.0;
	private Double totalCliques = 0.0;
	private Double totalCompartilhamentos = 0.0;
	
	public Double calcularProjecaoAlcanceVisualizacoes(Double valorInvestido, Integer qtdNiveisCompartilhamento) {
		
		Double visualizacoesAnuncioOriginal = this.calcularVisualizacoesPorValorInvestido(valorInvestido);
		this.totalVisualizacoes = visualizacoesAnuncioOriginal;
		
		this.calcularProjecaoVisualizacoesNovoCompartilhamento(visualizacoesAnuncioOriginal, qtdNiveisCompartilhamento);
		
		return this.totalVisualizacoes;
	}

	private Double calcularVisualizacoesPorValorInvestido(Double valorInvestido) {
		return valorInvestido * 30;
	}

	private Double calcularCliquesAposVisualizacoes(Double qtdVisualizacoes) {
		return (qtdVisualizacoes / 100) * 12;
	}

	private Double calcularCompartilhamentosAposCliques(Double qtdCliques) {
		return (qtdCliques / 100) * 15;
	}  

	private Double calcularVisualizacoesAposCompartilhamentos(Double qtdCompartilhamentos) {
		return qtdCompartilhamentos * 40;
	}

	private void calcularProjecaoVisualizacoesNovoCompartilhamento(Double qtdVisualizacoes, Integer qtdRestanteNiveisCompartilhamento) {
		
		if (qtdRestanteNiveisCompartilhamento > 0) {
			
			Double novosCliques = this.calcularCliquesAposVisualizacoes(qtdVisualizacoes);
			this.totalCliques += novosCliques;
			
			Double novosCompartilhamentos = this.calcularCompartilhamentosAposCliques(novosCliques);
			this.totalCompartilhamentos += novosCompartilhamentos;
			
			Double novasVisualizacoes = this.calcularVisualizacoesAposCompartilhamentos(novosCompartilhamentos);
			this.totalVisualizacoes += novasVisualizacoes;
			this.calcularProjecaoVisualizacoesNovoCompartilhamento(novasVisualizacoes, qtdRestanteNiveisCompartilhamento - 1);
			
		}
	}
}