package com.catalisa.colegio.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MatriculaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dataMatricula;


    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoModel curso;
}
