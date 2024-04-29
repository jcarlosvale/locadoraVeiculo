package com.study.locadora.controller;

import com.study.locadora.dto.CarDto;
import com.study.locadora.mapper.CarMapper;
import com.study.locadora.model.Car;
import com.study.locadora.repository.CarRepository;
import com.study.locadora.service.CarService;
import org.assertj.core.api.Assertions;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import static com.study.locadora.domain.CategoryEnum.PEQUENO;
import static com.study.locadora.domain.CategoryEnum.SUV;
import static com.study.locadora.mapper.CarMapper.toDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate; //browser

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
        Car car1 = Car.builder().ano(2020).placa("1234").descricao("Fiat Uno").categoria(PEQUENO).build();
        Car car2 = Car.builder().ano(2023).placa("1235").categoria(PEQUENO).descricao("Fiat Palio").build();
        Car car3 = Car.builder().ano(2024).placa("1253").categoria(PEQUENO).descricao("Kia Picanto").build();

        repository.save(car1);
        repository.save(car2);
        repository.save(car3);
    }

    @Test
    void findAllTest() {
        // given
        var total = repository.count();

        // when
        var response =
                restTemplate.getForEntity(URL, CarDto[].class);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(total, response.getBody().length);

        Assertions
                .assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        Assertions
                .assertThat(response.getBody())
                .hasSize((int)total);

    }


    @Test
    void findByIdTest() {
        // given
        var id = "1235";
        var expected =
                CarDto.builder()
                        .ano(2023)
                        .placa("1235")
                        .categoria(PEQUENO)
                        .descricao("Fiat Palio")
                        .build();

        // when
        var response =
                restTemplate.getForEntity(URL+"/{id}", CarDto.class, id);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    void saveTest() {
        //given
        final long total = repository.count();
        final CarDto dto =
                CarDto.builder()
                        .ano(2020)
                        .placa("7777")
                        .categoria(SUV)
                        .descricao("Novo carro")
                        .build();
        final Car car = CarMapper.toEntity(dto);


        //when
        var response = restTemplate.postForEntity(URL, dto, Void.class);

        //then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(total+1, repository.count());
        verify(service, times(1)).save(car);
    }

    @Test
    void delete() {
        // given
        final long total = repository.count();
        var id = "1235";

        // when
        var response = restTemplate
                .exchange(URL+"/{id}", HttpMethod.DELETE, null, Void.class, id);

        // then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(total-1, repository.count());
        assertFalse(repository.findById("1235").isPresent());
    }

    @Test
    void updateTest() {
        // given
        var id = "1235";
        var total = repository.count();
        CarDto dto =
                CarDto.builder()
                        .ano(2020)
                        .categoria(PEQUENO)
                        .descricao("Fiat Palio 4 portas")
                        .build();

        // when
        var response = restTemplate
                .exchange(URL+"/{id}", HttpMethod.PUT, new HttpEntity<>(dto), CarDto.class, id);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(total, repository.count());
        var car = repository.findById(id).get();
        //assertEquals(dto, toDto(car));  TODO: mostrar AssertJ
        Assertions
                .assertThat(toDto(car))
                .usingRecursiveComparison()
                .ignoringFields("placa")
                .isEqualTo(dto);

    }










}