package com.zupschool.gerenciamentoescolar.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

public class Matricula {
    private Long id;
    private String dataMatricula;
    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL)
    private List<Aluno> aluno;
    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL)
    private List<Curso> curso;
}
