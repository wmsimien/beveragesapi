package com.averywanda.beverageapi.repository;

import com.averywanda.beverageapi.model.Beverage;
import com.averywanda.beverageapi.model.BeverageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BeverageRepository extends JpaRepository<Beverage, Long> {

    // Get beverage by name
//    Beverage findByNameAndIdAndUserIdAndIdIsNot(String beverageName, Long userId, Long beverageType);
    Beverage findByIdAndUserId(Long beverageId, Long userId);
    List<Beverage> findByUserId(Long userId);

    // Get beverage by Id
   Optional<Beverage> findById(Long beverageId);


}
