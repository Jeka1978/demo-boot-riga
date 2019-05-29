package com.naya.demoboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.github.javafaker.GameOfThrones;
import com.naya.demostartersimple.GameOfThroneService;
import com.naya.demostartersimple.InjectRandom;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

/**
 * @author Evgeny Borisov
 */

@RestController
public class GameOfThroneController {

    @InjectRandom(min=3,max = 10)
    private int number;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private GameOfThroneService service;

    private GameOfThrones gameOfThrones = new Faker().gameOfThrones();

    @PostMapping("/person")
    @SneakyThrows
    public Person handlePerson(@RequestBody Person person) {

        return person.withName(gameOfThrones.character());

    }

    @GetMapping("/hello/{amount}")
    public String hello(@PathVariable("amount") Integer amount) {

        return gameOfThrones.dragon() + " " + amount*number;
    }

    @GetMapping("/hi")
    public String hello2(@RequestParam("name") String name) {

        return new Faker().gameOfThrones().dragon() + " " + name;
    }

}













