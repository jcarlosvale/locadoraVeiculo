package com.study.locadora.mapper;

import com.study.locadora.dto.CarDto;
import com.study.locadora.model.Car;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CarMapper {
    public static List<CarDto> toDto(final List<Car> cars) {
        return
                cars.stream()
                        .map(CarMapper::toDto)
                        .collect(Collectors.toList());
    }

    public static CarDto toDto(final Car car) {
        if (Objects.isNull(car)) return CarDto.builder().build();
        return CarDto.builder()
                .ano(car.getAno())
                .placa(car.getPlaca())
                .descricao(car.getDescricao())
                .categoria(car.getCategoria())
                .build();
    }

    public static Car toEntity(final CarDto dto) {
        if (Objects.isNull(dto)) return Car.builder().build();
        return Car.builder()
                .ano(dto.getAno())
                .placa(dto.getPlaca())
                .descricao(dto.getDescricao())
                .categoria(dto.getCategoria())
                .build();
    }
}
