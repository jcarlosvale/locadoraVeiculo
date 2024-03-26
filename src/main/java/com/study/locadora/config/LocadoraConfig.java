package com.study.locadora.config;


import com.study.locadora.dto.CarDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LocadoraConfig {

    @Bean("BancoDeDados")
    public List<CarDto> repositorio() {
        return new ArrayList<>();
    }

}
