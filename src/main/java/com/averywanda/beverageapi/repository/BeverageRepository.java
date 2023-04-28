package com.averywanda.beverageapi.repository;

import com.averywanda.beverageapi.model.Beverage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BeverageRepository extends JpaRepository<Beverage, Long> {

    // Get beverage by name
    Beverage findByName(String beverageName);

    // Get beverage by Id
   Optional<Beverage> findById(Long beverageId);

    List<Beverage> findBeverageByBeverageType(Long beverageTypeId);

}
