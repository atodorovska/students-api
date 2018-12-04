package com.finki.wp.studentsapi.controller;

import com.finki.wp.studentsapi.model.Student;
import com.finki.wp.studentsapi.model.StudentDisplay;
import com.finki.wp.studentsapi.service.StudentService;
import com.finki.wp.studentsapi.service.StudyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudyProgramService studyProgramService;

    public StudentController() {
    }

    @GetMapping("/")
    public ResponseEntity<List<StudentDisplay>> getAllStudents(){
        return this.studentService.getAllStudents().map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{index}")
    public ResponseEntity<Student> getStudentByIndex(@PathVariable String index){
        return this.studentService.getStudentByIndex(index).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by_study_program/{id}")
    public ResponseEntity<List<Student>> getStudentsByStudyProgramId(@PathVariable Long id){
        return this.studentService.getStudentsByStudyProgramId(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity addStudent(@RequestParam String index, @RequestParam String name, @RequestParam String lastName, @RequestParam String studyProgram){

        if(this.studentService.getStudentByIndex(index).isPresent()){
            return ResponseEntity.status(201).build();
        }else if(index == null || name == null || lastName == null || studyProgram == null){
            return ResponseEntity.status(400).build();
        }else if(index.length() != 6 || !index.matches("[0-9]+")){
            return ResponseEntity.status(400).build();
        }else if(this.studyProgramService.getStudyProgramByName(studyProgram) == null){
            return ResponseEntity.status(400).build();
        }else{
            Long id = this.studyProgramService.getStudyProgramByName(studyProgram);

            this.studentService.addStudent(new Student(index, name, lastName, id));
            return ResponseEntity.ok().build();
        }
    }

    @PatchMapping("/{index}")
    public ResponseEntity alterStudent(@PathVariable String index, @RequestParam String name, @RequestParam String lastName, @RequestParam String studyProgram){
        if(this.studentService.getStudentByIndex(index).isPresent()){

            Long id = this.studyProgramService.getStudyProgramByName(studyProgram);

            this.studentService.alterStudent(index, name, lastName, id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(404).build();

    }

    @DeleteMapping("/{index}")
    public ResponseEntity deleteStudent(@PathVariable String index){
        if(this.studentService.getStudentByIndex(index).isPresent()) {
            this.studentService.deleteStudent(index);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(404).build();
    }
}
