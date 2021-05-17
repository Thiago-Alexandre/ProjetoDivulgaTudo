package com.divulgatudo.projeto.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divulgatudo.projeto.dto.AnuncioDTO;
import com.divulgatudo.projeto.model.Anuncio;
import com.divulgatudo.projeto.repository.AnuncioRepository;

@Service
public class AnuncioServices {

    private final AnuncioRepository anuncioRepository;
	
	@Autowired
    public AnuncioServices(AnuncioRepository anuncioRepository) {
        this.anuncioRepository = anuncioRepository;
    }
	
	public AnuncioDTO salvarAnuncio(Anuncio novoAnuncio) {
		AnuncioDTO anuncioDTO = null;
		
		if (this.validarAnuncio(novoAnuncio)) {
			try {
				Anuncio anuncioSalvo = anuncioRepository.save(novoAnuncio);
				if (anuncioSalvo != null) {
					Calculadora cal = new Calculadora();
					anuncioDTO = cal.calcularProjecaoAlcanceVisualizacoes(anuncioSalvo, 3);
				}
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}	
		}
		
		return anuncioDTO;
	}
	
	public List<AnuncioDTO> pesquisarAnunciosPorCliente(String cliente){
		List<AnuncioDTO> anunciosDTO = new ArrayList<AnuncioDTO>();
		try {
			List<Anuncio> anunciosDoCliente = anuncioRepository.findByCliente(cliente);
			for (Anuncio anuncio : anunciosDoCliente) {
				Calculadora cal = new Calculadora();
				anunciosDTO.add(cal.calcularProjecaoAlcanceVisualizacoes(anuncio, 3));
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return anunciosDTO;
	}
	
	public List<AnuncioDTO> pesquisarAnunciosPorPeriodo(Date inicioPeriodo, Date finalPerido){
		List<AnuncioDTO> anunciosDTO = new ArrayList<AnuncioDTO>();
		try {
			List<Anuncio> anunciosPorPeriodo = anuncioRepository.findByPeriodBetweenPeriod(inicioPeriodo, finalPerido);
			for (Anuncio anuncio : anunciosPorPeriodo) {
				Calculadora cal = new Calculadora();
				anunciosDTO.add(cal.calcularProjecaoAlcanceVisualizacoes(anuncio, 3));
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return anunciosDTO;
	}
	
	public List<AnuncioDTO> pesquisarAnuncios(){
		List<AnuncioDTO> anunciosDTO = new ArrayList<AnuncioDTO>();
		try {
			List<Anuncio> anunciosPorPeriodo = anuncioRepository.findAll();
			for (Anuncio anuncio : anunciosPorPeriodo) {
				Calculadora cal = new Calculadora();
				anunciosDTO.add(cal.calcularProjecaoAlcanceVisualizacoes(anuncio, 3));
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return anunciosDTO;
	}
	
	private Boolean validarAnuncio(Anuncio anuncio) {
		if (anuncio.getNome().isEmpty()) {
			return false;
		}
		if (anuncio.getCliente().isEmpty()) {
			return false;
		}
		if (anuncio.getInvestimentoPorDia() <= 0) {
			return false;
		}
		DateTime dataInicio = new DateTime(anuncio.getDataInicio());
		DateTime dataTermino = new DateTime(anuncio.getDataTermino());
		Integer days = Days.daysBetween(dataInicio, dataTermino).getDays() + 1;
		if (days <= 0) {
			return false;
		}
		return true;
	}
}