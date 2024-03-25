package com.study.locadora.controller;


import com.study.locadora.dto.Car;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carro")
public class CarController {

    private List<Car> listaDeCarros = new ArrayList<>();

    @GetMapping
    public List<Car> getAll() {
        return listaDeCarros;
    }


    @GetMapping("/{id}")
    public Car findById(@PathVariable("id") final String placa) {
        final Optional<Car> carOptional =
                listaDeCarros.stream()
                        .filter(car -> car.getPlaca().equals(placa))
                        .findFirst();

        return carOptional.orElseGet(Car::new);
    }


    @PostMapping
    public void save(@RequestBody final Car carro) {
        listaDeCarros.add(carro);
    }

    @PutMapping("/{id}")
    public Car update(@PathVariable("id") final String placa,
                      @RequestBody final Car carroAtualizado) {

        final Optional<Car> carOptional =
                listaDeCarros.stream()
                        .filter(car -> car.getPlaca().equals(placa))
                        .findFirst();

        if (carOptional.isPresent()) {

            final Car carroEncontrado = carOptional.get();
            carroEncontrado.setAno(carroAtualizado.getAno());
            carroEncontrado.setDescricao(carroAtualizado.getDescricao());
            carroEncontrado.setPlaca(carroAtualizado.getPlaca());

            return carroEncontrado;

        } else {
            return new Car();
        }

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final String placa) {

        int indice = -1;

        for (int i = 0; i < listaDeCarros.size(); i++) {
            Car car = listaDeCarros.get(i);
            if (car.getPlaca().equals(placa)) {
                indice = i;
                break;
            }
        }

        if (indice > -1) {
            listaDeCarros.remove(indice);
        }

    }
}