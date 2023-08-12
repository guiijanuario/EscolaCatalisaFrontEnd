package com.zupschool.gerenciamentoescolar.repository;

import com.zupschool.gerenciamentoescolar.Model.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor,Long> {
}
