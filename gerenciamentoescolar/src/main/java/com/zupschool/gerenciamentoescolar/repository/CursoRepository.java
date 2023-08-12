package com.zupschool.gerenciamentoescolar.repository;

import com.zupschool.gerenciamentoescolar.Model.Curso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends CrudRepository<Curso,Long> {
}
