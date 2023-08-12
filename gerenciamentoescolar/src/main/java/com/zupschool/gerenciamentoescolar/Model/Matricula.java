package com.zupschool.gerenciamentoescolar.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dataMatricula;
    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL)
    private List<Aluno> aluno;
    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL)
    private List<Curso> curso;
}
