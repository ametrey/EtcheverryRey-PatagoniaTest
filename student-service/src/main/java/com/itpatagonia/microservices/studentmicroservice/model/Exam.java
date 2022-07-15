package com.itpatagonia.microservices.studentmicroservice.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    private Long id;
    private LocalDate examenDate;
    private Float score;
    private Long studentId;

}