package api.GestaoEscolar.model;

//Criar um aplicativo Java usando o Spring Framework que permite gerenciar alunos, cursos, professores e matrículas
// em uma escola.

import lombok.*;
import javax.persistence.*;

//Classe da Entidade Aluno
@Entity
@Data
@Table(name = "alunos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AlunoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String nome_aluno;
    @Column(length = 3, nullable = false)
    private int idade;
    @Column(length = 50, nullable = false)
    private String email;

}
