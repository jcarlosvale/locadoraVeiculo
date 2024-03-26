package com.study.locadora.service;

import com.study.locadora.dto.CarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//estereótipo: Service, Component, Repository, Bean
@Service
public class CarServiceImpl implements CarService{


    @Autowired
    @Qualifier("BancoDeDados")
    private List<CarDto> listaDeCarros;

    @Override
    public List<CarDto> findAll() {
        return listaDeCarros;
    }

    @Override
    public Optional<CarDto> findById(final String placa) {
        final Optional<CarDto> carOptional =
                listaDeCarros.stream()
                        .filter(carDto -> carDto.getPlaca().equals(placa))
                        .findFirst();
        return carOptional;
    }

    @Override
    public CarDto save(final CarDto carro) {
        listaDeCarros.add(carro);
        return carro;
    }

    @Override
    public Optional<CarDto> update(final String placa,
                                   final CarDto carroAtualizado) {

        final Optional<CarDto> carOptional = findById(placa);

        if (carOptional.isPresent()) {
            final CarDto carroEncontrado = carOptional.get();
            carroEncontrado.setAno(carroAtualizado.getAno());
            carroEncontrado.setDescricao(carroAtualizado.getDescricao());
            carroEncontrado.setPlaca(carroAtualizado.getPlaca());
        }

        return carOptional;
    }

    @Override
    public void delete(final String placa) {
        findById(placa).ifPresent(carDto -> listaDeCarros.remove(carDto));
    }
}
