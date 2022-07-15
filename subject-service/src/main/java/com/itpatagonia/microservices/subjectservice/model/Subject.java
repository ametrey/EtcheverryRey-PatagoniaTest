package com.itpatagonia.microservices.subjectservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "subject")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "studentid")
    private Long studentId;

}