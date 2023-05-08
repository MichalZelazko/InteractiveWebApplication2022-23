package pl.dmcs.iwamzelazko.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.iwamzelazko.model.Address;
import pl.dmcs.iwamzelazko.repository.AddressRepository;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/addresses")
public class AddressRESTController {
    private AddressRepository addressRepository;

    @Autowired
    public AddressRESTController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Address> findAllAddresses() {
        return addressRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Address> findAddress(@PathVariable("id") long id){
        Address address = addressRepository.findById(id);
        if (address == null){
            System.out.println("Address with id " + id + " not found");
            return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Address>(address, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Address> deleteAllAddresses(){
        addressRepository.deleteAll();
        return new ResponseEntity<Address>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Address> updateAddress(@RequestBody Address address, @PathVariable("id") long id){
        address.setId(id);
        addressRepository.save(address);
        return new ResponseEntity<Address>(address, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PATCH)
    public ResponseEntity<Address> updatePartOfAddress(@RequestBody Map<String, Object> updates, @PathVariable("id") long id){
        Address address = addressRepository.findById(id);
        if (address == null){
            System.out.println("Address with id " + id + " not found");
            return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
        }
        partialUpdate(address, updates);
        return new ResponseEntity<Address>(HttpStatus.OK);
    }

    private void partialUpdate(Address address, Map<String, Object> updates) {
        if (updates.containsKey("city")) {
            address.setCity((String) updates.get("city"));
        }
        if (updates.containsKey("street")) {
            address.setStreet((String) updates.get("street"));
        }
        if (updates.containsKey("number")) {
            address.setNumber((String) updates.get("number"));
        }
        if (updates.containsKey("postalCode")) {
            address.setPostalCode((String) updates.get("postalCode"));
        }
        addressRepository.save(address);
    }
}
