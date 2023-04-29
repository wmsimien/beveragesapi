package com.averywanda.beverageapi.controller;

import com.averywanda.beverageapi.model.Beverage;
import com.averywanda.beverageapi.model.BeverageType;
import com.averywanda.beverageapi.service.BeverageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api")
public class BeverageController {
    Logger logger = Logger.getLogger(BeverageService.class.getName());

    @Autowired
    BeverageService beverageService;
    @GetMapping(path = "/hello/")
    public String helloMsg() {
        return "This is a Test Hello, Folks!";
    }

    /**
     * Method calls the Beverage service object to handle creating a new beverage type object.
     * @param beverageTypeObject
     * @return
     */
    @PostMapping(path = "/beverage-type/")
    public BeverageType createBeverageTypes(@RequestBody BeverageType beverageTypeObject) {
        return beverageService.createBeverageTypes(beverageTypeObject);
    }

    /**
     * Method calls Beverage service object to handle getting all beverage types.
     * @return A list of BeverageType objects.
     */
    @GetMapping(path = "/beverage-type/")
    public List<BeverageType> getBeverageTypes() {
        return beverageService.getBeverageTypes();
    }

    /**
     * Method call Beverage service object to handle getting a specific beverage type by Id.
     * @param beverageTypeId
     * @return A BeverageType object.
     */
    @GetMapping(path = "/beverage-type/{beverageTypeId}/")
    public Optional<BeverageType> getBeverageType(@PathVariable Long beverageTypeId) {
        return beverageService.getBeverageType(beverageTypeId);
    }

    /**
     * Method calls the Beverage service object to handle updating a specific beverage type name.
     * @param beverageTypeId
     * @param beverageTypeObject
     * @return
     */
    @PutMapping(path = "/beverage-type/{beverageTypeId}/")
    public BeverageType updateBeverageType(@PathVariable Long beverageTypeId, @RequestBody BeverageType beverageTypeObject) {
        return beverageService.updateBeverageType(beverageTypeId, beverageTypeObject);
    }

    /**
     * Method calls the Beverage server object to handle deleting a specific beverage type by Id.
     * @param beverageTypeId
     * @return
     */
    @DeleteMapping(path = "/beverage-type/{beverageTypeId}/")
    public Optional<BeverageType> deleteBeverageType(@PathVariable Long beverageTypeId) {
        return beverageService.deleteBeverageType(beverageTypeId);
    }

    /**
     * Method calls beverage service object to handle creating of a new beverage object under a specific beverage type.
     * @param beverageObject
     * @return A Beverage object.
     */
    @PostMapping(path = "/beverage-type/{beverageTypeId}/")
    public Beverage createBeverageTypeBeverage(@PathVariable(value = "beverageTypeId") Long beverageTypeId, @RequestBody Beverage beverageObject) {
        return beverageService.createBeverageTypeBeverage(beverageTypeId, beverageObject);
    }

    /**
     * Method calls service object to return a list of beverages.
     * @return A list of beverage objects.
     */
    @GetMapping(path = "/beverages/")
    public List<Beverage> getBeverages() {
        return beverageService.getBeverages();
    }

    /**
     * Method calls service object to get a specific beverage based on an Id passed in.
     * @param beverageId
     * @return
     */
    @GetMapping(path="/beverages/{beverageId}/")
    public Optional<Beverage> getBeverage(@PathVariable Long beverageId) {
        return beverageService.getBeverage(beverageId);
    }

    /**
     * Method calls service object to get listing of all beverages for a specific beverage type id.
     * @param beverageTypeId
     * @return
     */
    @GetMapping(path = "/beverage-type/{beverageTypeId}/beverages/")
    public List<Beverage> getBeverageTypeBeverage(@PathVariable(value = "beverageTypeId") Long beverageTypeId) {
        return beverageService.getBeverageTypeBeverage(beverageTypeId);
    }

    @GetMapping(path = "/beverage-type/{beverageTypeId}/beverages/{beverageId}/")
    public List<Beverage> getBeverageTypeBeveragesBeverage(@PathVariable(value = "beverageTypeId") Long beverageTypeId, @PathVariable Long beverageId) {
        logger.info(beverageId.toString());
        logger.info(beverageTypeId.toString());
        return beverageService.getBeverageTypeBeveragesBeverage(beverageTypeId, beverageId);
    }



}
