package at.bmlv.test.demo.rest.service;

import at.bmlv.test.demo.dto.PersonDTO;
import at.bmlv.test.demo.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonService {
 private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> findAll(){
        return personRepository.findAll().stream().map(
                entity-> new PersonDTO(entity.getUuid(), entity.getFirstName(), entity.getLastName(), entity.getGender())
        ).toList();
    }


    public void deletePersonByUUID(UUID uuid) {
        personRepository.deleteById(uuid);
    }
}
