package com.catalisa.colegio.dto;

import com.catalisa.colegio.model.CursoModel;
import com.catalisa.colegio.model.ProfessorModel;
import lombok.Data;

import java.util.List;

@Data
public class CursoDto {
    private Long id;

    private String nome;
    private Integer cargaHoraria;
    private List<ProfessorModel> professores;
    private String nomeProfessor;

    public CursoDto(CursoModel curso){
        id = curso.getId();
        nome = curso.getNome();
        cargaHoraria = curso.getCargaHoraria();
        professores = curso.getProfessores();


    }
}

