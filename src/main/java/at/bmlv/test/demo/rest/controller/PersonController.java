package at.bmlv.test.demo.rest.controller;

import at.bmlv.test.demo.dto.PersonDTO;
import at.bmlv.test.demo.rest.service.PersonService;
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
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class PersonController {
    private static final String ENDPOINT = "/person";
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @PostMapping(value = "/person")
    @Operation(summary = "Create person", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))), @ApiResponse(description = "Authentication Failure", responseCode = "401", content = @Content)})
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) throws URISyntaxException {
        return ResponseEntity.created(new URI(ENDPOINT)).body(personService.create(personDTO));
    }


    @PutMapping(value = "/person/{UUID}")
    @Operation(summary = "Update country", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content), @ApiResponse(description = "Authentication Failure", responseCode = "401", content = @Content)})
    public ResponseEntity<Void> updatePerson(@PathVariable UUID UUID, @RequestBody PersonDTO personDTO) {
        personService.update(personDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/person")
    @Operation(summary = "Get all persons", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))), @ApiResponse(description = "Authentication Failure", responseCode = "401", content = @Content)})
    public ResponseEntity<List<PersonDTO>> findAllPersons(@RequestParam(required = false) String search, @RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "50", required = false) int size) {


        if (search != null && !search.isBlank()) {
            return ResponseEntity.ok(personService.findBySearch(search, PageRequest.of(page, size)));
        }
        return ResponseEntity.ok(personService.findAll(PageRequest.of(page, size)));
    }

    @GetMapping(value = "/person/{id}")
    @Operation(summary = "Get person by id", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))), @ApiResponse(description = "Not found", responseCode = "404", content = @Content), @ApiResponse(description = "Authentication Failure", responseCode = "401", content = @Content)})
    public ResponseEntity<PersonDTO> findPersonById(@PathVariable UUID id) {
        return ResponseEntity.of(personService.findById(id));
    }

    @DeleteMapping(value = "/person/{uuid}")
    @Operation(summary = "Delete person by id", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content), @ApiResponse(description = "Not found", responseCode = "404", content = @Content), @ApiResponse(description = "Authentication Failure", responseCode = "401", content = @Content)})
    public ResponseEntity<Void> deletePerson(@PathVariable UUID uuid) {
        personService.deletePersonByUUID(uuid);
        return ResponseEntity.ok().build();
    }
}
