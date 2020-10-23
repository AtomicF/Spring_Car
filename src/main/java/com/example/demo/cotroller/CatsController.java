package com.example.demo.cotroller;

import com.example.demo.entity.Cat;
import com.example.demo.repository.CatsRepository;
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
public class CatsController {

    private final CatsRepository catsRepository;

    private final DataSource dataSource;

    public CatsController(CatsRepository catsRepository, DataSource dataSource) {
        this.catsRepository = catsRepository;
        this.dataSource = dataSource;
    }

    @GetMapping("/cats/{id}")
    public Cat getCatById(@PathVariable("id") Integer id) {
        Cat cat = catsRepository.getOne(id);
        Cat result = new Cat();
        result.setId(cat.getId());
        result.setName(cat.getName());
        result.setColor(cat.getColor());
        result.setPawsCount(cat.getPawsCount());
        return result;
    }

    @GetMapping("/jdbc/cats/{id}")
    public Object getCatByIdJdbc(@PathVariable("id") Integer id) {
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        Map<String, Integer> paramMap = new HashMap<>();
        paramMap.put("id", id);
        Map<String, Object> result = jdbcTemplate.queryForMap("SELECT * FROM cats WHERE id=:id", paramMap);
        return result;
    }
}
