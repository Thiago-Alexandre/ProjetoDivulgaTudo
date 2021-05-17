package com.divulgatudo.projeto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AnuncioDTO {

	private String id;
	private String nome;
	private String cliente;
	private String dataInicio;
	private String dataTermino;
	private String investimentoPorDia;
	private String valorTotalInvestido;
	private String quantidadeMaximaVisualizacoes;
	private String quantidadeMaximaCliques;
	private String quantidadeMaximaCompartilhamentos;
}