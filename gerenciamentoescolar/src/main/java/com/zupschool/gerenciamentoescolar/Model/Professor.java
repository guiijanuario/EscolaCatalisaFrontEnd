package com.zupschool.gerenciamentoescolar.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeProfessor;
    private Integer idade;
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<Curso> curso = new ArrayList<>();
    private Double salario;


}
