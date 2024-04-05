package com.study.locadora.repository;

import com.study.locadora.model.Car;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataIngestion implements CommandLineRunner {

    private final CarRepository repository;

    @Override
    public void run(final String... args) throws Exception {
        Car carOne = Car.builder()
                .ano(2020)
                .placa("1234")
                .descricao("Fiat Uno")
                .build();
        Car carTwo = Car.builder()
                .ano(2023)
                .placa("1235")
                .descricao("Fiat Palio")
                .build();
        Car carThree = Car.builder()
                .ano(2024)
                .placa("1253")
                .descricao("Kia Picanto")
                .build();

        repository.save(carOne);
        repository.save(carTwo);
        repository.save(carThree);





    }
}
