package com.divulgatudo.projeto.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.divulgatudo.projeto.dto.AnuncioDTO;
import com.divulgatudo.projeto.model.Anuncio;
import com.divulgatudo.projeto.services.AnuncioServices;

@CrossOrigin
@RequestMapping("/DivulgaTudo/anuncios")
@RestController
public class AnuncioController {

	private final AnuncioServices anuncioServices;

	@Autowired
    public AnuncioController(AnuncioServices anuncioServices) {
        this.anuncioServices = anuncioServices;
    }
	
	@PostMapping(value="/novo")
    public ResponseEntity<AnuncioDTO> salvar(@RequestBody Anuncio anuncio) {
		AnuncioDTO anuncioSalvo = anuncioServices.salvarAnuncio(anuncio);
		if (anuncioSalvo != null){
			return new ResponseEntity<>(anuncioSalvo, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(anuncioSalvo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@GetMapping(value="/pesquisar")
	public ResponseEntity<List<AnuncioDTO>> pesquisar(@RequestParam(value="cliente", required=false) String cliente,
														@RequestParam(value="inicioPeriodo", required=false) String inicioPeriodo,
														@RequestParam(value="finalPeriodo", required=false) String finalPeriodo) {
		
		List<AnuncioDTO> anuncios = new ArrayList<AnuncioDTO>();
		if (cliente != null) {
			anuncios = anuncioServices.pesquisarAnunciosPorCliente(cliente);
		} else if (inicioPeriodo != null && finalPeriodo != null) {
			try {
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				Date inicio = formato.parse(inicioPeriodo);
				Date fim = formato.parse(finalPeriodo);
				anuncios = anuncioServices.pesquisarAnunciosPorPeriodo(inicio, fim);
			} catch (Exception e) {
				System.out.print(e.getMessage());
				anuncios = new ArrayList<AnuncioDTO>();
			}
		} else {
			anuncios = anuncioServices.pesquisarAnuncios();
		}
        return new ResponseEntity<>(anuncios, HttpStatus.OK);
	}
}