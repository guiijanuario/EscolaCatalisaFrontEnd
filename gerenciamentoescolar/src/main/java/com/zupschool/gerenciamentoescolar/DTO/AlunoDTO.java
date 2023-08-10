package com.zupschool.gerenciamentoescolar.DTO;

import com.zupschool.gerenciamentoescolar.Model.Aluno;

public class AlunoDTO {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String nome;
    private Integer idade;
    private String email;

    public AlunoDTO(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.idade = aluno.getIdade();
        this.email = aluno.getEmail();

    }
}