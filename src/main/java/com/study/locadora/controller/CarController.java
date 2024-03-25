package com.study.locadora.controller;


import com.study.locadora.dto.Car;
import com.study.locadora.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carro")
public class CarController {

    private final CarService service;

    @GetMapping
    public List<Car> getAll() {
        return service.findAll();
    }


    @GetMapping("/{id}")
    public Car findById(@PathVariable("id") final String placa) {
        return service.findById(placa).orElseGet(() -> new Car());
    }


    @PostMapping
    public void save(@RequestBody @Valid final Car carro) {
        service.save(carro);
    }

    @PutMapping("/{id}")
    public Car update(@PathVariable("id") final String placa,
                      @RequestBody @Valid final Car carroAtualizado) {

        final var optional = service.update(placa, carroAtualizado);

        return optional.orElseGet(() -> new Car());

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final String placa) {
        service.delete(placa);
    }
}