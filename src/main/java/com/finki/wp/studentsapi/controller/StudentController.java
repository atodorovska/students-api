package com.finki.wp.studentsapi.controller;

import com.finki.wp.studentsapi.model.Student;
import com.finki.wp.studentsapi.model.exceptions.InvalidIndexException;
import com.finki.wp.studentsapi.model.exceptions.InvalidProgramException;
import com.finki.wp.studentsapi.model.exceptions.ParametarMissingException;
import com.finki.wp.studentsapi.service.implementation.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin({"*"})
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    public StudentController() {
    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudents(){
        return this.studentServiceImpl.getAllStudents().map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{index}")
    public ResponseEntity<Student> getStudentByIndex(@PathVariable String index){
        return this.studentServiceImpl.getStudentByIndex(index).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by_study_program/{id}")
    public ResponseEntity<List<Student>> getStudentsByStudyProgramId(@PathVariable Long id){
        return this.studentServiceImpl.getStudentsByStudyProgramId(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public void addStudent(HttpServletResponse response, @RequestParam String index, @RequestParam String name, @RequestParam String lastName, @RequestParam String studyProgram) throws InvalidIndexException, ParametarMissingException, InvalidProgramException {

        if(this.studentServiceImpl.addStudent(index, name, lastName, studyProgram)) {
            response.setStatus(200);
        }else{
            response.setHeader("Location", "localhost:8080/students/{index}");
            response.setStatus(201);
        }
    }

    @PutMapping("/{index}")
    public ResponseEntity alterStudent(@PathVariable String index, @RequestParam String name, @RequestParam String lastName, @RequestParam String studyProgram){
        if(this.studentServiceImpl.alterStudent(index, name, lastName, studyProgram)) return ResponseEntity.ok().build();
        return ResponseEntity.status(404).build();

    }

    @DeleteMapping("/{index}")
    public ResponseEntity deleteStudent(@PathVariable String index){
        if(this.studentServiceImpl.getStudentByIndex(index).isPresent()) {
            this.studentServiceImpl.deleteStudent(index);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(404).build();
    }
}
