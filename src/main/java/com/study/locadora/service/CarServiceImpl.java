package com.study.locadora.service;

import com.study.locadora.dto.CarDto;
import com.study.locadora.model.Car;
import com.study.locadora.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//estereótipo: Service, Component, Repository, Bean
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository repository;

    @Override
    public List<CarDto> findAll() {
        final List<Car> carros = repository.findAll();
        return
                carros.stream()
                        .map(car -> CarDto.builder()
                                .ano(car.getAno())
                                .descricao(car.getDescricao())
                                .placa(car.getPlaca())
                                .build())
                        .collect(Collectors.toList());
    }

    @Override
    public Optional<CarDto> findById(final String placa) {
        final Optional<Car> carro = repository.findById(placa);
        if (carro.isPresent()) {
            final CarDto dto =
                    CarDto.builder()
                            .ano(carro.get().getAno())
                            .descricao(carro.get().getDescricao())
                            .placa(carro.get().getDescricao())
                            .build();

            return Optional.of(dto);
        }
        return Optional.empty();
    }

    @Override
    public CarDto save(final CarDto dto) {
        final Car car = Car.builder()
                .ano(dto.getAno())
                .descricao(dto.getDescricao())
                .placa(dto.getPlaca())
                .build();

        repository.save(car);

        return dto;
    }

    @Override
    public Optional<CarDto> update(final String placa,
                                   final CarDto dto) {

        final Optional<Car> carOptional = repository.findById(placa);

        if (carOptional.isPresent()) {
            final Car car = carOptional.get();

            car.setAno(dto.getAno());
            car.setDescricao(dto.getDescricao());
            car.setPlaca(dto.getPlaca());

            repository.save(car);

            return Optional.of(dto);
        }

        return Optional.empty();
    }

    @Override
    public void delete(final String placa) {
        repository.deleteById(placa);
    }
}
