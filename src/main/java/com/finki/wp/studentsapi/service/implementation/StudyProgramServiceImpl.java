package com.finki.wp.studentsapi.service.implementation;

import com.finki.wp.studentsapi.model.StudyProgram;
import com.finki.wp.studentsapi.repository.StudentRepository;
import com.finki.wp.studentsapi.repository.StudyProgramRepository;
import com.finki.wp.studentsapi.service.StudyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudyProgramServiceImpl implements StudyProgramService {

    @Autowired
    private StudyProgramRepository studyProgramRepository;

    @Autowired
    private StudentRepository studentRepository;


    public StudyProgramServiceImpl() {
    }

    public void addStudyProgram(String studyProgram) {
        this.studyProgramRepository.save(new StudyProgram(studyProgram));
    }

    public Optional<List<StudyProgram>> getAllPrograms() {
        return Optional.of(this.studyProgramRepository.findAll());
    }

    public boolean deleteProgram(Long id) {

        if(!this.studentRepository.findAllByStudyProgram(id).isPresent()) {
            this.studyProgramRepository.deleteById(id);
            return true;
        }return false;
    }

    @Override
    public Optional<StudyProgram> getProgramById(Long id) {
        return this.studyProgramRepository.findById(id);
    }


    @Override
    @Transactional
    public boolean alterProgram(Long id, String name) {

        if(this.studyProgramRepository.findById(id).isPresent()){
            StudyProgram studyProgram = this.studyProgramRepository.findById(id).get();

            studyProgram.setName(name);
            return true;

        }return false;

    }
}
