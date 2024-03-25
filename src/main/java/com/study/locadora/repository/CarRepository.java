package com.study.locadora.repository;

import com.study.locadora.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, String> {

    List<Car> findByDescricaoContaining(String descricao);
}
