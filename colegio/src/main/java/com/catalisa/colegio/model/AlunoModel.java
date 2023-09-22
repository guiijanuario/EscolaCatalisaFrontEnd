package com.catalisa.colegio.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table

public class AlunoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @OneToOne //(mappedBy = "aluno",cascade = CascadeType.ALL)
    private MatriculaModel matricula;
}
