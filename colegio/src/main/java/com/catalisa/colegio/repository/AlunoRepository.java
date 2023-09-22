package com.catalisa.colegio.repository;

import com.catalisa.colegio.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository <AlunoModel, Long> {
}
