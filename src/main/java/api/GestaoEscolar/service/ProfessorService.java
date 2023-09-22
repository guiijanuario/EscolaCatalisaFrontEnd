package api.GestaoEscolar.service;


import api.GestaoEscolar.dto.ProfessorDTO;
import api.GestaoEscolar.model.ProfessorModel;
import api.GestaoEscolar.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorService {

    ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    //Método para cadastrar professor
    public ProfessorModel cadastrarProfessor(ProfessorDTO professorDTO) {
        ProfessorModel professorCadastrado = new ProfessorModel();
        professorCadastrado.setNome_professor(professorDTO.getNome_professor());
        professorCadastrado.setIdade(professorDTO.getIdade());
        professorCadastrado.setSalario(professorDTO.getSalario());
        return professorRepository.save(professorCadastrado);

    }

    //Método para listar todos os professores
    public Iterable<ProfessorModel> listarTodosProfessores() {
        return professorRepository.findAll();
    }

    //Método para listar professor por ID
    public Optional<ProfessorModel> listarProfessorPorId(Long id) {
        return professorRepository.findById(id);
    }

    //Método para atualizar professor por ID
    public void atualizarProfessorPorId(Long id, ProfessorDTO professorDTO) {
        Optional<ProfessorModel> professorCadastrado = professorRepository.findById(id);
        if (professorCadastrado.isPresent()) {
            ProfessorModel professorModel = professorCadastrado.get();
            professorModel.setNome_professor(professorDTO.getNome_professor());
            professorModel.setIdade(professorDTO.getIdade());
            professorModel.setSalario(professorDTO.getSalario());
            professorRepository.save(professorModel);
        } else {
            throw new RuntimeException("Professor ID não encontrado com ID: " + id);
        }
    }

    //Método para deletar professor por ID
    public void deletarProfessorPorId(Long id) {
        Optional<ProfessorModel> professorCadastrado = professorRepository.findById(id);
        if (professorCadastrado.isPresent()) {
            professorRepository.delete(professorCadastrado.get());
        } else {
            throw new RuntimeException("Professor ID não encontrado com ID: " + id);
        }
    }

}
