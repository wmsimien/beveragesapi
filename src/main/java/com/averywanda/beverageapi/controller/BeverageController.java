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

    /** POST /api/beverage-type/
     * Method calls the Beverage service object to handle creating a new beverage type object for the current logged-in user.
     * @param beverageTypeObject Beverage type data of type BeverageType.
     * @return BeverageType class/objects.
     */
    @PostMapping(path = "/beverage-type/")
    public BeverageType createBeverageTypes(@RequestBody BeverageType beverageTypeObject) {
        return beverageService.createBeverageTypes(beverageTypeObject);
    }

    /** GET /aip/beverage-type/
     * Method calls Beverage service object to handle getting all beverage types for the current logged-in user.
     * @return A list of BeverageType classes/objects.
     */
    @GetMapping(path = "/beverage-type/")
    public List<BeverageType> getBeverageTypes() {
        return beverageService.getBeverageTypes();
    }

    /** GET /beverage-type/{beverageTypeId}/
     * Method call Beverage service object to handle getting a specific beverage type by Id for the current logged-in user.
     * @param beverageTypeId Beverage type id which is a type Long.
     * @return An optional of BeverageType classes/objects.
     */
    @GetMapping(path = "/beverage-type/{beverageTypeId}/")
    public Optional<BeverageType> getBeverageType(@PathVariable Long beverageTypeId) {
        return beverageService.getBeverageType(beverageTypeId);
    }

    /** PUT /api/beverage-type/{beverageTypeId}/
     * Method calls the Beverage service object to handle updating a specific beverage type name for the currently logged-in user.
     * @param beverageTypeId Beverage Type id which is a type Long.
     * @param beverageTypeObject Beverage type data  of BeverageType.
     * @return Updated Beverage Type object.
     */
    @PutMapping(path = "/beverage-type/{beverageTypeId}/")
    public BeverageType updateBeverageType(@PathVariable Long beverageTypeId, @RequestBody BeverageType beverageTypeObject) {
        return beverageService.updateBeverageType(beverageTypeId, beverageTypeObject);
    }

    /** DELETE /api/beverage-type/{beverageTypeId}/
     * Method calls the Beverage server object to handle deleting a specific beverage type by Id for the currently logged-in user.
     * @param beverageTypeId Beverage type data element of type Long.
     * @return An options of type BeverageType.
     */
    @DeleteMapping(path = "/beverage-type/{beverageTypeId}/")
    public Optional<BeverageType> deleteBeverageType(@PathVariable Long beverageTypeId) {
        return beverageService.deleteBeverageType(beverageTypeId);
    }

    /** POST /api/beverage-type/{beverageTypeId}/
     * Method calls beverage service object to handle creating of a new beverage object under a specific beverage type for the currently logged-in user.
     * @param beverageObject Beverage data elements of type Beverage.
     * @return A Beverage object.
     */
    @PostMapping(path = "/beverage-type/{beverageTypeId}/")
    public Beverage createBeverageTypeBeverage(@PathVariable(value = "beverageTypeId") Long beverageTypeId, @RequestBody Beverage beverageObject) {
        return beverageService.createBeverageTypeBeverage(beverageTypeId, beverageObject);
    }

    /** GET /api/beverages/
     * Method calls service object to return a list of all beverages created by the logged-in user.
     * @return A list of beverage objects of type Beverage.
     */
    @GetMapping(path = "/beverages/")
    public List<Beverage> getBeverages() {
        return beverageService.getBeverages();
    }

    /** GET /api/beverages/{beverageId}/
     * Method calls service object to get a specific beverage based on an Id passed in.
     * @param beverageId Beverage id of type Long.
     * @return An optional of type Beverage.
     */
    @GetMapping(path="/beverages/{beverageId}/")
    public Optional<Beverage> getBeverage(@PathVariable Long beverageId) {
        return beverageService.getBeverage(beverageId);
    }

    /** GET /api/beverage-type/{beverageTypeId}/beverages/
     * Method calls service object to get listing of all beverages for a specific beverage type id for the logged-in user.
     * @param beverageTypeId Beverage type id of type Long.
     * @return A list of Beverage type objects.
     */
    @GetMapping(path = "/beverage-type/{beverageTypeId}/beverages/")
    public List<Beverage> getBeverageTypeBeverages(@PathVariable(value = "beverageTypeId") Long beverageTypeId) {
        return beverageService.getBeverageTypeBeverages(beverageTypeId);
    }

    /** GET /api/beverage-type/{beverageTypeId}/beverages/{beverageId}/
     * Method calls service object to get a specific beverage for a specific beverage type for the logged-in user.
     * @param beverageTypeId Beverage type id of type Long.
     * @param beverageId Beverage id of type Long.
     * @return List of Beverage type objects.
     */
    @GetMapping(path = "/beverage-type/{beverageTypeId}/beverages/{beverageId}/")
    public List<Beverage> getBeverageTypeBeveragesBeverage(@PathVariable(value = "beverageTypeId") Long beverageTypeId, @PathVariable Long beverageId) {
        return beverageService.getBeverageTypeBeveragesBeverage(beverageTypeId, beverageId);
    }

    /** PUT /api/beverage-type/{beverageTypeId}/beverages/{beverageId}/
     * Calls service object to handle updating a specific beverage for a specific beverage type for the logged-in user.
     * @param beverageTypeId Beverage type id of type Long.
     * @param beverageId Beverage id of type Long.
     * @param beverageObject Beverage data elements of Beverage type.
     * @return A object of type Beverage.
     */
    @PutMapping(path = "/beverage-type/{beverageTypeId}/beverages/{beverageId}/")
    public Beverage updateBeverageTypeBeveragesBeverage(@PathVariable(value = "beverageTypeId") Long beverageTypeId, @PathVariable Long beverageId, @RequestBody Beverage beverageObject) {
        return beverageService.updateBeverageTypeBeveragesBeverage(beverageTypeId, beverageId, beverageObject);
    }

    /** DELETE /api/beverage-type/{beverageTypeId}/beverages/{beverageId}/
     * Calls service object to handle deleting a specific beverage for a specific beverage type for the logged-in user.
     * @param beverageTypeId Beverage type id of type Long.
     * @param beverageId Beverage id of type Long.
     */
    @DeleteMapping(path = "/beverage-type/{beverageTypeId}/beverages/{beverageId}/")
    public void deleteBeverageTypeBeveragesBeverage(@PathVariable(value = "beverageTypeId") Long beverageTypeId, @PathVariable Long beverageId) {
        beverageService.deleteBeverageTypeBeveragesBeverage(beverageTypeId, beverageId);
    }
}
