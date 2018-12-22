package com.finki.wp.studentsapi.controller;

import com.finki.wp.studentsapi.model.StudyProgram;
import com.finki.wp.studentsapi.service.implementation.StudyProgramServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/study_programs")
public class StudyProgramController {

    @Autowired
    private StudyProgramServiceImpl studyProgramServiceImpl;

    public StudyProgramController() {
    }

    @GetMapping("/")
    public ResponseEntity<List<StudyProgram>> getAllPrograms(){
        return this.studyProgramServiceImpl.getAllPrograms().map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity addProgram(@RequestParam String name){
        this.studyProgramServiceImpl.addStudyProgram(name);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProgram(@PathVariable Long id){
        if(this.studyProgramServiceImpl.deleteProgram(id)){
            return ResponseEntity.ok().build();
        }return ResponseEntity.status(403).build();
    }
}
