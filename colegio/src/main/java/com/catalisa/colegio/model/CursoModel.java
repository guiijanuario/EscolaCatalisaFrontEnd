package com.catalisa.colegio.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class CursoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nome;

    @OneToMany
    private List<ProfessorModel> professores;

    private Integer cargaHoraria;


    @OneToMany
    private List<MatriculaModel> matriculas;
}
