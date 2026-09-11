package com.odc.devops.controllers;

import com.odc.devops.entites.Students;
import com.odc.devops.services.StudentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudentControllers {

    private final StudentServices studentService;

    // Ajouter un étudiant
    @PostMapping
    public ResponseEntity<Map<String, Object>> createStudent(@RequestBody Students student) {
        Students createdStudent = studentService.saveStudent(student);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Étudiant créé avec succès !");
        response.put("status", HttpStatus.CREATED.value());
        response.put("data", createdStudent);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Récupérer la liste complète des étudiants
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStudents() {
        List<Students> students = studentService.getAllStudents();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Liste des étudiants récupérée avec succès.");
        response.put("count", students.size());
        response.put("data", students);

        return ResponseEntity.ok(response);
    }

    // Récupérer un étudiant par ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getStudentById(@PathVariable Long id) {
        Students student = studentService.getStudentById(id);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Étudiant trouvé.");
        response.put("data", student);

        return ResponseEntity.ok(response);
    }

    // Mettre à jour un étudiant
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateStudent(@PathVariable Long id, @RequestBody Students student) {
        Students updatedStudent = studentService.updateStudent(id, student);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Étudiant mis à jour avec succès !");
        response.put("data", updatedStudent);

        return ResponseEntity.ok(response);
    }

    // Supprimer un étudiant
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "L'étudiant avec l'ID " + id + " a été supprimé avec succès.");
        response.put("status", HttpStatus.OK.value());

        return ResponseEntity.ok(response);
    }

    // Rechercher par nom
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchByNom(@RequestParam String nom) {
        List<Students> results = studentService.searchByNom(nom);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Recherche effectuée pour le nom : " + nom);
        response.put("count", results.size());
        response.put("data", results);

        return ResponseEntity.ok(response);
    }
}