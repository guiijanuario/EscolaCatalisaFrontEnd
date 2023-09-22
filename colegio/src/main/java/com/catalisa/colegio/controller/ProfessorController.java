package com.catalisa.colegio.controller;

import com.catalisa.colegio.dto.ProfessorDto;
import com.catalisa.colegio.model.AlunoModel;
import com.catalisa.colegio.model.ProfessorModel;
import com.catalisa.colegio.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(path = "/professores")
@RestController
public class ProfessorController {

    @Autowired
    ProfessorService professoresService;

    //
    @CrossOrigin(origins = "http://127.0.0.1:3000")
    @GetMapping
//    public List<ProfessorModel> listarProfessor(){
//
//        return professoresService.listarProfessor();
//    }

    public ResponseEntity<List<ProfessorDto>> findAll() {
        List<ProfessorModel> list = professoresService.listarProfessor();
        List<ProfessorDto> listDto = list.stream().map(ProfessorDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);

    }

    @DeleteMapping(path = "{id}")
    public void deletaAluno(@PathVariable Long id) {
        professoresService.deletar(id);
    }

    @CrossOrigin(origins = "http://127.0.0.1:3000")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfessorModel cadastrarNovoProfessor(@RequestBody ProfessorModel professor){ //colocamos o contatosModel como parametro para saber o que presiamos colocar no corpo da requisição
        return professoresService.cadastrar(professor);
    }

}
