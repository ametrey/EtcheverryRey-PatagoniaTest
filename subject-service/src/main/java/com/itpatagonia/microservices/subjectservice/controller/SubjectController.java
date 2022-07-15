package com.itpatagonia.microservices.subjectservice.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.itpatagonia.microservices.subjectservice.Exceptions.NoEntityException;
import com.itpatagonia.microservices.subjectservice.model.Subject;
import com.itpatagonia.microservices.subjectservice.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping(value ="/create")
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject){
        Subject subjectNew = subjectService.createSubject(subject);
        return new ResponseEntity<Subject>(subjectNew,HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Subject>> getSubjects(){
        return ResponseEntity.ok(subjectService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubjectById(@PathVariable("id") Long id) {

        try {
            Subject subject = subjectService.findById(id);
            return ResponseEntity.ok(subject);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Subject No encontrado ");
        }
    }

    @GetMapping("/bystudent/{studentid}")
    public ResponseEntity<?> finByStudentId(@PathVariable("studentid") Long studentId){
        return ResponseEntity.ok().body(subjectService.finByStudentId(studentId));
    }

    @PutMapping("/update")
    public ResponseEntity<Subject> updateSubject(@RequestBody Subject subject){
        Subject subjectNew = new Subject();
        try {
            subjectNew = subjectService.updateSubject(subject);
            return ResponseEntity.ok(subjectNew);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            //return new ResponseEntity<>("Subject No encontrado", HttpStatusCode.valueOf(400));
            return ResponseEntity.status(400).body(subjectNew);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable("id") Long id){
        try {
            subjectService.deleteSubject(id);
            return ResponseEntity.ok("Subject Eliminado");
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            //return new ResponseEntity<>("Subject No encontrado", HttpStatusCode.valueOf(400));
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Subject No Eliminado");
        }
    }

}
