package com.spring.project.Repository;

import com.spring.project.Entity.Student;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository  extends JpaRepository<Student,Integer> {

    public Student findStudentBySerialId(Integer serialId);
}
