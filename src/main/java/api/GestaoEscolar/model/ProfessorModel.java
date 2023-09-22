package api.GestaoEscolar.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "professor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProfessorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String nome_professor;
    @Column(length = 3, nullable = false)
    private int idade;
    @Column(length = 10, nullable = false)
    private double salario;

    // @ManyToOne - Esta anotação é usada para especificar um relacionamento unidirecional de muitos para um
    // entre duas entidades, onde várias entidades têm uma referência para uma entidade.
    // Por exemplo, vários alunos podem ter o mesmo professor.
    @ManyToOne
    private CursoModel nome_curso;

}
