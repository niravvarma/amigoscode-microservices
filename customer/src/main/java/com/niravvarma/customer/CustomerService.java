package com.niravvarma.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(
        CustomerRepository customerRepository,
        RestTemplate restTemplate){

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo: check if email is valid
        // todo: check if email is already registered
        customerRepository.saveAndFlush(customer);

        //check if fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-checker/{customerId}",
                FraudCheckResponse.class,
                customer.getId());

        if(fraudCheckResponse.isFraudster()) {
            throw new IllegalArgumentException("Customer is a fraudster");
        }

        // todo: send notification if fraudster
    }
}
