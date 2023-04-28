package com.averywanda.beverageapi.service;

import com.averywanda.beverageapi.exception.InformationExistException;
import com.averywanda.beverageapi.exception.InformationNotFoundException;
import com.averywanda.beverageapi.model.Beverage;
import com.averywanda.beverageapi.model.BeverageType;
import com.averywanda.beverageapi.repository.BeverageRepository;
import com.averywanda.beverageapi.repository.BeverageTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class BeverageService {

    Logger logger = Logger.getLogger(BeverageService.class.getName());

    @Autowired
    private BeverageRepository beverageRepository;
    @Autowired
    private BeverageTypeRepository beverageTypeRepository;

    public BeverageType createBeverageTypes(BeverageType beverageTypeObject) {
        BeverageType beverageType = beverageTypeRepository.findByName(beverageTypeObject.getName());
        // check if exist
        if (beverageType != null) {
            throw new InformationExistException("Beverage Type with name " + beverageTypeObject.getName() + " already exist.");
        } else {
            return beverageTypeRepository.save(beverageTypeObject);
        }
    }
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
     * Method handles returning all beverage types.
     * @return A list of BeverageType objects.
     */
    public List<BeverageType> getBeverageTypes() {
        return beverageTypeRepository.findAll();
    }

    /**
     * Method handles returning all beverages.
     * @return List of Beverage objects.
     */
    public List<Beverage> getBeverages() {
        return beverageRepository.findAll();
    }

    /**
     * Method handles obtaining a specific beverage record based on the specific Id passed in.
     * @param beverageId
     * @return
     */
    public Optional<Beverage> getBeverage(Long beverageId) {
        Optional<Beverage> beverage = beverageRepository.findById(beverageId);
        // if the beverage exist
        if (beverage.isPresent()) {
            return beverage;
        } else {
            throw new InformationNotFoundException("Beverage with id " + beverageId + " is not found" );
        }
    }

//    public Beverage updateBeverage(@PathVariable Long beverageId, @RequestBody Beverage beverageObject) {
//        Optional<Beverage> beverage = beverageRepository.findById(beverageId);
//        // if the beverage exist
//        if (beverage.isPresent()) {
//            // check if the beverage
//        }
//
    }
