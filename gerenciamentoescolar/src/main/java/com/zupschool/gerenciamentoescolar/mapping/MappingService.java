package com.zupschool.gerenciamentoescolar.mapping;

import com.zupschool.gerenciamentoescolar.DTO.AlunoDTO;
import com.zupschool.gerenciamentoescolar.DTO.CursoDTO;
import com.zupschool.gerenciamentoescolar.DTO.ProfessorDTO;
import com.zupschool.gerenciamentoescolar.Model.Aluno;
import com.zupschool.gerenciamentoescolar.Model.Curso;
import com.zupschool.gerenciamentoescolar.Model.Professor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MappingService {
    @Autowired
    private ModelMapper modelMapper;

    public AlunoDTO mapAlunoToDTO(Aluno aluno){
        return modelMapper.map(aluno,AlunoDTO.class);
    }
    public Aluno mapDTOToAluno(AlunoDTO alunoDTO){
        return modelMapper.map(alunoDTO,Aluno.class);
    }

    public CursoDTO mapCursoToDTO(Curso curso){
        return modelMapper.map(curso,CursoDTO.class);
    }

    public Curso mapDTOToCurso (CursoDTO cursoDTO){
        return modelMapper.map(cursoDTO,Curso.class);
    }
    public ProfessorDTO mapProfessorToDTO(Professor professor){
        return modelMapper.map(professor, ProfessorDTO.class);
    }
    public Professor mapDTOToProfessor (ProfessorDTO professorDTO){
        return modelMapper.map(professorDTO,Professor.class);
    }

}
