package com.averywanda.beverageapi.repository;

import com.averywanda.beverageapi.model.BeverageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeverageTypeRepository extends JpaRepository<BeverageType, Long> {

    // obtain beverage type by name
    BeverageType findByName(String beverageTypeName);

    // obtain beverage type listing for current logged-in user
    List<BeverageType> findByUserId(Long userId);

    // obtain beverage type by current user id and beverageType name
    BeverageType findByUserIdAndName(Long userId, String beverageTypeName);

    // obtain beverage type by id
    Optional<BeverageType> findById(Long beverageTypeId);

}
