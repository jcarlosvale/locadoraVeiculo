package com.study.locadora.service;

import com.study.locadora.model.Car;
import com.study.locadora.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService{
    private final CarRepository repository;

    public List<Car> findAll() {
        return repository.findAll();
    }

    public Optional<Car> findById(final String placa) {
        return repository.findById(placa);
    }

    public List<Car> findByDescricao(final String descricao) {
        return repository.findByDescricaoContaining(descricao);
    }

    public Car save(final Car carro) {
        return repository.save(carro);
    }

    public Optional<Car> update(final String placa, final Car carroAtualizado) {
        final Optional<Car> carOptional = findById(placa);

        if (carOptional.isPresent()) {
            final Car carroEncontrado = carOptional.get();
            carroEncontrado.setAno(carroAtualizado.getAno());
            carroEncontrado.setDescricao(carroAtualizado.getDescricao());
            carroEncontrado.setPlaca(carroAtualizado.getPlaca());
            repository.save(carroEncontrado);
        }

        return carOptional;
    }

    public void delete(final String placa) {
        final Optional<Car> carOptional = findById(placa);
        carOptional.ifPresent(repository::delete);
    }
}
