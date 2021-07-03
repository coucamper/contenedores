package com.dawes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelos.PesajeVO;

@Repository
public interface PesajeRepo extends CrudRepository<PesajeVO, Integer> {

}
