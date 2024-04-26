package com.universidade.registrouniversitario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.universidade.registrouniversitario.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
   
    @Query(value = "SELECT * FROM ALUNO WHERE ativo = true",  nativeQuery = true)
    public  List<Aluno> findAllWhere();

   
    @Query(value = "SELECT * FROM ALUNO WHERE aluno_id = :id AND ativo = true",  nativeQuery = true)
    public  Optional<Aluno> findByIdWhere(@Param("id") Integer id);
}
