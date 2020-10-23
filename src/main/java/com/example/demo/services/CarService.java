package com.example.demo.services;

import com.example.demo.repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    private final CarsRepository carsRepository;

    @Autowired
    public CarService(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    public List<String> getCars() {

        List<String> cars = new ArrayList<>();
        return cars;
    }


    public String getCar() {
        return "d";
    }


}
