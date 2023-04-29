package com.averywanda.beverageapi.service;

import com.averywanda.beverageapi.exception.InformationExistException;
import com.averywanda.beverageapi.exception.InformationNotFoundException;
import com.averywanda.beverageapi.model.Beverage;
import com.averywanda.beverageapi.model.BeverageType;
import com.averywanda.beverageapi.repository.BeverageRepository;
import com.averywanda.beverageapi.repository.BeverageTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
     * Method handles returning all beverages for a specific beverage type.
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
     * Method handles returning a specific beverage type based on the Id passed-in.
     * @param beverageTypeId
     * @return
     */
    public Optional<BeverageType> getBeverageType(Long beverageTypeId) {
        Optional<BeverageType> beverageType = beverageTypeRepository.findById(beverageTypeId);
        // if beverage type exist
        if (beverageType.isPresent()) {
            return beverageType;
        } else {
            throw new InformationNotFoundException("Beverage Type with id " + beverageTypeId + " is not found" );
        }
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

    /**
     * Method handles updating a BeverageType name for a specific beverageTypeId.
     * @param beverageTypeId
     * @param beverageTypeObject
     * @return
     */
    public BeverageType updateBeverageType(@PathVariable Long beverageTypeId, @RequestBody BeverageType beverageTypeObject) {
        Optional<BeverageType> beverageType = beverageTypeRepository.findById(beverageTypeId);
        logger.info(beverageType.get().getName());
        // if exist
        if (beverageType.get().getName().equals(beverageTypeObject.getName())) {
            throw new InformationExistException("Beverage Type " + beverageType.get().getName() + " already exist.");
        } else {
            // get the container and set passed-in data
            BeverageType updatedBeverageType = beverageType.get();
            updatedBeverageType.setName(beverageTypeObject.getName());
            return beverageTypeRepository.save(updatedBeverageType);
        }
    }

    /**
     * Method handles deleting a specific BevereaeType by beverageId.
     * @param beverageTypeId
     * @return
     */
    public Optional<BeverageType> deleteBeverageType(Long beverageTypeId) {
        Optional<BeverageType> beverageType = beverageTypeRepository.findById(beverageTypeId);
        // if beverage type exist
        if (beverageType.isPresent()) {
            beverageTypeRepository.deleteById(beverageTypeId);
            return beverageType;
        } else {
            throw new InformationNotFoundException("Beverage Type with id " + beverageTypeId + " is not found" );
        }
    }

    /**
     * Method handles the creating of a new beverage object for a specific beverage type.
     * @param beverageObject
     * @return A new beverage object.
     */
    public Beverage createBeverageTypeBeverage(Long beverageTypeId, Beverage beverageObject) {
        Optional<BeverageType> beverageType = beverageTypeRepository.findById(beverageTypeId);
        if (beverageType.isPresent()) {
            beverageObject.setBeverageType(beverageType.get());
            return beverageRepository.save(beverageObject);
        } else {
            throw new InformationNotFoundException("BeverageType id " + beverageTypeId + " not found.");
        }
    }

    /**
     * Method handles returning all beverage objects for a specific beverage type Id.
     * @param beverageTypeId
     * @return List of Beverage objects.
     */
    public List<Beverage> getBeverageTypeBeverage(Long beverageTypeId) {
        Optional<BeverageType> beverageType = beverageTypeRepository.findById(beverageTypeId);
        if (beverageType.isPresent()){
            if (beverageType.get().getBeverageList().size() == 0) {
                throw new InformationNotFoundException("Beverages with BeverageTypeId " + beverageTypeId + " not found");
            } else {
                return beverageType.get().getBeverageList();
            }
        } else {
            throw new InformationNotFoundException("Beverages with BeverageTypeId " + beverageTypeId + " not found");
        }
    }

    /**
     * Method obtain beverages for a specific beverage type
     * @param beverageTypeId
     * @param beverageId
     * @return List of r Beverage objects.
     */
    public List<Beverage> getBeverageTypeBeveragesBeverage(Long beverageTypeId, Long beverageId) {
        Optional<BeverageType> beverageType = beverageTypeRepository.findById(beverageTypeId);
        if (beverageType.isPresent()) {
            return beverageType.get().getBeverageList().stream().filter(b -> b.getId().equals(beverageId)).collect(Collectors.toList());
        } else {
            throw new InformationNotFoundException("BeverageType id " + beverageTypeId + " not found.");
        }
    }

    /**
     * Method handles updating a specific beverage for a specific beverage type id
     * @param beverageTypeId
     * @param beverageId
     * @param beverageObject
     * @return Updated Beverage object.
     */
    public Beverage updateBeverageTypeBeveragesBeverage(Long beverageTypeId, Long beverageId, Beverage beverageObject) {
        Optional<BeverageType> beverageType = beverageTypeRepository.findById(beverageTypeId);
        if (beverageType.isPresent()) {
            Optional<Beverage> beverage = beverageRepository.findById(beverageId);
            if (beverage.isPresent()) {
                beverage.get().setName(beverageObject.getName());
                beverage.get().setBeverageType(beverageType.get());
                beverage.get().setDescription(beverageObject.getDescription());
                beverage.get().setPairing(beverageObject.getPairing());
                beverage.get().setGoodToKnow(beverageObject.getGoodToKnow());
                beverage.get().setProTip(beverageObject.getProTip());
                return beverageRepository.save(beverage.get());
            } else {
                throw new InformationNotFoundException("Beverage type with id " + beverageId + " and beverage type id " + beverageTypeId + " not found");
            }
        } else {
            throw new InformationNotFoundException("Beverage type with id " + beverageId +  " is not found");
        }
    }

    /**
     * Method handles deleting a specific beverage of a specific beverage type.
     * @param beverageTypeId
     * @param beverageId
     */
    public void deleteBeverageTypeBeveragesBeverage(Long beverageTypeId, Long beverageId) {
        Optional<BeverageType> beverageType = beverageTypeRepository.findById(beverageTypeId);
        if (beverageType.isPresent()) {
            Optional<Beverage> beverage = beverageRepository.findById(beverageId);
            beverageRepository.deleteById(beverage.get().getId());
        }
    }
}
