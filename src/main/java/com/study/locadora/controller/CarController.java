package com.study.locadora.controller;


import com.study.locadora.dto.Car;
import com.study.locadora.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/carro")
public class CarController {

    private final CarService service;

    @GetMapping
    public ResponseEntity<List<Car>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(params = {"desc"})
    public ResponseEntity<List<Car>> findByDescricao(@RequestParam("desc") final String descricao) {
        log.info(descricao);
        return ResponseEntity.ok(service.findByDescricao(descricao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> findById(@PathVariable("id") final String placa) {
        final Optional<Car> carOptional = service.findById(placa);
        return ResponseEntity.ok(carOptional.orElseGet(Car::new));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody final Car carro) {
        service.save(carro);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> update(@PathVariable("id") final String placa,
                      @Valid @RequestBody final Car carroAtualizado) {

       final Optional<Car> optionalCar = service.update(placa, carroAtualizado);
       if (optionalCar.isEmpty()) return ResponseEntity.notFound().build();
       return ResponseEntity.ok(carroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final String placa) {
        service.delete(placa);
        return ResponseEntity.noContent().build();
    }
}