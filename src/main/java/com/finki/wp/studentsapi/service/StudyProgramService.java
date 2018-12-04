package com.finki.wp.studentsapi.service;

import com.finki.wp.studentsapi.model.StudyProgram;
import com.finki.wp.studentsapi.repository.StudyProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyProgramService {

    @Autowired
    private StudyProgramRepository studyProgramRepository;


    public StudyProgramService() {
    }

    public void addStudyProgram(String studyProgram) {
        this.studyProgramRepository.save(new StudyProgram(studyProgram));
    }

    public Optional<List<StudyProgram>> getAllPrograms() {
        return Optional.of(this.studyProgramRepository.findAll());
    }

    public Long getStudyProgramByName(String studyProgram) {
        return this.studyProgramRepository.findByName(studyProgram).getId();
    }

    public void deleteProgram(Long id) {
        this.studyProgramRepository.deleteById(id);
    }
}
