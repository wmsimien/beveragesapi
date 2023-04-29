package com.averywanda.beverageapi.service;

import com.averywanda.beverageapi.exception.InformationExistException;
import com.averywanda.beverageapi.exception.InformationNotFoundException;
import com.averywanda.beverageapi.model.Beverage;
import com.averywanda.beverageapi.model.BeverageType;
import com.averywanda.beverageapi.model.User;
import com.averywanda.beverageapi.repository.BeverageRepository;
import com.averywanda.beverageapi.repository.BeverageTypeRepository;
import com.averywanda.beverageapi.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import javax.swing.text.html.Option;
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

    /**
     * Method handles obtaining the current user logged-in
     * @return A User : all the user detail of that logged-in user
     */
    public static User getCurrentLoggedInUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    /** @PostMapping(path = "/beverage-type/")
     * Method handles creating beverage types for the current logged-in user
     * @param beverageTypeObject
     * @return A BeverageType object.
     */
    public BeverageType createBeverageTypes(BeverageType beverageTypeObject) {
        BeverageType beverageType = beverageTypeRepository.findByUserIdAndName(getCurrentLoggedInUser().getId(), beverageTypeObject.getName());
        // check exist, for current logged-in user
        if (beverageType != null) {
            throw new InformationExistException("Beverage Type with name " + beverageTypeObject.getName() + " already exist.");
        } else {
            // set current logged-in user
            beverageTypeObject.setUser(getCurrentLoggedInUser());
            return beverageTypeRepository.save(beverageTypeObject);
        }
    }

    /** @GetMapping(path = "/beverage-type/")
     * Method handles returning all beverages for a specific beverage type for the current logged-in user.
     * @return A list of BeverageType objects of the current logged-in user.
     */
    public List<BeverageType> getBeverageTypes() {
        List<BeverageType> beverageTypes = beverageTypeRepository.findByUserId(getCurrentLoggedInUser().getId());
        if (beverageTypes.isEmpty()) {
            throw new InformationNotFoundException(" No Beverage Types found for userId " + getCurrentLoggedInUser().getId() + ".");
        } else {
            return beverageTypes;
        }
    }

    /** @GetMapping(path = "/beverages/")
     * Method handles returning all beverages for the current logged-in user.
     * @return List of Beverage objects.
     */
    public List<Beverage> getBeverages() {
        List<Beverage> beverageList = beverageRepository.findByUserId(getCurrentLoggedInUser().getId());
        if (beverageList.isEmpty()) {
            throw new InformationNotFoundException(" No Beverage found for userId " + getCurrentLoggedInUser().getId() + ".");
        } else {
            return beverageList;
        }
    }

    /** @GetMapping(path = "/beverage-type/{beverageTypeId}/")
     * Method handles returning a specific beverage type based on the Id passed-in for the current logged-in user.
     * @param beverageTypeId
     * @return
     */
    public Optional<BeverageType> getBeverageType(Long beverageTypeId) {
        Optional<BeverageType> beverageType = beverageTypeRepository.findByIdAndUserId(beverageTypeId, getCurrentLoggedInUser().getId());
        // if beverage type exist
        if (beverageType.isPresent()) {
            return beverageType;
        } else {
            throw new InformationNotFoundException("Beverage Type with id " + beverageTypeId + " for userId " + getCurrentLoggedInUser().getId() + " was not found." );
        }
    }

    /** @GetMapping(path="/beverages/{beverageId}/")
     * Method handles obtaining a specific beverage record based on the specific Id passed in for the current logged-in user.
     * @param beverageId
     * @return
     */
    public Optional<Beverage> getBeverage(Long beverageId) {
        List<Beverage> beverageList = beverageRepository.findByUserId(getCurrentLoggedInUser().getId());
        if (beverageList.isEmpty()) {
            Optional<Beverage> beverage = beverageList.stream().filter(bev -> bev.getId().equals(beverageId)).findFirst();
            if (beverage.isEmpty()) {
                return beverage;
            } else {
                throw new InformationNotFoundException("Beverage with id " + beverageId + " is not found for userId " + getCurrentLoggedInUser().getId());
            }
        } else {
            throw new InformationNotFoundException("No Beverage was found for userId " + getCurrentLoggedInUser().getId() + "." );
        }
    }

    /** @PutMapping(path = "/beverage-type/{beverageTypeId}/")
     * Method handles updating a BeverageType name for a specific beverageTypeId for the currently logged-in user.
     * @param beverageTypeId
     * @param beverageTypeObject
     * @return
     */
    public BeverageType updateBeverageType(Long beverageTypeId, BeverageType beverageTypeObject) {
        Optional<BeverageType> beverageType = beverageTypeRepository.findByIdAndUserId(beverageTypeId, getCurrentLoggedInUser().getId());

        if (beverageType.isPresent()) {
            // get the container and set passed-in data for current logged-in user
            BeverageType updatedBeverageType = beverageType.get();
            updatedBeverageType.setName(beverageTypeObject.getName());
            updatedBeverageType.setUser(getCurrentLoggedInUser());
            return beverageTypeRepository.save(updatedBeverageType);
        }
        else {
            throw new InformationNotFoundException("Beverage Type with id " + beverageTypeId + "for userid " + getCurrentLoggedInUser().getId() + " not found.");
        }
    }

    /** @DeleteMapping(path = "/beverage-type/{beverageTypeId}/")
     * Method handles deleting a specific BeverageType by beverageId for the currently logged-in user.
     * @param beverageTypeId
     * @return
     */
    public Optional<BeverageType> deleteBeverageType(Long beverageTypeId) {
        Optional<BeverageType> beverageType = beverageTypeRepository.findByIdAndUserId(beverageTypeId, getCurrentLoggedInUser().getId());
        // if beverage type exist
        if (beverageType.isPresent()) {
            beverageTypeRepository.deleteById(beverageTypeId);
            return beverageType;
        } else {
            throw new InformationNotFoundException("Beverage Type with id " + beverageTypeId + " for userId " + getCurrentLoggedInUser().getId() + " not found.");
        }
    }

    /** @PostMapping(path = "/beverage-type/{beverageTypeId}/")
     * Method handles the creating of a new beverage object for a specific beverage type for the currently logged-in user.
     * @param beverageObject
     * @return A new beverage object.
     */
    public Beverage createBeverageTypeBeverage(Long beverageTypeId, Beverage beverageObject) {
        Optional<BeverageType> beverageType = beverageTypeRepository.findByIdAndUserId(beverageTypeId, getCurrentLoggedInUser().getId());
        if (beverageType.isPresent()) {
            beverageObject.setBeverageType(beverageType.get());
            beverageObject.setUser(getCurrentLoggedInUser());
            return beverageRepository.save(beverageObject);
        } else {
            throw new InformationNotFoundException("BeverageType id " + beverageTypeId + " for userId " + getCurrentLoggedInUser().getId() + " not found.");
        }
    }

    /** @GetMapping(path = "/beverage-type/{beverageTypeId}/beverages/")
     * Method handles returning all beverage objects for a specific beverage type Id for the currently logged-in user.
     * @param beverageTypeId
     * @return List of Beverage objects.
     */
    public List<Beverage> getBeverageTypeBeverages(Long beverageTypeId) {
        Optional<BeverageType> beverageType = beverageTypeRepository.findByIdAndUserId(beverageTypeId, getCurrentLoggedInUser().getId());
        if (beverageType.isPresent()){
            if (beverageType.get().getBeverageList().size() == 0) {
                throw new InformationNotFoundException("Beverages with BeverageTypeId " + beverageTypeId + " for userId " + getCurrentLoggedInUser().getId() + " not found.");
            } else {
                return beverageType.get().getBeverageList();
            }
        } else {
            throw new InformationNotFoundException("Beverages with BeverageTypeId " + beverageTypeId + " for userId " + getCurrentLoggedInUser().getId() + " not found.");
        }
    }

    /** @GetMapping(path = "/beverage-type/{beverageTypeId}/beverages/{beverageId}/")
     * Method obtain beverages for a specific beverage type for the currently logged-in user.
     * @param beverageTypeId
     * @param beverageId
     * @return List of r Beverage objects.
     */
    public List<Beverage> getBeverageTypeBeveragesBeverage(Long beverageTypeId, Long beverageId) {
        Optional<BeverageType> beverageType = beverageTypeRepository.findByIdAndUserId(beverageTypeId, getCurrentLoggedInUser().getId());
        if (beverageType.isPresent()) {
            if (beverageType.get().getBeverageList().stream().filter(b -> b.getId().equals(beverageId)).count() == 0) {
                throw new InformationNotFoundException("BeverageType id " + beverageTypeId + " for userId " + getCurrentLoggedInUser().getId() + " not found.");
            } else {
                return beverageType.get().getBeverageList().stream().filter(b -> b.getId().equals(beverageId)).collect(Collectors.toList());
            }
        } else {
            throw new InformationNotFoundException("BeverageType id " + beverageTypeId + " for userId " + getCurrentLoggedInUser().getId() + " not found.");
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
