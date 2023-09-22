package com.catalisa.colegio.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table
public class ProfessorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nome;
    private String idade;

    private BigDecimal salario;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoModel curso;


}
