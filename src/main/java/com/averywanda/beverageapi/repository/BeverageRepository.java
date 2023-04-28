package com.averywanda.beverageapi.repository;

import com.averywanda.beverageapi.model.Beverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeverageRepository extends JpaRepository<Beverage, Long> {

    Beverage findByName(String beverageName);
}
