package com.study.locadora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private String placa;
    private String descricao;
    private int ano;


    public static void main(String[] args) {
//        var lista = List.of(1,2,3,4,5,6,7);

        Stream<Double> randomNumbersStream = Stream.generate(Math::random);

//        Stream<Integer> streamInteiros = Stream.of(1,2,3,4,5,6,7);

        randomNumbersStream
                .map(aDouble -> aDouble)
                .limit(10)
                .forEach(x -> {System.out.println(x);});





    }
}
