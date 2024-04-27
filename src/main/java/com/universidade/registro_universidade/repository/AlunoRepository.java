package com.universidade.registro_universidade.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.universidade.registro_universidade.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
   
    @Query(value = "SELECT ALUNO.*, PESSOA.*\n" + //
                "FROM ALUNO\n" + //
                "INNER JOIN PESSOA ON ALUNO.id = PESSOA.id\n" + //
                "WHERE PESSOA.ativo = true",  nativeQuery = true)
    public  List<Aluno> findAllWhere();

   
    @Query(value = "SELECT ALUNO.*, PESSOA.*\n" + //
                "FROM ALUNO\n" + //
                "INNER JOIN PESSOA ON ALUNO.id = PESSOA.id\n" + //
                "WHERE PESSOA.id = :id AND PESSOA.ativo = true",  nativeQuery = true)
    public  Optional<Aluno> findByIdWhere(@Param("id") Integer id);
}
