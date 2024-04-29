package com.study.locadora.controller;


import com.study.locadora.dto.CarDto;
import com.study.locadora.model.Car;
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
import java.util.Objects;
import java.util.Optional;

import static com.study.locadora.mapper.CarMapper.toDto;
import static com.study.locadora.mapper.CarMapper.toEntity;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/carro")
public class CarController {

    private final CarService service;

    @GetMapping
    public ResponseEntity<List<CarDto>> findAll() {
        return ResponseEntity
                .ok()
                .header("turma",
                        "1139")
                .body(toDto(service.findAll()));
    }

    @GetMapping(params = {"desc"})
    public ResponseEntity<List<CarDto>> findByDescricao(
            @RequestParam(name="desc", required = false) final String descricao)
    {
        if (Objects.isNull(descricao)) return findAll();
        log.info(descricao);
        return ResponseEntity.ok(toDto(service.findByDescricao(descricao)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> findById(@PathVariable("id") final String placa) {
        final Car car = service.findById(placa).orElse(null);
        return ResponseEntity.ok(toDto(car));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody final CarDto carro) {
        service.save(toEntity(carro));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDto> update(@PathVariable("id") final String placa,
                                         @Valid @RequestBody final CarDto carroAtualizado) {

       final Optional<Car> optionalCar = service.update(placa, toEntity(carroAtualizado));
       if (optionalCar.isEmpty()) return ResponseEntity.notFound().build();
       return ResponseEntity.ok(carroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final String placa) {
        service.delete(placa);
        return ResponseEntity.noContent().build();
    }
}