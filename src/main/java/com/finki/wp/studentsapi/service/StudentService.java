package com.finki.wp.studentsapi.service;

import com.finki.wp.studentsapi.model.Student;
import com.finki.wp.studentsapi.model.StudentDisplay;
import com.finki.wp.studentsapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentService() {
    }

    public Optional<List<StudentDisplay>> getAllStudents() {
        return Optional.of(this.studentRepository.findAll()
                .stream().map(i -> new StudentDisplay(i.getIndex(), i.getName(), i.getLastName()))
                .collect(Collectors.toList()));
    }

    public boolean addStudent(Student student) {

        if(this.studentRepository.findById(student.getIndex()).isPresent()) return false;

        this.studentRepository.save(student);
        return true;
    }

    public Optional<Student> getStudentByIndex(String index) {
        return this.studentRepository.findById(index);
    }

    public Optional<List<Student>> getStudentsByStudyProgramId(Long id) {
        return this.studentRepository.findAllByStudyProgram(id);
    }

    public void deleteStudent(String index) {
        this.studentRepository.deleteById(index);
    }

    @Transactional
    public void alterStudent(String index, String name, String lastName, Long studyProgram) {
       //this.deleteStudent(index);
       //this.addStudent(new Student(index, name, lastName, studyProgram));

        Student s = this.studentRepository.findById(index).get();
        s.setName(name);
        s.setLastName(lastName);
        s.setStudyProgram(studyProgram);
    }
}
