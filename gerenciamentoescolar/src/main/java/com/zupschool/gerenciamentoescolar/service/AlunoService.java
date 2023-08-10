package com.zupschool.gerenciamentoescolar.service;

import com.zupschool.gerenciamentoescolar.DTO.AlunoDTO;
import com.zupschool.gerenciamentoescolar.Model.Aluno;
import com.zupschool.gerenciamentoescolar.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;



@Service
public class AlunoService {

    @Autowired
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public ResponseEntity<ArrayList<AlunoDTO>> listaAlunos (){
        Iterable<Aluno> alunos = alunoRepository.findAll();
        ArrayList<AlunoDTO> alunoDTO = new ArrayList<>();
        for (Aluno aluno: alunos){
            alunoDTO.add(new AlunoDTO(aluno));

        }

        return new ResponseEntity<>(alunoDTO,HttpStatus.OK);
    }
}
