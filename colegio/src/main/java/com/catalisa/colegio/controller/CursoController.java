package com.catalisa.colegio.controller;

import com.catalisa.colegio.dto.CursoDto;
import com.catalisa.colegio.model.CursoModel;
import com.catalisa.colegio.model.MatriculaModel;
import com.catalisa.colegio.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping(path = "/cursos")
@RestController

public class CursoController {

    @Autowired
    CursoService cursoService;

    @CrossOrigin(origins = "http://127.0.0.1:3000")
    @GetMapping

    public ResponseEntity<List<CursoDto>> findAll(){
        List<CursoModel> list= cursoService.listarCurso();
        List<CursoDto> listDto = list.stream().map(CursoDto::new).collect(Collectors.toList());
        return  ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CursoModel> buscarPorId(@PathVariable Long id) {

        Optional<CursoModel> curso = cursoService.listarPorId(id);

        if (curso.isPresent()) {

            return ResponseEntity.ok(curso.get());

        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping(path = "{id}")
    public void deletaCurso(@PathVariable Long id){
        cursoService.deletar(id);
    }

    @CrossOrigin(origins = "http://127.0.0.1:3000")
    @PostMapping
    public CursoModel cadastrar(@RequestBody CursoModel cursoModel){
        return cursoService.cadastrar(cursoModel);
    }

}

