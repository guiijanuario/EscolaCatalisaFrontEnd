package com.zupschool.gerenciamentoescolar.repository;

import com.zupschool.gerenciamentoescolar.Model.Matricula;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends CrudRepository<Matricula,Long> {
}
