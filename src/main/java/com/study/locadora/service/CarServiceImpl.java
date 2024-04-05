package com.study.locadora.service;

import com.study.locadora.exception.CarroJaExisteException;
import com.study.locadora.exception.CarroNaoExisteException;
import com.study.locadora.mapper.CarMapper;
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

        if(repository.findById(entity.getPlaca()).isPresent()) {
            throw new CarroJaExisteException("O carro com placa " + entity.getPlaca() + " ja existe.");
        }

        return repository.save(entity);
    }

    @Override
    public Optional<Car> update(final String placa,
                                final Car newCarInfo) {

        final Optional<Car> carOptional = repository.findById(placa);

        if (carOptional.isPresent()) {
            final Car entity = carOptional.get();

            CarMapper.copy(newCarInfo, entity);

            repository.save(entity);

            return Optional.of(newCarInfo);
        }

        return Optional.empty();
    }

    @Override
    public void delete(final String placa) {
        Optional<Car> entity = repository.findById(placa);

        if(entity.isPresent()) {
            repository.delete(entity.get());
        }

        throw new CarroNaoExisteException("O carro com placa " + placa + " nao existe.");
    }
}
