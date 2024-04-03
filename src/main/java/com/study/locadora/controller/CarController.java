package com.study.locadora.controller;


import com.study.locadora.dto.CarDto;
import com.study.locadora.model.Car;
import com.study.locadora.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.study.locadora.mapper.CarMapper.toDto;
import static com.study.locadora.mapper.CarMapper.toEntity;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carro")
public class CarController {

    private final CarService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> getAll() {
        return toDto(service.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<CarDto> findById(@PathVariable("id") final String placa) {

        Optional<Car> carro = service.findById(placa);

        if (carro.isPresent()) {
            return ResponseEntity.ok(toDto(carro.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid final CarDto dto) {
        service.save(toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDto> update(@PathVariable("id") final String placa,
                         @RequestBody @Valid final CarDto dto) {

        final var carro = service.update(placa, toEntity(dto));

        if (carro.isPresent()) {
            return ResponseEntity.ok(toDto(carro.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final String placa) {
        service.delete(placa);
        return ResponseEntity.noContent().build();
    }
}