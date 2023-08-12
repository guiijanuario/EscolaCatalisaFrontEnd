package com.zupschool.gerenciamentoescolar.Model;

import com.zupschool.gerenciamentoescolar.DTO.AlunoDTO;
import com.zupschool.gerenciamentoescolar.DTO.CursoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer cargaHoraria;
    @ManyToOne
    @JoinColumn(name="professor.id")
    private Professor professor;


}
