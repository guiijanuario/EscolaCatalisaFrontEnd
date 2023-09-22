package com.catalisa.colegio.dto;

import com.catalisa.colegio.model.MatriculaModel;
import lombok.Data;

@Data
public class MatriculaDto {

    private String data;
    private String nome;

    public MatriculaDto(MatriculaModel matricula){
        //   nome = matricula.getAluno().getNome();
        data = matricula.getDataMatricula();

    }

}

