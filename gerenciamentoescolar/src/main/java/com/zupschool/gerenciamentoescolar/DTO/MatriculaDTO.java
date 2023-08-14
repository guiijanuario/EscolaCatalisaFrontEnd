package com.zupschool.gerenciamentoescolar.DTO;

import com.zupschool.gerenciamentoescolar.Model.Aluno;
import com.zupschool.gerenciamentoescolar.Model.Curso;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class MatriculaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String dataMatricula;

    private List<AlunoDTO> aluno = new ArrayList<>();

    private List<CursoDTO> curso = new ArrayList<>();
}
