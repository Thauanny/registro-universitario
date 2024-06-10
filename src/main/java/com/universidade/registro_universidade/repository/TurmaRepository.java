package com.universidade.registro_universidade.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.universidade.registro_universidade.model.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {
   
   
    @Query(value = "SELECT * \n" + //
                "FROM Turma\n" + //
                "WHERE id = :id AND ativo = true",  nativeQuery = true)
    public  Optional<Turma> findByIdWhere(@Param("id") Integer id);
}
