package com.finki.wp.studentsapi.controller;

import com.finki.wp.studentsapi.model.StudyProgram;
import com.finki.wp.studentsapi.service.StudyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/study_programs")
public class StudyProgramController {

    @Autowired
    private StudyProgramService studyProgramService;

    public StudyProgramController() {
    }

    @GetMapping("/")
    public ResponseEntity<List<StudyProgram>> getAllPrograms(){
        return this.studyProgramService.getAllPrograms().map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity addProgram(@RequestParam String name){
        this.studyProgramService.addStudyProgram(name);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProgram(@PathVariable Long id){
        this.studyProgramService.deleteProgram(id);
        return ResponseEntity.ok().build();
    }
}
