package api.GestaoEscolar;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import api.GestaoEscolar.controller.CursoController;
import api.GestaoEscolar.model.CursoModel;
import api.GestaoEscolar.service.CursoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CursoController.class)
public class CursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CursoService cursoService;

    private List<CursoModel> cursos;

    @BeforeEach
    @Test
    void setupLista() {
        // Preparando o cenário de teste, criando uma lista de cursos e seus respectivos dados.
        cursos = new ArrayList<>();
        cursos.add(new CursoModel(1L, "Matemática", 100));
        cursos.add(new CursoModel(2L, "História", 200));

        //Printar o cenário de teste no console para confirmar visualmente.
        System.out.println("-----------------------------");
        System.out.println("Criando o cenário de teste - Curso e carga horária: " );
        System.out.println(cursos);
    }

    @Test
    public void testListarTodosCursos() throws Exception {
        // Arrange:Prepara o cenário de teste, definindo comportamentos de mocks
        // e configurando estados iniciais.
        given(cursoService.listarTodosCursos()).willReturn(cursos);

        // Act & Assert!
        // Act: Realiza a ação que está sendo testada (chamada ao endpoint mockado).
        // Assert: Verifica o resultado esperado,com as expectativas definidas.
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cursos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome_curso").value("Matemática"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carga_horaria").value(100))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome_curso").value("História"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].carga_horaria").value(200))
                //Printar o cenário de teste no console para confirmar visualmente.
                .andDo(print());
    }

    @Test
    public void testListarCursoPorIdExistente() throws Exception {
        // Arrange
        given(cursoService.listarCursoPorId(1L)).willReturn(Optional.of(cursos.get(0)));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cursos/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome_curso").value("Matemática"))

                //Printar o cenário de teste no console para confirmar visualmente.
                .andDo(print());
    }

    @Test
    public void testListarCursoPorIdNaoExistente() throws Exception {
        // Arrange
        given(cursoService.listarCursoPorId(1L)).willReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cursos/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                //Printar o cenário de teste no console para confirmar visualmente.
                .andDo(print());
    }

    // Outros testes para os demais endpoints futuros.
}
