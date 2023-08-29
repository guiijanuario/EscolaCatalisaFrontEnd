package com.zupschool.gerenciamentoescolar;


import com.zupschool.gerenciamentoescolar.DTO.AlunoDTO;
import com.zupschool.gerenciamentoescolar.DTO.CursoDTO;
import com.zupschool.gerenciamentoescolar.DTO.MatriculaDTO;
import com.zupschool.gerenciamentoescolar.Model.Matricula;
import com.zupschool.gerenciamentoescolar.controller.CursoController;
import com.zupschool.gerenciamentoescolar.controller.MatriculaController;
import com.zupschool.gerenciamentoescolar.service.CursoService;
import com.zupschool.gerenciamentoescolar.service.MatriculaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MatriculaController.class)
class TesteMatriculaController {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MatriculaService matriculaService;

    TesteMatriculaController() {
    }

    @Test
    void testaCriarMatricula() throws Exception {

        List<AlunoDTO> alunos = new ArrayList<>();
        alunos.add(new AlunoDTO(1L, "Alice", 20, "alice@example.com", 123L, 456L));

        List<CursoDTO> cursos = new ArrayList<>();
        cursos.add(new CursoDTO(1L, "Math", 60, 789L, 987L));

        MatriculaDTO matriculaDTO = new MatriculaDTO(1L, "2023-08-23", alunos, cursos);
        matriculaDTO.setAluno(alunos);
        matriculaDTO.setCurso(cursos);

        // Mock the service behavior
        when(matriculaService.cadastraMatricula(any(MatriculaDTO.class))).thenReturn(matriculaDTO);

        // Perform the POST request
        mockMvc.perform(post("/matricula")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"dataMatricula\":\"2023-08-23\",\"aluno\":[{\"id\":1,\"nome\":\"Alice\",\"idade\":20,\"email\":\"alice@example.com\",\"matriculaId\":123,\"cursoId\":456}],\"curso\":[{\"id\":1,\"nome\":\"Math\",\"cargaHoraria\":60,\"professorId\":789,\"matriculaId\":987}]}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":1,\"dataMatricula\":\"2023-08-23\",\"aluno\":[{\"id\":1,\"nome\":\"Alice\",\"idade\":20,\"email\":\"alice@example.com\",\"matriculaId\":123,\"cursoId\":456}],\"curso\":[{\"id\":1,\"nome\":\"Math\",\"cargaHoraria\":60,\"professorId\":789,\"matriculaId\":987}]}"));

        // Verify that matriculaService.cadastraMatricula was called with the correct DTO
        verify(matriculaService).cadastraMatricula(matriculaDTO);
    }



    @Test
    void testeListaMatricula() throws Exception {
        List<AlunoDTO> alunos = new ArrayList<>();
        alunos.add(new AlunoDTO(1L, "Alice", 20, "alice@example.com", 123L, 456L));

        List<CursoDTO> cursos = new ArrayList<>();
        cursos.add(new CursoDTO(1L, "Math", 60, 789L, 987L));

        MatriculaDTO matriculaDTO = new MatriculaDTO(1L, "2023-08-23", alunos, cursos);
        matriculaDTO.setAluno(alunos);
        matriculaDTO.setCurso(cursos);

        MatriculaDTO matriculaDTO1 = new MatriculaDTO(2L,"2023-08-23",alunos,cursos);
        List<MatriculaDTO> matriculaDTOList = Arrays.asList(matriculaDTO,matriculaDTO1);
        when(matriculaService.listaMatriculas()).thenReturn(matriculaDTOList);

        mockMvc.perform(get("/matricula")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"id\":1,\"dataMatricula\":\"2023-08-23\",\"aluno\":[{\"id\":1,\"nome\":\"Alice\",\"idade\":20,\"email\":\"alice@example.com\",\"matriculaId\":123,\"cursoId\":456}],\"curso\":[{\"id\":1,\"nome\":\"Math\",\"cargaHoraria\":60,\"professorId\":789,\"matriculaId\":987}]}, " +
                                  "{\"id\":2,\"dataMatricula\":\"2023-08-23\",\"aluno\":[{\"id\":1,\"nome\":\"Alice\",\"idade\":20,\"email\":\"alice@example.com\",\"matriculaId\":123,\"cursoId\":456}],\"curso\":[{\"id\":1,\"nome\":\"Math\",\"cargaHoraria\":60,\"professorId\":789,\"matriculaId\":987}]}]"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"dataMatricula\":\"2023-08-23\",\"aluno\":[{\"id\":1,\"nome\":\"Alice\",\"idade\":20,\"email\":\"alice@example.com\",\"matriculaId\":123,\"cursoId\":456}],\"curso\":[{\"id\":1,\"nome\":\"Math\",\"cargaHoraria\":60,\"professorId\":789,\"matriculaId\":987}]}, {\"id\":2,\"dataMatricula\":\"2023-08-23\",\"aluno\":[{\"id\":1,\"nome\":\"Alice\",\"idade\":20,\"email\":\"alice@example.com\",\"matriculaId\":123,\"cursoId\":456}],\"curso\":[{\"id\":1,\"nome\":\"Math\",\"cargaHoraria\":60,\"professorId\":789,\"matriculaId\":987}]}]"));

        verify(matriculaService).listaMatriculas();
    }

    @Test
    void testeGetMatriculaById() throws Exception {
        List<AlunoDTO> alunos = new ArrayList<>();
        alunos.add(new AlunoDTO(1L, "Alice", 20, "alice@example.com", 123L, 456L));

        List<CursoDTO> cursos = new ArrayList<>();
        cursos.add(new CursoDTO(1L, "Math", 60, 789L, 987L));

        MatriculaDTO matriculaDTO = new MatriculaDTO(1L, "2023-08-23", alunos, cursos);
        matriculaDTO.setAluno(alunos);
        matriculaDTO.setCurso(cursos);

        when(matriculaService.getMatriculaPorId(1L)).thenReturn(Optional.of(matriculaDTO));

        mockMvc.perform(get("/matricula/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1," +
                        "\"dataMatricula\":\"2023-08-23\"," +
                        "\"aluno\":[{\"id\":1,\"nome\":\"Alice\",\"idade\":20,\"email\":\"alice@example.com\",\"matriculaId\":123,\"cursoId\":456}]," +
                        "\"curso\":[{\"id\":1,\"nome\":\"Math\",\"cargaHoraria\":60,\"professorId\":789,\"matriculaId\":987}]}"));
        verify(matriculaService).getMatriculaPorId(1L);
    }

    @Test
    void testAtualizaMatricula() throws Exception {
        List<AlunoDTO> alunos = new ArrayList<>();
        alunos.add(new AlunoDTO(1L, "Alice", 20, "alice@example.com", 123L, 456L));

        List<CursoDTO> cursos = new ArrayList<>();
        cursos.add(new CursoDTO(1L, "Math", 60, 789L, 987L));

        MatriculaDTO matriculaDTO = new MatriculaDTO(1L, "2023-08-23", alunos, cursos);
        matriculaDTO.setAluno(alunos);
        matriculaDTO.setCurso(cursos);

        // Mock the service behavior
        when(matriculaService.atualizaMatricula(any(MatriculaDTO.class))).thenReturn(matriculaDTO);
        when(matriculaService.getMatriculaPorId(1L)).thenReturn(Optional.of(matriculaDTO));

        // Perform the PUT request
        mockMvc.perform(put("/matricula/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1," +
                                "\"dataMatricula\":\"2023-08-23\"," +
                                "\"aluno\":[{\"id\":1,\"nome\":\"Alice\",\"idade\":20,\"email\":\"alice@example.com\"," +
                                "\"matriculaId\":123,\"cursoId\":456}]," +
                                "\"curso\":[{\"id\":1,\"nome\":\"Math\",\"cargaHoraria\":60,\"professorId\":789," +
                                "\"matriculaId\":987}]}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1," +
                        "\"dataMatricula\":\"2023-08-23\"," +
                        "\"aluno\":[{\"id\":1,\"nome\":\"Alice\",\"idade\":20,\"email\":\"alice@example.com\"," +
                        "\"matriculaId\":123,\"cursoId\":456}]," +
                        "\"curso\":[{\"id\":1,\"nome\":\"Math\",\"cargaHoraria\":60,\"professorId\":789," +
                        "\"matriculaId\":987}]}"));

        verify(matriculaService).atualizaMatricula(matriculaDTO);
    }

    @Test
    public void testeDeletaMatricula() throws Exception {
        mockMvc.perform(delete("/matricula/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify that alunoService.deletaAluno was called with the correct ID
        verify(matriculaService).deletaMatricula(1L);


    }


}


