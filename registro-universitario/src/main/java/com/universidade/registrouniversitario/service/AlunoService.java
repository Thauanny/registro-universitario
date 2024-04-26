package com.universidade.registrouniversitario.service;

import com.universidade.registrouniversitario.model.Aluno;
import com.universidade.registrouniversitario.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno aluno(Integer id) {
        Optional<Aluno> alunoOptional = alunoRepository.findByIdWhere(id);
        if (alunoOptional.isPresent()) {
            return alunoOptional.get();
        } else {
            throw new EntityNotFoundException(
                    "Aluno não encontrado para o ID: " + id);
        }
    }

    public List<Aluno> alunos() {
        return alunoRepository.findAllWhere();
    }
    public List<Aluno> alunosAtivosNaoAtivos() {
        return alunoRepository.findAll();
    }

    
    public Aluno register(Aluno aluno) {
            return alunoRepository.save(aluno);
    }

    public Aluno update(Aluno aluno, Integer id) {
        Aluno _aluno = aluno(id);
        _aluno.setCpf(aluno.getCpf());
        _aluno.setEmail(aluno.getEmail());
        _aluno.setPassword(aluno.getPassword());
        _aluno.setUsername(aluno.getUsername());
        return register(_aluno);
    }

    public void delete(Integer id) {
        alunoRepository.deleteById(id);
    }

    public void deleteLogic(Integer id) {
        Aluno _aluno = aluno(id);
        _aluno.setAtivo(false);
        register(_aluno);

    }
}
