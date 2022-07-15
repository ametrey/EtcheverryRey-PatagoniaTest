package com.itpatagonia.microservices.studentmicroservice.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    private Long id;
    private String name;
    private Float score;
    private Long studentId;

}