package com.zupschool.gerenciamentoescolar.DTO;



import java.util.ArrayList;
import java.util.List;

public class ProfessorDTO {
    private Long getId;
    private String nomeProfessor;

    private Integer idade;

    private List<CursoDTO> curso = new ArrayList<>();

    private Double salario;
}
