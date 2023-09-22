package api.GestaoEscolar.controller;

import api.GestaoEscolar.dto.CursoDTO;
import api.GestaoEscolar.model.CursoModel;
import api.GestaoEscolar.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    CursoService cursoService;


    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    //Endpoint para cadastrar curso
    @PostMapping
    public ResponseEntity<CursoModel> cadastrarCurso(@RequestBody CursoDTO cursoDTO) {
        CursoModel cursoCadastrado = cursoService.cadastrarCurso(cursoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoCadastrado);
    }

    //Endpoint para listar todos os cursos
    @GetMapping
    public ResponseEntity<Iterable<CursoModel>> listarTodosCursos() {
        Iterable<CursoModel> cursos = cursoService.listarTodosCursos();
        return ResponseEntity.ok(cursos);
    }

    //Endpoint para listar curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<CursoModel> listarCursoPorId(@PathVariable Long id) {
        Optional<CursoModel> cursoCadastrado = cursoService.listarCursoPorId(id);
        if (cursoCadastrado.isPresent()) {
            return ResponseEntity.ok(cursoCadastrado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Endpoint para atualizar curso por ID
    @PutMapping("/{id}")
    public ResponseEntity<CursoModel> atualizarCursoPorId(@PathVariable Long id, @RequestBody CursoDTO cursoDTO) {
        cursoService.atualizarCursoPorId(id, cursoDTO);
        return ResponseEntity.noContent().build();
    }

    //Endpoint para deletar curso por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCursoPorId(@PathVariable Long id) {
        cursoService.deletarCursoPorId(id);
        return ResponseEntity.noContent().build();
    }
}