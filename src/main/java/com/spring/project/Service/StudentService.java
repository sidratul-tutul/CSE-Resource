package com.spring.project.Service;

import com.spring.project.Entity.Student;
import com.spring.project.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public void saveStudent(Student student)
    {
        studentRepository.save(student);
    }

    public List<Student> findAllStudent()
    {
        return studentRepository.findAll();
    }

    public Student getOne(Integer serialId) {
        return  studentRepository.getOne(serialId);
    }

    public void remove(Student student) {
        studentRepository.delete(student);
    }

    public Student findStudentBySerialId(Integer serialId)
    {
      return studentRepository.findStudentBySerialId(serialId);
    }
}
