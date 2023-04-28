package com.averywanda.beverageapi.service;

import com.averywanda.beverageapi.model.Beverage;
import com.averywanda.beverageapi.repository.BeverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class BeverageService {

    Logger logger = Logger.getLogger(BeverageService.class.getName());

    @Autowired
    private BeverageRepository beverageRepository;

    public Beverage createBeverage(Beverage beverageObject) {
        Beverage beverage = beverageRepository.findByName(beverageObject.getName());
        if (beverage != null) {
            logger.info("Beverage with name " + beverageObject.getName() + " already exist.");
        }
        return beverageRepository.save(beverageObject);
    }
}
