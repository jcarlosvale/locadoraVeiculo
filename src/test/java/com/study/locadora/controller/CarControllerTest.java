package com.study.locadora.controller;

import com.study.locadora.dto.CarDto;
import com.study.locadora.model.Car;
import com.study.locadora.repository.CarRepository;
import com.study.locadora.service.CarService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.Objects;

import static com.study.locadora.domain.CategoryEnum.PEQUENO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @SpyBean
    private CarService service;

    private String URL;

    @BeforeEach
    void setup() {
        URL = "http://localhost:" + port + "/carro";
        insertCars();
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }


    private void insertCars() {
        Car carOne = Car.builder().ano(2020).placa("1234").descricao("Fiat Uno").categoria(PEQUENO).build();
        Car carTwo = Car.builder().ano(2023).placa("1235").categoria(PEQUENO).descricao("Fiat Palio").build();
        Car carThree = Car.builder().ano(2024).placa("1253").categoria(PEQUENO).descricao("Kia Picanto").build();

        repository.save(carOne);
        repository.save(carTwo);
        repository.save(carThree);
    }

    @Test
    void findAll() {
        //GIVEN

        //WHEN
        var response = restTemplate.getForEntity(URL, CarDto[].class);

        //THEN
        verify(service, times(1)).findAll();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody())).hasSize(3);
    }

    @Test
    void findById() {
        //GIVEN

        //WHEN
        var response = restTemplate.getForEntity(URL, CarDto[].class);

        //THEN
        verify(service, times(1)).findAll();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody())).hasSize(3);
    }

}