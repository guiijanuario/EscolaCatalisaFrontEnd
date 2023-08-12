package com.zupschool.gerenciamentoescolar.service;

import com.zupschool.gerenciamentoescolar.DTO.CursoDTO;
import com.zupschool.gerenciamentoescolar.DTO.ProfessorDTO;
import com.zupschool.gerenciamentoescolar.Model.Curso;
import com.zupschool.gerenciamentoescolar.Model.Professor;
import com.zupschool.gerenciamentoescolar.mapping.MappingService;
import com.zupschool.gerenciamentoescolar.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    @Autowired
    private final ProfessorRepository professorRepository;
    @Autowired
    private MappingService mappingService;

    public ProfessorService(ProfessorRepository professorRepository, MappingService mappingService) {
        this.professorRepository = professorRepository;
        this.mappingService = mappingService;
    }
    public List<ProfessorDTO> listaProfessores (){
        Iterable<Professor> profesores = professorRepository.findAll();
        List<ProfessorDTO> professorDTOS = new ArrayList<>();
        for (Professor professor: profesores){
            ProfessorDTO professorMapeado = mappingService.mapProfessorToDTO(professor);
            professorDTOS.add(professorMapeado);

        }

        return professorDTOS;
    }
    public Optional<ProfessorDTO> getProfessorPorId(Long id) {
        Optional<Professor> professor = professorRepository.findById(id);
        if (professor.isEmpty()) {

            return Optional.empty();
        }
        return professor.map(p->mappingService.mapProfessorToDTO(p));
    }
    public ProfessorDTO cadastraProfessor(ProfessorDTO professorDTO) {
        Professor professor = mappingService.mapDTOToProfessor(professorDTO);
        Professor professorSaved = professorRepository.save(professor);

        return mappingService.mapProfessorToDTO(professorSaved);
    }
    public ProfessorDTO atualizaProfessor(ProfessorDTO professorDTO){
        ProfessorDTO professorAtual = getProfessorPorId(professorDTO.getId()).orElse(null);
        if(professorAtual!=null){
            professorAtual.setId(professorDTO.getId());
            professorAtual.setNomeProfessor(professorDTO.getNomeProfessor());
            professorAtual.setCurso(professorDTO.getCurso());
            professorAtual.setIdade(professorDTO.getIdade());
            professorAtual.setSalario(professorDTO.getSalario());

            Professor professor = mappingService.mapDTOToProfessor(professorAtual);
            Professor professorSaved = professorRepository.save(professor);

            return mappingService.mapProfessorToDTO(professorSaved);
        }
        return null;

    }
    public void deletaProfessor(Long id) {
        professorRepository.deleteById(id);
    }

}
