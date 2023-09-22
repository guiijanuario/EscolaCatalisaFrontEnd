package com.catalisa.colegio.controller;

import com.catalisa.colegio.dto.MatriculaDto;
import com.catalisa.colegio.model.MatriculaModel;
import com.catalisa.colegio.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(path = "/matriculas")
@RestController
public class MatriculaController {

    @Autowired
    MatriculaService matriculaService;

    @GetMapping
//        public List<Matricula> listarMatricula(){
//
//            return matriculaService.listarMatricula();
//        }
    public ResponseEntity<List<MatriculaDto>> findAll(){
        List<MatriculaModel> list= matriculaService.listarMatricula();
        List<MatriculaDto> listDto = list.stream().map(MatriculaDto::new).collect(Collectors.toList());
        return  ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public MatriculaModel cadastrar(@RequestBody MatriculaModel matricula){
        return matriculaService.cadastrar(matricula);
    }
}
