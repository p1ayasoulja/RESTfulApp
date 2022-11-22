package com.example.restful.Exceptions;

public class DoctorNotFoundException extends RuntimeException {
    public DoctorNotFoundException(Long id) {
        super("Cant Find Doctor by this id ="+ id);
    }
}
