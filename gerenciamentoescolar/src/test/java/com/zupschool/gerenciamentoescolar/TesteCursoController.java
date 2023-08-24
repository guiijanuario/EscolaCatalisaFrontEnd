package com.zupschool.gerenciamentoescolar;


import com.zupschool.gerenciamentoescolar.DTO.CursoDTO;
import com.zupschool.gerenciamentoescolar.controller.CursoController;
import com.zupschool.gerenciamentoescolar.service.CursoService;
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

@WebMvcTest(CursoController.class)
class TesteCursoController {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CursoService cursoService;

    @Test
    void testaCriarCurso() throws Exception {
        CursoDTO cursoDTO = new CursoDTO(1L, "java", 340, 1L, 1L);
        CursoDTO mockedcursoDTO = new CursoDTO(1L, "java", 340, 1L, 1L);
        when(cursoService.cadastraCurso(any(CursoDTO.class))).thenReturn(cursoDTO);

        mockMvc.perform(post("/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1," +
                                "\"nome\":\"java\"," +
                                "\"cargaHoraria\":340," +
                                "\"professorId\":1," +
                                "\"matriculaId\":1}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":1," +
                        "\"nome\":\"java\"," +
                        "\"cargaHoraria\":340," +
                        "\"professorId\":1," +
                        "\"matriculaId\":1}"));
        verify(cursoService).cadastraCurso(mockedcursoDTO);

    }

    @Test
    void testeListaCurso() throws Exception {
        CursoDTO cursoDTO = new CursoDTO(1L, "John Doe", 340, 3L, 1L);
        CursoDTO cursoDTO1 = new CursoDTO(2L, "Jane Smith", 340, 2L, 1L);
        List<CursoDTO> cursos = Arrays.asList(cursoDTO, cursoDTO1);
        when(cursoService.listaCursos()).thenReturn(cursos);

        mockMvc.perform(get("/cursos")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"nome\":\"John Doe\",\"cargaHoraria\":340,\"professorId\":3,\"matriculaId\":1}," +
                        "{\"id\":2,\"nome\":\"Jane Smith\",\"cargaHoraria\":340,\"professorId\":2,\"matriculaId\":1}]"));

        verify(cursoService).listaCursos();
    }

    @Test
    void testeGetCursoById() throws Exception {
        CursoDTO cursoDTO = new CursoDTO(1L, "John Doe", 25, 123L, 456L);

        when(cursoService.getCursoPorId(1L)).thenReturn(Optional.of(cursoDTO));
        mockMvc.perform(get("/cursos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1," +
                        "\"nome\":\"John Doe\"," +
                        "\"cargaHoraria\":25," +
                        "\"professorId\":123," +
                        "\"matriculaId\":456}"));
        verify(cursoService).getCursoPorId(1L);

    }

    @Test
    void testAtualizaCurso() throws Exception {
        CursoDTO cursoDTO = new CursoDTO(
                1L,
                "Java",
                25,
                123L,
                456L
        );

        // Mock the service behavior
        when(cursoService.atualizaCurso(any(CursoDTO.class))).thenReturn(cursoDTO);
        when(cursoService.getCursoPorId(1L)).thenReturn(Optional.of(cursoDTO));

        // Perform the PUT request
        mockMvc.perform(put("/cursos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nome\":\"Java\",\"cargaHoraria\":25,\"professorId\":123,\"matriculaId\":456}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"nome\":\"Java\",\"cargaHoraria\":25,\"professorId\":123,\"matriculaId\":456}"));

        // Verify that alunoService.atualizaAluno was called with the correct DTO
        verify(cursoService).atualizaCurso(cursoDTO);
    }

    @Test
    public void testeDeletaCurso() throws Exception {
        mockMvc.perform(delete("/cursos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify that alunoService.deletaAluno was called with the correct ID
        verify(cursoService).deletaCurso(1L);


    }


}


