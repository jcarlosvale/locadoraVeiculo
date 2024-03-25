package com.study.locadora.service;

import com.study.locadora.dto.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//estereótipo: Service, Component, Repository, Bean
@Service
public class CarServiceImpl implements CarService{


    private List<Car> listaDeCarros = new ArrayList<>();

    @Override
    public List<Car> findAll() {
        return listaDeCarros;
    }

    @Override
    public Optional<Car> findById(final String placa) {
        final Optional<Car> carOptional =
                listaDeCarros.stream()
                        .filter(car -> car.getPlaca().equals(placa))
                        .findFirst();
        return carOptional;
    }

    @Override
    public Car save(final Car carro) {
        listaDeCarros.add(carro);
        return carro;
    }

    @Override
    public Optional<Car> update(final String placa,
                                final Car carroAtualizado) {

        final Optional<Car> carOptional = findById(placa);

        if (carOptional.isPresent()) {
            final Car carroEncontrado = carOptional.get();
            carroEncontrado.setAno(carroAtualizado.getAno());
            carroEncontrado.setDescricao(carroAtualizado.getDescricao());
            carroEncontrado.setPlaca(carroAtualizado.getPlaca());
        }

        return carOptional;
    }

    @Override
    public void delete(final String placa) {
        findById(placa).ifPresent(car -> listaDeCarros.remove(car));
    }
}
