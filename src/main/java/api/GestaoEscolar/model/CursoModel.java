package api.GestaoEscolar.model;

import lombok.*;import javax.persistence.*;

//Classe da Entidade Curso
@Entity
@Data
@Table(name = "cursos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CursoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String nome_curso;
    @Column(length = 10, nullable = false)
    private int carga_horaria;

}