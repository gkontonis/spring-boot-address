package at.bmlv.test.demo.rest.controller;

import at.bmlv.test.demo.dto.AddressDTO;
import at.bmlv.test.demo.rest.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Create address", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AddressDTO.class))), @ApiResponse(description = "Authentication Failure", responseCode = "401", content = @Content)})
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) throws URISyntaxException {
        return ResponseEntity.created(new URI(ENDPOINT)).body(addressService.create(addressDTO));
    }


    @PutMapping(value = "/address/{id}")
    @Operation(summary = "Update address", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content), @ApiResponse(description = "Authentication Failure", responseCode = "401", content = @Content)})
    public ResponseEntity<Void> updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        addressService.update(addressDTO);
        return ResponseEntity.ok().build();
    }


    //TODO Arrayy sceme by everyting with an array
    @GetMapping(value = "/address")
    @Operation(summary = "Get all addresses", responses = {@ApiResponse(description = "Success", responseCode = "200",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AddressDTO.class)))),
            @ApiResponse(description = "Authentication Failure", responseCode = "401", content = @Content)})
    public ResponseEntity<List<AddressDTO>> findAllAddresses(@RequestParam(required = false) String search, @RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "50", required = false) int size) {
        if (search != null && !search.isBlank()) {
            return ResponseEntity.ok(addressService.findBySearch(search, PageRequest.of(page, size)));
        }
        return ResponseEntity.ok(addressService.findAll(PageRequest.of(page, size)));
    }


    @GetMapping(value = "/address/{id}")
    @Operation(summary = "Get address by id", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AddressDTO.class))), @ApiResponse(description = "Not found", responseCode = "404", content = @Content), @ApiResponse(description = "Authentication Failure", responseCode = "401", content = @Content)})
    public ResponseEntity<AddressDTO> findAddressById(@PathVariable Long id) {
        return ResponseEntity.of(addressService.findById(id));
    }


    @DeleteMapping(value = "/address/{id}")
    @Operation(summary = "Delete address by id", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content), @ApiResponse(description = "Not found", responseCode = "404", content = @Content), @ApiResponse(description = "Authentication Failure", responseCode = "401", content = @Content)})
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteByAddressID(id);
        return ResponseEntity.ok().build();
    }
}
