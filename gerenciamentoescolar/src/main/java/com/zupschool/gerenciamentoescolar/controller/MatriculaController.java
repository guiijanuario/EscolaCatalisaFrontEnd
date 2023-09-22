package com.zupschool.gerenciamentoescolar.controller;

import com.zupschool.gerenciamentoescolar.DTO.MatriculaDTO;
import com.zupschool.gerenciamentoescolar.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/matricula")
public class MatriculaController {
    @Autowired
    MatriculaService matriculaService;

    @GetMapping
    public ResponseEntity<List<MatriculaDTO>> listaMatriculas(){
        return ResponseEntity.ok(matriculaService.listaMatriculas());
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<MatriculaDTO>> getMatriculaPorId(@PathVariable Long id){

        return ResponseEntity.ok(matriculaService.getMatriculaPorId(id));
    }
    @PostMapping
    @CrossOrigin(origins = "http://127.0.0.1:3000/")
    public ResponseEntity<MatriculaDTO> cadastraMatricula(@RequestBody MatriculaDTO matriculaDTO){
        MatriculaDTO matriculaNova = matriculaService.cadastraMatricula(matriculaDTO);
        return new ResponseEntity<>(matriculaNova, HttpStatus.CREATED);
    }

    @PutMapping(path="/{id}")
    public MatriculaDTO atulizaMatricula(@PathVariable Long id, @RequestBody MatriculaDTO matriculaDTO){
        return matriculaService.atualizaMatricula(matriculaDTO);
    }

    @DeleteMapping(path="/{id}")
    public void deleteMatricula (@PathVariable Long id){
        matriculaService.deletaMatricula(id);
    }


}
