package com.itpatagonia.microservices.subjectservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.itpatagonia.microservices.subjectservice.Exceptions.NoEntityException;
import com.itpatagonia.microservices.subjectservice.model.Subject;
import com.itpatagonia.microservices.subjectservice.repository.SubjectRepository;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public Subject findById(Long id) throws NoEntityException {
        return subjectRepository.findById(id).orElseThrow(() -> new NoEntityException("No existe Subject con " + id));
    }

    public List<Subject> finByStudentId(Long studentId){
        return subjectRepository.findByStudentId(studentId);
    }

    public Subject updateSubject(Subject subject) throws NoEntityException {
        Subject subjectOld = subjectRepository.findById(subject.getId()).orElseThrow(
                () -> new NoEntityException("No existe Subject con " + subject.getId()));
        //subjectOld.setName(subject.getName());
        return subjectRepository.save(subjectOld);
    }

    public void deleteSubject(Long id) throws NoEntityException {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new NoEntityException("No existe Subject con " + id));
        subjectRepository.delete(subject);
    }

}
