package api.GestaoEscolar.controller;

import api.GestaoEscolar.dto.ProfessorDTO;
import api.GestaoEscolar.model.ProfessorModel;
import api.GestaoEscolar.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    //Endpoint para cadastrar professor
    @PostMapping
    public ResponseEntity<ProfessorModel> cadastrarProfessor(@RequestBody ProfessorDTO professorDTO) {
        ProfessorModel professorCadastrado = professorService.cadastrarProfessor(professorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorCadastrado);
    }

    //Endpoint para listar todos os professores
    @GetMapping
    public ResponseEntity<Iterable<ProfessorModel>> listarTodosProfessores() {
        Iterable<ProfessorModel> professores = professorService.listarTodosProfessores();
        return ResponseEntity.ok(professores);
    }

    //Endpoint para listar professor por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProfessorModel> listarProfessorPorId(@PathVariable Long id) {
        Optional <ProfessorModel> professorCadastrado = professorService.listarProfessorPorId(id);
        if(professorCadastrado.isPresent()) {
            return ResponseEntity.ok(professorCadastrado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Endpoint para atualizar professor por ID
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarProfessorPorId(@PathVariable Long id, @RequestBody ProfessorDTO professorDTO) {
        professorService.atualizarProfessorPorId(id, professorDTO);
        return ResponseEntity.ok("Professor atualizado com sucesso!");
    }

    //Endpoint para deletar professor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProfessorPorId(@PathVariable Long id) {
        professorService.deletarProfessorPorId(id);
        return ResponseEntity.noContent().build();
    }

}
