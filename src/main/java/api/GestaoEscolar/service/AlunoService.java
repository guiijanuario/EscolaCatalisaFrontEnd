package api.GestaoEscolar.service;

import api.GestaoEscolar.dto.AlunoDTO;
import api.GestaoEscolar.model.AlunoModel;
import api.GestaoEscolar.repository.AlunoRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    AlunoRepository alunoRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    //Método para cadastrar aluno no sistema
    public AlunoModel cadastrarAluno(AlunoDTO alunoDTO) {
        AlunoModel alunoModel = new AlunoModel();
        alunoModel.setNome_aluno(alunoDTO.getNome_aluno());
        alunoModel.setIdade(alunoDTO.getIdade());
        alunoModel.setEmail(alunoDTO.getEmail());
        return alunoRepository.save(alunoModel);
    }

    //Método para listar todos os alunos do sistema
    public List<AlunoModel> listarTodosAlunos() {
        return alunoRepository.findAll();
    }

    //Método para listar aluno por ID
    public Optional<AlunoModel> listarAlunoPorId(Long id) {
        return alunoRepository.findById(id);
    }

    //Método para deletar aluno por ID
    @SneakyThrows
    public void deletarAlunoPorId(Long id) {
        Optional<AlunoModel> alunoCadastrado = alunoRepository.findById(id);
        if (alunoCadastrado.isPresent()) {
            alunoRepository.delete(alunoCadastrado.get());
        } else {
            throw new RuntimeException("Aluno ID não encontrado com ID: " + id);
        }

    }

    //Método para atualizar aluno por ID
    @SneakyThrows
    public void atualizarAlunoPorId(Long id, AlunoDTO alunoDTO) {
        Optional<AlunoModel> alunoCadastrado = alunoRepository.findById(id);
        //Aqui terá o throw new Exception para retornar caso Aluno não seja encontrado
        if (alunoCadastrado.isPresent()) {
            AlunoModel alunoModel = alunoCadastrado.get();
            alunoModel.setNome_aluno(alunoDTO.getNome_aluno());
            alunoModel.setIdade(alunoDTO.getIdade());
            alunoModel.setEmail(alunoDTO.getEmail());
            alunoRepository.save(alunoModel);
        } else {

            throw new RuntimeException("Aluno não encontrado com ID: " + id);
        }

    }


}
