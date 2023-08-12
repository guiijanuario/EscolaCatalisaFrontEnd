package com.zupschool.gerenciamentoescolar.DTO;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Data
@AllArgsConstructor
public class ProfessorDTO {
    private Long id;
    private String nomeProfessor;

    private Integer idade;

    private List<CursoDTO> curso = new ArrayList<>();

    private Double salario;
}
