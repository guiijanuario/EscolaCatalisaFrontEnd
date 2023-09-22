package com.catalisa.colegio.service;

import com.catalisa.colegio.model.MatriculaModel;
import com.catalisa.colegio.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaService {
    @Autowired
    MatriculaRepository matriculaRepository;

    //Listar Todos os matricula
    public List<MatriculaModel> listarMatricula() {
        return matriculaRepository.findAll();

    }
    public MatriculaModel buscarPorId(Long id){
        return matriculaRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

    }

    public MatriculaModel cadastrar(MatriculaModel matricula){

        return matriculaRepository.save(matricula); //salva as informações dos usuários
    }
}

