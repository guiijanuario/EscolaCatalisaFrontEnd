package com.catalisa.colegio.dto;

import com.catalisa.colegio.model.AlunoModel;
import lombok.Data;

@Data
public class AlunoDto {
    private Long id;

    private String nome;
    private Integer idade;

    private String email;

    public AlunoDto(AlunoModel aluno){
        id = aluno.getId();
        nome = aluno.getNome();
        idade = aluno.getIdade();
        email = aluno.getEmail();
    }



}
