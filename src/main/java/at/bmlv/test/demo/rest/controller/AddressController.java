package at.bmlv.test.demo.rest.controller;

import at.bmlv.test.demo.dto.AddressDTO;
import at.bmlv.test.demo.rest.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping(value = "/address")
    public ResponseEntity<List<AddressDTO>> findAllAddresses() {
        return ResponseEntity.ok(addressService.findAll());
    }

    @DeleteMapping(value = "/address/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id){
        addressService.deleteByAddressID(id);
        return ResponseEntity.ok().build();
    }
}
