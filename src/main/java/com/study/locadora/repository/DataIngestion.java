package com.study.locadora.repository;

import com.study.locadora.model.Car;
import com.study.locadora.model.Customer;
import com.study.locadora.model.Rent;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@AllArgsConstructor
public class DataIngestion implements CommandLineRunner {

    private final CarRepository carRepository;

    private final CustomerRepository customerRepository;

    private final RentRepository rentRepository;

    @Override
    public void run(final String... args) throws Exception {
        cadastrarCarro();
        cadastrarCliente();
        cadastrarAluguel();


        listarAlugueis();

    }

    public void listarAlugueis() {
        System.out.println("#######################");
        var rent = rentRepository.findAll().stream().findFirst().get();
        System.out.println(rent.getId());
        System.out.println("#######################");

        Hibernate.initialize(rent.getLocatario());
        System.out.println("******************");
        System.out.println(rent.getLocatario());
        System.out.println("******************");

    }

    public void cadastrarAluguel() {
        final var locatario = customerRepository.findById(2L).get();
        final var carro = carRepository.findById("1235").get();

        final Rent aluguel = Rent.builder()
                .carro(carro)
                .locatario(locatario)
                .dataAluguel(ZonedDateTime.now())
                .build();

        rentRepository.save(aluguel);
    }

    public void cadastrarCliente() {
        Customer customer1 = Customer.builder()
                .name("Talita")
                .build();

        Customer customer2 = Customer.builder()
                .name("Gabriel")
                .build();

        Customer customer3 = Customer.builder()
                .name("Joana")
                .build();

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
    }

    public void cadastrarCarro() {
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

        carRepository.save(carOne);
        carRepository.save(carTwo);
        carRepository.save(carThree);
    }
}
