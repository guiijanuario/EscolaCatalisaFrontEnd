package com.zupschool.gerenciamentoescolar.controller;

import com.zupschool.gerenciamentoescolar.DTO.CursoDTO;
import com.zupschool.gerenciamentoescolar.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/cursos")
public class CursoController {
    @Autowired
    CursoService cursoService;

    @GetMapping
    @CrossOrigin(origins = "http://127.0.0.1:3000/")
    public ResponseEntity<List<CursoDTO>> listaCursos(){
        return ResponseEntity.ok(cursoService.listaCursos());
    }

    @GetMapping(path = "/{id}")
    @CrossOrigin(origins = "http://127.0.0.1:3000/")
    public ResponseEntity<Optional<CursoDTO>> getCursoPorId(@PathVariable Long id){

        return ResponseEntity.ok(cursoService.getCursoPorId(id));
    }
    @PostMapping
    @CrossOrigin(origins = "http://127.0.0.1:3000/")
    public ResponseEntity<CursoDTO> cadastraCurso(@RequestBody CursoDTO cursoDTO){
        CursoDTO cursoNovo = cursoService.cadastraCurso(cursoDTO);
        return new ResponseEntity<>(cursoNovo, HttpStatus.CREATED);
    }

    @PutMapping(path="/{id}")
    @CrossOrigin(origins = "http://127.0.0.1:3000/")
    public CursoDTO atulizaCurso(@PathVariable Long id, @RequestBody CursoDTO cursoDTO){
        return cursoService.atualizaCurso(cursoDTO);
    }

    @DeleteMapping(path="/{id}")
    @CrossOrigin(origins = "http://127.0.0.1:3000/")
    public void deleteCurso (@PathVariable Long id){
        cursoService.deletaCurso(id);
    }
}
