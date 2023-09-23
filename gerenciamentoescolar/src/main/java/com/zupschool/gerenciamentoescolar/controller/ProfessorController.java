package com.zupschool.gerenciamentoescolar.controller;

import com.zupschool.gerenciamentoescolar.DTO.CursoDTO;
import com.zupschool.gerenciamentoescolar.DTO.ProfessorDTO;
import com.zupschool.gerenciamentoescolar.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/professor")
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @GetMapping
    @CrossOrigin(origins = "http://127.0.0.1:3000/")
    public ResponseEntity<List<ProfessorDTO>> listaProfessores(){
        return ResponseEntity.ok(professorService.listaProfessores());
    }
    @GetMapping(path = "/{id}")
    @CrossOrigin(origins = "http://127.0.0.1:3000/")
    public ResponseEntity<Optional<ProfessorDTO>> getProfessorPorId(@PathVariable Long id){

        return ResponseEntity.ok(professorService.getProfessorPorId(id));
    }
    @PostMapping
    @CrossOrigin(origins = "http://127.0.0.1:3000/")
    public ResponseEntity<ProfessorDTO> cadastraProfessor(@RequestBody ProfessorDTO professorDTO){
        ProfessorDTO professorNovo = professorService.cadastraProfessor(professorDTO);
        return new ResponseEntity<>(professorNovo, HttpStatus.CREATED);
    }

    @PutMapping(path="/{id}")
    @CrossOrigin(origins = "http://127.0.0.1:3000/")
    public ProfessorDTO atulizaProfessor(@PathVariable Long id, @RequestBody ProfessorDTO professorDTO){
        return professorService.atualizaProfessor(professorDTO);
    }

    @DeleteMapping(path="/{id}")
    @CrossOrigin(origins = "http://127.0.0.1:3000/")
    public void deleteCurso (@PathVariable Long id){
        professorService.deletaProfessor(id);
    }
}
