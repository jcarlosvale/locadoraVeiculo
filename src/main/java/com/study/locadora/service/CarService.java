package com.study.locadora.service;

import com.study.locadora.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> findAll();
    Optional<Car> findById(final String placa);
    Car save(final Car carro);
    Optional<Car> update(final String placa, final Car carroAtualizado);
    void delete(final String placa);
}
