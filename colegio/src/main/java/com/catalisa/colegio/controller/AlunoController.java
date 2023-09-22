package com.catalisa.colegio.controller;

import com.catalisa.colegio.dto.AlunoDto;
import com.catalisa.colegio.model.AlunoModel;
import com.catalisa.colegio.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(path = "/alunos")
@RestController
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @CrossOrigin(origins = "http://127.0.0.1:3000")
    @GetMapping
//    public List<AlunoModel> listarAluno(){
//
//        return alunoService.listarAluno();
//    }
    public ResponseEntity<List<AlunoDto>> findAll(){
        List<AlunoModel> list= alunoService.listarAluno();
        List<AlunoDto> listDto = list.stream().map(AlunoDto::new).collect(Collectors.toList());
        return  ResponseEntity.ok().body(listDto);
    }


    @CrossOrigin(origins = "http://127.0.0.1:3000")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlunoModel cadastrarNovoAluno(@RequestBody AlunoModel aluno){ //colocamos o contatosModel como parametro para saber o que presiamos colocar no corpo da requisição
        return alunoService.cadastrar(aluno);
    }


    @DeleteMapping(path = "{id}")
    public void deletaAluno(@PathVariable Long id){
        alunoService.deletar(id);
    }
}
