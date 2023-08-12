package com.zupschool.gerenciamentoescolar.service;

import com.zupschool.gerenciamentoescolar.DTO.CursoDTO;
import com.zupschool.gerenciamentoescolar.Model.Curso;
import com.zupschool.gerenciamentoescolar.mapping.MappingService;
import com.zupschool.gerenciamentoescolar.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CursoService {

    @Autowired
    private final CursoRepository cursoRepository;


    @Autowired
    private MappingService mappingService;

    public CursoService(CursoRepository cursoRepository, MappingService mappingService) {
        this.cursoRepository = cursoRepository;
        this.mappingService = mappingService;
    }

    public List<CursoDTO> listaCursos (){
        Iterable<Curso> cursos = cursoRepository.findAll();
        List<CursoDTO> cursoDTOS = new ArrayList<>();
        for (Curso curso: cursos){
            CursoDTO cursoMapeado = mappingService.mapCursoToDTO(curso);
            cursoDTOS.add(cursoMapeado);

        }

        return cursoDTOS;
    }
    public Optional<CursoDTO> getCursoPorId(Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        if (curso.isEmpty()) {

            return Optional.empty();
        }
        return curso.map(c->mappingService.mapCursoToDTO(c));
    }
    public CursoDTO cadastraCurso(CursoDTO cursoDTO) {
        Curso curso = mappingService.mapDTOToCurso(cursoDTO);
        Curso cursoSaved = cursoRepository.save(curso);

        return mappingService.mapCursoToDTO(cursoSaved);
    }

    public CursoDTO atualizaCurso(CursoDTO cursoDTO){
       CursoDTO cursoAtual = getCursoPorId(cursoDTO.getId()).orElse(null);
        if(cursoAtual!=null){
            cursoAtual.setId(cursoDTO.getId());
            cursoAtual.setNome(cursoDTO.getNome());
            cursoAtual.setCargaHoraria(cursoDTO.getCargaHoraria());
            cursoAtual.setProfessorId(cursoDTO.getProfessorId());
            Curso curso = mappingService.mapDTOToCurso(cursoAtual);
            Curso cursoSalvo = cursoRepository.save(curso);

            return mappingService.mapCursoToDTO(cursoSalvo);
        }
        return null;

    }
    public void deletaCurso(Long id) {
        cursoRepository.deleteById(id);
    }


}
