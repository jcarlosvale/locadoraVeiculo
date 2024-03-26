package com.study.locadora.service;

import com.study.locadora.dto.CarDto;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<CarDto> findAll();
    Optional<CarDto> findById(final String placa);
    CarDto save(final CarDto carro);
    Optional<CarDto> update(final String placa, final CarDto carroAtualizado);
    void delete(final String placa);
}
