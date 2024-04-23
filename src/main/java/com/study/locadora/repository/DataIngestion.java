package com.study.locadora.repository;

import com.study.locadora.model.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.study.locadora.domain.CategoryEnum.PEQUENO;

@Component
@RequiredArgsConstructor
public class DataIngestion implements CommandLineRunner {

    private final CarRepository carRepository;

    @Override
    public void run(final String... args) throws Exception {
        Car carOne = Car.builder()
                .ano(2020)
                .placa("1234")
                .descricao("Fiat Uno")
                .categoria(PEQUENO)
                .build();
        Car carTwo = Car.builder()
                .ano(2023)
                .placa("1235")
                .categoria(PEQUENO)
                .descricao("Fiat Palio")
                .build();
        Car carThree = Car.builder()
                .ano(2024)
                .placa("1253")
                .categoria(PEQUENO)
                .descricao("Kia Picanto")
                .build();

        carRepository.save(carOne);
        carRepository.save(carTwo);
        carRepository.save(carThree);
    }
}
