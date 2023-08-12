package com.zupschool.gerenciamentoescolar.service;

import com.zupschool.gerenciamentoescolar.DTO.MatriculaDTO;
import com.zupschool.gerenciamentoescolar.DTO.ProfessorDTO;
import com.zupschool.gerenciamentoescolar.Model.Matricula;
import com.zupschool.gerenciamentoescolar.Model.Professor;
import com.zupschool.gerenciamentoescolar.mapping.MappingService;
import com.zupschool.gerenciamentoescolar.repository.MatriculaRepository;
import com.zupschool.gerenciamentoescolar.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {
    @Autowired
    private final MatriculaRepository matriculaRepository;
    @Autowired
    private MappingService mappingService;

    public MatriculaService(MatriculaRepository matriculaRepository, MappingService mappingService) {
        this.matriculaRepository = matriculaRepository;
        this.mappingService = mappingService;
    }
    public List<MatriculaDTO> listaMatriculas (){
        Iterable<Matricula> matriculas = matriculaRepository.findAll();
        List<MatriculaDTO> matriculaDTOS = new ArrayList<>();
        for (Matricula matricula: matriculas){
            MatriculaDTO matriculaMapeado = mappingService.mapMatriculaToDTO(matricula);
            matriculaDTOS.add(matriculaMapeado);

        }

        return matriculaDTOS;
    }
    public Optional<MatriculaDTO> getMatriculaPorId(Long id) {
        Optional<Matricula> matricula = matriculaRepository.findById(id);
        if (matricula.isEmpty()) {

            return Optional.empty();
        }
        return matricula.map(m->mappingService.mapMatriculaToDTO(m));
    }
    public MatriculaDTO cadastraMatricula(MatriculaDTO matriculaDTO) {
        Matricula matricula = mappingService.mapDTOToMatricula(matriculaDTO);
        Matricula matriculaSaved = matriculaRepository.save(matricula);

        return mappingService.mapMatriculaToDTO(matriculaSaved);
    }
    public MatriculaDTO atualizaMatricula(MatriculaDTO matriculaDTO){
        MatriculaDTO matriculaAtual = getMatriculaPorId(matriculaDTO.getId()).orElse(null);
        if(matriculaAtual!=null){
            matriculaAtual.setId(matriculaDTO.getId());

            LocalDate data =  LocalDate.now();
            matriculaDTO.setDataMatricula(String.valueOf(data));

            matriculaAtual.setDataMatricula(matriculaDTO.getDataMatricula());

            matriculaAtual.setCurso(matriculaDTO.getCurso());
            matriculaAtual.setAluno(matriculaDTO.getAluno());

            Matricula matricula = mappingService.mapDTOToMatricula(matriculaAtual);
            Matricula matriculaSaved = matriculaRepository.save(matricula);

            return mappingService.mapMatriculaToDTO(matriculaSaved);
        }
        return null;

    }
    public void deletaMatricula(Long id) {
        matriculaRepository.deleteById(id);
    }

}
