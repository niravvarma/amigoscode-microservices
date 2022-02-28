package com.niravvarma.fraud;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fraud-checker")
public record FraudController(FraudCheckService fraudCheckService) {

    @GetMapping(path = "/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        boolean isFraud = fraudCheckService.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(isFraud);
    }
}
