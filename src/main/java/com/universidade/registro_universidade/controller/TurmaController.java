package com.universidade.registro_universidade.controller;

import org.springframework.web.bind.annotation.RestController;

import com.universidade.registro_universidade.DTO.TurmaCreateDTO;
import com.universidade.registro_universidade.DTO.TurmaDTO;
import com.universidade.registro_universidade.service.TurmaService;

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
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "/turma")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> turma(@PathVariable Integer id) {

        try {
            Map<String, Object> map = new HashMap<>();
            TurmaDTO turma = turmaService.turma(id);
            map.put("turma", turma);
            return ResponseEntity.ok(map);

        } catch (EntityNotFoundException e) {
            Map<String, String> map = new HashMap<>();
            map.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\": \"Algo deu errado\"}");
        }
    }

    @GetMapping("/ActivenotActive/")
    public ResponseEntity<?> turmaAtivosNaoAtivos() {
        try {
            return ResponseEntity.ok(turmaService.turmaAtivosNaoAtivos());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\": \"Algo deu errado\"}");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody TurmaCreateDTO turma) {
        try {
            return ResponseEntity.ok(turmaService.save(turma));
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
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\": \"Algo deu errado\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @RequestBody TurmaDTO turma,
            @PathVariable Integer id) {
        try {
            return ResponseEntity.ok(turmaService.update(turma, id));
        } catch (EntityNotFoundException e) {
            Map<String, String> map = new HashMap<>();
            map.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
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
            turmaService.delete(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("{\"message\": \"turma deletada com sucesso\"}");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\": \"Algo deu errado\"}");
        }
    }

    @PutMapping("/addProfessor/{id}")
    public ResponseEntity<?> registerProfessorOnTurma(@PathVariable Integer id,
            @RequestParam("id_professor") Integer idProfessor) {
        try {

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(turmaService.registerProfessorOnTurma(id, idProfessor));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\": \"Algo deu errado\"}");
        }
    }

    @PutMapping("/addAluno/{id}")
    public ResponseEntity<?> registerAlunoOnTurma(@PathVariable Integer id, @RequestParam("id_aluno") Integer idAluno) {
        try {

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(turmaService.registerAlunoOnTurma(id, idAluno));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\": \"Algo deu errado\"}");
        }
    }

    @PutMapping("/deleteLogic/{id}")
    public ResponseEntity<?> deleteLogic(@PathVariable Integer id) {
        try {
            turmaService.deleteLogic(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("{\"message\": \"turma deletada com sucesso\"}");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\": \"Algo deu errado\"}");
        }
    }
}
