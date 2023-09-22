package com.catalisa.colegio.service;

import com.catalisa.colegio.model.AlunoModel;
import com.catalisa.colegio.model.CursoModel;
import com.catalisa.colegio.model.MatriculaModel;
import com.catalisa.colegio.model.ProfessorModel;
import com.catalisa.colegio.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    //Listar Todos os professores
    public List<ProfessorModel> listarProfessor() {
        return professorRepository.findAll();

    }

    public void deletar(Long id) {
        professorRepository.deleteById(id);

    }
    public ProfessorModel cadastrar(ProfessorModel professor){

        return professorRepository.save(professor); //salva as informações dos usuários
    }
}
