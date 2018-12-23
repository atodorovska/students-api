package com.finki.wp.studentsapi.service;

import com.finki.wp.studentsapi.model.StudyProgram;

import java.util.List;
import java.util.Optional;

public interface StudyProgramService {

    void addStudyProgram(String studyProgram);

    Optional<List<StudyProgram>> getAllPrograms();

    boolean deleteProgram(Long id);

    Optional<StudyProgram> getProgramById(Long id);

    boolean alterProgram(Long id, String name);
}
