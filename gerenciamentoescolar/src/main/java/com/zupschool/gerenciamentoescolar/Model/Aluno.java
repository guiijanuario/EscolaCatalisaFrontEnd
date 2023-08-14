package com.zupschool.gerenciamentoescolar.Model;

import com.zupschool.gerenciamentoescolar.DTO.AlunoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int idade;
    @Email
    private String email;
    @ManyToOne
    @JoinColumn(name="matricula.id")
    private Matricula matricula;

}
