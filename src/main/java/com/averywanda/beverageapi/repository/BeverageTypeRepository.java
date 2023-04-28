package com.averywanda.beverageapi.repository;

import com.averywanda.beverageapi.model.BeverageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeverageTypeRepository extends JpaRepository<BeverageType, Long> {

    BeverageType findByName(String beverageTypeName);

    Optional<BeverageType> findById(Long beverageTypeId);
}
