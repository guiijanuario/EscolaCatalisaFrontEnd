package com.catalisa.colegio.service;

import com.catalisa.colegio.model.CursoModel;
import com.catalisa.colegio.model.MatriculaModel;
import com.catalisa.colegio.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    //Listar Todos os cursos
    public List<CursoModel> listarCurso() {
        return cursoRepository.findAll();

    }

    public Optional<CursoModel> listarPorId(Long id) {

        return cursoRepository.findById(id);

    }
    public void deletar(Long id){
        cursoRepository.deleteById(id);

    }
    public CursoModel cadastrar(CursoModel cursoModel){

        return cursoRepository.save(cursoModel); //salva as informações dos usuários
    }

}

