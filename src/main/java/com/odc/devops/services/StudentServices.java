package com.odc.devops.services;

import com.odc.devops.entites.Students;
import java.util.List;

public interface StudentServices {

    Students saveStudent(Students student);

    Students updateStudent(Long id, Students studentDetails);

    List<Students> getAllStudents();

    Students getStudentById(Long id);

    void deleteStudent(Long id);

    List<Students> searchByNom(String nom);
}