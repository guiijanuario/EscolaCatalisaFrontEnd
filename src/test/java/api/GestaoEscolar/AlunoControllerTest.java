package api.GestaoEscolar;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import api.GestaoEscolar.controller.AlunoController;
import api.GestaoEscolar.model.AlunoModel;
import api.GestaoEscolar.service.AlunoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(AlunoController.class)
public class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AlunoService alunoService;
    private List<AlunoModel> alunos;

    @BeforeEach
    @Test
    void setupLista() {
        alunos = new ArrayList<>();
        alunos.add(new AlunoModel(1L, "Carlos", 40, "carlos@example.com"));
        alunos.add(new AlunoModel(2L, "Rafael", 15, "rafael@example.com"));

        //Printar o cenário de teste no console para confirmar visualmente.
        System.out.println("-----------------------------");
        System.out.println("Criando o cenário de teste - Aluno, idade e email: " );
        System.out.println(alunos);
    }

    @Test
    public void testListarTodosAlunos() throws Exception {
        given(alunoService.listarTodosAlunos()).willReturn(alunos);

        mockMvc.perform(get("/api/alunos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nome_aluno").value("Carlos"))
                .andExpect(jsonPath("$[1].nome_aluno").value("Rafael"))
                .andDo(print());
    }

    @Test
    public void testListarAlunoPorIdExistente() throws Exception {
        given(alunoService.listarAlunoPorId(1L)).willReturn(Optional.of(alunos.get(0)));

        mockMvc.perform(get("/api/alunos/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("AlunoModel(id=1, nome_aluno=Carlos, idade=40, email=carlos@example.com)"))
                .andDo(print());
    }

    @Test
    public void testListarAlunoPorIdNaoExistente() throws Exception {
        given(alunoService.listarAlunoPorId(4L)).willReturn(Optional.empty());

        mockMvc.perform(get("/api/alunos/{id}", 4L))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Aluno não encontrado!"))
                .andDo(print());
    }

    // Outros testes futuros

}
