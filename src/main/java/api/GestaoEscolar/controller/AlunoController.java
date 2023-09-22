package api.GestaoEscolar.controller;

import api.GestaoEscolar.dto.AlunoDTO;
import api.GestaoEscolar.model.AlunoModel;
import api.GestaoEscolar.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    AlunoService alunoService;

    @Autowired
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    //Endpoint para cadastrar aluno no sistema
    @PostMapping
    public ResponseEntity<AlunoModel> cadastrarAluno(@RequestBody AlunoDTO alunoDTO) {
        AlunoModel alunoCadastrado = alunoService.cadastrarAluno(alunoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoCadastrado);

    }

    //Endpoint para listar todos os alunos do sistema
    @GetMapping
    public ResponseEntity<List<AlunoModel>> listarTodosAlunos() {
        List<AlunoModel> alunos = alunoService.listarTodosAlunos();
        return ResponseEntity.ok(alunos);
    }

    //Endpoint para listar aluno por ID
    @GetMapping("/{id}")
    public ResponseEntity<String> listarAlunoPorId(@PathVariable Long id) {
        Optional<AlunoModel> alunoModel = alunoService.listarAlunoPorId(id);
        if (alunoModel.isPresent()) {
            return ResponseEntity.ok(alunoModel.get().toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
        }
    }

    //Endpoint para atualizar aluno por ID
    @PutMapping("/{id}")
    public ResponseEntity<AlunoModel> atualizarAlunoPorId(@PathVariable Long id, @RequestBody AlunoDTO alunoDTO) {
        alunoService.atualizarAlunoPorId(id, alunoDTO);
        return ResponseEntity.noContent().build();
    }

    //Endpoint para deletar aluno por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAlunoPorId(@PathVariable Long id) {
        alunoService.deletarAlunoPorId(id);
        return ResponseEntity.noContent().build();
    }

}
