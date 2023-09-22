package api.GestaoEscolar.model;


import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "matricula")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MatriculaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private LocalDate data_matricula;

    // @ManyToOne - Esta anotação é usada para especificar um relacionamento unidirecional de muitos para um
    // entre duas entidades, onde várias entidades têm uma referência para uma entidade.
    // Por exemplo, vários alunos podem ter o mesmo professor.
    @ManyToOne
    private AlunoModel nome_aluno;
    @ManyToOne
    private CursoModel nome_curso;

}