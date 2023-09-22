package com.catalisa.colegio.dto;

import com.catalisa.colegio.model.ProfessorModel;
import lombok.Data;

@Data
public class ProfessorDto {
    private Long id;

    private String nome;
    private String idade;

    private String nomeCurso;

    public ProfessorDto(ProfessorModel professor){
        id = professor.getId();
        nome = professor.getNome();
        idade = professor.getIdade();
        nomeCurso = professor.getCurso().getNome();

    }
}
