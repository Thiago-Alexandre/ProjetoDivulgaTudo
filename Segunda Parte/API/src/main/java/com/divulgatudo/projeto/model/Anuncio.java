package com.divulgatudo.projeto.model;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "anuncio")
public class Anuncio {

	@Id
    private String id;
	
	private String nome;
	
	private String cliente;
	
	private DateTime dataInicio;
	
	private DateTime dataTermino;
	
	private Double investimentoPorDia;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public DateTime getDataInicio() {
		return dataInicio.withZone(DateTimeZone.UTC);
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = new DateTime(dataInicio).withZone(DateTimeZone.UTC);
	}

	public DateTime getDataTermino() {
		return dataTermino.withZone(DateTimeZone.UTC);
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = new DateTime(dataTermino).withZone(DateTimeZone.UTC);
	}

	public Double getInvestimentoPorDia() {
		return investimentoPorDia;
	}

	public void setInvestimentoPorDia(Double investimentoPorDia) {
		this.investimentoPorDia = investimentoPorDia;
	}
}