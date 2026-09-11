package com.odc.devops.services;

import com.odc.devops.entites.Students;
import com.odc.devops.repositories.StudentRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor

    public class StudentsServiceImpl implements StudentServices {

        private final StudentRepositories StudentRepositories;

        @Override
        public Students saveStudent(Students student) {
            Date now = new Date();
            student.setCreationDate(now);
            student.setUpdateDate(now);
            return StudentRepositories.save(student);
        }

        @Override
        public Students updateStudent(Long id, Students studentDetails) {
            Students existingStudent = getStudentById(id);

            existingStudent.setNom(studentDetails.getNom());
            existingStudent.setPrenom(studentDetails.getPrenom());
            existingStudent.setDateNaissance(studentDetails.getDateNaissance());
            existingStudent.setUpdateDate(new Date()); // Mise à jour automatique de la date de modification

            return StudentRepositories.save(existingStudent);
        }

        @Override
        public List<Students> getAllStudents() {
            return StudentRepositories.findAll();
        }

        @Override
        public Students getStudentById(Long id) {
            return (Students) StudentRepositories.findById(id)
                    .orElseThrow(() -> new RuntimeException("Étudiant introuvable avec l'ID : " + id));
        }

        @Override
        public void deleteStudent(Long id) {
            Students student = getStudentById(id);
            StudentRepositories.delete(student);
        }

        @Override
        public List<Students> searchByNom(String nom) {
            return StudentRepositories.findByNomContainingIgnoreCase(nom);
        }
    }
