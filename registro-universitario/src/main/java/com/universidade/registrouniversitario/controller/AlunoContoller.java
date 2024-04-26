package com.universidade.registrouniversitario.controller;

import com.universidade.registrouniversitario.model.Aluno;
import com.universidade.registrouniversitario.service.AlunoService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/aluno")
public class AlunoContoller {

  @Autowired
  private AlunoService alunoService;

  @GetMapping("/{id}")
  public ResponseEntity<?> aluno(@PathVariable Integer id) {
    try {
      return ResponseEntity.ok(alunoService.aluno(id));
    }catch (EntityNotFoundException e) {
      Map<String, String> map = new HashMap<>();
            map.put("message", e.getMessage());
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(map);
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("{\"message\": \"Algo deu errado\"}");
    }
  }

  @GetMapping("/ActivenotActive/")
  public ResponseEntity<?> alunosAtivosNaoAtivos() {
    try {
      return ResponseEntity.ok(alunoService.alunosAtivosNaoAtivos());
    }catch (EntityNotFoundException e) {  
      Map<String, String> map = new HashMap<>();
            map.put("message", e.getMessage());
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(map);
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("{\"message\": \"Algo deu errado\"}");
    }
  }

  @GetMapping("/all")
  public ResponseEntity<?> alunos() {
    try {
      return ResponseEntity.ok(alunoService.alunos());
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("{\"message\": \"Algo deu errado\"}");
    }
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody Aluno aluno) {
    try {

      return ResponseEntity.ok(alunoService.register(aluno));

    } catch (ConstraintViolationException ex) {
      Map<String, Object> errorMap = new HashMap<>();
      List<String> errorMessages = new ArrayList<>();
      for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
        errorMessages.add(violation.getMessage());
      }
      errorMap.put("message", errorMessages);

      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(errorMap);

    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("{\"message\": \"Algo deu errado\"}");
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(
      @RequestBody Aluno aluno,
      @PathVariable Integer id) {
    try {
      return ResponseEntity.ok(alunoService.update(aluno, id));
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("{\"message\": \"Algo deu errado\"}");
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    try {
      alunoService.delete(id);
      return ResponseEntity
          .status(HttpStatus.OK)
          .body("{\"message\": \"Aluno deletado com sucesso\"}");
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("{\"message\": \"Algo deu errado\"}");
    }
  }

  @PutMapping("/deleteLogic/{id}")
  public ResponseEntity<?> deleteLogic(@PathVariable Integer id) {
    try {
      alunoService.deleteLogic(id);
      return ResponseEntity
          .status(HttpStatus.OK)
          .body("{\"message\": \"Aluno deletado com sucesso\"}");
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("{\"message\": \"Algo deu errado\"}");
    }
  }
}
