package com.universidade.registro_universidade.controller;

import org.springframework.web.bind.annotation.RestController;

import com.universidade.registro_universidade.DTO.AlunoDTO;
import com.universidade.registro_universidade.DTO.ProfessorDTO;
import com.universidade.registro_universidade.service.AlunoService;
import com.universidade.registro_universidade.service.ProfessorService;

import jakarta.persistence.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value = "/registro")
public class RegistroController {

    @Autowired
    private ProfessorService professorService;
    @Autowired
    private AlunoService alunoService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {

        try {
            Map<String, Object> map = new HashMap<>();
            List<ProfessorDTO> professores = professorService.professores();
            List<AlunoDTO> alunos = alunoService.alunos();
            map.put("professores", professores);
            map.put("alunos", alunos);
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
}
