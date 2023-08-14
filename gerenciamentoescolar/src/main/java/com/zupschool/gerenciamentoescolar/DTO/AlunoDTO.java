package com.zupschool.gerenciamentoescolar.DTO;

import com.zupschool.gerenciamentoescolar.Model.Aluno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@Data
@AllArgsConstructor
public class AlunoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String nome;
    private Integer idade;
    private String email;
    private Long matriculaId;
    private Long cursoId;


}
