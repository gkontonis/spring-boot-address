package at.bmlv.test.demo.rest.controller;

import at.bmlv.test.demo.dto.IdDTO;
import at.bmlv.test.demo.dto.Person_AddressDTO;
import at.bmlv.test.demo.rest.service.Person_AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "person-address-controller")
public class Person_AddressController {

    private static final String ENDPOINT = "/person_address";
    private final Person_AddressService person_addressService;

    public Person_AddressController(Person_AddressService personAddressService) {
        person_addressService = personAddressService;
    }


    @PostMapping(value = "/person_address")
    @Operation(summary = "Create person_address", responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Person_AddressDTO.class))),
            @ApiResponse(description = "Authentication Failure", responseCode = "401", content = @Content)})
    public ResponseEntity<Person_AddressDTO> createPerson_Address(@RequestBody Person_AddressDTO dto) throws URISyntaxException {
        return ResponseEntity.created(new URI(ENDPOINT)).body(person_addressService.create(dto));
    }
    /*
     @PutMapping(value = "/person_address/{id}")
     public ResponseEntity<Void> updateperson_address(@PathVariable Long id, @RequestBody Person_AddressDTO person_addressDTO) {
         person_addressService.update(person_addressDTO);
         return ResponseEntity.ok().build();
     }
     not needed?*/

    @GetMapping(value = "/person_address")
    @Operation(summary = "Get all persons", responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Person_AddressDTO.class))),
            @ApiResponse(description = "Authentication Failure", responseCode = "401", content = @Content)})
    public ResponseEntity<List<Person_AddressDTO>> findAllPerson_Address(@RequestParam(required = false) String search, @RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "50", required = false) int size) {
        if (search != null && !search.isBlank()) {
            return ResponseEntity.ok(person_addressService.findBySearch(search, PageRequest.of(page, size)));
        }
        return ResponseEntity.ok(person_addressService.findAll(PageRequest.of(page, size)));
    }

    @GetMapping(value = "/person_address/{id}")
    @Operation(summary = "Get person_address by id", responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Person_AddressDTO.class))),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Authentication Failure", responseCode = "401", content = @Content)
    })
    public ResponseEntity<Person_AddressDTO> findPerson_AddressById(@PathVariable Long id) {
        return ResponseEntity.of(person_addressService.findById(id));
    }


    @DeleteMapping(value = "/person_address/{id}")
    @Operation(summary = "Delete person_address by id", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Authentication Failure", responseCode = "401", content = @Content)
    })
    public ResponseEntity<Void> deletePerson_Address(@PathVariable Long id) {
        person_addressService.deletePerson_AddressByID(id);
        return ResponseEntity.ok().build();
    }
}
