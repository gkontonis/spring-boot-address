package at.bmlv.test.demo.rest.controller;

import at.bmlv.test.demo.dto.AddressDTO;
import at.bmlv.test.demo.rest.service.AddressService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AddressController {

    private static final String ENDPOINT = "/address";
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping(value = "/address")
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) throws URISyntaxException{
        return ResponseEntity.created(new URI(ENDPOINT)).body(addressService.create(addressDTO));
    }
    @Transactional
    @PutMapping(value = "/address/{id}")
    public ResponseEntity<Void> updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO){
        addressService.update(addressDTO);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "/address")
    public ResponseEntity<List<AddressDTO>> findAllAddresses(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "50", required = false) int size
    ) {
        if (search != null && !search.isBlank()) {
            return ResponseEntity.ok(addressService.findBySearch(
                    search, PageRequest.of(page, size)));
        }
        return ResponseEntity.ok(addressService.findAll(PageRequest.of(page, size)));
    }

    @GetMapping(value = "/address/{id}")
    public ResponseEntity<AddressDTO> findAddressById(@PathVariable Long id) {
        return ResponseEntity.of(addressService.findById(id));
    }

    @DeleteMapping(value = "/address/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteByAddressID(id);
        return ResponseEntity.ok().build();
    }
}
