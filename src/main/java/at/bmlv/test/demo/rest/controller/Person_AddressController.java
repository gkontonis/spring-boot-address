package at.bmlv.test.demo.rest.controller;

import at.bmlv.test.demo.dto.Person_AddressDTO;
import at.bmlv.test.demo.rest.service.Person_AddressService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class Person_AddressController {

    private static final String ENDPOINT = "/person_address";
    private final Person_AddressService person_addressService;

    public Person_AddressController(Person_AddressService personAddressService) {
        person_addressService = personAddressService;
    }

    @PostMapping(value = "/person_address")
    public ResponseEntity<Person_AddressDTO> createPerson_Address(@RequestBody Person_AddressDTO person_addressDTO) throws URISyntaxException {
        return ResponseEntity.created(new URI(ENDPOINT)).body(person_addressService.create(person_addressDTO));
    }
   /* @Transactional
    @PutMapping(value = "/person_address/{id}")
    public ResponseEntity<Void> updateperson_address(@PathVariable Long id, @RequestBody Person_AddressDTO person_addressDTO) {
        person_addressService.update(person_addressDTO);
        return ResponseEntity.ok().build();
    }

    not needed?*/
   @GetMapping(value = "/person_address")
   public ResponseEntity<List<Person_AddressDTO>> findAllPerson_Address(
           @RequestParam(required = false) String search,
           @RequestParam(defaultValue = "0", required = false) int page,
           @RequestParam(defaultValue = "50", required = false) int size
   ) {
       if (search != null && !search.isBlank()) {
           return ResponseEntity.ok(person_addressService.findBySearch(search, PageRequest.of(page, size)));
       }
       return ResponseEntity.ok(person_addressService.findAll(PageRequest.of(page, size)));
   }
    @GetMapping(value = "/person_address/{id}")
    public ResponseEntity<Person_AddressDTO> findPerson_AddressById(@PathVariable Long id) {
        return ResponseEntity.of(
                person_addressService.findById(id)
        );
    }

    @DeleteMapping(value = "/person_address/{id}")
    public ResponseEntity<Void> deletePerson_Address(@PathVariable Long id) {
        person_addressService.deletePerson_AddressByID(id);
        return ResponseEntity.ok().build();
    }
}
