package com.pb.StudentRecordMgtSys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pb.StudentRecordMgtSys.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
