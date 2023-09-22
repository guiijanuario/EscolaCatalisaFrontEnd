package api.GestaoEscolar.service;

import api.GestaoEscolar.dto.CursoDTO;
import api.GestaoEscolar.model.CursoModel;
import api.GestaoEscolar.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CursoService {
    CursoRepository cursoRepository;

    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    // Método para cadastrar curso
    public CursoModel cadastrarCurso(CursoDTO cursoDTO) {
        CursoModel cursoModel = new CursoModel();
        cursoModel.setNome_curso(cursoDTO.getNome_curso());
        cursoModel.setCarga_horaria(cursoDTO.getCarga_horaria());
        return cursoRepository.save(cursoModel);

    }

    //Método para listar todos os cursos
    public Iterable<CursoModel> listarTodosCursos() {
        return cursoRepository.findAll();
    }

    //Método para listar curso por ID
    public Optional<CursoModel> listarCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    //Método para atualizar curso por ID
    public String atualizarCursoPorId(Long id, CursoDTO cursoDTO) {
        Optional<CursoModel> cursoCadastrado = cursoRepository.findById(id);
        if (cursoCadastrado.isPresent()) {
            CursoModel cursoModel = cursoCadastrado.get();
            cursoModel.setNome_curso(cursoDTO.getNome_curso());
            cursoModel.setCarga_horaria(cursoDTO.getCarga_horaria());
            cursoRepository.save(cursoModel);
            return ("Curso atualizado com sucesso!");
        } else {
            throw new RuntimeException("Curso ID não encontrado com ID: " + id);
        }
    }

    //Método para deletar curso por ID utilizando o Optional
    public String deletarCursoPorId(Long id) {
        Optional<CursoModel> cursoCadastrado = cursoRepository.findById(id);
        if (cursoCadastrado.isPresent()) {
            cursoRepository.delete(cursoCadastrado.get());
            return ("Curso deletado com sucesso!");
        } else {
            throw new RuntimeException("Curso ID não encontrado com ID: " + id);
        }

    }
}
