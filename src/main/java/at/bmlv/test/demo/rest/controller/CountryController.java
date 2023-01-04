package at.bmlv.test.demo.rest.controller;

import at.bmlv.test.demo.dto.CountryDTO;
import at.bmlv.test.demo.rest.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
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
public class CountryController {

    private static final String ENDPOINT = "/country";
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }


    @PostMapping(value = "/country")
    @Operation(summary = "Create country", responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CountryDTO.class))),
            @ApiResponse(description = "Authentication Failure", responseCode = "401", content = @Content)})
    public ResponseEntity<CountryDTO> createCountry(@RequestBody CountryDTO countryDTO) throws URISyntaxException {
        return ResponseEntity.created(new URI(ENDPOINT)).body(countryService.create(countryDTO));
    }


    @PutMapping(value = "/country/{id}")
    @Operation(summary = "Update country", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "Authentication Failure", responseCode = "401", content = @Content)})
    public ResponseEntity<Void> updateCountry(@PathVariable Long id, @RequestBody CountryDTO countryDTO) {
        countryService.update(countryDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/country")
    @Operation(summary = "Get all countrys", responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CountryDTO.class))),
            @ApiResponse(description = "Authentication Failure", responseCode = "401", content = @Content)})
    public ResponseEntity<List<CountryDTO>> findAllCountries(@RequestParam(required = false) String search, @RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "50", required = false) int size) {
        if (search != null && !search.isBlank()) {
            return ResponseEntity.ok(countryService.findBySearch(search, PageRequest.of(page, size)));
        }
        return ResponseEntity.ok(countryService.findAll(PageRequest.of(page, size)));
    }


    @GetMapping(value = "/country/{id}")
    @Operation(summary = "Get country by id", responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CountryDTO.class))),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Authentication Failure", responseCode = "401", content = @Content)
    })
    public ResponseEntity<CountryDTO> findCountryById(@PathVariable Long id) {
        return ResponseEntity.of(countryService.findById(id));
    }

    @DeleteMapping(value = "/country/{id}")
    @Operation(summary = "Delete country by id", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Authentication Failure", responseCode = "401", content = @Content)
    })
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        countryService.deleteCountryByID(id);
        return ResponseEntity.ok().build();
    }
}
