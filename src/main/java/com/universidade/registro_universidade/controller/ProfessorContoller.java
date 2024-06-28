package com.universidade.registro_universidade.controller;

import com.universidade.registro_universidade.DTO.ProfessorCreateDTO;
import com.universidade.registro_universidade.DTO.ProfessorDTO;
import com.universidade.registro_universidade.service.ProfessorService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
@RequestMapping(value = "/professor")
public class ProfessorContoller {

  @Autowired
  private ProfessorService professorService;

  @GetMapping("/{id}")
  public ResponseEntity<?> professor(@PathVariable Integer id) {
    try {
      return ResponseEntity.ok(professorService.professor(id));
    } catch (EntityNotFoundException e) {
      Map<String, String> map = new HashMap<>();
      map.put("message", e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("{\"message\": \"Algo deu errado\"}");
    }
  }

  @GetMapping("/ActivenotActive/")
  public ResponseEntity<?> professorAtivosNaoAtivos() {
    try {
      return ResponseEntity.ok(professorService.professorAtivosNaoAtivos());
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("{\"message\": \"Algo deu errado\"}");
    }
  }

  @GetMapping("/all")
  public ResponseEntity<?> professor() {
    try {
      return ResponseEntity.ok(professorService.professores());
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("{\"message\": \"Algo deu errado\"}");
    }
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody ProfessorCreateDTO professor) {
    return ResponseEntity.badRequest().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(
      @RequestBody ProfessorDTO professor,
      @PathVariable Integer id) {
    try {
      return ResponseEntity.ok(professorService.update(professor, id));
    } catch (EntityNotFoundException e) {
      Map<String, String> map = new HashMap<>();
      map.put("message", e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    } catch (ConstraintViolationException ex) {
      Map<String, Object> errorMap = new HashMap<>();
      List<String> errorMessages = new ArrayList<>();
      for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
        errorMessages.add(violation.getMessage());
      }
      errorMap.put("message", errorMessages);

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("{\"message\": \"Algo deu errado\"}");
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    try {
      professorService.delete(id);
      return ResponseEntity
          .status(HttpStatus.OK)
          .body("{\"message\": \"Professor deletado com sucesso\"}");
    }catch (EntityNotFoundException e) {
      Map<String, String> map = new HashMap<>();
      map.put("message", "Professor não encontrado");
      return ResponseEntity
              .status(HttpStatus.NOT_FOUND)
              .body(map);
  } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("{\"message\": \"Algo deu errado\"}");
    }
  }

  @PutMapping("/deleteLogic/{id}")
  public ResponseEntity<?> deleteLogic(@PathVariable Integer id) {
    try {
      professorService.deleteLogic(id);
      return ResponseEntity
          .status(HttpStatus.OK)
          .body("{\"message\": \"Professor deletado com sucesso\"}");
    } catch (EntityNotFoundException e) {
      Map<String, String> map = new HashMap<>();
      map.put("message", "Professor não encontrado");
      return ResponseEntity
              .status(HttpStatus.NOT_FOUND)
              .body(map);
  }catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("{\"message\": \"Algo deu errado\"}");
    }
  }
}
