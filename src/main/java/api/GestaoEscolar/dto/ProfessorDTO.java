package api.GestaoEscolar.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {
    private String nome_professor;
    private int idade;
    private Long curso_id;
    private double salario;
}
