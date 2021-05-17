package com.divulgatudo.projeto.services;

import org.joda.time.Days;

import com.divulgatudo.projeto.dto.AnuncioDTO;
import com.divulgatudo.projeto.model.Anuncio;

public class Calculadora {
	
	private Double totalVisualizacoes = 0.0;
	private Double totalCliques = 0.0;
	private Double totalCompartilhamentos = 0.0;
	
	public AnuncioDTO calcularProjecaoAlcanceVisualizacoes(Anuncio anuncio, Integer qtdRestanteNiveisCompartilhamento) {
		
		Double investimentoTotal = (Days.daysBetween(anuncio.getDataInicio(), anuncio.getDataTermino()).getDays() + 1) * anuncio.getInvestimentoPorDia();
		
		Double visualizacoesAnuncioOriginal = this.calcularVisualizacoesPorValorInvestido(investimentoTotal);
		this.totalVisualizacoes = visualizacoesAnuncioOriginal;
		
		this.calcularProjecaoVisualizacoesNovoCompartilhamento(visualizacoesAnuncioOriginal, qtdRestanteNiveisCompartilhamento);
		AnuncioDTO dto = new AnuncioDTO(anuncio.getId(), 
										anuncio.getNome(),
										anuncio.getCliente(),
										anuncio.getDataInicio().toString("dd/MM/yyyy"), 
										anuncio.getDataTermino().toString("dd/MM/yyyy"),
										String.format("%.2f", anuncio.getInvestimentoPorDia()),
										String.format("%.2f", investimentoTotal),
										String.format("%.2f", this.totalVisualizacoes),
										String.format("%.2f", this.totalCliques),
										String.format("%.2f", this.totalCompartilhamentos)
		);
		return dto;
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