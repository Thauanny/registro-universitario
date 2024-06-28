package com.universidade.registro_universidade.repository;

import com.universidade.registro_universidade.model.Professor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
  @Query(
    value = "SELECT Professor.*, PESSOA.*\n" + //
    "FROM Professor\n" + //
    "INNER JOIN PESSOA ON Professor.id = PESSOA.id\n" + //
    "WHERE PESSOA.ativo = true",
    nativeQuery = true
  )
  public List<Professor> findAllWhere();

  @Query(
    value = "SELECT Professor.*, PESSOA.*\n" + //
    "FROM Professor\n" + //
    "INNER JOIN PESSOA ON Professor.id = PESSOA.id\n" + //
    "WHERE PESSOA.id = :id AND PESSOA.ativo = true",
    nativeQuery = true
  )
  public Optional<Professor> findByIdWhere(@Param("id") Integer id);

  public UserDetails findByEmail(String email);
}
