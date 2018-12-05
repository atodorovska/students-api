package com.finki.wp.studentsapi.service;

import com.finki.wp.studentsapi.model.Student;
import com.finki.wp.studentsapi.model.StudentDisplay;
import com.finki.wp.studentsapi.model.exceptions.InvalidIndexException;
import com.finki.wp.studentsapi.model.exceptions.InvalidProgramException;
import com.finki.wp.studentsapi.model.exceptions.ParametarMissingException;
import com.finki.wp.studentsapi.repository.StudentRepository;
import com.finki.wp.studentsapi.repository.StudyProgramRepository;
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

    @Autowired
    private StudyProgramRepository studyProgramRepository;

    public StudentService() {
    }

    public Optional<List<StudentDisplay>> getAllStudents() {
        return Optional.of(this.studentRepository.findAll()
                .stream().map(i -> new StudentDisplay(i.getIndex(), i.getName(), i.getLastName()))
                .collect(Collectors.toList()));
    }

    public boolean addStudent(String index, String name, String lastName, String studyProgram) throws ParametarMissingException, InvalidIndexException, InvalidProgramException {

        if(this.studentRepository.findById(index).isPresent()) return false;

        if(index.isEmpty() || name.isEmpty() || lastName.isEmpty() || studyProgram.isEmpty()){
            throw new ParametarMissingException();
        }else if(index.length() != 6 || !index.matches("[0-9]+")){
            throw new InvalidIndexException();
        }else if(this.studyProgramRepository.findByName(studyProgram) == null){
            throw new InvalidProgramException();
        }else{
            Long id = this.studyProgramRepository.findByName(studyProgram).getId();

            this.studentRepository.save(new Student(index, name, lastName, id));
            return true;

        }
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
    public boolean alterStudent(String index, String name, String lastName, String studyProgram) {

        if(this.studyProgramRepository.findByName(studyProgram) == null) return false;

        if(this.studentRepository.findById(index).isPresent()){
            Student s = this.studentRepository.findById(index).get();

            Long id = this.studyProgramRepository.findByName(studyProgram).getId();

            s.setName(name);
            s.setLastName(lastName);
            s.setStudyProgram(id);

            return true;
        } return false;

    }
}
