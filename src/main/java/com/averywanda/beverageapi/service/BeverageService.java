package com.averywanda.beverageapi.service;

import com.averywanda.beverageapi.exception.InformationExistException;
import com.averywanda.beverageapi.model.Beverage;
import com.averywanda.beverageapi.repository.BeverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BeverageService {

    Logger logger = Logger.getLogger(BeverageService.class.getName());

    @Autowired
    private BeverageRepository beverageRepository;

    /**
     * Method handles the creating of a new beverage object.
     * @param beverageObject
     * @return A new beverage object.
     */
    public Beverage createBeverage(Beverage beverageObject) {
        Beverage beverage = beverageRepository.findByName(beverageObject.getName());
        if (beverage != null) {
            throw new InformationExistException("Beverage with name " + beverageObject.getName() + " already exist.");
        } else {
            return beverageRepository.save(beverageObject);
        }
    }

    /**
     * Method handles returning all beverages
     * @return List of Beverage objects.
     */
    public List<Beverage> getBeverages() {
        return beverageRepository.findAll();
    }

}
