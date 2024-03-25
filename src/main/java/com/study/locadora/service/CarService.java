package com.study.locadora.service;

import com.study.locadora.dto.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {
    private List<Car> listaDeCarros = new ArrayList<>();

    public List<Car> findAll() {
        return listaDeCarros;
    }

    public List<Car> findByDescricao(final String descricao) {
        return
                listaDeCarros.stream()
                        .filter(car -> car.getDescricao().contains(descricao))
                        .collect(Collectors.toList());
    }

    public Optional<Car> findById(final String placa) {
        return listaDeCarros.stream()
                .filter(car -> car.getPlaca().equals(placa))
                .findFirst();
    }

    public void save(final Car carro) {
        listaDeCarros.add(carro);
    }

    public Optional<Car> update(final String placa, final Car carroAtualizado) {
        final Optional<Car> carOptional = findById(placa);

        if (carOptional.isPresent()) {
            final Car carroEncontrado = carOptional.get();
            carroEncontrado.setAno(carroAtualizado.getAno());
            carroEncontrado.setDescricao(carroAtualizado.getDescricao());
            carroEncontrado.setPlaca(carroAtualizado.getPlaca());
        }

        return carOptional;
    }

    public void delete(final String placa) {
        final Optional<Car> carOptional = findById(placa);
        carOptional.ifPresent(car -> listaDeCarros.remove(car));
    }
}
