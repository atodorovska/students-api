package com.finki.wp.studentsapi.repository;

import com.finki.wp.studentsapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student, String> {

    Optional<List<Student>> findAllByStudyProgram(Long id);
}
