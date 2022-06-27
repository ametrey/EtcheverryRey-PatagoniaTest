package com.tutorial.clientservice.feignclients;


import com.tutorial.clientservice.model.Loan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "bike-service")
@RequestMapping("/bike")
public interface LoanFeignClient {

    @PostMapping()
    Loan save(@RequestBody Loan loan);

}
