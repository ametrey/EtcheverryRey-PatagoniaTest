package com.itpatagonia.microservices.studentmicroservice.feignclients;


import com.itpatagonia.microservices.studentmicroservice.model.Exam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "exam-service")
@RequestMapping("/exams")
public interface ExamFeignCli {

    @PostMapping()
    Exam save(@RequestBody Exam loan);

}
