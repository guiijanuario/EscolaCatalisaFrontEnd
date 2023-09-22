package br.com.zup.gerenciamentoEscolar.controller;

import br.com.zup.gerenciamentoEscolar.dto.AlunoDTO;
import br.com.zup.gerenciamentoEscolar.enums.TipoLogEvento;
import br.com.zup.gerenciamentoEscolar.model.AlunoModel;
import br.com.zup.gerenciamentoEscolar.service.AlunoService;
import br.com.zup.gerenciamentoEscolar.service.LogEventosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/alunos", produces = {"application/json"})
@Tag(name = "Feature - Alunos")
@CrossOrigin(origins = "http://127.0.0.1:5501/")
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @Autowired
    LogEventosService logEventosService;

    @GetMapping
    @Operation(summary = " : Lista todos os alunos", method = "GET")
    public ResponseEntity<List<AlunoDTO>> listarTodosAlunos() {
        List<AlunoModel> alunosEncontrados = alunoService.listarTodasContas();

        List<AlunoDTO> alunosDTO = new ArrayList<>();
        for (AlunoModel aluno : alunosEncontrados) {
            AlunoDTO alunoDTO = new AlunoDTO(
                    aluno.getId(),
                    aluno.getNome(),
                    aluno.getIdade(),
                    aluno.getEmail(),
                    aluno.getCpf(),
                    aluno.getSenha());
            alunosDTO.add(alunoDTO);
        }

        logEventosService.gerarLogListarAll(TipoLogEvento.LISTOU_ALUNOS);
        return ResponseEntity.ok(alunosDTO);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = " : Lista um aluno pelo ID", method = "GET")
    public ResponseEntity<?> listarAlunoId(@PathVariable Long id){
        Optional<AlunoModel> alunoEncontrado = alunoService.buscarAlunoPeloId(id);

        if(alunoEncontrado.isEmpty()){
            AlunoModel alunoNull = new AlunoModel();
            logEventosService.gerarLogBuscaDePeloId(alunoNull, TipoLogEvento.ALUNO_NAO_ENCONTRADO);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado tente novamente!");
        }

        AlunoModel aluno = alunoEncontrado.orElseThrow(() -> new NoSuchElementException("Aluno não encontrado"));

        logEventosService.gerarLogBuscaDePeloId(aluno, TipoLogEvento.LISTOU_ALUNO);

        return ResponseEntity.ok(alunoEncontrado.get());
    }

    @PostMapping
    @Operation(summary = " : Cadastra um novo aluno", method = "POST")
    public ResponseEntity<AlunoDTO> cadastrarAluno(@RequestBody AlunoModel alunoModel){
        AlunoModel novoAluno = alunoService.criarAluno(alunoModel);
        logEventosService.gerarLogCadastroRealizado(novoAluno, TipoLogEvento.ALUNO_CADASTRADO);

        AlunoDTO alunoDTO = new AlunoDTO(
                novoAluno.getId(),
                novoAluno.getNome(),
                 novoAluno.getIdade(),
                novoAluno.getEmail(),
                novoAluno.getCpf(),
                novoAluno.getSenha());
        return new ResponseEntity<>(alunoDTO, HttpStatus.CREATED);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> fazLogin(@RequestBody AlunoModel alunoModel){
        AlunoModel alunoExistente = alunoService.buscarAlunoPorEmail(alunoModel.getEmail());

        if (alunoExistente != null && alunoExistente.getSenha().equals(alunoModel.getSenha())) {
            return ResponseEntity.ok("Parabéns, você está logado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas. Verifique seu e-mail e senha.");
        }
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = " : Deleta um aluno pelo ID", method = "DELETE")
    public void deletarAluno(@PathVariable Long id){
        Optional<AlunoModel> alunoEncontrado = alunoService.buscarAlunoPeloId(id);
        AlunoModel aluno = alunoEncontrado.orElseThrow(() -> new NoSuchElementException("Aluno não encontrado"));
        logEventosService.gerarLogDeleteRealizado(aluno, TipoLogEvento.ALUNO_DELETADO);
        alunoService.deletarAluno(id);
    }

}
