package com.study.locadora.mapper;

import com.study.locadora.dto.CarDto;
import com.study.locadora.model.Car;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CarMapper {

    public static List<CarDto> toDto(final List<Car> entities) {

//        final List<CarDto> response = new ArrayList<>();
//
//        if (Objects.nonNull(entities)) {  // (entities != null)
//            for(Car entity : entities) {
//                response.add(toDto(entity));
//            }
//        }
//
//        return response;

        return entities.stream()
                .map(entity -> toDto(entity))
                .collect(Collectors.toList());
    }

    public static CarDto toDto(final Car entity) {
        if (Objects.nonNull(entity)) {
            return CarDto.builder()
                    .ano(entity.getAno())
                    .descricao(entity.getDescricao())
                    .placa(entity.getPlaca())
                    .build();
        } else {
            return CarDto.builder().build();
        }
    }

    public static Car toEntity(final CarDto dto) {
        return Car.builder()
                .ano(dto.getAno())
                .descricao(dto.getDescricao())
                .placa(dto.getPlaca())
                .build();
    }


    public static void copy(final Car source, final Car destiny) {
        destiny.setPlaca(source.getPlaca());
        destiny.setAno(source.getAno());
        destiny.setDescricao(source.getDescricao());
    }


}
