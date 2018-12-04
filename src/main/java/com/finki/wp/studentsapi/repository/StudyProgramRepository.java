package com.finki.wp.studentsapi.repository;

import com.finki.wp.studentsapi.model.StudyProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyProgramRepository extends JpaRepository<StudyProgram, Long> {

    StudyProgram findByName(String studyProgram);
}
