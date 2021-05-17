package com.divulgatudo.projeto.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.divulgatudo.projeto.model.Anuncio;

public interface AnuncioRepository extends MongoRepository<Anuncio, String> {

	public List<Anuncio> findByCliente(String cliente);
	
//	db.anuncio.find({
//    	'dataInicio':{$lte:ISODate("2021-05-30")},
//    	'dataTermino':{$gte:ISODate("2021-05-01")}
//	})
	
	@Query("{'dataInicio':{$lte:?1},'dataTermino':{$gte:?0}}")
	public List<Anuncio> findByPeriodBetweenPeriod(Date inicioPeriodo, Date finalPeriodo);
}