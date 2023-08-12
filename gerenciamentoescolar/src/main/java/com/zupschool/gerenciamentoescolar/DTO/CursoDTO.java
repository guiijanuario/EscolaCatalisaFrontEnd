package com.zupschool.gerenciamentoescolar.DTO;

import com.zupschool.gerenciamentoescolar.Model.Curso;
import com.zupschool.gerenciamentoescolar.Model.Professor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CursoDTO {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private Integer cargaHoraria;
    private Long professorId;

}
