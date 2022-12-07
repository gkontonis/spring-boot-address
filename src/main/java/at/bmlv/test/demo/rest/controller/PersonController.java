package at.bmlv.test.demo.rest.controller;

import at.bmlv.test.demo.dto.PersonDTO;
import at.bmlv.test.demo.rest.service.PersonService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/person")
    public ResponseEntity<List<PersonDTO>> findAllPersons(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "50", required = false) int size
    ) {
        if (search != null && !search.isBlank()) {
            return ResponseEntity.ok(personService.findBySearch(search, PageRequest.of(page, size)));
        }
        return ResponseEntity.ok(personService.findAll(PageRequest.of(page, size)));
    }

    @DeleteMapping(value = "/person/{uuid}")//uuid großklein wichtig?
    public ResponseEntity<Void> deletePerson(@PathVariable UUID uuid) {
        personService.deletePersonByUUID(uuid);
        return ResponseEntity.ok().build();
    }
}
