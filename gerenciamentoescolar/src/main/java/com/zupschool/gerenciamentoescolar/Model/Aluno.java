package com.zupschool.gerenciamentoescolar.Model;

import com.zupschool.gerenciamentoescolar.DTO.AlunoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int idade;
    @Email
    private String email;

    public Aluno(AlunoDTO alunoDTO) {
        this(alunoDTO.getId(),
                alunoDTO.getNome(),
                alunoDTO.getIdade(),
                alunoDTO.getEmail());
    }
}
