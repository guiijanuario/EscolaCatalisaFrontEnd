package com.zupschool.gerenciamentoescolar;


import com.zupschool.gerenciamentoescolar.DTO.AlunoDTO;
import com.zupschool.gerenciamentoescolar.DTO.CursoDTO;
import com.zupschool.gerenciamentoescolar.DTO.MatriculaDTO;
import com.zupschool.gerenciamentoescolar.DTO.ProfessorDTO;
import com.zupschool.gerenciamentoescolar.controller.MatriculaController;
import com.zupschool.gerenciamentoescolar.controller.ProfessorController;
import com.zupschool.gerenciamentoescolar.service.MatriculaService;
import com.zupschool.gerenciamentoescolar.service.ProfessorService;
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

@WebMvcTest(ProfessorController.class)
class TesteProfessorController {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProfessorService professorService;

    @Test
    void testaCriarProfessor() throws Exception {

        List<CursoDTO> cursos = new ArrayList<>();
        cursos.add(new CursoDTO(1L, "Java", 60, 789L, 987L));
        cursos.add(new CursoDTO(2L, "Mockito", 50, 1L, 3L));
        ProfessorDTO professorDTO = new ProfessorDTO(1L,"James Gosling",100,cursos,100000.00);
        // Mock the service behavior
        when(professorService.cadastraProfessor(any(ProfessorDTO.class))).thenReturn(professorDTO);

        // Perform the POST request
        mockMvc.perform(post("/professor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nomeProfessor\":\"James Gosling\",\"idade\":100,\"curso\":[{\"id\":1,\"nome\":\"Java\",\"cargaHoraria\":60,\"professorId\":789,\"matriculaId\":987},{\"id\":2,\"nome\":\"Mockito\",\"cargaHoraria\":50,\"professorId\":1,\"matriculaId\":3}],\"salario\":100000.0}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":1,\"nomeProfessor\":\"James Gosling\",\"idade\":100,\"curso\":[{\"id\":1,\"nome\":\"Java\",\"cargaHoraria\":60,\"professorId\":789,\"matriculaId\":987},{\"id\":2,\"nome\":\"Mockito\",\"cargaHoraria\":50,\"professorId\":1,\"matriculaId\":3}],\"salario\":100000.0}"));

        // Verify that matriculaService.cadastraMatricula was called with the correct DTO
        verify(professorService).cadastraProfessor(professorDTO);
    }



    @Test
    void testeListaProfessor() throws Exception {
        List<CursoDTO> cursos = new ArrayList<>();
        cursos.add(new CursoDTO(1L, "Java", 60, 789L, 987L));
        cursos.add(new CursoDTO(2L, "Mockito", 50, 1L, 3L));

        ProfessorDTO professorDTO = new ProfessorDTO(1L, "James Gosling", 100, cursos, 100000.00);
        ProfessorDTO professorDTO1 = new ProfessorDTO(2L, "Mike Sheridan", 100, cursos, 100000.00);
        List<ProfessorDTO> professorDTOList = Arrays.asList(professorDTO, professorDTO1);

        when(professorService.listaProfessores()).thenReturn(professorDTOList);

        mockMvc.perform(get("/professor")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"nomeProfessor\":\"James Gosling\",\"idade\":100,\"curso\":[{\"id\":1,\"nome\":\"Java\",\"cargaHoraria\":60,\"professorId\":789,\"matriculaId\":987},{\"id\":2,\"nome\":\"Mockito\",\"cargaHoraria\":50,\"professorId\":1,\"matriculaId\":3}],\"salario\":100000.0}," +
                        "{\"id\":2,\"nomeProfessor\":\"Mike Sheridan\",\"idade\":100,\"curso\":[{\"id\":1,\"nome\":\"Java\",\"cargaHoraria\":60,\"professorId\":789,\"matriculaId\":987},{\"id\":2,\"nome\":\"Mockito\",\"cargaHoraria\":50,\"professorId\":1,\"matriculaId\":3}],\"salario\":100000.0}]"));

        verify(professorService).listaProfessores();
    }

    @Test
    void testeGetProfessorById() throws Exception {
        List<CursoDTO> cursos = new ArrayList<>();
        cursos.add(new CursoDTO(1L, "Java", 60, 789L, 987L));
        cursos.add(new CursoDTO(2L, "Mockito", 50, 1L, 3L));
        ProfessorDTO professorDTO = new ProfessorDTO(1L,"James Gosling",100,cursos,100000.00);

        when(professorService.getProfessorPorId(1L)).thenReturn(Optional.of(professorDTO));

        mockMvc.perform(get("/professor/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"nomeProfessor\":\"James Gosling\",\"idade\":100,\"curso\":[{\"id\":1,\"nome\":\"Java\",\"cargaHoraria\":60,\"professorId\":789,\"matriculaId\":987},{\"id\":2,\"nome\":\"Mockito\",\"cargaHoraria\":50,\"professorId\":1,\"matriculaId\":3}],\"salario\":100000.0}"));
        verify(professorService).getProfessorPorId(1L);
    }

    @Test
    void testAtualizaProfessor() throws Exception {
        List<CursoDTO> cursos = new ArrayList<>();
        cursos.add(new CursoDTO(1L, "Java", 60, 789L, 987L));
        cursos.add(new CursoDTO(2L, "Mockito", 50, 1L, 3L));
        ProfessorDTO professorDTO = new ProfessorDTO(1L,"James Gosling",100,cursos,100000.00);


        when(professorService.getProfessorPorId(1L)).thenReturn(Optional.of(professorDTO));
        when(professorService.atualizaProfessor(any(ProfessorDTO.class))).thenReturn(professorDTO);

        // Perform the PUT request
        mockMvc.perform(put("/professor/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nomeProfessor\":\"James Gosling\",\"idade\":100,\"curso\":[{\"id\":1,\"nome\":\"Java\",\"cargaHoraria\":60,\"professorId\":789,\"matriculaId\":987},{\"id\":2,\"nome\":\"Mockito\",\"cargaHoraria\":50,\"professorId\":1,\"matriculaId\":3}],\"salario\":100000.0}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"nomeProfessor\":\"James Gosling\",\"idade\":100,\"curso\":[{\"id\":1,\"nome\":\"Java\",\"cargaHoraria\":60,\"professorId\":789,\"matriculaId\":987},{\"id\":2,\"nome\":\"Mockito\",\"cargaHoraria\":50,\"professorId\":1,\"matriculaId\":3}],\"salario\":100000.0}"));
        verify(professorService).atualizaProfessor(professorDTO);
    }

    @Test
    public void testeDeletaMatricula() throws Exception {
        mockMvc.perform(delete("/professor/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify that alunoService.deletaAluno was called with the correct ID
        verify(professorService).deletaProfessor(1L);


    }


}


