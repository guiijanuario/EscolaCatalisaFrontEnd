package com.zupschool.gerenciamentoescolar.service;

import com.zupschool.gerenciamentoescolar.DTO.AlunoDTO;
import com.zupschool.gerenciamentoescolar.Model.Aluno;
import com.zupschool.gerenciamentoescolar.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AlunoService {

    @Autowired
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<AlunoDTO> listaAlunos (){
        Iterable<Aluno> alunos = alunoRepository.findAll();
        List<AlunoDTO> alunoDTO = new ArrayList<>();
        for (Aluno aluno: alunos){
            alunoDTO.add(new AlunoDTO(aluno));

        }

        return alunoDTO;
    }
    public Optional<AlunoDTO> getAlunoPorId(Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isEmpty()) {

            return Optional.empty();
        }
        return aluno.map(AlunoDTO::new);
    }
    public AlunoDTO cadastraAluno(AlunoDTO alunoDTO) {
        Aluno aluno = alunoRepository.save(new Aluno(alunoDTO));
        return new AlunoDTO(aluno);
    }

    public AlunoDTO atualizaAluno(AlunoDTO alunoDTO){
       Aluno alunoAtual = alunoRepository.findById(alunoDTO.getId()).get();
        if (alunoAtual.getId()!=null){
            alunoAtual.setId(alunoDTO.getId());
            alunoAtual.setNome(alunoDTO.getNome());
            alunoAtual.setEmail(alunoDTO.getEmail());
            alunoAtual.setIdade(alunoDTO.getIdade());
        }
        Aluno alunoSalvo = alunoRepository.save(alunoAtual);
        return new AlunoDTO(alunoSalvo);
    }
    public void deletaAluno(Long id) {
        alunoRepository.deleteById(id);
    }


}
