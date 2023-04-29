package com.averywanda.beverageapi.repository;

import com.averywanda.beverageapi.model.Beverage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BeverageRepository extends JpaRepository<Beverage, Long> {

    // get beverage by id and user id
    Beverage findByIdAndUserId(Long beverageId, Long userId);

    // get beverage by user id
    List<Beverage> findByUserId(Long userId);

    // Get beverage by Id
   Optional<Beverage> findById(Long beverageId);


}
