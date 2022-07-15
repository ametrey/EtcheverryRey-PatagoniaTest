package com.itpatagonia.microservices.subjectservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itpatagonia.microservices.subjectservice.model.Subject;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    List<Subject> findByStudentId(Long studentId);
}
