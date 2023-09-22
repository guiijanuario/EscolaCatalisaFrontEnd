package api.GestaoEscolar.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTO {

    //Atributos que serão trafegados
    private String nome_curso;
    private int carga_horaria;
}

