package com.example.demo.cotroller;

import com.example.demo.entity.Car;
import com.example.demo.repository.CarsRepository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class CarsController {

    private final CarsRepository carsRepository;

    private final DataSource dataSource;

    public CarsController(CarsRepository catsRepository, DataSource dataSource) {
        this.carsRepository = catsRepository;
        this.dataSource = dataSource;
    }

    @GetMapping("/cars/{id}")
    public Car getCatById(@PathVariable("id") Integer id) {
        Car cat = carsRepository.getOne(id);
        Car result = new Car();
        result.setId(cat.getId());
        result.setName(cat.getName());
        result.setColor(cat.getColor());
        result.setNumberOfDoors(cat.getNumberOfDoors());
        return result;
    }

    @GetMapping("/jdbc/cars/{id}")
    public Object getCatByIdJdbc(@PathVariable("id") Integer id) {
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        Map<String, Integer> paramMap = new HashMap<>();
        paramMap.put("id", id);
        Map<String, Object> result = jdbcTemplate.queryForMap("SELECT * FROM cars WHERE id=:id", paramMap);
        return result;
    }
}
