package at.bmlv.test.demo.rest.controller;

import at.bmlv.test.demo.dto.PersonDTO;
import at.bmlv.test.demo.rest.service.PersonService;
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
    public ResponseEntity<List<PersonDTO>> findAllPersons() {
        return ResponseEntity.ok(personService.findAll());
    }

    @DeleteMapping(value = "/person/{uuid}")//uuid großklein wichtig?
    public ResponseEntity<Void> deletePerson(@PathVariable UUID uuid){
        personService.deletePersonByUUID(uuid);
        return ResponseEntity.ok().build();
    }
}
