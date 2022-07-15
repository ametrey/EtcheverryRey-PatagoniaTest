package com.itpatagonia.microservices.studentmicroservice.feignclients;


import com.itpatagonia.microservices.studentmicroservice.model.Subject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "subject-service")
@RequestMapping("/subjects")
public interface SubjectFeignCli {

    @PostMapping()
    Subject save(@RequestBody Subject loan);

}