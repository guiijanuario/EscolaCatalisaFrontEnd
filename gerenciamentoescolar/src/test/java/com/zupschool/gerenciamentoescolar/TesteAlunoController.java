package com.zupschool.gerenciamentoescolar;

import com.zupschool.gerenciamentoescolar.DTO.AlunoDTO;
import com.zupschool.gerenciamentoescolar.controller.AlunoController;
import com.zupschool.gerenciamentoescolar.service.AlunoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AlunoController.class)
class TesteAlunoController {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AlunoService alunoService;

    @Test
    void testaCriarAluno() throws Exception {
        AlunoDTO alunoDTO = new AlunoDTO(
                1L,
                "John Doe",
                25,
                "john@example.com",
                123L,
                456L
        );

        // Mock the service behavior
        AlunoDTO mockedAlunoDTO = new AlunoDTO(
                1L,
                "John Doe",
                25,
                "john@example.com",
                123L,
                456L
        );
        when(alunoService.cadastraAluno(any(AlunoDTO.class))).thenReturn(mockedAlunoDTO);
        mockMvc.perform(post("/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1," +
                                "\"nome\":\"John Doe\"," +
                                "\"idade\":25," +
                                "\"email\":\"john@example.com\"," +
                                "\"matriculaId\":123," +
                                "\"cursoId\":456}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":1," +
                        "\"nome\":\"John Doe\",\"idade\":25," +
                        "\"email\":\"john@example.com\"," +
                        "\"matriculaId\":123," +
                        "\"cursoId\":456}"));
        verify(alunoService).cadastraAluno(mockedAlunoDTO);

    }

    @Test
    void testeListaAluno() throws Exception {
        AlunoDTO aluno1 = new AlunoDTO(1L, "John Doe", 25, "john@example.com", 123L, 456L);
        AlunoDTO aluno2 = new AlunoDTO(2L, "Jane Smith", 22, "jane@example.com", 789L, 456L);
        List<AlunoDTO> alunos = Arrays.asList(aluno1, aluno2);
        when(alunoService.listaAlunos()).thenReturn(alunos);

        mockMvc.perform(get("/alunos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"nome\":\"John Doe\",\"idade\":25,\"email\":\"john@example.com\",\"matriculaId\":123,\"cursoId\":456},{\"id\":2,\"nome\":\"Jane Smith\",\"idade\":22,\"email\":\"jane@example.com\",\"matriculaId\":789,\"cursoId\":456}]"));

        verify(alunoService).listaAlunos();
    }

    @Test
    void testeGetAlunoById() throws Exception{
        AlunoDTO aluno = new AlunoDTO(1L, "John Doe", 25, "john@example.com", 123L, 456L);

        when(alunoService.getAlunoPorId(1L)).thenReturn(Optional.of(aluno));
        mockMvc.perform(get("/alunos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1," +
                        "\"nome\":\"John Doe\"," +
                        "\"idade\":25," +
                        "\"email\":\"john@example.com\"," +
                        "\"matriculaId\":123," +
                        "\"cursoId\":456}"));
        verify(alunoService).getAlunoPorId(1L);

    }
    @Test
    void testAtualizaAluno() throws Exception {
        AlunoDTO alunoDTO = new AlunoDTO(
                1L,
                "John Doe",
                25,
                "john@example.com",
                123L,
                456L
        );

        // Mock the service behavior
        when(alunoService.atualizaAluno(any(AlunoDTO.class))).thenReturn(alunoDTO);
        when(alunoService.getAlunoPorId(1L)).thenReturn(Optional.of(alunoDTO));

        // Perform the PUT request
        mockMvc.perform(put("/alunos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nome\":\"John Doe\",\"idade\":25,\"email\":\"john@example.com\",\"matriculaId\":123,\"cursoId\":456}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"nome\":\"John Doe\",\"idade\":25,\"email\":\"john@example.com\",\"matriculaId\":123,\"cursoId\":456}"));

        // Verify that alunoService.atualizaAluno was called with the correct DTO
        verify(alunoService).atualizaAluno(alunoDTO);
    }

    @Test
    public void testeDeletaALuno() throws Exception{
        mockMvc.perform(delete("/alunos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify that alunoService.deletaAluno was called with the correct ID
        verify(alunoService).deletaAluno(1L);


    }


}


