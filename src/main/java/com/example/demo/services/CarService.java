package com.example.demo.services;

import com.example.demo.entity.Car;
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
        final Iterable<Car> all = carsRepository.findAll();
        List<String> list = new ArrayList<>();
        for (Car car : all) {
            list.add(car.getName());
        }
        return list;
    }

}
