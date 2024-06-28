package com.universidade.registro_universidade.controller;

import com.universidade.registro_universidade.DTO.AlunoCreateDTO;
import com.universidade.registro_universidade.DTO.AuthenticationDTO;
import com.universidade.registro_universidade.DTO.PessoaCreateDTO;
import com.universidade.registro_universidade.DTO.ProfessorCreateDTO;
import com.universidade.registro_universidade.service.AlunoService;
import com.universidade.registro_universidade.service.ProfessorService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private AlunoService alunoService;

  @Autowired
  private ProfessorService professorService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO body) {
    var userNamePassword = new UsernamePasswordAuthenticationToken(
      body.getEmail(),
      body.getPassword()
    );
    var auth = this.authenticationManager.authenticate(userNamePassword); 
    return ResponseEntity.ok().build();
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody @Valid PessoaCreateDTO body) {
    switch (body.getType()) {
      case aluno:
        if (
          alunoService.findByEmail(body.getEmail()) != null
        ) return ResponseEntity.badRequest().body("Usuario já cadastrado");
        var aluno = new AlunoCreateDTO();
        aluno.setCpf(body.getCpf());
        aluno.setDataNascimento(body.getDataNascimento());
        aluno.setEmail(body.getEmail());
        aluno.setGenero(body.getGenero());
        aluno.setMatricula(body.getMatricula());
        aluno.setNome(body.getNome());
        aluno.setPassword(body.getPassword());
        aluno.setRole(body.getRole());
        aluno.setType(body.getType());
        return alunoRegister(aluno);
      default:
        if (
          professorService.findByEmail(body.getEmail()) != null
        ) return ResponseEntity.badRequest().body("Usuario já cadastrado");
        var professor = new ProfessorCreateDTO();
        professor.setCpf(body.getCpf());
        professor.setDataNascimento(body.getDataNascimento());
        professor.setEmail(body.getEmail());
        professor.setGenero(body.getGenero());
        professor.setMatricula(body.getMatricula());
        professor.setNome(body.getNome());
        professor.setPassword(body.getPassword());
        professor.setRole(body.getRole());
        professor.setType(body.getType());
        return professorRegister(professor);
    }
  }

  ResponseEntity<?> alunoRegister(AlunoCreateDTO aluno) {
    try {
      alunoService.register(aluno);
      return ResponseEntity.ok().build();
    } catch (ConstraintViolationException ex) {
      Map<String, Object> errorMap = new HashMap<>();
      List<String> errorMessages = new ArrayList<>();
      for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
        errorMessages.add(violation.getMessage());
      }
      errorMap.put("message", errorMessages);

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    } catch (IllegalArgumentException ex) {
      Map<String, Object> errorMap = new HashMap<>();
      errorMap.put("message", ex.getMessage().replaceAll("raw", ""));

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    } catch (DataIntegrityViolationException e) {
      return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body("{\"message\": \"Dados CPF ou Email já cadastrados\"}");
    } catch (Exception e) {
      return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body("{\"message\": \"Algo deu errado\"}");
    }
  }

  ResponseEntity<?> professorRegister(ProfessorCreateDTO professor) {
    try {
      professorService.register(professor);
      return ResponseEntity.ok().build();
    } catch (ConstraintViolationException ex) {
      Map<String, Object> errorMap = new HashMap<>();
      List<String> errorMessages = new ArrayList<>();
      for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
        errorMessages.add(violation.getMessage());
      }
      errorMap.put("message", errorMessages);

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    } catch (IllegalArgumentException ex) {
      Map<String, Object> errorMap = new HashMap<>();
      errorMap.put("message", ex.getMessage().replaceAll("raw", ""));

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    } catch (DataIntegrityViolationException e) {
      return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body("{\"message\": \"Dados CPF ou Email já cadastrados\"}");
    } catch (Exception e) {
      return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body("{\"message\": \"Algo deu errado\"}");
    }
  }
}
