package com.finki.wp.studentsapi.service;

import com.finki.wp.studentsapi.model.Student;
import com.finki.wp.studentsapi.model.StudentDisplay;
import com.finki.wp.studentsapi.model.exceptions.InvalidIndexException;
import com.finki.wp.studentsapi.model.exceptions.InvalidProgramException;
import com.finki.wp.studentsapi.model.exceptions.ParametarMissingException;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Optional<List<Student>> getAllStudents();

    boolean addStudent(String index, String name, String lastName, String studyProgram) throws ParametarMissingException, InvalidIndexException, InvalidProgramException;

    Optional<Student> getStudentByIndex(String index);

    Optional<List<Student>> getStudentsByStudyProgramId(Long id);

    void deleteStudent(String index);

    boolean alterStudent(String index, String name, String lastName, String studyProgram);
}
