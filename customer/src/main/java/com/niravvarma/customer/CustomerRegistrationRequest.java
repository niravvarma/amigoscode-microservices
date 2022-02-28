package com.niravvarma.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email){
}
