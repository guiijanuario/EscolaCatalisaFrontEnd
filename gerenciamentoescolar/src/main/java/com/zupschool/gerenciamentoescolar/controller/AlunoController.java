package com.zupschool.gerenciamentoescolar.controller;

import com.zupschool.gerenciamentoescolar.DTO.AlunoDTO;
import com.zupschool.gerenciamentoescolar.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/alunos")
public class AlunoController {
    @Autowired
    AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> listaAlunos(){
        return ResponseEntity.ok(alunoService.listaAlunos());
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<AlunoDTO>> getAlunoPorId(@PathVariable Long id){

        return ResponseEntity.ok(alunoService.getAlunoPorId(id));
    }
    @PostMapping
    public ResponseEntity<AlunoDTO> cadastraAluno(@RequestBody AlunoDTO alunoDTO){
        AlunoDTO alunoNovo = alunoService.cadastraAluno(alunoDTO);
        return new ResponseEntity<>(alunoNovo, HttpStatus.CREATED);
    }

    @PutMapping(path="/{id}")
    public AlunoDTO atulizaAluno(@PathVariable Long id, @RequestBody AlunoDTO alunoDTO){
        return alunoService.atualizaAluno(alunoDTO);
    }

    @DeleteMapping(path="/{id}")
    public void deleteAluno (@PathVariable Long id){
        alunoService.deletaAluno(id);
    }
}
