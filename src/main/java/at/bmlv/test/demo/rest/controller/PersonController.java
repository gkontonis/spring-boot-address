package at.bmlv.test.demo.rest.controller;

import at.bmlv.test.demo.dto.PersonDTO;
import at.bmlv.test.demo.rest.service.PersonService;
import jakarta.transaction.Transactional;
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

    @Transactional
    @PostMapping(value = "/person")
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) throws URISyntaxException {
        return ResponseEntity.created(new URI(ENDPOINT)).body(personService.create(personDTO));
    }

    @Transactional
    @PutMapping(value = "/person/{UUID}")
    public ResponseEntity<Void> updatePerson(@PathVariable UUID UUID, @RequestBody PersonDTO personDTO) {
        personService.update(personDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/person")
    public ResponseEntity<List<PersonDTO>> findAllPersons(@RequestParam(required = false) String search, @RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "50", required = false) int size) {
        if (search != null && !search.isBlank()) {
            return ResponseEntity.ok(personService.findBySearch(search, PageRequest.of(page, size)));
        }
        return ResponseEntity.ok(personService.findAll(PageRequest.of(page, size)));
    }

    @GetMapping(value = "/person/{id}")
    public ResponseEntity<PersonDTO> findPersonById(@PathVariable UUID id) {
        return ResponseEntity.of(personService.findById(id));
    }

    @Transactional
    @DeleteMapping(value = "/person/{uuid}")//uuid großklein wichtig?
    public ResponseEntity<Void> deletePerson(@PathVariable UUID uuid) {
        personService.deletePersonByUUID(uuid);
        return ResponseEntity.ok().build();
    }
}
