package com.catalisa.colegio.service;

import com.catalisa.colegio.model.AlunoModel;
import com.catalisa.colegio.model.CursoModel;
import com.catalisa.colegio.model.MatriculaModel;
import com.catalisa.colegio.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {
    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    MatriculaService matriculaService;

    //Listar Todos os alunos
    public List<AlunoModel> listarAluno() {
        return alunoRepository.findAll();

    }

    //método que cadastra um novo aluno no banco
//    public AlunoModel cadastrar(AlunoModel aluno){
//        Long matriculaId = aluno.getMatricula().getId();
//
//        //metodo para buscar matricula id
//
//        MatriculaModel matricula = matriculaService.buscarPorId(matriculaId);
//        aluno.setMatricula(matricula);
//
//        return alunoRepository.save(aluno); //salva as informações dos usuários
//    }
    public AlunoModel cadastrar(AlunoModel aluno){

        return alunoRepository.save(aluno); //salva as informações dos usuários
    }

    public void deletar(Long id){
        alunoRepository.deleteById(id);

    }

}
