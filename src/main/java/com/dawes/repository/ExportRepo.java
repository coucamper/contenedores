package com.dawes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelos.ExportVO;

@Repository
public interface ExportRepo extends CrudRepository<ExportVO, Integer> {
	
}
