package api.GestaoEscolar.dto;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDTO {

    //Atributos que serão trafegados
    private String nome_aluno;
    private int idade;
    private String email;
}
