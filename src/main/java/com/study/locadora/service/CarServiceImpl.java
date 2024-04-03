package com.study.locadora.service;

import com.study.locadora.model.Car;
import com.study.locadora.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//estereótipo: Service, Component, Repository, Bean
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository repository;

    @Override
    public List<Car> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Car> findById(final String placa) {
        return repository.findById(placa);
    }

    @Override
    public Car save(final Car entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<Car> update(final String placa,
                                final Car entity) {

        final Optional<Car> carOptional = repository.findById(placa);

        if (carOptional.isPresent()) {
            final Car car = carOptional.get();

            car.setAno(entity.getAno());
            car.setDescricao(entity.getDescricao());
            car.setPlaca(entity.getPlaca());

            repository.save(car);

            return Optional.of(entity);
        }

        return Optional.empty();
    }

    @Override
    public void delete(final String placa) {
        repository.deleteById(placa);
    }
}
