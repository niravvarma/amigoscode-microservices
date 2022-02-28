package com.niravvarma.fraud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/fraud-checker")
public record FraudController(FraudCheckService fraudCheckService) {

    @GetMapping(path = "/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        log.info("Checking if customer {} is a fraudster", customerId);
        boolean isFraud = fraudCheckService.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(isFraud);
    }
}
